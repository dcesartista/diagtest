package com.example.p_code.diagnostictest.Utils;

import android.app.Application;

/**
 * Created by P-CODE on 2/13/2016.
 */
public class Data extends Application {
    public static final String kompetensi1 = "Memahami dan menjelaskan peristiwa pemuaian";
    public static final String kompetensi2 = "Memahami skala suhu pada termometer";
    public static final String kompetensi3 = "Mengetahui definisi suhu dan thermometer";
    public static final String kompetensi4 = "Memahami kalor, perubahan suhu serta perpindahan kalor dan akibatnya";
    public static String nisn;
    public static String nama;
    public static float jumlahKompetensi1;
    public static float jumlahKompetensi2;
    public static float jumlahKompetensi3;
    public static float jumlahKompetensi4;
    public static float pemahamanKompetensi1;
    public static float pemahamanKompetensi2;
    public static float pemahamanKompetensi3;
    public static float pemahamanKompetensi4;
    public static String[] timeHistory = new String[3];
    public static String[] markHistory = new String[3];


    public static String notification;

    public void setData(String nisn, String nama) {
        this.nisn = nisn;
        this.nama = nama;
    }

    public void setNotif(String notification) {
        this.notification = notification;
    }
}
