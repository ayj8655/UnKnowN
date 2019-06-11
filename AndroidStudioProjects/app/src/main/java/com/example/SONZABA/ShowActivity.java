package com.example.SONZABA;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Vibrator;
import android.renderscript.Double3;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

public class ShowActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    private LobbyActivity lb;
    private ProfileActivity pf;
    private String mJsonString;
    private boolean flag;
    private Button btnExit;
    private TextView nameView, ageView, phoneView;
    private static final int REQUEST_ENABLE_BT = 10000;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 100;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner scanner;
    private TextView deviceName, Rssi, address;
    private TextView under_notification; //하단 경고상태
    private TextView status1, status2, status3, status4;
    private SharedPreferences sp;
    private String id;

    private double distance = 0;
    private double rssi = 0;
    public Double[] stack;
    private double rssi_avg=0;
    private static Intent serviceIntent = null;
    private ScanCallback call = null;
    private ImageView emoticon;

    public static int bluetooth_intensity_far = -80;
    private static int bluetooth_intensity_normal = -70;
    private static int bluetooth_intensity_safe = -60;

    public void setServiceIntent() {
        serviceIntent = new Intent(this, MyIntentService.class);
    }

    public void settingScanCallback() {
        Log.d("LJH", "showactivity scancallback before");
        call = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                super.onScanResult(callbackType, result);
                Log.d("LJH", "showactivity scancallback in");
                if (result.getDevice().getName() != null && result.getDevice().getName().equals("UnKnowN")) {
                    Log.d("LJH", "Size: " + result.getDevice().getName());
                    Log.d("LJH", "Size: " + result.getScanRecord().getServiceUuids().size());
                    Log.d("LJH", "Size: " + UUID.nameUUIDFromBytes(result.getScanRecord().getBytes()).toString());
                    Log.d("LJH", "Length: " + result.getScanRecord().getBytes().length);
                    Log.d("LJH", "RSSI:" + result.getRssi());
                    rssi = result.getRssi();
                    rssi_stack(rssi);
                    rssi_avg=(stack[0]+stack[1]+stack[2]+stack[3]+stack[4])/5.0;
                    Log.d("LJH","AVG RSSI : "+Double.toString(rssi_avg));
                    //distance = calculateAccuracy(-59, rssi);
                    distance = (Math.pow(10,(-56-rssi_avg)/(10*2)));
                    Log.d("LJH","distance : "+distance);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Rssi.setText("Distance :" + " " + (Math.round(distance * 10) / 10.0) + " " + "(M)");
                            Rssi.setText("Rssi :" + " " + String.format("%.3f",rssi_avg));
                            deviceName.setText("DeviceName : " + " " + result.getDevice().getName());
                            address.setText("Distance : " + " " + String.format("%.2f",distance));

                            if (rssi_avg >= bluetooth_intensity_safe) { //안전

                                status1.setTextColor(0xFF20E801);
                                status2.setTextColor(0xFF666666);
                                status3.setTextColor(0xFF666666);
                                status4.setTextColor(0xFF666666);

                                Drawable img = (Drawable) getResources().getDrawable(R.drawable.smile, null);
                                emoticon.setImageDrawable(img);
                            } else if (bluetooth_intensity_safe > rssi_avg
                                    && rssi_avg >= bluetooth_intensity_normal) {//주의

                                status2.setTextColor(0xFFFF9900);
                                status1.setTextColor(0xFF666666);
                                status3.setTextColor(0xFF666666);
                                status4.setTextColor(0xFF666666);

                                Drawable img = getResources().getDrawable(R.drawable.smilely, null);
                                emoticon.setImageDrawable(img);
                            } else if (bluetooth_intensity_normal > rssi_avg
                                    && rssi_avg >= bluetooth_intensity_far) { //위험

                                status3.setTextColor(0xFFFF0000);
                                status1.setTextColor(0xFF666666);
                                status2.setTextColor(0xFF666666);
                                status4.setTextColor(0xFF666666);

                                Drawable img = getResources().getDrawable(R.drawable.sad, null);
                                emoticon.setImageDrawable(img);
                            } else if (rssi_avg < bluetooth_intensity_far && flag == false) { //미아발생

                                status4.setTextColor(0xFF800000);
                                status1.setTextColor(0xFF666666);
                                status2.setTextColor(0xFF666666);
                                status3.setTextColor(0xFF666666);

                                Drawable img = getResources().getDrawable(R.drawable.sad_emoji, null);
                                emoticon.setImageDrawable(img);

                                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, AudioManager.FLAG_PLAY_SOUND);
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
                                mediaPlayer.start();
                                vibrator.vibrate(1000);
                                show();

                                flag = true;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        flag = false;
                                    }
                                }, 60000);//60000 = 1분
                            }
                        }
                    });
                    Log.d("LJH", "-------------------------------------------------");
                }
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
            }
        };
    }

    private void rssi_stack(double rssi) {
        if (stack[0].equals(0.0))
            stack[0]=rssi;
        else {
            if (stack[1].equals(0.0))
                stack[1]=rssi;
            else {
                if (stack[2].equals(0.0))
                    stack[2]=rssi;
                else {
                    if (stack[3].equals(0.0))
                        stack[3]=rssi;
                    else {
                        if (stack[4].equals(0.0))
                            stack[4]=rssi;
                        else {
                            stack[4]=stack[3];
                            stack[3]=stack[2];
                            stack[2]=stack[1];
                            stack[1]=stack[0];
                            stack[0]=rssi;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        stack = new Double[5];
        for (int i = 0; i <= 4; i++) {
            stack[i] = 0.0;
        }
        Log.d("LJH","init STACK : "+Double.toString(stack[0])+" "+Double.toString(stack[1])+" "+Double.toString(stack[2]));
        View backgroundimage = findViewById(R.id.background_show);
        Drawable background = backgroundimage.getBackground();
        background.setAlpha(100);

        sp = getSharedPreferences("NFC",MODE_PRIVATE);
        id = Integer.toString(sp.getInt("id",0));
        GetData task = new GetData();
        task.execute(id);

        nameView = findViewById(R.id.textView_Show_Name);
        ageView = findViewById(R.id.textView_Show_Age);
        phoneView = findViewById(R.id.textView_Show_Phone);
        emoticon = findViewById(R.id.Emoticon);
        deviceName = findViewById(R.id.devicename);
        Rssi = findViewById(R.id.rssi);
        address = findViewById(R.id.address);
        status1 = findViewById(R.id.Status1);
        status2 = findViewById(R.id.Status2);
        status3 = findViewById(R.id.Status3);
        status4 = findViewById(R.id.Status4);

        // 나가기 버튼
        btnExit = findViewById(R.id.button9);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        deviceName = findViewById(R.id.devicename);
        Rssi = findViewById(R.id.rssi);
        address = findViewById(R.id.address);
        Log.d("LJH", "onCreate Call");
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        scanner = bluetoothAdapter.getBluetoothLeScanner();
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if (bluetoothAdapter == null) {
            Toast.makeText(this, R.string.not_avaliable_bluetooth, Toast.LENGTH_LONG).show();
            finish();
        } else {
            if (bluetoothAdapter.isEnabled()) {
                // BLE 기기 찾기.
                if (call == null) {
                    Log.d("LJH", "Call Null!");
                    settingScanCallback();
                    scanner.startScan(call);
                }
            } else {
                Log.d("LJH", "블루투스 연결설정중");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d("LJH", "블루투스 설정 확인신호 누름.");
            if (bluetoothAdapter.isEnabled()) {
                // BLE 기기 찾기.
                scanner = bluetoothAdapter.getBluetoothLeScanner();
                Log.d("LJH", "블루투스 활성화 상태");
                if (call == null) {
                    Log.d("LJH", "Call Null!");
                    settingScanCallback();
                    scanner.startScan(call);
                }
            }
        } else {
            Toast.makeText(this, R.string.enable_the_bluetooth, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public static double calculateAccuracy(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }

        double ratio = rssi * 1.0 / txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        } else {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0;
            return accuracy;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LJH", "onStop");
        if (!bluetoothAdapter.isEnabled()) {
            call = null;
        } else {
            scanner.stopScan(call);
            call = null;
        }
        MyIntentService.scanBLE = true;
        setServiceIntent();
        startService(serviceIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // stop service that running in background
        MyIntentService.scanBLE = false;
        setServiceIntent();
        startService(serviceIntent);
        Log.d("LJH", "ScanBle False야, Stop SerVice");
        Log.d("LJH", "ReStart...");
        if (bluetoothAdapter == null) {
            Toast.makeText(this, R.string.not_avaliable_bluetooth, Toast.LENGTH_LONG).show();
        } else {
            if (bluetoothAdapter.isEnabled()) {
                // BLE 기기 찾기.
                if (call == null) {
                    Log.d("LJH", "Call Null!");
                    settingScanCallback();
                    scanner.startScan(call);
                }
            } else {
                Log.d("LJH", "블루투스 연결설정중");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    void show() {
        AlertDialog.Builder alert = new AlertDialog.Builder(ShowActivity.this);
        alert.setTitle(R.string.lost_child);
        alert.setMessage(R.string.call_manager);
        alert.setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mediaPlayer.stop();
                mediaPlayer.release();
                String tel = "tel:01023459527";
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });
        alert.setNegativeButton(R.string.button_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    // GET DATA ABOUT PROFILE THAT USER ENTERED
    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowActivity.this,
                    "Searching Data...", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(lb.TAG, "response - " + result);

            if (result == null){
                Log.d(lb.TAG, errorString);
            }
            else {
                mJsonString = result;
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray(lb.TAG_JSON);
                    for(int i=0;i<jsonArray.length();i++) {

                        JSONObject item = jsonArray.getJSONObject(i);

                        nameView.setText(item.getString(lb.TAG_NAME));
                        ageView.setText(item.getString(lb.TAG_AGE));
                        phoneView.setText(item.getString(lb.TAG_PHONE));
                    }
                } catch (JSONException e) {
                    Log.d(lb.TAG, "showResult : ", e);
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = "http://" + lb.IP_ADDRESS + "/query.php";
            // DEVICE NUMBER
            String postParameters = "id="+params[0];

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(lb.TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(lb.TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LJH", " PAUSE야  빽키");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LJH", " RESUME야 ");
        MyIntentService.scanBLE = false;
        setServiceIntent();
        startService(serviceIntent);
        Log.d("LJH", "ScanBle False야, Stop SerVice");
    }
}

