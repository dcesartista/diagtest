package com.example.p_code.diagnostictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        float score = getIntent().getFloatExtra(SoalAdapterNew.EXTRA_SCORE,0);
        TextView tvScore = (TextView) findViewById(R.id.tvScoreResult);
        tvScore.setText(""+score);
    }
}
