package com.example.p_code.diagnostictest.Utils;

/**
 * Created by ACER on 3/8/2016.
 */
public class Soal {
    private static String idSoal[], kompetensi[], pertanyaan[]
            , jawaban[][], alasan[][], kunci[];

    public Soal() {
        idSoal = new String[20];
        kompetensi = new String[20];
        pertanyaan = new String[20];
        jawaban = new String[20][4];
        alasan = new String[20][4];
        kunci = new String[20];
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


}
