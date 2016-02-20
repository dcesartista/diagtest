/*package com.example.p_code.diagnostictest;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
*/
/**
 * Created by ACER on 2/16/2016.
 */

/*
public class SoalAdapter extends RecyclerView.Adapter<SoalAdapter.SoalHolder> {
    public static final String TAG = SoalAdapter.class.getSimpleName();
    private ArrayList<Soal> mSoal;
    private final Context context;
    private SoalHolder soalHolder;
    ArrayAdapter<String> optionsAdapter = null;
    ArrayAdapter<String> reasonsAdapter = null;
    ArrayList<Integer> answers;

    public class SoalHolder extends RecyclerView.ViewHolder {
        TextView soal;
        ListView options;
        TextView alasan;
        ListView reasons;
        Context context;
        int answer = -1;

        public SoalHolder(View view, final Context context) {
            super(view);
            this.context = context;
            soal = (TextView) view.findViewById(R.id.tvSoals);
            options = (ListView) view.findViewById(R.id.listOpsi);
            alasan = (TextView) view.findViewById(R.id.tvAlasan);
            reasons = (ListView) view.findViewById(R.id.listAlasan);
            Log.v(TAG, "created again");


            options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    boolean isSelected = ((ColorDrawable)view.getBackground()) != null &&
                                         ((ColorDrawable)view.getBackground()).getColor() ==
                                         ContextCompat.getColor(context, R.color.colorPrimary);
                    answer = -1;
                    TextView text = (TextView) view;

                    int finalRadius = (int)Math.hypot(view.getWidth()/2, view.getHeight()/2);

                    if (isSelected) {
                        //text1.setText(baconTitle);
                        //text2.setText(baconText);
                        answer = -1;
                        Log.v(TAG, String.valueOf(answer));
                        text.setTextColor(ContextCompat.getColor(context, R.color.black));
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    } else {
                        for (int i = 0; i < options.getChildCount(); i++){
                            TextView child = (TextView) options.getChildAt(i);
                            child.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                            child.setTextColor(ContextCompat.getColor(context, R.color.black));
                        }
                        Animator anim = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            //anim = ViewAnimationUtils.createCircularReveal(view, (int) view.getWidth() / 2, (int) view.getHeight() / 2, 0, finalRadius);
                        }
                        answer = position;
                        Log.v(TAG, String.valueOf(answer));
                        text.setTextColor(ContextCompat.getColor(context, R.color.white));
                        //text1.setText(veggieTitle);
                        //text2.setText(veggieText);
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        if (anim != null)
                            anim.start();
                    }
                }
            });

            reasons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    boolean isSelected = ((ColorDrawable)view.getBackground()) != null &&
                                         ((ColorDrawable)view.getBackground()).getColor() ==
                                         ContextCompat.getColor(context, R.color.colorPrimary);
                    int answer = -1;
                    TextView text = (TextView) view;

                    for (int i = 0; i < options.getChildCount(); i++){
                        TextView child = (TextView) reasons.getChildAt(i);
                        child.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                        child.setTextColor(ContextCompat.getColor(context, R.color.black));
                    }

                    int finalRadius = (int)Math.hypot(view.getWidth()/2, view.getHeight()/2);

                    if (isSelected) {
                        //text1.setText(baconTitle);
                        //text2.setText(baconText);
                        text.setTextColor(ContextCompat.getColor(context, R.color.black));
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                        answer = -1;
                        Log.v(TAG, String.valueOf(answer));
                    } else {
                        Animator anim = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            //anim = ViewAnimationUtils.createCircularReveal(view, (int) view.getWidth() / 2, (int) view.getHeight() / 2, 0, finalRadius);
                        }
                        answer = position;
                        Log.v(TAG, String.valueOf(answer));
                        text.setTextColor(ContextCompat.getColor(context, R.color.white));
                        //text1.setText(veggieTitle);
                        //text2.setText(veggieText);
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        if (anim != null)
                            anim.start();
                    }
                }
            });
        }


        public ListView getOptions(){
            return options;
        }

        public ListView getReasons(){
            return  reasons;
        }


    }

    public SoalAdapter(ArrayList<Soal> mySoal,  Context context) {
        answers = new ArrayList<>();
        mSoal = mySoal;
        this.context = context;
        for (int i=0;i<mSoal.size();i++){
            answers.add(-1);
        }
    }



    @Override
    public SoalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.soal_ujian, parent, false);

        soalHolder = new SoalHolder(view, context);
        return soalHolder;
    }

    @Override
    public void onViewAttachedToWindow(SoalHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.v(TAG, "wtf" + holder.reasons.getChildCount());
    }

    @Override
    public void onViewDetachedFromWindow(SoalHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.v(TAG, "lol " + holder.answer);
    }

    @Override
    public void onBindViewHolder(SoalHolder holder, int position) {
        Log.v(TAG, "executed");
        holder.soal.setText(mSoal.get(position).getSoal());
        //if (optionsAdapter == null)
        optionsAdapter = new ArrayAdapter<>(context, R.layout.opsi, mSoal.get(position).getOptions());
        holder.options.setAdapter(optionsAdapter);
        Log.v(TAG, String.valueOf(holder.answer));
        holder.alasan.setText(mSoal.get(position).getAlasan());
        //if (reasonsAdapter == null)
        reasonsAdapter = new ArrayAdapter<>(context, R.layout.alasan, mSoal.get(position).getReasons());
        holder.reasons.setAdapter(reasonsAdapter);
        //while (holder.reasons.getChildCount() <= 0)
        Log.v(TAG, String.valueOf(holder.reasons.getChildCount()));
        Utility.setListViewHeightBasedOnItems(holder.options);
        Utility.setListViewHeightBasedOnItems(holder.reasons);
        //holder.soal.measure(0, 0);
        //optionsAdapter.getView(0,null,holder.options).measure(0,0);
        //Log.v(TAG, String.valueOf(optionsAdapter.getView(1, null,holder.options).getMeasuredHeight()));
    }

    public SoalHolder getSoalHolder(){
        return soalHolder;
    }

    @Override
    public int getItemCount() {
        return mSoal.size();
    }


}*/
