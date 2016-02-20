package com.example.p_code.diagnostictest;

import android.content.Context;
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
    private ArrayList<Soal> mSoal;
    private Context context;
    private static LayoutInflater inflater = null;
    SoalHolder soalHolder;
    int answers[] = new int[100];
    int reasons[] = new int[100];

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


        }

    }

    @Override
    public View getView(final int soalPosition, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.soal_ujian, parent, false);

        soalHolder = new SoalHolder(view, context);
        //Log.v("Soal No", String.valueOf(soalPosition));

        if(answers[soalPosition] > 0){
            soalHolder.options.check(soalHolder.options.getChildAt(answers[soalPosition]-1).getId());
            Log.v("jawaban :", String.valueOf(answers[soalPosition]));
        }

        if(reasons[soalPosition] > 0){
            soalHolder.reasons.check(soalHolder.reasons.getChildAt(reasons[soalPosition] - 1).getId());
            Log.v("alasan :", String.valueOf(reasons[soalPosition]));
        }

        for (int i =0;i<4;i++){
            RadioButton opsi = (RadioButton) soalHolder.options.getChildAt(i);
            opsi.setText(mSoal.get(soalPosition).getOptions()[i]);
            RadioButton alasan = (RadioButton) soalHolder.reasons.getChildAt(i);
            alasan.setText(mSoal.get(soalPosition).getReasons()[i]);
        }

        soalHolder.soal.setText(mSoal.get(soalPosition).getSoal());
        Log.v("Jumlah Opsi : ", String.valueOf(soalHolder.options.getChildCount()));
        soalHolder.alasan.setText(mSoal.get(soalPosition).getAlasan());

        soalHolder.options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                answers[soalPosition] = checkedId - 2131492971;
                for (int a = 0; a < 5; a++) {
                    Log.v("Jawaban :", String.valueOf(answers[a]));
                }
            }
        });

        soalHolder.reasons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                reasons[soalPosition] = checkedId-2131492977;
                for(int a = 0; a<5; a++){
                    Log.v("Alasan :", String.valueOf(reasons[a]));
                }
            }
        });


        Log.v(TAG, "executed");

        return view;
    }

}