package com.example.p_code.diagnostictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_code.diagnostictest.Utils.Data;
import com.example.p_code.diagnostictest.Utils.MainApplication;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView namaT;
    String res_nama;
    Button soalBtn;
    Data accountData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
    }

    private void initObject() {
        namaT = (TextView) findViewById(R.id.nama_text);
        soalBtn = (Button) findViewById(R.id.soal_btn);

        res_nama = accountData.nama;

        soalBtn.setOnClickListener(this);
//
        showResult();
    }

    private void showResult() {
        //Toast.makeText(this, res_nisn, Toast.LENGTH_SHORT);
        namaT.setText(res_nama);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.soal_btn:
                Intent intent = new Intent(this, UjianActivity.class);
                startActivity(intent);
        }
    }
}
