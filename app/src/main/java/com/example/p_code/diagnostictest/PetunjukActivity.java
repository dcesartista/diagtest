package com.example.p_code.diagnostictest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class PetunjukActivity extends AppCompatActivity {
    WebView aturan1,aturan2, aturan3, aturan4, aturan5;
    String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aturan1 = (WebView)findViewById(R.id.aturan1);
        aturan2 = (WebView)findViewById(R.id.aturan2);
        aturan3 = (WebView)findViewById(R.id.aturan3);
        aturan4 = (WebView)findViewById(R.id.aturan4);
        aturan5 = (WebView)findViewById(R.id.aturan5);

        aturan1.loadData(String.format(htmlText,getString(R.string.aturan1)), "text/html", "utf-8");
        WebSettings webSettings = aturan1.getSettings();
        webSettings.setDefaultFixedFontSize(18);
        aturan2.loadData(String.format(htmlText,getString(R.string.aturan2)), "text/html", "utf-8");
        WebSettings webSettings2 = aturan2.getSettings();
        webSettings.setDefaultFixedFontSize(18);
        aturan3.loadData(String.format(htmlText,getString(R.string.aturan3)), "text/html", "utf-8");
        WebSettings webSettings3 = aturan3.getSettings();
        webSettings.setDefaultFixedFontSize(18);
        aturan4.loadData(String.format(htmlText,getString(R.string.aturan4)), "text/html", "utf-8");
        WebSettings webSettings4 = aturan4.getSettings();
        webSettings.setDefaultFixedFontSize(18);
        aturan5.loadData(String.format(htmlText,getString(R.string.aturan5)), "text/html", "utf-8");
        WebSettings webSettings5 = aturan5.getSettings();
        webSettings.setDefaultFixedFontSize(18);

        TextView mulai =(TextView) findViewById(R.id.tvMulai);
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PetunjukActivity.this)
                        .setMessage("Apakah anda siap untuk mengerjakan ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent = new Intent(PetunjukActivity.this, UjianActivity2.class);
                                startActivity(intent);
                                finish();
                            }})
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }
}
