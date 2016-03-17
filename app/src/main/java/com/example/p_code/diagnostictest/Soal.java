package com.example.p_code.diagnostictest;

/**
 * Created by ACER on 2/14/2016.
 */
public class Soal {
    private String soal, alasan;
    private String[] options, reasons;

    public Soal (){

    }

    public Soal (String soal,String opsiA, String opsiB, String opsiC, String opsiD,
                 String alasan, String alasan1, String alasan2, String alasan3, String alasan4){
        super();
        this.soal = soal;
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
}
