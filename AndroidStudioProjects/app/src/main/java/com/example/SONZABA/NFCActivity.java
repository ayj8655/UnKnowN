package com.example.SONZABA;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NFCActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private int id=0;
    private String tid;
    private String mJsonString;
    private SharedPreferences sp;
    ImageView nfc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        sp = getSharedPreferences("NFC",MODE_PRIVATE);

        //animation//////////////////////////////////////////////////
        nfc = (ImageView) findViewById(R.id.imageView2);
        animation_nfc();
        //animation//////////////////////////////////////////////////
        // NEW USER -> NEED TAG
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter!=null)
        {
            if (!nfcAdapter.isEnabled())
            {
                show();
            }
            Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        }
    }

    private void animation_nfc() {
        nfc.setImageResource(R.drawable.movingtagnfc);
        AnimationDrawable pleasetagnfc = (AnimationDrawable)nfc.getDrawable();
        pleasetagnfc.start();
    }

    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.nfc_title));
        builder.setMessage(getString(R.string.nfc_message));
        // builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.move_to_setting), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            GetData task = new GetData();
            // NFC 고유번호를 이용하여 사용하게 될 디바이스의 DB id 배정
            byte[] tagId = tag.getId();
            tid = toHexString(tagId);
            task.execute(tid);
        }
    }
    public static final String CHARS = "0123456789ABCDEF";

    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F))
                    .append(CHARS.charAt(data[i] & 0x0F));
        }
        return sb.toString();
    }

    public void Tagging(View view) {
        Intent i = new Intent(this, LobbyActivity.class);
        id = 1;
        Bundle bun = new Bundle();
        bun.putInt("id",id);
        bun.putString("tid","A6D7A0D3");
        i.putExtra("DeviceData",bun);
        startActivity(i);
    }

    private class GetData extends AsyncTask<String, Void, String> {
        Intent intent = new Intent(NFCActivity.this, ProfileActivity.class);
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(NFCActivity.this,
                    "Searching Data...", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if (result != null){
                mJsonString = result;
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("unknown");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        id = Integer.parseInt(item.getString("id"));
                        if (id != 0) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean("isUsing",true);
                            editor.putInt("id",id);
                            editor.putString("tid",tid);
                            editor.commit();
                            Bundle bun = new Bundle();
                            bun.putInt("id", id);
                            bun.putString("tid", tid);
                            intent.putExtra("DeviceData", bun);
                            Toast.makeText(NFCActivity.this, String.format("id: %d "+getResources().getString(R.string.tag_confirm), id), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            break;
                        }
                    }
                }
                catch (JSONException e) { }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = "http://" + LobbyActivity.IP_ADDRESS + "/matching_serial.php";
            // 디바이스 고유번호
            String postParameters = "serial="+params[0];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                InputStream inputStream;

                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) { sb.append(line); }
                bufferedReader.close();
                return sb.toString().trim();
            }
            catch (Exception e) {
                errorString = e.toString();
                return null;
            }
        }
    }
}