package com.example.p_code.diagnostictest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ACER on 2/16/2016.
 */
public class SoalAdapter extends RecyclerView.Adapter<SoalAdapter.SoalHolder> {
    public static final String TAG = SoalAdapter.class.getSimpleName();
    private ArrayList<Soal> mSoal;
    private final Context context;

    public static class SoalHolder extends RecyclerView.ViewHolder {
        TextView soal;
        ListView options;
        TextView alasan;
        ListView reasons;

        public SoalHolder(View view) {
            super(view);
            soal = (TextView) view.findViewById(R.id.tvSoals);
            options = (ListView) view.findViewById(R.id.listOpsi);
            alasan = (TextView) view.findViewById(R.id.tvAlasan);
            reasons = (ListView) view.findViewById(R.id.listAlasan);
        }

    }

    public SoalAdapter(ArrayList<Soal> mySoal,  Context context) {
        mSoal = mySoal;
        this.context = context;
    }



    @Override
    public SoalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.soal_ujian, parent, false);

        SoalHolder soalHolder = new SoalHolder(view);
        return soalHolder;
    }

    @Override
    public void onBindViewHolder(SoalHolder holder, int position) {
        holder.soal.setText(mSoal.get(position).getSoal());
        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(context, R.layout.opsi, mSoal.get(position).getOptions());
        Log.v(TAG, mSoal.get(position).getOptions()[1]);
        holder.options.setAdapter(optionsAdapter);
        holder.alasan.setText(mSoal.get(position).getAlasan());
        ArrayAdapter<String> reasonsAdapter = new ArrayAdapter<>(context, R.layout.alasan, mSoal.get(position).getReasons());
        holder.reasons.setAdapter(reasonsAdapter);
    }


    @Override
    public int getItemCount() {
        return mSoal.size();
    }
}
