package com.example.p_code.diagnostictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.p_code.diagnostictest.Utils.Data;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        boolean isLulus = getIntent().getBooleanExtra(SoalAdapterNew.EXTRA_LULUS, false);
        String score = getIntent().getStringExtra(SoalAdapterNew.EXTRA_SCORE);
        TextView tvScore = (TextView) findViewById(R.id.tvScoreResult);
        TextView tvKetLulus = (TextView) findViewById(R.id.tvKetLulus);

        if (isLulus) {
            tvKetLulus.setText("Selamat ya...");
        } else {
            tvKetLulus.setText("Belajar lagi ya...");
        }

        TextView tvKompetensi1 = (TextView) findViewById(R.id.tvKompetensi1);
        TextView tvKompetensi2 = (TextView) findViewById(R.id.tvKompetensi2);
        TextView tvKompetensi3 = (TextView) findViewById(R.id.tvKompetensi3);
        TextView tvKompetensi4 = (TextView) findViewById(R.id.tvKompetensi4);

        String pemahamanKompetensi1 = String.format("%.2f", Data.pemahamanKompetensi1);
        String pemahamanKompetensi2 = String.format("%.2f", Data.pemahamanKompetensi2);
        String pemahamanKompetensi3 = String.format("%.2f", Data.pemahamanKompetensi3);
        String pemahamanKompetensi4 = String.format("%.2f", Data.pemahamanKompetensi4);

        tvKompetensi1.setText(Data.kompetensi1 + " : " + pemahamanKompetensi1 + "%");
        tvKompetensi2.setText(Data.kompetensi2 + " : " + pemahamanKompetensi2 + "%");
        tvKompetensi3.setText(Data.kompetensi3 + " : " + pemahamanKompetensi3 + "%");
        tvKompetensi4.setText(Data.kompetensi4 + " : " + pemahamanKompetensi4 + "%");

        tvScore.setText(""+score);

        Button ok = (Button) findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
