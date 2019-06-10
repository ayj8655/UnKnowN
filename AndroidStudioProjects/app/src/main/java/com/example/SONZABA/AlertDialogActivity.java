package com.example.SONZABA;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import android.view.View.OnClickListener;

import android.view.Window;

import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;


public class AlertDialogActivity extends Activity {
    MediaPlayer mediaPlayer;
    private String notiTitle, notiMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, AudioManager.FLAG_PLAY_SOUND);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
        mediaPlayer.start();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle bun = getIntent().getExtras();
        notiTitle = bun.getString("notiTitle");
        notiMessage = bun.getString("notiMessage");
        setContentView(R.layout.activity_alert_dialog);
        TextView adTitle = (TextView)findViewById(R.id.Dialog_TITLE);
        TextView adMessage = (TextView)findViewById(R.id.Dialog_message);
        adTitle.setText(notiTitle);
        adMessage.setText(notiMessage);
        Button goToLobby = (Button)findViewById(R.id.gotolobby);
        Button Call = (Button)findViewById(R.id.call);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON// Screen 을 켜진 상태로 유지
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD// Keyguard를 해지
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON// Screen On
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);// Lock 화면 위로 실행
        goToLobby.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                finish();
            }
        });
        Call.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                String tel = "tel:01023459527";
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                finish();
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (MyIntentService.wakeLock.isHeld())
            MyIntentService.wakeLock.release();
    }
}