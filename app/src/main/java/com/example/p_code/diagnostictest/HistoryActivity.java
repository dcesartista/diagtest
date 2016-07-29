package com.example.p_code.diagnostictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.EndPointAPI;
import com.example.p_code.diagnostictest.Template.Template;
import com.example.p_code.diagnostictest.Utils.Data;
import com.example.p_code.diagnostictest.Utils.JSONParser;
import com.example.p_code.diagnostictest.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity implements VolleyInterface {

    public VolleyRequest mRequest;
    public JSONParser mJSONParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mRequest = new VolleyRequest(this);
        mJSONParser = new JSONParser(this);

        getHistory();
    }

    private void getHistory() {
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.HISTORY);
        map.put(Template.Query.NISN, Data.nisn);
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST_HISTORY, map);
        Log.v("ASDASDASDA","AHAHAHAH");
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        Log.v("LALALALALALLA HEHE",jsonObject.toString());
        Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(VolleyError errorListener) {
        Toast.makeText(this, "FAIL!"+errorListener, Toast.LENGTH_SHORT).show();
        Log.v("FAIL!",errorListener.toString());
    }
}
