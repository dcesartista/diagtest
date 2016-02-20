package com.example.p_code.diagnostictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_code.diagnostictest.Utils.Data;
import com.example.p_code.diagnostictest.Utils.MainApplication;

public class MainActivity extends AppCompatActivity {

    TextView nisnT, namaT;
    String res_nisn, res_nama;
    Data accountData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
    }

    private void initObject() {
        nisnT = (TextView) findViewById(R.id.nisn_text);
        namaT = (TextView) findViewById(R.id.nama_text);

        res_nisn = accountData.nisn;
        res_nama = accountData.nama;
//
        showResult();
    }

    private void showResult() {
        //Toast.makeText(this, res_nisn, Toast.LENGTH_SHORT);
        nisnT.setText("NISN : " + res_nisn);
        namaT.setText("NAMA : " + res_nama);
    }
}
