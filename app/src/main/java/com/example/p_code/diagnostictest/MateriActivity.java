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
import android.widget.FrameLayout;
import android.widget.TextView;

public class MateriActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   View.OnClickListener{
    FrameLayout parent;
    TextView currentSelected;
    DrawerLayout drawer;


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
                    break;
                case R.id.tvTermometer:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_2, null));
                    break;
                case R.id.tvPemuaian:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_3, null));
                    break;
                case R.id.tvPemuaianSehariHari:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.suhu_materi_4, null));
                    break;
                case R.id.tvPengertianKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_1, null));
                    break;
                case R.id.tvFungsiKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_2, null));
                    break;
                case R.id.tvKapasitasKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_3, null));
                    break;
                case R.id.tvPerpindahanKalor:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_4, null));
                    break;
                case R.id.tvKalorSehariHari:
                    parent.addView(View.inflate(MateriActivity.this, R.layout.kalor_materi_5, null));
                    break;
            }
        }
    }
}
