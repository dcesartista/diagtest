package com.example.p_code.diagnostictest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MateriActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   View.OnClickListener{
    FrameLayout parent;
    TextView currentSelected;
    DrawerLayout drawer;
    String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        parent = (FrameLayout) findViewById(R.id.parentMateri);
        TextView materi1 = (TextView) findViewById(R.id.tvPengertianSuhu);
        TextView materi2 = (TextView) findViewById(R.id.tvTermometer);
        TextView materi3 = (TextView) findViewById(R.id.tvPemuaian);
        TextView materi4 = (TextView) findViewById(R.id.tvPemuaianSehariHari);
        TextView materi5 = (TextView) findViewById(R.id.tvPengertianKalor);
        TextView materi6 = (TextView) findViewById(R.id.tvFungsiKalor);
        TextView materi7 = (TextView) findViewById(R.id.tvKapasitasKalor);
        TextView materi8 = (TextView) findViewById(R.id.tvPerpindahanKalor);
        TextView materi9 = (TextView) findViewById(R.id.tvKalorSehariHari);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        materi1.setOnClickListener(this);
        materi2.setOnClickListener(this);
        materi3.setOnClickListener(this);
        materi4.setOnClickListener(this);
        materi5.setOnClickListener(this);
        materi6.setOnClickListener(this);
        materi7.setOnClickListener(this);
        materi8.setOnClickListener(this);
        materi9.setOnClickListener(this);

        currentSelected = (TextView) findViewById(R.id.tvPengertianSuhu);
        parent.addView(View.inflate(this,R.layout.suhu_materi_1,null));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        WebView suhu1 = (WebView) findViewById(R.id.suhu1);
        suhu1.loadData(String.format(htmlText,getString(R.string.suhu_1)), "text/html", "utf-8");
        WebSettings webSettings = suhu1.getSettings();
        webSettings.setDefaultFontSize(18);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.materi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() != currentSelected.getId()) {
            drawer.closeDrawer(GravityCompat.START);
            currentSelected.setTextColor(getResources().getColor(R.color.black));
            currentSelected = (TextView) v;
            currentSelected.setTextColor(getResources().getColor(R.color.colorPrimary));
            parent.removeAllViews();
            switch (v.getId()) {
                case R.id.tvPengertianSuhu:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_1, null));
                    WebView suhu1 = (WebView) findViewById(R.id.suhu1);
                    WebSettings webSettings = suhu1.getSettings();
                    webSettings.setDefaultFixedFontSize(18);
                    suhu1.loadData(String.format(htmlText,getString(R.string.suhu_1)), "text/html; charset=utf-8", "utf-8");
                    break;
                case R.id.tvTermometer:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_2, null));
                    WebView suhu2 = (WebView) findViewById(R.id.suhu2_1);
                    suhu2.loadData(String.format(htmlText,getString(R.string.suhu_2)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings1 = suhu2.getSettings();
                    webSettings1.setDefaultFixedFontSize(18);
                    WebView suhu2_3_1 = (WebView) findViewById(R.id.suhu2_3_1);
                    suhu2_3_1.loadData(String.format(htmlText,getString(R.string.suhu_2_3_1b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings2 = suhu2_3_1.getSettings();
                    webSettings2.setDefaultFixedFontSize(18);
                    WebView suhu2_3_2 = (WebView) findViewById(R.id.suhu2_3_2);
                    suhu2_3_2.loadData(String.format(htmlText,getString(R.string.suhu_2_3_2b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings3 = suhu2_3_2.getSettings();
                    webSettings3.setDefaultFixedFontSize(18);
                    WebView suhu2_3_3 = (WebView) findViewById(R.id.suhu2_3_3);
                    suhu2_3_3.loadData(String.format(htmlText,getString(R.string.suhu_2_3_3b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings4 = suhu2_3_3.getSettings();
                    webSettings4.setDefaultFixedFontSize(18);
                    WebView suhu2_3_4 = (WebView) findViewById(R.id.suhu2_3_4);
                    suhu2_3_4.loadData(String.format(htmlText,getString(R.string.suhu_2_3_4b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings5 = suhu2_3_4.getSettings();
                    webSettings5.setDefaultFixedFontSize(18);
                    break;
                case R.id.tvPemuaian:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_3, null));
                    WebView suhu3_1 = (WebView) findViewById(R.id.suhu3_1);
                    suhu3_1.loadData(String.format(htmlText,getString(R.string.suhu_3_1)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings6 = suhu3_1.getSettings();
                    webSettings6.setDefaultFixedFontSize(18);
                    WebView suhu3_2 = (WebView) findViewById(R.id.suhu3_2);
                    suhu3_2.loadData(String.format(htmlText,getString(R.string.suhu_3_2)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings7 = suhu3_2.getSettings();
                    webSettings7.setDefaultFixedFontSize(18);
                    WebView suhu3_3 = (WebView) findViewById(R.id.suhu3_3);
                    suhu3_3.loadData(String.format(htmlText,getString(R.string.suhu_3_3)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings8 = suhu3_3.getSettings();
                    webSettings8.setDefaultFixedFontSize(18);
                    WebView suhu3_4 = (WebView) findViewById(R.id.suhu3_4);
                    suhu3_4.loadData(String.format(htmlText,getString(R.string.suhu_3_4)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings9 = suhu3_4.getSettings();
                    webSettings9.setDefaultFixedFontSize(18);
                    break;
                case R.id.tvPemuaianSehariHari:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_4, null));
                    WebView suhu4_1 = (WebView) findViewById(R.id.suhu4_1);
                    suhu4_1.loadData(String.format(htmlText,getString(R.string.suhu_4_1)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings10 = suhu4_1.getSettings();
                    webSettings10.setDefaultFixedFontSize(18);
                    WebView suhu4_2 = (WebView) findViewById(R.id.suhu4_2);
                    suhu4_2.loadData(String.format(htmlText,getString(R.string.suhu_4_2b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings11 = suhu4_2.getSettings();
                    webSettings11.setDefaultFixedFontSize(18);
                    WebView suhu4_3 = (WebView) findViewById(R.id.suhu4_3);
                    suhu4_3.loadData(String.format(htmlText,getString(R.string.suhu_4_3b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings12 = suhu4_3.getSettings();
                    webSettings12.setDefaultFixedFontSize(18);
                    WebView suhu4_4 = (WebView) findViewById(R.id.suhu4_4);
                    suhu4_4.loadData(String.format(htmlText,getString(R.string.suhu_4_4b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings13 = suhu4_4.getSettings();
                    webSettings13.setDefaultFixedFontSize(18);
                    WebView suhu4_5 = (WebView) findViewById(R.id.suhu4_5);
                    suhu4_5.loadData(String.format(htmlText,getString(R.string.suhu_4_5b)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings14 = suhu4_5.getSettings();
                    webSettings14.setDefaultFixedFontSize(18);
                    break;
                case R.id.tvPengertianKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_1, null));
                    WebView kalor1 = (WebView) findViewById(R.id.kalor1);
                    kalor1.loadData(String.format(htmlText,getString(R.string.kalor_1)), "text/html; charset=utf-8", "utf-8");
                    WebSettings webSettings15 = kalor1.getSettings();
                    webSettings15.setDefaultFixedFontSize(18);
                    break;
                case R.id.tvFungsiKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_2, null));
                    WebView kalor2_1 = (WebView) findViewById(R.id.kalor2_1);
                    kalor2_1.loadData(String.format(htmlText,getString(R.string.kalor_2)), "text/html; charset=utf-8", "utf-8");
                    kalor2_1.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_1b = (WebView) findViewById(R.id.kalor2_1b);
                    kalor2_1b.loadData(String.format(htmlText,getString(R.string.kalor_2_1b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_1b.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2 = (WebView) findViewById(R.id.kalor2_2);
                    kalor2_2.loadData(String.format(htmlText,getString(R.string.kalor_2_2b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2_2_1 = (WebView) findViewById(R.id.kalor2_2_2_1);
                    kalor2_2_2_1.loadData(String.format(htmlText,getString(R.string.kalor_2_2_2_1b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2_2_1.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2_2_2 = (WebView) findViewById(R.id.kalor2_2_2_2);
                    kalor2_2_2_2.loadData(String.format(htmlText,getString(R.string.kalor_2_2_2_2b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2_2_2 .getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2_2_3 = (WebView) findViewById(R.id.kalor2_2_2_3);
                    kalor2_2_2_3.loadData(String.format(htmlText,getString(R.string.kalor_2_2_2_3b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2_2_3.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2_3_1 = (WebView) findViewById(R.id.kalor2_2_3_1);
                    kalor2_2_3_1.loadData(String.format(htmlText,getString(R.string.kalor_2_2_3_1b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2_3_1.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2_3_2 = (WebView) findViewById(R.id.kalor2_2_3_2);
                    kalor2_2_3_2.loadData(String.format(htmlText,getString(R.string.kalor_2_2_3_2b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2_3_2.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor2_2_3_3 = (WebView) findViewById(R.id.kalor2_2_3_3);
                    kalor2_2_3_3.loadData(String.format(htmlText,getString(R.string.kalor_2_2_3_3b)), "text/html; charset=utf-8", "utf-8");
                    kalor2_2_3_3 .getSettings().setDefaultFixedFontSize(18);
                    break;
                case R.id.tvKapasitasKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_3, null));
                    WebView kalor3 = (WebView) findViewById(R.id.kalor3);
                    kalor3.loadData(String.format(htmlText,getString(R.string.kalor_3)), "text/html; charset=utf-8", "utf-8");
                    break;
                case R.id.tvPerpindahanKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_4, null));
                    WebView kalor4_1a = (WebView) findViewById(R.id.kalor4_1a);
                    kalor4_1a.loadData(String.format(htmlText,getString(R.string.kalor_4_1a)), "text/html; charset=utf-8", "utf-8");
                    kalor4_1a.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor4_1b= (WebView) findViewById(R.id.kalor4_1b);
                    kalor4_1b.loadData(String.format(htmlText,getString(R.string.kalor_4_1b)), "text/html; charset=utf-8", "utf-8");
                    kalor4_1b.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor4_2a= (WebView) findViewById(R.id.kalor4_2a);
                    kalor4_2a.loadData(String.format(htmlText,getString(R.string.kalor_4_2a)), "text/html; charset=utf-8", "utf-8");
                    kalor4_2a.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor4_2b= (WebView) findViewById(R.id.kalor4_2b);
                    kalor4_2b.loadData(String.format(htmlText,getString(R.string.kalor_4_2b)), "text/html; charset=utf-8", "utf-8");
                    kalor4_2b.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor4_3a= (WebView) findViewById(R.id.kalor4_3a);
                    kalor4_3a.loadData(String.format(htmlText,getString(R.string.kalor_4_3a)), "text/html; charset=utf-8", "utf-8");
                    kalor4_3a.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor4_3b= (WebView) findViewById(R.id.kalor4_3b);
                    kalor4_3b.loadData(String.format(htmlText,getString(R.string.kalor_4_3b)), "text/html; charset=utf-8", "utf-8");
                    kalor4_3b.getSettings().setDefaultFixedFontSize(18);
                    break;
                case R.id.tvKalorSehariHari:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_5, null));
                    WebView kalor5_1= (WebView) findViewById(R.id.kalor5_1);
                    kalor5_1.loadData(String.format(htmlText,getString(R.string.kalor_5_1)), "text/html; charset=utf-8", "utf-8");
                    kalor5_1.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor5_2= (WebView) findViewById(R.id.kalor5_2);
                    kalor5_2.loadData(String.format(htmlText,getString(R.string.kalor_5_2)), "text/html; charset=utf-8", "utf-8");
                    kalor5_2.getSettings().setDefaultFixedFontSize(18);
                    WebView kalor5_3= (WebView) findViewById(R.id.kalor5_3);
                    kalor5_3.loadData(String.format(htmlText,getString(R.string.kalor_5_3)), "text/html; charset=utf-8", "utf-8");
                    kalor5_3.getSettings().setDefaultFixedFontSize(18);
                    break;
            }
        }
    }
}
