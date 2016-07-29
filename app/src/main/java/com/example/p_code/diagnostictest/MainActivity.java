package com.example.p_code.diagnostictest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.p_code.diagnostictest.Utils.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView namaT;
    String res_nama;
    Button soalBtn;
    Data accountData;
    CardView materi,ujian,history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        materi = (CardView) findViewById(R.id.cardMateri);
        ujian = (CardView) findViewById(R.id.cardUjian);
        history = (CardView) findViewById(R.id.cardHistory);

        materi.setOnClickListener(this);
        ujian.setOnClickListener(this);
        history.setOnClickListener(this);

        //initObject();
    }

    /*private void initObject() {
        namaT = (TextView) findViewById(R.id.nama_text);
        soalBtn = (Button) findViewById(R.id.soal_btn);

        res_nama = accountData.nama;

        soalBtn.setOnClickListener(this);

        showResult();
    }*/

    private void showResult() {
        //Toast.makeText(this, res_nisn, Toast.LENGTH_SHORT);
        namaT.setText(res_nama);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.soal_btn:
                Intent intent = new Intent(this, UjianActivity2.class);
                startActivity(intent);*/
            case R.id.cardUjian:
                Intent intent = new Intent(this, PetunjukActivity.class);
                startActivity(intent);
                break;
            case R.id.cardMateri:
                Intent intent1 = new Intent(this, MateriActivity.class);
                startActivity(intent1);
                break;
            case R.id.cardHistory:
                Intent intent2 = new Intent(this, HistoryActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
