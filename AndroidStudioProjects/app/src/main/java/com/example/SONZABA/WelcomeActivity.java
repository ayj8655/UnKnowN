package com.example.SONZABA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.SONZABA.MyPagerAdapter;
import com.example.SONZABA.R;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[]dotstv;
    private int[]layouts;
    private Button btnSkip;
    private Button btnNext;
    private MyPagerAdapter pagerAdapter;
    private SharedPreferences ref, sp;
    private Boolean FirstTime, isUsing;
    private Locale myLocale;
    private boolean isProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        FirstTime = ref.getBoolean("FirstTimeStartFlag", true);
        if(!isFirstTimeStartApp()) {
                startMainActivity();
                finish();
        }
        sp = getSharedPreferences("NFC",MODE_PRIVATE);
        isUsing = sp.getBoolean("isUsing",false);
        isProfile = sp.getBoolean("isProfile",false);

        setStatusBarTransparent();

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        layoutDot = findViewById(R.id.dotLayout);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUsing == false)
                    startMainActivity();
                else if (isProfile == false)
                    startActivity(new Intent(WelcomeActivity.this, ProfileActivity.class));
                else
                    startActivity(new Intent(WelcomeActivity.this, LobbyActivity.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length) {
                    viewPager.setCurrentItem(currentPage);
                } else {
                    if (isUsing == false)
                        startMainActivity();
                    else if (isProfile == false)
                        startActivity(new Intent(WelcomeActivity.this, ProfileActivity.class));
                    else
                        startActivity(new Intent(WelcomeActivity.this, LobbyActivity.class));
                    finish();
                }
            }
        });
        layouts = new int[]{R.layout.slider_1, R.layout.slider_2, R.layout.slider_3, R.layout.slider_4, R.layout.slider_5, R.layout.slider_6, R.layout.slider_7, R.layout.slider_8, R.layout.slider_9, R.layout.slider_10, R.layout.slider_11, R.layout.slider_13, R.layout.slider_12};
        pagerAdapter = new MyPagerAdapter(layouts,getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position == layouts.length-1) {
                    btnNext.setText("START");
                    btnSkip.setVisibility(View.GONE);
                }else {
                    btnNext.setText("NEXT");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);
    }

    private boolean isFirstTimeStartApp() {
        return  ref.getBoolean("FirstTimeStartFlag", true);
    }

    private void setFirstTimeStartStatus(boolean stt) {
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FirstTimeStartFlag", stt);
        editor.commit();
    }
    private void setDotStatus(int page) {
        layoutDot.removeAllViews();
        dotstv = new  TextView[layouts.length];
        for (int i = 0; i < dotstv.length; i++) {
            dotstv[i] = new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);
        }
        if(dotstv.length>0) {
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }
    private  void startMainActivity() {
        setFirstTimeStartStatus(true);
        startActivity(new Intent(WelcomeActivity.this, NFCActivity.class));
        finish();
    }
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
