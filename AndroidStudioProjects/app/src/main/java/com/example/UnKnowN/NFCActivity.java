package com.example.UnKnowN;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Locale;

public class NFCActivity extends AppCompatActivity {

    private String Device1Serial = "A6D7A0D3";
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public int id;
    public String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String nationality = Locale.getDefault().getLanguage();
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            byte[] tagId = tag.getId();
            tid = toHexString(tagId);
            if(tid.equals(Device1Serial)){
                Intent i = new Intent(this, LobbyActivity.class);
                id = 1;
                Bundle bun = new Bundle();
                bun.putInt("id",id);
                bun.putString("tid",tid);
                i.putExtra("DeviceData",bun);
                if(nationality.compareTo("ko")==0)
                    Toast.makeText(this,String.format("id: %d 태그확인",id),Toast.LENGTH_LONG).show();//DB조회 하기
                else if(nationality.compareTo("en")==0)
                    Toast.makeText(this,String.format("id: %d Tag Checked",id),Toast.LENGTH_LONG).show();//DB조회 하기

                startActivity(i);
            }
        }
    }
    public static final String CHARS = "0123456789ABCDEF";

    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F))
                    .append(CHARS.charAt(data[i] & 0x0F));
        }
        return sb.toString();
    }
}