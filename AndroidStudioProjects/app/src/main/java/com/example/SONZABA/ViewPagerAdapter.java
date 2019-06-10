package com.example.SONZABA;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {
            R.drawable.slide_0,
            R.drawable.slide_1,
            R.drawable.slide_2,
            R.drawable.slide_3,
            R.drawable.slide_4,
            R.drawable.slide_5
    };
    Uri uri = null;
    Intent intent = null;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        ////////////// ↓Clikable Image Slider/////////////////////////////////////////////////
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0) {
                    Uri uri = Uri.parse("http://www.everland.com/pick/festival_2019/rose.html");
                    Intent readMore = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(readMore);
                } else if (position == 1) {
                    Uri uri = Uri.parse("http://www.everland.com/mobile/everland/discount/1219237_14049.html");
                    Intent readMore = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(readMore);
                } else if (position == 2) {
                    Uri uri = Uri.parse("http://www.everland.com/web/everland/now/card/1219307_14935.html");
                    Intent readMore = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(readMore);
                } else if (position == 3) {
                    Uri uri = Uri.parse("http://www.everland.com/web/everland/now/month/1219298_14942.html");
                    Intent readMore = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(readMore);
                } else if (position == 4) {
                    Uri uri = Uri.parse("http://www.everland.com/web/everland/now/news/1219233_10436.html");
                    Intent readMore = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(readMore);
                } else  {
                    Uri uri = Uri.parse("http://www.everland.com/web/everland/now/news/1219091_10436.html");
                    Intent readMore = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(readMore);
                }
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}