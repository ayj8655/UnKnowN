package com.example.UnKnowN;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    Button btnExit2;
    TextView nameView;
    TextView ageView;
    TextView phoneView;
    TextView idView;
    TextView serialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Lobby 에서 json 파싱한 결과를 받아서 TextView 를 setText 함.
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Data");
        nameView = (TextView) findViewById(R.id.textView_Show_Name);
        ageView = (TextView) findViewById(R.id.textView_Show_Age);
        phoneView = (TextView) findViewById(R.id.textView_Show_Phone);
        idView = (TextView) findViewById(R.id.textView_Show_ID);
        serialView = (TextView) findViewById(R.id.textView_serial);

        nameView.setText(bundle.getString("name"));
        ageView.setText(bundle.getString("age"));
        phoneView.setText(bundle.getString("phone"));
        idView.setText(bundle.getString("id"));
        serialView.setText(bundle.getString("serial"));

        // 나가기 버튼
        btnExit2 = (Button) findViewById(R.id.button9);
        btnExit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
