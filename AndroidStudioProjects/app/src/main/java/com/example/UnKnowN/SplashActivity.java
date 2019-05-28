package com.example.UnKnowN;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


public class SplashActivity extends Activity {

    private SharedPreferences sp;
    private boolean isUsing;

    //permission
    final int PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sp = getSharedPreferences("NFC",MODE_PRIVATE);
        isUsing = sp.getBoolean("isUsing",false);

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
                startActivity(new Intent(this, NFCActivity.class));
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
            Intent intent = new Intent(SplashActivity.this, LobbyActivity.class);
            Bundle bun = new Bundle();
            bun.putInt("id", usingId);
            bun.putString("tid", usingTid);
            intent.putExtra("DeviceData", bun);
            startActivity(intent);
            finish();
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
                        startActivity(new Intent(this, NFCActivity.class));
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
}
