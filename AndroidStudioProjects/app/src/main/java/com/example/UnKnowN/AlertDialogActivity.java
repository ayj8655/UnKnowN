package com.example.UnKnowN;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Vibrator;
import android.view.View;

import android.view.View.OnClickListener;

import android.view.Window;

import android.widget.Button;

import android.widget.TextView;


public class AlertDialogActivity extends Activity {
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    private String notiMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spacealarm);
        mediaPlayer.start();
        vibrator.vibrate(1000);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle bun = getIntent().getExtras();
        notiMessage = bun.getString("notiMessage");
        setContentView(R.layout.activity_alert_dialog);
        TextView adMessage = (TextView)findViewById(R.id.message);
        adMessage.setText(notiMessage);
        Button goToLobby = (Button)findViewById(R.id.gotolobby);
        Button Call = (Button)findViewById(R.id.call);
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

}