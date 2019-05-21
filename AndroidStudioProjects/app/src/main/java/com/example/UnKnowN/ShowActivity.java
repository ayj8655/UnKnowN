package com.example.UnKnowN;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    private Button btnExit;
    private TextView nameView, ageView, phoneView, idView, serialView;

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
    }
}
