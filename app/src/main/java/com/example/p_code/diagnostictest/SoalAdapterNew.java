package com.example.p_code.diagnostictest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ACER on 2/17/2016.
 */
public class SoalAdapterNew extends BaseAdapter {
    private final static String TAG = SoalAdapterNew.class.getSimpleName();
    public final static String EXTRA_SCORE = "com.example.p_code.diagnostictest.SCORE";
    private ArrayList<Soal> mSoal;
    private Context context;
    private static LayoutInflater inflater = null;
    SoalHolder soalHolder;
    static int jumlahSoal = 4;
    static int answers[] = new int[100];
    static int reasons[] = new int[100];
    private float score;

    public SoalAdapterNew(Context context, ArrayList<Soal> mSoal) {
        this.context = context;
        this.mSoal = mSoal;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mSoal.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class SoalHolder {
        TextView nomorSoal;
        TextView soal;
        RadioGroup options;
        TextView alasan;
        RadioGroup reasons;

        public SoalHolder(View view, final Context context) {
            //super(view);
            //ListView lv = (ListView) view.
            //soalPosition = lv.getPositionForView(view);
            soal = (TextView) view.findViewById(R.id.tvSoals);
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
                    score = 0;
                    for (int i = 0; i < jumlahSoal; i++) {
                        if (UjianActivity2.changeAnswerIdtoLetter(answers[i]).equals(UjianActivity2.changeAnswerIdtoLetter(UjianActivity2.getAnswerKey()[i]))) {
                            score += 2.5;
                        }
                        if (UjianActivity2.changeReasonIdtoLetter(reasons[i]).equals(UjianActivity2.changeReasonIdtoLetter(UjianActivity2.getReasonKey()[i]))) {
                            score += 2.5;
                        }
                    }
                    Log.v("Score ", String.valueOf(score));
                    new AlertDialog.Builder(context)
                            .setMessage("Apakah anda yakin? \n Pastikan anda mengecek jawaban anda sebelum melakukan submit" )
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(context,ResultActivity.class);
                                    Log.v("Score ", String.valueOf(score));
                                    intent.putExtra(EXTRA_SCORE, score);
                                    context.startActivity(intent);
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
                opsi.setText(mSoal.get(soalPosition).getOptions()[i]);
                RadioButton alasan = (RadioButton) soalHolder.reasons.getChildAt(i);
                alasan.setText(mSoal.get(soalPosition).getReasons()[i]);
            }

            soalHolder.nomorSoal.setText((soalPosition+1)+".");
            soalHolder.soal.setText(mSoal.get(soalPosition).getSoal());
            soalHolder.alasan.setText(mSoal.get(soalPosition).getAlasan());

            soalHolder.options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    answers[soalPosition] = checkedId - 2131492978 - 38;
                    UjianActivity2.updateOverview(answers, reasons);
                }
            });

            soalHolder.reasons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    reasons[soalPosition] = checkedId - 2131492984 - 38;
                    UjianActivity2.updateOverview(answers, reasons);
                }
            });

            Log.v(TAG, "executed");
            Log.v("Soal ke:", String.valueOf(soalPosition));
        }

        return view;
    }

    public static int[] getAnswers(){
        return answers;
    }
    public static int[] getReasons(){
        return reasons;
    }

}
