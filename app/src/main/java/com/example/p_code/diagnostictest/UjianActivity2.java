package com.example.p_code.diagnostictest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UjianActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = UjianActivity2.class.getSimpleName();
    private ListView mListView;
    private SoalAdapterNew mAdapter;
    private static Menu overview;
    private int[] answerKey;
    private int[] reasonKey;
    private float score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        answerKey = new int[]{1,2,1,2,3,3,4,2,3,4,2,1,1,4,4,3,2,4,4,3};
        reasonKey = new int[]{1,2,1,2,3,3,4,2,3,4,2,1,1,4,4,3,2,4,4,3};
        score = 0;

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new SoalAdapterNew(this,getDataSet());
        mListView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        overview = (Menu) ((NavigationView) findViewById(R.id.nav_view)).getMenu();
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                overview.getItem(firstVisibleItem).setChecked(true);
            }
        });

        TextView submitButton = (TextView) findViewById(R.id.tvSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<20; i++){
                    if(changeAnswerIdtoLetter(SoalAdapterNew.getAnswers()[i]).equals(changeAnswerIdtoLetter(answerKey[i]))){
                        score += 2.5;
                    }
                    if(changeReasonIdtoLetter(SoalAdapterNew.getReasons()[i]).equals(changeReasonIdtoLetter(reasonKey[i]))){
                        score += 2.5;
                    }
                }
                Log.v("Score ", String.valueOf(score));
                score=0;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ujian_activity2, menu);
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
        int id = item.getItemId();
        Log.v("order ", String.valueOf(id));

        switch (id){
            case R.id.ov1:
                mListView.setSelection(0);
                break;
            case R.id.ov2:
                mListView.setSelection(1);
                break;
            case R.id.ov3:
                mListView.setSelection(2);
                break;
            case R.id.ov4:
                mListView.setSelection(3);
                break;
            case R.id.ov5:
                mListView.setSelection(4);
                break;
            case R.id.ov6:
                mListView.setSelection(5);
                break;
            case R.id.ov7:
                mListView.setSelection(6);
                break;
            case R.id.ov8:
                mListView.setSelection(7);
                break;
            case R.id.ov9:
                mListView.setSelection(8);
                break;
            case R.id.ov10:
                mListView.setSelection(9);
                break;
            case R.id.ov11:
                mListView.setSelection(10);
                break;
            case R.id.ov12:
                mListView.setSelection(11);
                break;
            case R.id.ov13:
                mListView.setSelection(12);
                break;
            case R.id.ov14:
                mListView.setSelection(13);
                break;
            case R.id.ov15:
                mListView.setSelection(14);
                break;
            case R.id.ov16:
                mListView.setSelection(15);
                break;
            case R.id.ov17:
                mListView.setSelection(16);
                break;
            case R.id.ov18:
                mListView.setSelection(17);
                break;
            case R.id.ov19:
                mListView.setSelection(18);
                break;
            case R.id.ov20:
                mListView.setSelection(19);
                break;
        }
        //mListView.smoothScrollToPosition(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private ArrayList<Soal> getDataSet() {
        ArrayList results = new ArrayList<Soal>();
        for (int index = 0; index < 20; index++) {
            results.add(new Soal("Soal " + (index + 1), "Opsi AD", "Opsi B", "Opsi C", "Opsi D", "Alasan",
                                 "Alasan 3", "Alasan 2", "Alasan 3", "Alasan 4"));
        }
        return results;
    }


    public static void updateOverview(int[] answers, int[] reasons){
        for (int i=0;i<20;i++){
            if(i>9)
                overview.getItem(i).setTitle((i + 1) + ". " + changeAnswerIdtoLetter(answers[i]) + "     Alasan: " + changeReasonIdtoLetter(reasons[i]));
            else
                overview.getItem(i).setTitle("0" + (i + 1) + ". " + changeAnswerIdtoLetter(answers[i]) + "     Alasan: " + changeReasonIdtoLetter(reasons[i]));
        }
    }

    public static String changeAnswerIdtoLetter(int id){
        switch (id){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            default:
                return "-";
        }
    }

    public static String changeReasonIdtoLetter(int id){
        if (id>0)
            return String.valueOf(id);
        else
            return "-";
    }

    public static Menu getOverview(){
        return overview;
    }
}
