package com.example.UnKnowN;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.HashMap;

public class LobbyActivity extends AppCompatActivity {

    private static String TAG = "Show";
    public static String IP_ADDRESS = "211.229.241.115";
    private static String ADMIN_PASS = "q1w2e3r4";

    private static final String TAG_JSON = "unknown";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";
    private static final String TAG_PHONE = "phone";

    String[] inProcessDevice;

    Button btnEnter;

    ArrayList<HashMap<String, String>> mArrayList;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        // GET INFO OF DEVICE
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DeviceData");

        inProcessDevice = new String[2];
        inProcessDevice[0]=Integer.toString(bundle.getInt("id"));
        inProcessDevice[1]=bundle.getString("tid");

        //\\//\\ ENTER THE PROFILE //\\//\\
        btnEnter = (Button) findViewById(R.id.button);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundleId = new Bundle();
                bundleId.putString("id",inProcessDevice[0]);
                Intent intent = new Intent(LobbyActivity.this, ProfileActivity.class);
                intent.putExtra("DeviceData",bundleId);
                startActivity(intent);
            }
        });

        //\\//\\ SHOW THE PROFILE //\\//\\
        Button button_search = (Button) findViewById(R.id.button2);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mArrayList.clear();
                GetData task = new GetData();
                // 디바이스 번호
                task.execute(inProcessDevice[0]);

                /*
                Intent intent = new Intent(LobbyActivity.this, ShowActivity.class);
                startActivity(intent);
                */
            }
        });

        mArrayList = new ArrayList<>();
    }

    public void AdminMode_Pass(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText admin_pass = new EditText(LobbyActivity.this);
        admin_pass.setFocusable(true);
        admin_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        admin_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        builder.setView(admin_pass);
        // 확인 버튼 설정
        builder.setPositiveButton(getString(R.string.button_main_insert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = admin_pass.getText().toString();
                if (value.equals(ADMIN_PASS)) {
                    dialog.dismiss();
                    Bundle bundleId = new Bundle();
                    bundleId.putString("id",inProcessDevice[0]);
                    Intent intent = new Intent(LobbyActivity.this, AdminActivity.class);
                    intent.putExtra("DeviceData",bundleId);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),getString(R.string.invalid_access),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton(getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     // close
            }
        });
        builder.show();
    }

    private class GetData extends AsyncTask<String, Void, String> {

        Intent intent = new Intent(LobbyActivity.this, ShowActivity.class);

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(LobbyActivity.this,
                    "Searching Data...", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(TAG, "response - " + result);

            if (result == null){
                Log.d(TAG, errorString);
            }
            else {
                mJsonString = result;
                //showResult();
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
                    // 추후 수정 필요 (for 문 없애기)
                    for(int i=0;i<jsonArray.length();i++) {

                        JSONObject item = jsonArray.getJSONObject(i);

                        // 파싱한 json 을 변수별로 정리하는 작업
                        String id = item.getString(TAG_ID);
                        String name = item.getString(TAG_NAME);
                        String age = item.getString(TAG_AGE);
                        String phone = item.getString(TAG_PHONE);

                        // 번들로 묶어서 Show로 넘김
                        Bundle bundle = new Bundle();
                        bundle.putString("serial",inProcessDevice[1]);
                        bundle.putString("id",id);
                        bundle.putString("name",name);
                        bundle.putString("age",age);
                        bundle.putString("phone",phone);

                        intent.putExtra("Data",bundle);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "showResult : ", e);
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {

            //String searchKeyword = "params[0]";

            String serverURL = "http://" + IP_ADDRESS + "/query.php";
            // 디바이스 번호
            String postParameters = "id="+inProcessDevice[0];

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
                Log.d(TAG, "response code - " + responseStatusCode);

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

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    public void ExitApp(View view) {
        finish();
    }
}
