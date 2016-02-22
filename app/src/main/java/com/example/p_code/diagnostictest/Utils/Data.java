package com.example.p_code.diagnostictest.Utils;

import android.app.Application;

/**
 * Created by P-CODE on 2/13/2016.
 */
public class Data extends Application {
    public static String nisn;
    public static String nama;

    public void setData(String nisn, String nama) {
        this.nisn = nisn;
        this.nama = nama;
    }
}
