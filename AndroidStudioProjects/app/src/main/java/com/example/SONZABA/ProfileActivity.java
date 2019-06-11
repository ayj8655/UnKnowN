package com.example.SONZABA;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class ProfileActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "211.229.241.115"; // 의철's server
    private static String TAG = "unknown";
    private SharedPreferences sp;

    public String id;
    public String name;
    public String age;
    public String phone;
    private InputMethodManager imm;
    private EditText mEditTextName;
    private EditText mEditTextAge;
    private EditText mEditTextPhone;
    private TextView mTextViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sp = getSharedPreferences("NFC",MODE_PRIVATE);

        View backgroundimage = findViewById(R.id.background_profile);
        Drawable background = backgroundimage.getBackground();
        background.setAlpha(100);

        mTextViewId = findViewById(R.id.textViewId);
        mEditTextName = findViewById(R.id.editText_main_name);
        mEditTextAge = findViewById(R.id.editText_main_age);
        mEditTextPhone = findViewById(R.id.editText_main_phone);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mTextViewId.setText(Integer.toString(sp.getInt("id",0)));
        Button buttonInsert = findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_info();
            }
        });
        Button btnExit = findViewById(R.id.button3);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEditTextPhone.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    insert_info();
                    finish();
                    return true;
                }
                else return false;
            }
        });
    }

    private void insert_info() {
        hideKeyboard();
        String name = mEditTextName.getText().toString();
        String age = mEditTextAge.getText().toString();
        String phone = mEditTextPhone.getText().toString();
        String id = mTextViewId.getText().toString();

        InsertData task = new InsertData();
        task.execute("http://" + IP_ADDRESS + "/insert.php",id,name,age,phone);
        mEditTextName.setText("");
        mEditTextAge.setText("");
        mEditTextPhone.setText("");
        finish();
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ProfileActivity.this,
                    "Please Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Intent intent = new Intent(ProfileActivity.this, ShowActivity.class);
            startActivity(intent);
            //mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }
        @Override
        protected String doInBackground(String... params) {
            id = params[1];
            name = params[2];
            age = params[3];
            phone = params[4];
            String serverURL = params[0];
            String postParameters = "id=" + id + "&name=" + name + "&age=" + age + "&phone=" + phone;
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) { sb.append(line); }
                bufferedReader.close();
                return sb.toString();
            }
            catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                return new String("Error: " + e.getMessage());
            }
        }
    }
    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(mEditTextName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEditTextAge.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEditTextPhone.getWindowToken(), 0);
    }
}