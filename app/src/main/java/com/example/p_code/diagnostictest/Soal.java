package com.example.p_code.diagnostictest;

import android.graphics.drawable.Drawable;

/**
 * Created by ACER on 2/14/2016.
 */
public class Soal {
    private String soal, soal2, alasan;
    private String[] options, reasons;
    private Drawable gambar, reason1, reason2, reason3, reason4;

   public Soal (){

    }

    public Soal (String soal, String soal2, String opsiA, String opsiB, String opsiC, String opsiD,
                 String alasan, String alasan1, String alasan2, String alasan3, String alasan4,
                 Drawable gambar, Drawable reason1, Drawable reason2, Drawable reason3, Drawable reason4){
        super();
        this.soal = soal;
        this.soal2 = soal2;
        this.gambar = gambar;
        this.reason1 = reason1;
        this.reason2 = reason2;
        this.reason3 = reason3;
        this.reason4 = reason4;
        this.options = new String[]{opsiA, opsiB, opsiC, opsiD};
        this.alasan = alasan;
        this.reasons = new String[]{alasan1, alasan2, alasan3, alasan4};
    }

    public String getSoal(){
        return soal;
    }

    public String[] getOptions(){
        return options;
    }

    public String getAlasan(){
        return alasan;
    }
    public String[] getReasons(){
        return reasons;
    }
    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getSoal2() {
        return soal2;
    }

    public void setSoal2(String soal2) {
        this.soal2 = soal2;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setReasons(String[] reasons) {
        this.reasons = reasons;
    }

    public Drawable getGambar() {
        return gambar;
    }

    public void setGambar(Drawable gambar) {
        this.gambar = gambar;
    }

    public Drawable getReason1() {
        return reason1;
    }

    public void setReason1(Drawable reason1) {
        this.reason1 = reason1;
    }

    public Drawable getReason2() {
        return reason2;
    }

    public void setReason2(Drawable reason2) {
        this.reason2 = reason2;
    }

    public Drawable getReason3() {
        return reason3;
    }

    public void setReason3(Drawable reason3) {
        this.reason3 = reason3;
    }

    public Drawable getReason4() {
        return reason4;
    }

    public void setReason4(Drawable reason4) {
        this.reason4 = reason4;
    }
}
