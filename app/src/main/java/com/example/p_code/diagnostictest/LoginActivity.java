package com.example.p_code.diagnostictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.EndPointAPI;
import com.example.p_code.diagnostictest.Template.Template;
import com.example.p_code.diagnostictest.Utils.JSONParser;
import com.example.p_code.diagnostictest.Utils.MainApplication;
import com.example.p_code.diagnostictest.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, VolleyInterface{

    EditText mNisn;
    EditText mPassword;
    Button loginBtn;

    public VolleyRequest mRequest;
    public JSONParser mJSONParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mJSONParser = new JSONParser(this);
        mRequest = new VolleyRequest(this);

        initObject();

    }

    private void initObject() {
        mNisn = (EditText) findViewById(R.id.nisn_box);
        mPassword = (EditText) findViewById(R.id.password_box);
        loginBtn = (Button) findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                putData();
                break;
        }
    }

    private void putData() {
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.LOGIN);
        map.put(Template.Query.NISN, mNisn.getText().toString());
        map.put(Template.Query.PASSWORD, mPassword.getText().toString());
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST, map);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onSucces(JSONObject jsonObject) {
        mJSONParser.isSuccess(jsonObject);
    }

    @Override
    public void onFailed(VolleyError errorListener) {
        Toast.makeText(this, "Error : " + errorListener, Toast.LENGTH_SHORT).show();
    }
}
