package com.example.SONZABA;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;


public class SplashActivity extends Activity {

    private SharedPreferences sp;
    private boolean isUsing;
    private boolean isProfile;
    private Locale myLocale;

    //permission
    final int PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load Current Language in App
        try {
            setLanguage();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Is User Using?
        sp = getSharedPreferences("NFC",MODE_PRIVATE);
        isUsing = sp.getBoolean("isUsing",false);
        isProfile = sp.getBoolean("isProfile",false);

        // Checking Permission
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionCheck2= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck3= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck4= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED &&
                permissionCheck2 == PackageManager.PERMISSION_GRANTED &&
                permissionCheck3 == PackageManager.PERMISSION_GRANTED &&
                permissionCheck4 == PackageManager.PERMISSION_GRANTED)
        {
            // All Permission Accepted

            if (isUsing==false) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }

        }
        else {
            // Start Granting Permission From User
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION);
        }

        if (isUsing==true) {
            int usingId = sp.getInt("id",0);
            String usingTid = sp.getString("tid", "");
            if (usingId == 0 && usingTid == "") {
                finish();
                return;
            }
            if (isProfile==false) {
                Intent intent = new Intent(SplashActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(SplashActivity.this, LobbyActivity.class);
                Bundle bun = new Bundle();
                bun.putInt("id", usingId);
                bun.putString("tid", usingTid);
                intent.putExtra("DeviceData", bun);
                startActivity(intent);
                finish();
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSION:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                        grantResults[1]==PackageManager.PERMISSION_GRANTED &&
                        grantResults[2]==PackageManager.PERMISSION_GRANTED &&
                        grantResults[3]==PackageManager.PERMISSION_GRANTED)
                {
                    if (isUsing==false) {
                        Toast.makeText(getApplicationContext(), R.string.All_Permissions_Approved, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, WelcomeActivity.class));
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.Permissions_Denied, Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    private void setLanguage()
    {
        SharedPreferences langSp = getSharedPreferences("CommonPrefs",MODE_PRIVATE);
        String lang = langSp.getString("Language", Locale.getDefault().getLanguage());
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(myLocale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
