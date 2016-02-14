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

    public JSONParser(Context context) {
        this.mContext = context;
    }

    public boolean isSuccess(JSONObject jsonObject) {
        String nisn = "";
        String nama = "";

        JSONArray array;

        try
        {
            array = jsonObject.getJSONArray("response");
            JSONObject a = array.getJSONObject(0);
            nisn = a.getString("nisn");
            nama = a.getString("nama");

            finalNisn = nisn.toString();
            finalNama = nama.toString();
            //Toast.makeText(mContext, nisn.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
        }

        mainData.setData(finalNisn, finalNama);
        if (nisn != "") {
            Toast.makeText(mContext, "Login Successfully", Toast.LENGTH_LONG).show();
            return true;
        } else
            return false;
    }
}
