package com.example.SONZABA;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

public class MyPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private int[] layouts;
    private Context context;
    private SharedPreferences langSp;
    private Locale myLocale;

    public MyPagerAdapter(int[] layouts, Context context) {
        this.layouts = layouts;
        this.context = context;
        // Change Language
        langSp = context.getSharedPreferences("CommonPrefs", 0);
        String lang = langSp.getString("Language", Locale.getDefault().getLanguage());
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(myLocale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layouts[position], container, false);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View v = (View) object;
        container.removeView(v);
    }
}
