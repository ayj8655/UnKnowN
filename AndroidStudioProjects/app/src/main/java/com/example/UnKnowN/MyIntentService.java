package com.example.UnKnowN;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;


public class MyIntentService extends IntentService {
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private int rssi;
    private String CHANEL_ID = "UnKnown";
    private Integer notificationId = 123;
    private double distance = 0 ;
    private NotificationCompat.Builder builder;
    private Boolean scanBLE = true;
    public MyIntentService() {
        super("MyIntentService");
    }

    final Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LJH", "onCreate 들어온다. onStartCommand 전에 일회성 서비스 검사.");
        createNotificationChannel();
        builder = new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Hello")
                .setContentText("HIHIHIHIHIHI")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarm);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LJH", "onStartCommand 들어온다.");
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        return super.onStartCommand(intent, flags, startId);
    }


    @SuppressLint("MissingPermission")
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("LJH", "onHandleIntent로 들어옴");
        BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();

        scanner.startScan(new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                if(result.getDevice().getName()!= null && result.getDevice().getName().equals("UnKnown")){
                    Log.d("LJH", "1Size: " + result.getDevice().getName());
                    Log.d("LJH", "1Size: " + result.getScanRecord().getServiceUuids().size());
                    Log.d("LJH", "1Size: " + UUID.nameUUIDFromBytes(result.getScanRecord().getBytes()).toString());
                    Log.d("LJH", "1Length: " + result.getScanRecord().getBytes().length);
                    Log.d("LJH", "1RSSI:" + result.getRssi());
                    rssi = result.getRssi();
                    distance = SearchBle.calculateAccuracy(-59, rssi);
                    Log.d("LJH", "거리는 " + (Math.round(distance * 10) / 10.0) + "입니다.");
                    Log.d("LJH", "-------------------------------------------------");
                    if ((Math.round(distance * 10) / 10.0) > 20.0) {
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                        notificationManager.notify(notificationId, builder.build());
                    }
                }
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d("LJH", "onBatchScanResults에 들어온다");
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d("LJH", "onScanFailed에 들어온다");
            }
        });
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANEL_ID , name , importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}