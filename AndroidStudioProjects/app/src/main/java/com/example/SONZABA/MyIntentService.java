package com.example.SONZABA;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.List;
import java.util.UUID;


public class MyIntentService extends IntentService {
    MediaPlayer mediaPlayer;
    private boolean flag;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private int rssi;
    private String CHANEL_ID = "UnKnowN";
    private Integer notificationId = 123;
    private double distance = 0 ;
    private NotificationCompat.Builder builder;
    public static volatile Boolean scanBLE = true;
    public static PowerManager.WakeLock wakeLock;

    public void settingScanCallback1() {
        call1 = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, final ScanResult result) {
                super.onScanResult(callbackType, result);

                if(result.getScanRecord().getDeviceName()!= null && result.getScanRecord().getDeviceName().equals("UnKnowN")){
                    Log.d("LJH","1Size: " +  result.getDevice().getName());
                    Log.d("LJH","1Size: " + result.getScanRecord().getServiceUuids().size());
                    Log.d("LJH","1Size: " + UUID.nameUUIDFromBytes(result.getScanRecord().getBytes()).toString());
                    Log.d("LJH", "1Length: " + result.getScanRecord().getBytes().length);
                    Log.d("LJH", "1RSSI:" + result.getRssi());
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
    public MyIntentService() {
        super("MyIntentService");
    }
    public static Intent serviceIntent = null;
    private static ScanCallback call1 = null;

    final Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LJH", "onCreate 들어온다. onStartCommand 전에 일회성 서비스 검사.");
        PowerManager pm =(PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"DD:DDDD");//슬립상태에 빠진 핸드폰의 CPU만 키게합니다
        wakeLock.acquire();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
        Bitmap mLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.unknown_logo);

        Intent leftIntent = new Intent();
        leftIntent.setData(Uri.parse("tel:01026341063"));
        leftIntent.setAction(Intent.ACTION_DIAL);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, leftIntent, 0);
        createNotificationChannel();

        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(getString(R.string.lost_child))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarm)
                .setLargeIcon(mLargeIcon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.call_manager)))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
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
        if(scanBLE == true){
            settingScanCallback1();
            scanner.startScan(call1);
            Log.d("LJH", "SERVICE START");
        }else{
            scanner.stopScan(call1);
            Log.d("LJH", "SERVICE STOP");
        }
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