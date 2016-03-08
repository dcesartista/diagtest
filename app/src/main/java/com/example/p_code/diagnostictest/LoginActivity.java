package com.example.p_code.diagnostictest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.EndPointAPI;
import com.example.p_code.diagnostictest.Template.Template;
import com.example.p_code.diagnostictest.Utils.JSONParser;
import com.example.p_code.diagnostictest.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, VolleyInterface{

    EditText mNisn;
    EditText mPassword;
    Button loginBtn;
    TextView signupBtn;
    ProgressBar mProgress;
    ProgressDialog progressDialog;

    public VolleyRequest mRequest;
    public JSONParser mJSONParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mJSONParser = new JSONParser(this);
        mRequest = new VolleyRequest(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        initObject();

    }

    private void initObject() {
        mNisn = (EditText) findViewById(R.id.nisn_box);
        mPassword = (EditText) findViewById(R.id.password_box);
        loginBtn = (Button) findViewById(R.id.login_btn);
        signupBtn = (TextView) findViewById(R.id.signup_btn);
        mProgress = (ProgressBar) findViewById(R.id.login_progress);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging In...");
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                putData();
                break;

            case R.id.signup_btn:
                signUp();
                break;
        }
    }

    private void signUp() {
        finish();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void putData() {
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.LOGIN);
        map.put(Template.Query.NISN, mNisn.getText().toString());
        map.put(Template.Query.PASSWORD, mPassword.getText().toString());
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST, map);
    }

    private void onProgress(boolean isLoading) {
        if(isLoading == true) {
            mProgress.setVisibility(ProgressBar.VISIBLE);
        } else {
            mProgress.setVisibility(ProgressBar.GONE);
        }
    }

    private void switchToApplication() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPrepare() {
        onProgress(false);
    }

    @Override
    public void onSucces(JSONObject jsonObject) {
        onProgress(false);
        progressDialog.dismiss();
        mJSONParser.isSuccess(jsonObject);
        switchToApplication();
    }

    @Override
    public void onFailed(VolleyError errorListener) {
        onProgress(false);
        progressDialog.dismiss();
        Toast.makeText(this, "Error : " + errorListener, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
