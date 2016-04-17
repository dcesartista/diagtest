package com.example.p_code.diagnostictest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Soal mSoal;
    private Context context;
    private static LayoutInflater inflater = null;
    SoalHolder soalHolder;
    static float jumlahSoal = 15;
    static int answers[] = new int[100];
    static int reasons[] = new int[100];
    private float score;
    private float jumlahBenar;
    ProgressDialog progressDialog;
    String theScore;
    private VolleyRequest mRequest;
    String formatKirimJawaban;

    public SoalAdapterNew(Context context, Soal mSoal) {
        this.context = context;
        this.mSoal = mSoal;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRequest = new VolleyRequest(this);
        progressDialog = new ProgressDialog(context);
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
    public void onSucces(JSONObject jsonObject) {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        Intent intent = new Intent(context,ResultActivity.class);
        Log.v("Score ", theScore);
        intent.putExtra(EXTRA_SCORE, theScore);
        context.startActivity(intent);
    }

    @Override
    public void onFailed(VolleyError errorListener) {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        Toast.makeText(context,"Gagal mengirimkan jawaban anda, harap periksa koneksi internet!!",Toast.LENGTH_LONG).show();
    }

    public static class SoalHolder {
        TextView nomorSoal;
        TextView soal, soal2;
        ImageView gambar;
        RadioGroup options;
        TextView alasan;
        RadioGroup reasons;

        public SoalHolder(View view, final Context context) {
            //super(view);
            //ListView lv = (ListView) view.
            //soalPosition = lv.getPositionForView(view);
            gambar = (ImageView) view.findViewById(R.id.ivSoals);
            soal = (TextView) view.findViewById(R.id.tvSoals);
            soal2 = (TextView) view.findViewById(R.id.tvSoals2);
            options = (RadioGroup) view.findViewById(R.id.rgOptions);
            alasan = (TextView) view.findViewById(R.id.tvAlasan);
            reasons = (RadioGroup) view.findViewById(R.id.rgReasons);
            nomorSoal = (TextView) view.findViewById(R.id.nomorSoal);
        }

    }

    @Override
    public View getView(final int soalPosition, View convertView, ViewGroup parent) {
        View view;

        if (soalPosition == jumlahSoal){
            view = inflater.inflate(R.layout.submit_button, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumlahBenar = 0;
                    score = 0;
                    formatKirimJawaban = "";
                    for (int i = 0; i < jumlahSoal; i++) {
                        formatKirimJawaban = formatKirimJawaban.concat(mSoal.getIdSoal()[i]+"_"+UjianActivity2.changeAnswerIdtoLetter(answers[i]).toLowerCase()+
                                                    "."+UjianActivity2.changeReasonIdtoLetter(reasons[i])+"#");
                        Log.v("jawaban", UjianActivity2.changeAnswerIdtoLetter(answers[i]));
                        Log.v("kunci", ""+UjianActivity2.getAnswerKey()[i]);
                        Log.v("alasan",UjianActivity2.changeReasonIdtoLetter(reasons[i]));
                        Log.v("kunci alasan", UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]));

                        if (UjianActivity2.changeAnswerIdtoLetter(answers[i]).equals(UjianActivity2.getAnswerKey()[i])) {
                            jumlahBenar+=1;
                        }
                        if (UjianActivity2.changeReasonIdtoLetter(reasons[i]).equals(UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]))) {
                            jumlahBenar+=1;
                        }
                    }
                    Log.v("ASDASDASDAS","AHDAHSDAHS");
                    Log.v("formatted answer",formatKirimJawaban);
                    Log.v("JUMLAH BENAR", ""+jumlahBenar);
                    Log.v("JUMLAH TOTAL", ""+(jumlahSoal*2));
                    score = (float) (jumlahBenar/(jumlahSoal*2))*100;
                    if(score>100)
                        score = 100;
                    theScore = String.format("%.2f", score);
                    Log.v("Score ", String.valueOf(score));
                    new AlertDialog.Builder(context)
                            .setMessage("Apakah anda yakin? \n Pastikan anda mengecek jawaban anda sebelum melakukan submit" )
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    sendAnswer(formatKirimJawaban.substring(0,formatKirimJawaban.length()-1));
                                    Log.v("FORMATTED FIX", formatKirimJawaban.substring(0,formatKirimJawaban.length()-1));
                                }})
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    score=0;
                                }
                            }).show();
                }
            });
        }
        else {
            view = inflater.inflate(R.layout.soal_ujian, parent, false);

            //UjianActivity2.getOverview().getItem(soalPosition).setChecked(true);

            soalHolder = new SoalHolder(view, context);
            //Log.v("Soal No", String.valueOf(soalPosition));

            if (answers[soalPosition] > 0) {
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
                    alasan.setCompoundDrawablesWithIntrinsicBounds(mSoal.getGambarRsn()[soalPosition][i], null, null, null);
                    alasan.setText("");
                }
                else {
                    alasan.setText(mSoal.getAlasan()[soalPosition][i]);
                }
            }

            soalHolder.nomorSoal.setText((soalPosition+1)+".");
            soalHolder.soal.setText(mSoal.getPertanyaan()[soalPosition]);
            soalHolder.soal2.setText(mSoal.getPertanyaan2()[soalPosition]);
            soalHolder.gambar.setImageDrawable(mSoal.getGambar()[soalPosition]);

            soalHolder.options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    answers[soalPosition] = checkedId - 2131492978 - 40;
                    UjianActivity2.updateOverview(answers, reasons);
                }
            });

            soalHolder.reasons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    reasons[soalPosition] = checkedId - 2131492984 - 40;
                    UjianActivity2.updateOverview(answers, reasons);
                }
            });

            Log.v(TAG, "executed");
            Log.v("Soal ke:", String.valueOf(soalPosition));
        }

        return view;
    }

    private void sendAnswer(String formattedAnswer){
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.JAWABAN);
        map.put(Template.Query.NISN, Data.nisn);
        map.put(Template.Query.ANSWER, formattedAnswer);
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST_SUBMIT,map);
    }

    public static int[] getAnswers(){
        return answers;
    }
    public static int[] getReasons(){
        return reasons;
    }

}
