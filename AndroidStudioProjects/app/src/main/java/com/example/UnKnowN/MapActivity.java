package com.example.UnKnowN;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    int child,cnt;
    int[] height;
    double[] list;
    String[] name,att_url;
    TextView textView;
    Button button;
    SeekBar sb;
    MarkerOptions markerOptions = new MarkerOptions();
    ArrayList<LatLng> locations = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        sb  = (SeekBar) findViewById(R.id.seekbar);
        textView = (TextView)findViewById(R.id.textView) ;
        button = (Button)findViewById(R.id.button);

        child = 190;
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        cnt=0;
        list = new double[]{37.290163, 127.201815,37.294090, 127.202491,37.291328, 127.205685,37.294204, 127.202594,37.294064, 127.202577,
                37.293244, 127.202790,37.291758, 127.207748,37.294175, 127.202577,37.294985, 127.201348,37.293638, 127.199839,37.294976, 127.201401,37.293697, 127.199828,
                37.294192, 127.203128,37.293689, 127.199839,37.292552, 127.201006,37.293638, 127.199828,37.294175, 127.201006,37.291243, 127.200254,37.292426, 127.201500,
                37.292518, 127.201006,37.293544, 127.199807,37.293723, 127.199764,37.293535, 127.199785,37.293518, 127.199839,37.292890, 127.200042,37.293484, 127.199764,
                37.293450, 127.199968,37.293535, 127.199624,37.292314, 127.201788,37.292433, 127.200995,37.292331, 127.201842,37.293572, 127.200060,37.293894, 127.199957,
                37.291208, 127.200179,37.291834, 127.203908,37.291842, 127.203672,37.291106, 127.200211,37.293575, 127.204048};
        name = new String[]{"T 익스프레스","랩터레인저","아마존 익스프레스","썬더폴스","콜럼버스 대탐험","판다월드","로스트 밸리","사파리 월드",
                "자이로 VR","키즈빌리지","로봇 VR","키즈커버리","헬로 터닝 어드벤처","매직 쿠키 하우스","롤링 엑스 트레인","허리케인","챔피온쉽 로데오","로얄 쥬빌리 캐로셀",
                "시크릿쥬쥬 비행기","자동차 왕국","플래쉬 팡팡","피터팬","나는 코끼리","로보트카","붕붕카","범퍼카","스카이 댄싱","매직 스윙","비룡열차","우주 전투기",
                "스푸키 펀 하우스","레이싱 코스터","볼 하우스","페스티벌 트레인","스페이스 투어","로테이팅 하우스","VR 어드벤처","프라이드 인 코리아"};
        att_url = new String[]{"http://www.everland.com/mobile/everland/favorite/attraction/22.el","http://www.everland.com/mobile/everland/favorite/attraction/3645.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/89.el","http://www.everland.com/mobile/everland/favorite/attraction/2558.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/54.el","http://www.everland.com/mobile/everland/favorite/attraction/2757.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/1680.el","http://www.everland.com/mobile/everland/favorite/attraction/91.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/3043.el","http://www.everland.com/mobile/everland/favorite/attraction/2978.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/2960.el","http://www.everland.com/mobile/everland/favorite/attraction/1120.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/2937.el","http://www.everland.com/mobile/everland/favorite/attraction/1460.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/56.el","http://www.everland.com/mobile/everland/favorite/attraction/43.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/21.el","http://www.everland.com/mobile/everland/favorite/attraction/87.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/2458.el","http://www.everland.com/mobile/everland/favorite/attraction/67.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/76.el","http://www.everland.com/mobile/everland/favorite/attraction/75.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/74.el","http://www.everland.com/mobile/everland/favorite/attraction/66.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/65.el","http://www.everland.com/mobile/everland/favorite/attraction/73.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/72.el","http://www.everland.com/mobile/everland/favorite/attraction/71.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/78.el","http://www.everland.com/mobile/everland/favorite/attraction/80.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/79.el","http://www.everland.com/mobile/everland/favorite/attraction/60.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/59.el","http://www.everland.com/mobile/everland/favorite/attraction/86.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/85.el","http://www.everland.com/mobile/everland/favorite/attraction/83.el",
                "http://www.everland.com/mobile/everland/favorite/attraction/2701.el","http://www.everland.com/mobile/everland/favorite/attraction/2557.el"};
        height = new int[]{129,79,109,109,109,79,109,79,129,79,129,79,79,109,119,129,129,109,99,89,99,99,99,89,89,119,99,99,109,109,79,109,109,119,109,109,119,99};
        System.out.print("list개수:" + list.length);
        System.out.print("name개수:" + name.length);
        System.out.print("url 개수:" + att_url.length);
        System.out.print("height 개수:" + height.length);

        for(int i =0; i < list.length; i+=2) {
            locations.add(new LatLng(list[i],list[i+1]));
        }
        for(LatLng location : locations) {
            markerOptions.position(location);
            markerOptions.title(name[cnt]);
            markerOptions.snippet("자세한 정보 클릭!");
            if(height[cnt] < child)
                map.addMarker(markerOptions);
            cnt+=1;
        }
        cnt=0;
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(getString(R.string.height)+" : " + progress);
                child = progress;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.clear();
                        for(LatLng location : locations) {
                            markerOptions.position(location);
                            markerOptions.title(name[cnt]);
                            if(height[cnt] < child)
                                map.addMarker(markerOptions);
                            cnt+=1;
                        }
                        cnt=0;
                    }
                });
            }
        });
        //정보창 클릭 리스너
        map.setOnInfoWindowClickListener(infoWindowClickListener);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.292568, 127.203429),15));
    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            cnt = 0;
            while(true){
                if(marker.getTitle().equals(name[cnt]))
                    break;
                cnt += 1;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(att_url[cnt]);
            cnt = 0;
            intent.setData(uri);
            startActivity(intent);
        }
    };
    public void ExitApp(View view) { finish(); }
}
