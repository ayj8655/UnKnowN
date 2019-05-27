package com.example.UnKnowN;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.List;
import java.util.UUID;

public class ShowActivity extends AppCompatActivity {

    private Button btnExit;
    private TextView nameView, ageView, phoneView, idView, serialView;
    private static final int REQUEST_ENABLE_BT = 10000;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 100;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner scanner;
    private TextView deviceName, Rssi, address;
    private double distance = 0;
    private double rssi = 0;
    public static Intent serviceIntent = null;
    private static ScanCallback call = null;

    public void setServiceIntent(){
        serviceIntent = new Intent(this, MyIntentService.class);
    }
    public void settingScanCallback() {
        call = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                super.onScanResult(callbackType, result);
                if(result.getDevice().getName() !=null && result.getDevice().getName().equals("UnKnown")) {
                    Log.d("LJH","Size: " +  result.getDevice().getName());
                    Log.d("LJH","Size: " + result.getScanRecord().getServiceUuids().size());
                    Log.d("LJH","Size: " + UUID.nameUUIDFromBytes(result.getScanRecord().getBytes()).toString());
                    Log.d("LJH", "Length: " + result.getScanRecord().getBytes().length);
                    Log.d("LJH", "RSSI:" + result.getRssi());
                    rssi = result.getRssi();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            distance = calculateAccuracy(-59,rssi);
                            Rssi.setText("Distance :" + " "+(Math.round(distance*10)/10.0)+" " +"(M)");
                            deviceName.setText("DeviceName : " +" "+ result.getDevice().getName());
                            address.setText("Address : " + " " + result.getDevice().getAddress());
                        }
                    });
                    Log.d("LJH","-------------------------------------------------");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Data");
        nameView = findViewById(R.id.textView_Show_Name);
        ageView = findViewById(R.id.textView_Show_Age);
        phoneView = findViewById(R.id.textView_Show_Phone);
        idView = findViewById(R.id.textView_Show_ID);
        serialView = findViewById(R.id.textView_serial);

        nameView.setText(bundle.getString("name"));
        ageView.setText(bundle.getString("age"));
        phoneView.setText(bundle.getString("phone"));
        idView.setText(bundle.getString("id"));
        serialView.setText(bundle.getString("serial"));

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

        if(bluetoothAdapter == null){
            Toast.makeText(this, "블루투스 기능을 지원하지 않습니다.",Toast.LENGTH_LONG).show();
        }else{
            if(bluetoothAdapter.isEnabled()){
                Toast.makeText(this, "블루투스 기능이 이미 실행중입니다.",Toast.LENGTH_LONG).show();
                // BLE 기기 찾기.
                if(call == null) {
                    Log.d("LJH", "Call Null!");
                    settingScanCallback();
                    scanner.startScan(call);
                }
            }else{
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
        if(resultCode == RESULT_OK){
            Log.d("LJH", "블루투스 설정 확인신호 누름.");
            if(bluetoothAdapter.isEnabled()){
                // BLE 기기 찾기.
                scanner = bluetoothAdapter.getBluetoothLeScanner();
                Log.d("LJH", "블루투스 활성화 상태");
                if(call == null) {
                    Log.d("LJH", "Call Null!");
                    settingScanCallback();
                    scanner.startScan(call);
                }
            }
        }
    }

    public static double calculateAccuracy(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }

        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio,10);
        }
        else {
            double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0;
            return accuracy;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LJH", "onStop 된다");
        if(serviceIntent == null){
            Log.d("LJH", "서비스 NULL 서비스 시작한다.");
            setServiceIntent();
            startService(serviceIntent);
        }else{
            Log.d("LJH", "이미 서비스 들어있어 ㅄ아, 실행 ㄴㄴ");
        }
        if(!bluetoothAdapter.isEnabled()){
            call = null;
        }else{
            scanner.stopScan(call);
            call = null;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LJH", "ReStart...");
        if(bluetoothAdapter == null){
            Toast.makeText(this, "블루투스 기능을 지원하지 않습니다.",Toast.LENGTH_LONG).show();
        }else{
            if(bluetoothAdapter.isEnabled()){
                Toast.makeText(this, "블루투스 기능이 이미 실행중입니다.",Toast.LENGTH_LONG).show();
                // BLE 기기 찾기.
                if(call == null) {
                    Log.d("LJH", "Call Null!");
                    settingScanCallback();
                    scanner.startScan(call);
                }
            }else{
                Log.d("LJH", "블루투스 연결설정중");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }
}
