package com.example.p_code.diagnostictest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.EndPointAPI;
import com.example.p_code.diagnostictest.Template.Template;
import com.example.p_code.diagnostictest.Utils.Data;
import com.example.p_code.diagnostictest.Utils.Soal;
import com.example.p_code.diagnostictest.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACER on 2/17/2016.
 */
public class SoalAdapterNew extends BaseAdapter implements VolleyInterface {
    private final static String TAG = SoalAdapterNew.class.getSimpleName();
    public final static String EXTRA_SCORE = "com.example.p_code.diagnostictest.SCORE";
    public final static String EXTRA_LULUS = "com.example.p_code.diagnostictest.LULUS";
    private Soal mSoal;
    private Context context;
    private Activity activity;
    private static LayoutInflater inflater = null;
    SoalHolder soalHolder;
    static float jumlahSoal = 15;
    static int answers[];
    static int reasons[];
    private float score;
    private float jumlahBenar;
    ProgressDialog progressDialog;
    String theScore;
    long timeRemaining;
    private VolleyRequest mRequest;
    String formatKirimJawaban;
    float jumlahBenarKompetensi1, jumlahBenarKompetensi2,
            jumlahBenarKompetensi3, jumlahBenarKompetensi4;
    private boolean isLulus;

    public SoalAdapterNew(final Context context, Soal mSoal, Activity activity) {
        this.context = context;
        this.mSoal = mSoal;
        this.activity = activity;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRequest = new VolleyRequest(this);
        progressDialog = new ProgressDialog(context);
        answers = new int[100];
        reasons = new int[100];

        /*new CountDownTimer(4800000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                UjianActivity2.setTimeRemaining(millisUntilFinished/1000);
                if (millisUntilFinished == 1800000){
                    Toast.makeText(context,"Waktu anda tinggal : " + millisUntilFinished/60000 + " menit",Toast.LENGTH_LONG).show();
                } else if (millisUntilFinished == 900000){
                    Toast.makeText(context,"Waktu anda tinggal : " + millisUntilFinished/60000 + " menit",Toast.LENGTH_LONG).show();
                } else if (millisUntilFinished == 300000){
                    Toast.makeText(context,"Waktu anda tinggal : " + millisUntilFinished/60000 + " menit",Toast.LENGTH_LONG).show();
                } if (millisUntilFinished == 60000){
                    Toast.makeText(context,"Waktu anda tinggal : " + millisUntilFinished/1000 + " detik",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFinish() {
                submitTest(true);
            }
        }.start();*/

        jumlahBenarKompetensi1 = 0;
        jumlahBenarKompetensi2 = 0;
        jumlahBenarKompetensi3 = 0;
        jumlahBenarKompetensi4 = 0;
    }

    @Override
    public int getCount() {
        return 16;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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
        Intent intent = new Intent(context,ResultActivity.class);
        Log.v("Score ", theScore);
        intent.putExtra(EXTRA_SCORE, theScore);
        intent.putExtra(EXTRA_LULUS, isLulus);
        answers = null;
        reasons = null;
        context.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onFailed(VolleyError errorListener) {
         if (progressDialog.isShowing()){
                progressDialog.dismiss();
        }
        Toast.makeText(context,"Gagal mengirimkan jawaban anda, harap periksa koneksi internet!!",Toast.LENGTH_LONG).show();
        Log.v("ERROR BOSQUE", String.valueOf(errorListener));
    }

    public static class SoalHolder {
        TextView nomorSoal;
        WebView soal, soal2;
        ImageView gambar;
        RadioGroup options;
        TextView alasan;
        RadioGroup reasons;

        public SoalHolder(View view, final Context context) {
            //super(view);
            //ListView lv = (ListView) view.
            //soalPosition = lv.getPositionForView(view);
            gambar = (ImageView) view.findViewById(R.id.ivSoals);
            soal = (WebView) view.findViewById(R.id.tvSoals);
            soal2 = (WebView) view.findViewById(R.id.tvSoals2);
            options = (RadioGroup) view.findViewById(R.id.rgOptions);
            alasan = (TextView) view.findViewById(R.id.tvAlasan);
            reasons = (RadioGroup) view.findViewById(R.id.rgReasons);
            nomorSoal = (TextView) view.findViewById(R.id.nomorSoal);
        }

    }

    @Override
    public View getView(final int soalPosition, View convertView, ViewGroup parent) {
        View view;
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";

        if (soalPosition == jumlahSoal){
            view = inflater.inflate(R.layout.submit_button, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitTest(false);
                }
            });
        }
        else {
            view = inflater.inflate(R.layout.soal_ujian, parent, false);

            //UjianActivity2.getOverview().getItem(soalPosition).setChecked(true);

            soalHolder = new SoalHolder(view, context);
            //Log.v("Soal No", String.valueOf(soalPosition));

            if (answers[soalPosition] > 0) {
                Log.v("JAWaBAN", answers[soalPosition]+"");
                soalHolder.options.check(soalHolder.options.getChildAt(answers[soalPosition] - 1).getId());
            }

            if (reasons[soalPosition] > 0) {
                soalHolder.reasons.check(soalHolder.reasons.getChildAt(reasons[soalPosition] - 1).getId());
            }


            for (int i = 0; i < 4; i++) {
                RadioButton opsi = (RadioButton) soalHolder.options.getChildAt(i);
                opsi.setText(mSoal.getJawaban()[soalPosition][i]);
                RadioButton alasan = (RadioButton) soalHolder.reasons.getChildAt(i);
                if (null != mSoal.getGambarRsn()[soalPosition][i]){
                    Log.v("WAAHAHA", "HAHAHA");
                    alasan.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mSoal.getGambarRsn()[soalPosition][i], null);
                    alasan.setText("");
                }
                else {
                    alasan.setText(mSoal.getAlasan()[soalPosition][i]);
                }
            }

            soalHolder.nomorSoal.setText((soalPosition+1)+".");
            //soalHolder.soal.setText(mSoal.getPertanyaan()[soalPosition]);
            //soalHolder.soal2.setText(mSoal.getPertanyaan2()[soalPosition]);
            soalHolder.soal.loadData(String.format(htmlText,mSoal.getPertanyaan()[soalPosition]), "text/html; charset=utf-8", "utf-8");
            WebSettings webSettings = soalHolder.soal.getSettings();
            webSettings.setDefaultFixedFontSize(18);
            soalHolder.soal2.loadData(String.format(htmlText,mSoal.getPertanyaan2()[soalPosition]), "text/html; charset=utf-8", "utf-8");
            WebSettings webSettings2 = soalHolder.soal2.getSettings();
            webSettings2.setDefaultFixedFontSize(18);

            soalHolder.gambar.setImageDrawable(mSoal.getGambar()[soalPosition]);

            soalHolder.options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rbOpsiA:
                            answers[soalPosition] = 1;
                            break;
                        case R.id.rbOpsiB:
                            answers[soalPosition] = 2;
                            break;
                        case R.id.rbOpsiC:
                            answers[soalPosition] = 3;
                            break;
                        case R.id.rbOpsiD:
                            answers[soalPosition] = 4;
                            break;
                    }
                    //answers[soalPosition] = checkedId - 2131492978 - 41;
                    UjianActivity2.updateOverview(answers, reasons);
                }
            });

            soalHolder.reasons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rbReasonA:
                            reasons[soalPosition] = 1;
                            break;
                        case R.id.rbReasonB:
                            reasons[soalPosition] = 2;
                            break;
                        case R.id.rbReasonC:
                            reasons[soalPosition] = 3;
                            break;
                        case R.id.rbReasonD:
                            reasons[soalPosition] = 4;
                            break;
                    }
                    //reasons[soalPosition] = checkedId - 2131492984 - 41;
                    UjianActivity2.updateOverview(answers, reasons);
                }
            });

            Log.v(TAG, "executed");
            Log.v("Soal ke:", String.valueOf(soalPosition));
        }

        return view;
    }

    private void submitTest(boolean timeOut){
        jumlahBenar = 0;
        score = 0;
        jumlahBenarKompetensi1=0;
        jumlahBenarKompetensi2=0;
        jumlahBenarKompetensi3=0;
        jumlahBenarKompetensi4=0;
        formatKirimJawaban = "";
        for (int i = 0; i < jumlahSoal; i++) {
            formatKirimJawaban = formatKirimJawaban.concat(mSoal.getIdSoal()[i]+"_"+UjianActivity2.changeAnswerIdtoLetter(answers[i]).toLowerCase()+
                                                           "."+UjianActivity2.changeReasonIdtoLetter(reasons[i])+"#");
            Log.v("jawaban", UjianActivity2.changeAnswerIdtoLetter(answers[i]));
            Log.v("kunci", ""+UjianActivity2.getAnswerKey()[i]);
            Log.v("alasan",UjianActivity2.changeReasonIdtoLetter(reasons[i]));
            Log.v("kunci alasan", UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]));

            if (UjianActivity2.changeAnswerIdtoLetter(answers[i]).equals(UjianActivity2.getAnswerKey()[i])) {
                if (mSoal.getKompetensi()[i].equals("Memahami dan menjelaskan peristiwa pemuaian")){
                    jumlahBenarKompetensi1+=1;
                } else if (mSoal.getKompetensi()[i].equals("Memahami skala suhu pada termometer")){
                    jumlahBenarKompetensi2+=1;
                } else if (mSoal.getKompetensi()[i].equals("Mengetahui definisi suhu dan thermometer")){
                    jumlahBenarKompetensi3+=1;
                } else if (mSoal.getKompetensi()[i].equals("Memahami kalor, perubahan suhu serta perpindahan kalor dan akibatnya")){
                    jumlahBenarKompetensi4+=1;
                }
                jumlahBenar+=1;
            }
            if (UjianActivity2.changeReasonIdtoLetter(reasons[i]).equals(UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]))) {
                if (mSoal.getKompetensi()[i].equals(Data.kompetensi1)){
                    jumlahBenarKompetensi1+=1;
                } else if (mSoal.getKompetensi()[i].equals(Data.kompetensi2)){
                    jumlahBenarKompetensi2+=1;
                } else if (mSoal.getKompetensi()[i].equals(Data.kompetensi3)){
                    jumlahBenarKompetensi3+=1;
                } else if (mSoal.getKompetensi()[i].equals(Data.kompetensi4)){
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
            sendAnswer(formatKirimJawaban.substring(0,formatKirimJawaban.length()-1), jumlahBenar, score);
            Log.v("FORMATTED FIX", formatKirimJawaban.substring(0,formatKirimJawaban.length()-1));
        } else {
            new AlertDialog.Builder(context)
                    .setMessage("Apakah anda yakin? \n Pastikan anda mengecek jawaban anda sebelum melakukan submit" )
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            sendAnswer(formatKirimJawaban.substring(0,formatKirimJawaban.length()-1), jumlahBenar, score);
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

    private void sendAnswer(String formattedAnswer, float jmlbenar, float score){
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.JAWABAN);
        map.put("input",Data.nisn + " " + formattedAnswer + " " + jmlbenar + " " + score);
        /*map.put(Template.Query.NISN, Data.nisn);
        map.put(Template.Query.ANSWER, formattedAnswer);*/
        /*map.put("score", String.valueOf(jmlbenar));
        map.put("mark", String.valueOf(score));*/
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST_SUBMIT,map);
    }

    public static int[] getAnswers(){
        return answers;
    }
    public static int[] getReasons(){
        return reasons;
    }

}
