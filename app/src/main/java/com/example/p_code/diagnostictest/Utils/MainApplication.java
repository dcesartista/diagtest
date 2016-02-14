package com.example.p_code.diagnostictest.Utils;

import android.app.Application;
import android.content.Context;
/**
 * Created by P-CODE on 2/13/2016.
 */
public class MainApplication extends Application {
    private static MainApplication mainApplication;
    public static MainApplication getInstance() {
        return mainApplication;
    }
    public static Context getContext() {
        return mainApplication.getApplicationContext();
    }

//    private String nisn;
//    private String nama;
//
//    public void setData(String nisn, String nama) {
//        this.nisn = nisn;
//        this.nama = nama;
//    }
//
//    public String getNisn() {
//        return this.nisn;
//    }
//
//    public String getNama() {
//        return this.nama;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
    }



}
