package com.example.UnKnowN;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;
import java.util.UUID;


public class MyIntentService extends IntentService {
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    private boolean flag;
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
    public static Intent serviceIntent = null;

    final Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LJH", "onCreate 들어온다. onStartCommand 전에 일회성 서비스 검사.");
        Bitmap mLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.unknown_logo);

        Intent snoozeIntent = new Intent();
        snoozeIntent.setData(Uri.parse("tel:01026341063"));
        snoozeIntent.setAction(Intent.ACTION_DIAL);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, snoozeIntent,
                PendingIntent.FLAG_ONE_SHOT);

        createNotificationChannel();
        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("아이가 탐색되지 않습니다.")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarm)
                .setLargeIcon(mLargeIcon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("관리자에게 전화하시겠습니까?"))
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.common_google_signin_btn_icon_dark, getString(R.string.button_yes),pendingIntent)
                .addAction(R.drawable.common_google_signin_btn_icon_dark, getString(R.string.button_no), null);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
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
        if (!bluetoothAdapter.isEnabled())
            return;

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
                    distance = ShowActivity.calculateAccuracy(-59, rssi);
                    Log.d("LJH", "거리는 " + (Math.round(distance * 10) / 10.0) + "입니다.");
                    Log.d("LJH", "-------------------------------------------------");
                    if ((Math.round(distance * 10) / 10.0) > 3.0 && flag==false) {

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                        notificationManager.notify(notificationId, builder.build());

                        AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
                        mediaPlayer.start();
                        vibrator.vibrate(1000);


                        flag=true;
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                flag=false;
                            }
                        }, 60000);//60000 = 1분
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