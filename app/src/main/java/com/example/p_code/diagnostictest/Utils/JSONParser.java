package com.example.p_code.diagnostictest.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.example.p_code.diagnostictest.UjianActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

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
    Map<String, Drawable> imageMap;

    public JSONParser(Context context) {
        this.mContext = context;
    }

    public boolean isSuccess(JSONObject jsonObject, Context context) {
        String mResponse;
        imageMap = UjianActivity2.getImageMap();

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

                return true;
                //Toast.makeText(mContext, finalNisn + finalNama, Toast.LENGTH_LONG).show();

            } else if (mResponse.equals("signup")) {
                array = jsonObject.getJSONArray("signup");
                String notification = "";

                JSONObject b = array.getJSONObject(0);

                notification = b.getString("notif");
                finalNotif = notification.toString();

                Data.notification = finalNotif;

                Toast.makeText(context,notification,Toast.LENGTH_LONG).show();

                return true;
            } else if(mResponse.equals("soal")){
                array = jsonObject.getJSONArray("soal");
                soalsoal = new Soal();
                Data.jumlahKompetensi1 = 0;
                Data.jumlahKompetensi2 = 0;
                Data.jumlahKompetensi3 = 0;
                Data.jumlahKompetensi4 = 0;

                for(int i=0;i<array.length();i++){
                    JSONObject soal = array.getJSONObject(i);
                    JSONObject arrayJawaban = soal.getJSONObject("jawaban");
                    JSONObject arrayAlasan = soal.getJSONObject("alasan");

                    Log.v("KOMPETENSI", soal.getString("kompetensi"));

                    soalsoal.setIdSoal(i, soal.getString("id_soal"));
                    soalsoal.setKompetensi(i, soal.getString("kompetensi"));
                    if (soal.getString("kompetensi").equals("Memahami dan menjelaskan peristiwa pemuaian")){
                        Data.jumlahKompetensi1+=1;
                    } else if (soal.getString("kompetensi").equals("Memahami skala suhu pada termometer")){
                        Data.jumlahKompetensi2+=1;
                    } else if (soal.getString("kompetensi").equals("Mengetahui definisi suhu dan thermometer")){
                        Data.jumlahKompetensi3+=1;
                    } else if (soal.getString("kompetensi").equals("Memahami kalor, perubahan suhu serta perpindahan kalor dan akibatnya")){
                        Data.jumlahKompetensi4+=1;
                    }

                    String pertanyaan = soal.getString("pertanyaan");
                    String pertanyaan1 = "";
                    String pertanyaan2 = "";
                    if(pertanyaan.contains("#")){
                        int hashIndex = pertanyaan.indexOf("#");
                        pertanyaan1 = pertanyaan.substring(0, hashIndex);
                        pertanyaan2 = pertanyaan.substring(hashIndex+1);
                        soalsoal.setPertanyaan(i, pertanyaan1);
                    }
                    else {
                        soalsoal.setPertanyaan(i, pertanyaan);
                    }
                    soalsoal.setPertanyaan2(i, pertanyaan2);

                    String responseGambar = soal.getString("id_gambar");
                    if (!responseGambar.equals("0000")){
                        soalsoal.setGambar(i, imageMap.get(responseGambar));
                    } else{
                        soalsoal.setGambar(i, null);
                    }

                    soalsoal.setJawaban(i, 0, "A. " + arrayJawaban.getString("ans_a"));
                    soalsoal.setJawaban(i, 1, "B. " + arrayJawaban.getString("ans_b"));
                    soalsoal.setJawaban(i, 2, "C. " + arrayJawaban.getString("ans_c"));
                    soalsoal.setJawaban(i, 3, "D. " + arrayJawaban.getString("ans_d"));

                    if(arrayAlasan.getString("rsn_1").contains("#")){
                        soalsoal.setAlasan(i, 0, "");
                        Log.v("THE KEY :", arrayAlasan.getString("rsn_1").replace("#",""));
                        soalsoal.setGambarRsn(i, 0, imageMap.get(arrayAlasan.getString("rsn_1").replace("#","")));
                    } else {
                        Log.v("THE KEY :", "NOTHING");
                        soalsoal.setAlasan(i, 0, arrayAlasan.getString("rsn_1"));
                        soalsoal.setGambarRsn(i, 0, null);
                    }

                    if(arrayAlasan.getString("rsn_2").contains("#")){
                        soalsoal.setAlasan(i, 1, "");
                        soalsoal.setGambarRsn(i, 1, imageMap.get(arrayAlasan.getString("rsn_2").replace("#","")));
                    } else {
                        soalsoal.setAlasan(i, 1, arrayAlasan.getString("rsn_2"));
                        soalsoal.setGambarRsn(i, 1, null);
                    }

                    if(arrayAlasan.getString("rsn_3").contains("#")){
                        soalsoal.setAlasan(i, 2, "");
                        soalsoal.setGambarRsn(i, 2, imageMap.get(arrayAlasan.getString("rsn_3").replace("#", "")));
                    } else {
                        soalsoal.setAlasan(i, 2,  arrayAlasan.getString("rsn_3"));
                        soalsoal.setGambarRsn(i, 2, null);
                    }

                    if(arrayAlasan.getString("rsn_4").contains("#")){
                        soalsoal.setAlasan(i, 3, "");
                        soalsoal.setGambarRsn(i, 3, imageMap.get(arrayAlasan.getString("rsn_4").replace("#","")));
                    } else {
                        soalsoal.setAlasan(i, 3, arrayAlasan.getString("rsn_4"));
                        soalsoal.setGambarRsn(i, 3, null);
                    }

                    kunci[i]=soal.getString("kunci");
                    Log.d("Success bro", kunci[i]);
                }
                return true;
            }
            else if (response.equals("error")){
                String message = jsonObject.getString("message");
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                return false;
            }

            else{
                return true;
            }

            //Toast.makeText(mContext, nisn.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
        }

        return false;
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
