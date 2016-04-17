package com.example.p_code.diagnostictest.Utils;

import android.graphics.drawable.Drawable;

/**
 * Created by ACER on 3/8/2016.
 */
public class Soal {
    private static String idSoal[], kompetensi[], pertanyaan[], pertanyaan2[]
            , jawaban[][], alasan[][], kunci[];
    private static Drawable gambar[], gambarRsn[][];

    public Soal() {
        idSoal = new String[20];
        kompetensi = new String[20];
        pertanyaan = new String[20];
        pertanyaan2 = new String[20];
        jawaban = new String[20][4];
        alasan = new String[20][4];
        kunci = new String[20];
        gambar = new Drawable[20];
        gambarRsn = new Drawable[20][4];
    }


    public String[] getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(int index, String idSoal) {
        this.idSoal[index] = idSoal;
    }

    public String[] getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(int index, String kompetensi) {
        this.kompetensi[index] = kompetensi;
    }

    public String[] getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(int index, String pertanyaan) {
        this.pertanyaan[index] = pertanyaan;
    }

    public static String[] getPertanyaan2() {
        return pertanyaan2;
    }

    public static void setPertanyaan2(int index, String pertanyaan2) {
        Soal.pertanyaan2[index] = pertanyaan2;
    }

    public String[][] getJawaban() {
        return jawaban;
    }

    public void setJawaban(int index, int index2, String jawaban) {
        this.jawaban[index][index2] = jawaban;
    }

    public String[][] getAlasan() {
        return alasan;
    }

    public void setAlasan(int index, int index2, String alasan) {
        this.alasan[index][index2] = alasan;
    }

    public String[] getKunci() {
        return kunci;
    }

    public void setKunci(int index, String kunci) {
        this.kunci[index] = kunci;
    }

    public static Drawable[] getGambar() {
        return gambar;
    }

    public static void setGambar(int index, Drawable gambar) {
        Soal.gambar[index] = gambar;
    }

    public static Drawable[][] getGambarRsn() {
        return gambarRsn;
    }

    public static void setGambarRsn(int index1, int index2, Drawable gambarRsn) {
        Soal.gambarRsn[index1][index2] = gambarRsn;
    }

}
