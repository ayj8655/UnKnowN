package com.example.SONZABA;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class LobbyActivity extends AppCompatActivity {

    public static String TAG = "SUC";
    public static String IP_ADDRESS = "211.229.241.115";
    private static String ADMIN_PASS = "q1w2e3r4";

    public static final String TAG_JSON = "unknown";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_AGE = "age";
    public static final String TAG_PHONE = "phone";

    private long btnPressTime = 0;
    public String[] inProcessDevice;
    private Button btnAdmin, lang_change_btn, logout_btn;
    private ImageView enterProfile, showStatus, viewMap;
    private TextView textNotice;
    private ArrayList<HashMap<String, String>> mArrayList;
    private String mJsonString;
    private Locale myLocale;

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private  ImageView[] dots;
    private static Intent serviceIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setLogo(R.drawable.title1);
        myToolbar.setTitle("UnKnowN");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        View backgroundimage = findViewById(R.id.background_lobby);
        Drawable background = backgroundimage.getBackground();
        background.setAlpha(100);

        lang_change_btn = findViewById(R.id.lang_change_btn);
        logout_btn = findViewById(R.id.logout_btn);

        //viewpager step1
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //viewpager step2 ( auto viewpager )
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        //viewpager step3 ( Dots Indicator )
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams. WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            sliderDotspanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                for(int a = 0; a < dotscount; a++){
                    dots[a].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
                }
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });

        // GET INFO OF DEVICE
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DeviceData");

        // GET LATELY NOTICE (EVERY 1min)
        textNotice = findViewById(R.id.textView_notice_content);
        UpdateNotice();
        textNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNotice(); // DIALOG POPUP
            }
        });

        inProcessDevice = new String[2];
        inProcessDevice[0]=Integer.toString(bundle.getInt("id"));
        inProcessDevice[1]=bundle.getString("tid");

        // GO TO PROFILE ACTIVITY THAT CAN ENTER USER PROFILE TO USE DEVICE WELL
        enterProfile = findViewById(R.id.EnterProfile_Image);
        enterProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundleId = new Bundle();
                bundleId.putString("id",inProcessDevice[0]);
                Intent intent = new Intent(LobbyActivity.this, ProfileActivity.class);
                intent.putExtra("DeviceData",bundleId);
                startActivity(intent);
            }
        });

        // GO TO SHOW ACTIVITY THAT LISTED ABOUT USER PROFILE
        showStatus = findViewById(R.id.ShowProfile_Image);
        showStatus.setOnClickListener(new View.OnClickListener() {
            // GET DATA IN DB
            public void onClick(View v) {
                /*
                mArrayList.clear();
                GetData task = new GetData();
                task.execute(inProcessDevice[0]);
                */
                Intent intent = new Intent(LobbyActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

        // ADMIN MODE (Double Click the Specific Location in 1sec)
        btnAdmin = findViewById(R.id.buttonAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() > btnPressTime + 1000) {
                    btnPressTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() <= btnPressTime + 1000) {
                    AdminMode_Pass(v);
                }
            }
        });
        mArrayList = new ArrayList<>();

        // View Map onClick
        viewMap = findViewById(R.id.ViewMap_Image);
        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LobbyActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.lang_change_btn:
                change_lang();
                break;
            // action with ID action_settings was selected
            case R.id.logout_btn:
                logout_user();
                break;
            case R.id.about:
                Intent intent = new Intent(LobbyActivity.this, AboutActivity.class);
                startActivity(intent);
            default:
                break;
        }

        return true;
    }

    //viewpager step2//////////////////////////////////////////////////////////////
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            LobbyActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(4);
                    } else if (viewPager.getCurrentItem() == 4) {
                        viewPager.setCurrentItem(5);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    // Administrator Button onClick
    public void AdminMode_Pass(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText admin_pass = new EditText(LobbyActivity.this);
        admin_pass.setFocusable(true);
        admin_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        admin_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        builder.setView(admin_pass);
        // YES BUTTON (CHECK PASSWORD)
        builder.setPositiveButton(getString(R.string.button_main_insert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = admin_pass.getText().toString();
                if (value.equals(ADMIN_PASS)) {
                    dialog.dismiss();
                    Bundle bundleId = new Bundle();
                    bundleId.putString("id",inProcessDevice[0]);
                    Intent intent = new Intent(LobbyActivity.this, AdminActivity.class);
                    intent.putExtra("DeviceData",bundleId);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),getString(R.string.invalid_access),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        // NO BUTTON
        builder.setNegativeButton(getString(R.string.button_exit), null);
        builder.show();
    }
    // METHOD TO UPDATE NOTICE TEXTVIEW
    public void UpdateNotice()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new GetNotice().execute();
                            }
                        });
                        Thread.sleep(60000); // EXECUTE METHOD EVERY MINUTE
                    }
                    catch (InterruptedException e) { Log.d(TAG, "UpdateNotice : ", e); }
            }
        }).start();
    }

    // WHEN CLICK THE NOTICE TEXTVIEW
    public void clickNotice()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.textView_notice);
        String[] notice_content = textNotice.getText().toString().split("  :  ");
        String content = notice_content[0];
        String date = notice_content[1];
        builder.setMessage(""+content+"\n"+date);
        builder.setNegativeButton(getString(R.string.button_exit), null);
        builder.show();
    }

    public void change_lang() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add(getString(R.string.en));
        ListItems.add(getString(R.string.ko));
        ListItems.add(getString(R.string.ja));
        ListItems.add(getString(R.string.zh));
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.lang_title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                if (selectedText == getString(R.string.en)) {
                    String lang="en";
                    changeLang(lang);
                }
                else if (selectedText == getString(R.string.ko)) {
                    String lang = "ko";
                    changeLang(lang);
                }
                else if (selectedText == getString(R.string.ja)) {
                    String lang = "ja";
                    changeLang(lang);
                }
                else if (selectedText == getString(R.string.zh)) {
                    String lang = "zh";
                    changeLang(lang);
                }
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.show();
    }

    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(myLocale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
    public void setServiceIntent() {
        serviceIntent = new Intent(this, MyIntentService.class);
    }

    public void logout_user() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.textView_logout_title);
        builder.setMessage(R.string.textView_logout);
        builder.setPositiveButton(getString(R.string.button_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = getSharedPreferences("NFC",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("isUsing"); editor.remove("id"); editor.remove("tid");
                editor.commit();
                Toast.makeText(LobbyActivity.this,R.string.textView_logout_confirm, Toast.LENGTH_SHORT).show();
                MyIntentService.scanBLE = false;
                setServiceIntent();
                startService(serviceIntent);
                finish();
            }
        });
        builder.setNegativeButton(getString(R.string.button_no), null);
        builder.show();
    }

    // GET DATA ABOUT PROFILE THAT USER ENTERED
    private class GetData extends AsyncTask<String, Void, String> {

        Intent intent = new Intent(LobbyActivity.this, ShowActivity.class);

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(LobbyActivity.this,
                    "Searching Data...", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(TAG, "response - " + result);

            if (result == null){
                Log.d(TAG, errorString);
            }
            else {
                mJsonString = result;
                //showResult();
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
                    for(int i=0;i<jsonArray.length();i++) {

                        JSONObject item = jsonArray.getJSONObject(i);

                        // Separate json by variables
                        String id = item.getString(TAG_ID);
                        String name = item.getString(TAG_NAME);
                        String age = item.getString(TAG_AGE);
                        String phone = item.getString(TAG_PHONE);

                        // Bind bundle and put in intent
                        Bundle bundle = new Bundle();
                        bundle.putString("serial",inProcessDevice[1]);
                        bundle.putString("id",id);
                        bundle.putString("name",name);
                        bundle.putString("age",age);
                        bundle.putString("phone",phone);

                        intent.putExtra("Data",bundle);
                        startActivity(intent); // SHOW ACTIVITY
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "showResult : ", e);
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = "http://" + IP_ADDRESS + "/query.php";
            // DEVICE NUMBER
            String postParameters = "id="+params[0];

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }

        }
    }

    private class GetNotice extends AsyncTask<String, Void, String> {
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d(TAG, "response - " + result);
            if (result == null){
                Log.d(TAG, errorString);
            }
            else {
                mJsonString = result;
                //showResult();
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("notice");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String content = item.getString("content");
                        String date = item.getString("date");

                        textNotice.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                        textNotice.setText(content+"  :  "+date);
                        textNotice.setSelected(true);
                        textNotice.setSingleLine(true);
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "showResult : ", e);
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = "http://" + IP_ADDRESS + "/get_notice.php";
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) { sb.append(line); }
                bufferedReader.close();
                return sb.toString().trim();
            }
            catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }
        }
    }
    public void ExitApp(View view) { finish(); }
}
