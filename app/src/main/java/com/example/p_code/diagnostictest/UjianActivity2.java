package com.example.p_code.diagnostictest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.EndPointAPI;
import com.example.p_code.diagnostictest.Template.Template;
import com.example.p_code.diagnostictest.Utils.Data;
import com.example.p_code.diagnostictest.Utils.JSONParser;
import com.example.p_code.diagnostictest.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UjianActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
                    , VolleyInterface {
    public static final String TAG = UjianActivity2.class.getSimpleName();
    private ListView mListView;
    private SoalAdapterNew mAdapter;
    private float score;
    private float jumlahBenar;
    View timeout;
    ProgressDialog progressDialog;
    String theScore;
    String formatKirimJawaban;
    float jumlahBenarKompetensi1, jumlahBenarKompetensi2,
            jumlahBenarKompetensi3, jumlahBenarKompetensi4;
    private boolean isLulus;

    public static String[] getAnswerKey() {
        return answerKey;
    }

    public static int[] getReasonKey() {
        return reasonKey;
    }

    private static Menu overview;

    public static long getTimeRemaining() {
        return timeRemaining;
    }

    public static void setTimeRemaining(long timeRemaining) {
        UjianActivity2.timeRemaining = timeRemaining;
    }

    private static long timeRemaining;
    private static String[] answerKey;
    ProgressDialog fetchingSoal;
    VolleyRequest requestSoal;
    VolleyRequest submitAnswer;
    JSONParser mJSONParser;
    com.example.p_code.diagnostictest.Utils.Soal soalsoal;
    static int jumlahSoal;
    private static Map<String,Drawable> imageMap = new HashMap();

    public static void setReasonKey(int[] reasonKey) {
        UjianActivity2.reasonKey = reasonKey;
    }

    public static void setAnswerKey(String[] answerKey) {
        UjianActivity2.answerKey = answerKey;
    }

    private static int[] reasonKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);

        TextView nama = (TextView) headerView.findViewById(R.id.tvNamaNav);
        TextView nisn = (TextView) headerView.findViewById(R.id.tvNISNNav);
        TextView submitbtn = (TextView) findViewById(R.id.tvSubmit2);
        timeout = findViewById(R.id.timeout_view);
        nama.setText(Data.nama);
        nisn.setText(Data.nisn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTest(false);
            }
        });

        imageMap.put("gb01", getResources().getDrawable(R.drawable.gambar_gelas));
        imageMap.put("gb02",getResources().getDrawable(R.drawable.gambar_bimetal));
        imageMap.put("gb03",getResources().getDrawable(R.drawable.gambar_kaos));
        imageMap.put("gb05",getResources().getDrawable(R.drawable.gambar_sakit));
        imageMap.put("gb06",getResources().getDrawable(R.drawable.gambar_bimetal2));
        imageMap.put("gb13",getResources().getDrawable(R.drawable.gambar_suhu));

        imageMap.put("rsn04_1",getResources().getDrawable(R.drawable.opsi4_1));
        imageMap.put("rsn04_2",getResources().getDrawable(R.drawable.opsi4_2));
        imageMap.put("rsn04_3",getResources().getDrawable(R.drawable.opsi4_3));
        imageMap.put("rsn04_4",getResources().getDrawable(R.drawable.opsi4_4));
        imageMap.put("rsn09_1",getResources().getDrawable(R.drawable.opsi9_1));
        imageMap.put("rsn09_2",getResources().getDrawable(R.drawable.opsi9_2));
        imageMap.put("rsn09_3",getResources().getDrawable(R.drawable.opsi9_3));
        imageMap.put("rsn09_4",getResources().getDrawable(R.drawable.opsi9_4));
        imageMap.put("rsn10_1",getResources().getDrawable(R.drawable.opsi10_1));
        imageMap.put("rsn10_2",getResources().getDrawable(R.drawable.opsi10_2));
        imageMap.put("rsn10_3",getResources().getDrawable(R.drawable.opsi10_3));
        imageMap.put("rsn10_4",getResources().getDrawable(R.drawable.opsi10_4));
        imageMap.put("rsn12_1",getResources().getDrawable(R.drawable.opsi12_1));
        imageMap.put("rsn12_2",getResources().getDrawable(R.drawable.opsi12_2));
        imageMap.put("rsn12_3",getResources().getDrawable(R.drawable.opsi12_3));
        imageMap.put("rsn12_4",getResources().getDrawable(R.drawable.opsi12_4));

        fetchingSoal = new ProgressDialog(this);
        progressDialog = new ProgressDialog(this);
        requestSoal = new VolleyRequest(this);
        submitAnswer = new VolleyRequest(new VolleyInterface() {
            @Override
            public void onPrepare() {
                progressDialog.isIndeterminate();
                progressDialog.setMessage("Mengirim Jawaban...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                if (!progressDialog.isShowing()){
                    progressDialog.show();
                }
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Intent intent = new Intent(UjianActivity2.this, ResultActivity.class);
                Log.v("Score ", theScore);
                intent.putExtra(SoalAdapterNew.EXTRA_SCORE, theScore);
                intent.putExtra(SoalAdapterNew.EXTRA_LULUS, isLulus);
                SoalAdapterNew.answers = null;
                SoalAdapterNew.reasons = null;
                UjianActivity2.this.startActivity(intent);
                finish();
            }

            @Override
            public void onFailed(VolleyError errorListener) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Toast.makeText(UjianActivity2.this,"Gagal mengirimkan jawaban anda, harap periksa koneksi internet!!",Toast.LENGTH_LONG).show();
            }
        });
        mJSONParser = new JSONParser(this);

        fetchSoal();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mListView = (ListView) findViewById(R.id.listView);
        //mAdapter = new SoalAdapterNew(this,getDataSet());
        //mListView.setAdapter(mAdapter);

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


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Apabila anda kembali ke menu utama, jawaban anda akan hilang \n Apakah anda yakin akan kembali ke menu utama?" )
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            UjianActivity2.super.onBackPressed();
                        }})
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

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
            /*case R.id.ov16:
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
                break;*/
        }
        //mListView.smoothScrollToPosition(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private ArrayList<Soal> getDataSet() {
        ArrayList results = new ArrayList<Soal>();
        /*for (int index = 0; index < 4; index++) {
            results.add(new Soal(soalsoal.getPertanyaan()[index], soalsoal.getJawaban()[index][0]
                    , soalsoal.getJawaban()[index][1], soalsoal.getJawaban()[index][2], soalsoal.getJawaban()[index][3],
                                 "Alasan", soalsoal.getAlasan()[index][0], soalsoal.getAlasan()[index][1]
                    , soalsoal.getAlasan()[index][2], soalsoal.getAlasan()[index][3]));
        }*/
        results.add(new Soal());
        return results;
    }


    public static void updateOverview(int[] answers, int[] reasons){
        for (int i=0;i<jumlahSoal;i++){
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

    public void fetchSoal(){
        requestSoal.sendGetRequest(EndPointAPI.DIAGTEST_SOAL);
    }

    @Override
    public void onPrepare() {
        fetchingSoal.setMessage("Mempersiapkan Soal..");
        fetchingSoal.setIndeterminate(true);
        fetchingSoal.setCancelable(false);
        fetchingSoal.show();
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        fetchingSoal.dismiss();
        mJSONParser.isSuccess(jsonObject, this);
        soalsoal = mJSONParser.getSoalfromJSON();
        jumlahSoal = mJSONParser.getJumlahSoal();

        answerKey = new String[jumlahSoal];
        reasonKey = new int[jumlahSoal];

        for(int i=0;i<jumlahSoal;i++){
            Log.v("kunci", mJSONParser.getKunci()[i].substring(2));
            answerKey[i] = mJSONParser.getKunci()[i].toUpperCase().substring(0,1);
            reasonKey[i] = Integer.parseInt(mJSONParser.getKunci()[i].substring(2));
        }

        mAdapter = new SoalAdapterNew(this, soalsoal, this);
        mListView.setAdapter(mAdapter);
        new CountDownTimer(3600000,300000){
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(UjianActivity2.this,"Waktu anda tinggal "+(millisUntilFinished/60000)+" menit lagi",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinish() {
                mListView.setVisibility(View.GONE);
                timeout.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    public void onFailed(VolleyError errorListener) {
        fetchingSoal.dismiss();
        Toast.makeText(this,"Gagal mendapatkan soal! Pastikan anda terhubung dengan internet dan coba lagi..",Toast.LENGTH_LONG).show();
        finish();
    }

    public int changeKuncitoId(String kunci){
        switch (kunci){
            case "a":
                return 1;
            case "b":
                return 2;
            case "c":
                return 3;
            case "d":
                return 4;
        }
        return 0;
    }

    public static Map<String, Drawable> getImageMap(){
        return imageMap;
    }

    private void submitTest(boolean timeOut){
        jumlahBenar = 0;
        score = 0;
        formatKirimJawaban = "";
        for (int i = 0; i < jumlahSoal; i++) {
            formatKirimJawaban = formatKirimJawaban.concat(soalsoal.getIdSoal()[i]+"_"+UjianActivity2.changeAnswerIdtoLetter(SoalAdapterNew.answers[i]).toLowerCase()+
                                                           "."+UjianActivity2.changeReasonIdtoLetter(SoalAdapterNew.reasons[i])+"#");
            Log.v("jawaban", UjianActivity2.changeAnswerIdtoLetter(SoalAdapterNew.answers[i]));
            Log.v("kunci", ""+UjianActivity2.getAnswerKey()[i]);
            Log.v("alasan",UjianActivity2.changeReasonIdtoLetter(SoalAdapterNew.reasons[i]));
            Log.v("kunci alasan", UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]));

            if (UjianActivity2.changeAnswerIdtoLetter(SoalAdapterNew.answers[i]).equals(UjianActivity2.getAnswerKey()[i])) {
                if (soalsoal.getKompetensi()[i].equals("Memahami dan menjelaskan peristiwa pemuaian")){
                    jumlahBenarKompetensi1+=1;
                } else if (soalsoal.getKompetensi()[i].equals("Memahami skala suhu pada termometer")){
                    jumlahBenarKompetensi2+=1;
                } else if (soalsoal.getKompetensi()[i].equals("Mengetahui definisi suhu dan thermometer")){
                    jumlahBenarKompetensi3+=1;
                } else if (soalsoal.getKompetensi()[i].equals("Memahami kalor, perubahan suhu serta perpindahan kalor dan akibatnya")){
                    jumlahBenarKompetensi4+=1;
                }
                jumlahBenar+=1;
            }
            if (UjianActivity2.changeReasonIdtoLetter(SoalAdapterNew.reasons[i]).equals(UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]))) {
                if (soalsoal.getKompetensi()[i].equals(Data.kompetensi1)){
                    jumlahBenarKompetensi1+=1;
                } else if (soalsoal.getKompetensi()[i].equals(Data.kompetensi2)){
                    jumlahBenarKompetensi2+=1;
                } else if (soalsoal.getKompetensi()[i].equals(Data.kompetensi3)){
                    jumlahBenarKompetensi3+=1;
                } else if (soalsoal.getKompetensi()[i].equals(Data.kompetensi4)){
                    jumlahBenarKompetensi4+=1;
                }
                jumlahBenar+=1;
            }
        }

        Data.pemahamanKompetensi1 = (jumlahBenarKompetensi1/(Data.jumlahKompetensi1*2)) * 100;
        Data.pemahamanKompetensi2 = (jumlahBenarKompetensi2/(Data.jumlahKompetensi2*2)) * 100;
        Data.pemahamanKompetensi3 = (jumlahBenarKompetensi3/(Data.jumlahKompetensi3*2)) * 100;
        Data.pemahamanKompetensi4 = (jumlahBenarKompetensi4/(Data.jumlahKompetensi4*2)) * 100;

        Log.v("ASDASDASDAS","AHDAHSDAHS");
        Log.v("formatted answer",formatKirimJawaban);
        Log.v("JUMLAH BENAR", ""+jumlahBenar);
        Log.v("JUMLAH TOTAL", ""+(jumlahSoal*2));

        score = (jumlahBenar/(jumlahSoal*2))*100;
        if(score >= 70){
            isLulus = true;
        } else {
            isLulus = false;
        }

        if(score>100)
            score = 100;
        theScore = String.format("%.2f", score);
        Log.v("Score ", String.valueOf(score));

        if(timeOut){
            sendAnswer(formatKirimJawaban.substring(0,formatKirimJawaban.length()-1),jumlahBenar,score);
            Log.v("FORMATTED FIX", formatKirimJawaban.substring(0,formatKirimJawaban.length()-1));
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Apakah anda yakin? \n Pastikan anda mengecek jawaban anda sebelum melakukan submit" )
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            sendAnswer(formatKirimJawaban.substring(0,formatKirimJawaban.length()-1),jumlahBenar,score);
                            Log.v("FORMATTED FIX", formatKirimJawaban.substring(0,formatKirimJawaban.length()-1));
                        }})
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            score=0;
                        }
                    }).show();
        }

    }

    private void sendAnswer(String formattedAnswer, float jmlbnr, float score ){
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.JAWABAN);
        //map.put("input",Data.nisn + " " + formattedAnswer + " " + jmlbnr + " " + score);
        map.put(Template.Query.NISN, Data.nisn);
        map.put(Template.Query.ANSWER, formattedAnswer);
        /*map.put("score", String.valueOf(jmlbnr));
        map.put("mark", String.valueOf(score));*/
        submitAnswer.sendPostRequest(EndPointAPI.DIAGTEST_SUBMIT,map);
    }



}
