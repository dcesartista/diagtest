package com.example.p_code.diagnostictest.Utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.p_code.diagnostictest.Utils.Data;

import java.lang.reflect.Array;

/**
 * Created by P-CODE on 2/13/2016.
 */
public class JSONParser {
    private Context mContext;
    //Data mainData = new Data();
    String finalNisn, finalNama, response, finalNotif;
    JSONArray array;

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
            }

            //Toast.makeText(mContext, nisn.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
