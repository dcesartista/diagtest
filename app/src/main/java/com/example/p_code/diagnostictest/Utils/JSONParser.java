package com.example.p_code.diagnostictest.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by P-CODE on 2/13/2016.
 */
public class JSONParser {
    private Context mContext;
    //Data mainData = new Data();
    String finalNisn, finalNama, response, finalNotif;
    JSONArray array;
    Soal soalsoal;
    String[] kunci = new String[20];

    public JSONParser(Context context) {
        this.mContext = context;
    }

    public boolean isSuccess(JSONObject jsonObject) {
        String mResponse;

        try
        {
            response = jsonObject.getString("response");
            mResponse = response.toString();
            //array = jsonObject.getJSONArray("login");
            if (mResponse.equals("login")) {
                array = jsonObject.getJSONArray("login");
                //Toast.makeText(mContext, mResponse, Toast.LENGTH_SHORT).show();
                String nisn = "";
                String nama = "";

                JSONObject a = array.getJSONObject(0);

                nisn = a.getString("nisn");
                nama = a.getString("nama");

                finalNisn = nisn.toString();
                finalNama = nama.toString();
                //mainData.setData(finalNisn, finalNama);
                Data.nama = finalNama;
                Data.nisn = finalNisn;
                //Toast.makeText(mContext, finalNisn + finalNama, Toast.LENGTH_LONG).show();

            } else if (mResponse.equals("signup")) {
                array = jsonObject.getJSONArray("signup");
                String notification = "";

                JSONObject b = array.getJSONObject(0);

                notification = b.getString("notif");
                finalNotif = notification.toString();

                Data.notification = finalNotif;
            } else if(mResponse.equals("soal")){
                array = jsonObject.getJSONArray("soal");
                soalsoal = new Soal();

                for(int i=0;i<array.length();i++){
                    JSONObject soal = array.getJSONObject(i);
                    JSONObject arrayJawaban = soal.getJSONObject("jawaban");
                    JSONObject arrayAlasan = soal.getJSONObject("alasan");

                    soalsoal.setIdSoal(i,soal.getString("id_soal"));
                    soalsoal.setKompetensi(i, soal.getString("kompetensi"));
                    soalsoal.setPertanyaan(i, soal.getString("pertanyaan"));
                    soalsoal.setJawaban(i, 0, arrayJawaban.getString("ans_a"));
                    soalsoal.setJawaban(i, 1, arrayJawaban.getString("ans_b"));
                    soalsoal.setJawaban(i, 2, arrayJawaban.getString("ans_c"));
                    soalsoal.setJawaban(i, 3, arrayJawaban.getString("ans_d"));
                    soalsoal.setAlasan(i, 0, arrayAlasan.getString("rsn_1"));
                    soalsoal.setAlasan(i, 1, arrayAlasan.getString("rsn_2"));
                    soalsoal.setAlasan(i, 2, arrayAlasan.getString("rsn_3"));
                    soalsoal.setAlasan(i, 3, arrayAlasan.getString("rsn_4"));
                    kunci[i]=soal.getString("kunci");
                    Log.d("Success bro", kunci[i]);
                }
            }

            //Toast.makeText(mContext, nisn.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public Soal getSoalfromJSON(){
        return soalsoal;
    }

    public String[] getKunci(){
        return kunci;
    }

    public int getJumlahSoal(){
        return array.length();
    }
}
