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
    Data mainData = new Data();
    String finalNisn, finalNama;
    JSONArray array;

    public JSONParser(Context context) {
        this.mContext = context;
    }

    public boolean isSuccess(JSONObject jsonObject) {

        try
        {

            array = jsonObject.getJSONArray("login");
            if (array != null) {
                String nisn = "";
                String nama = "";

                JSONObject a = array.getJSONObject(0);

                nisn = a.getString("nisn");
                nama = a.getString("nama");

                finalNisn = nisn.toString();
                finalNama = nama.toString();
                mainData.setData(finalNisn, finalNama);

            } else {
                try {
                    array = jsonObject.getJSONArray("signUp");
                    if (array != null) {
                        String notif;
                        JSONObject b = array.getJSONObject(0);

                        notif = b.getString("notif");

                        Toast.makeText(mContext, notif, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
                }
            }

            //Toast.makeText(mContext, nisn.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
