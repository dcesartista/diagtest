package com.example.p_code.diagnostictest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, VolleyInterface{

    EditText nisnBox, nameBox, passwordBox, confirmBox;
    Button signupBtn;
    String nisn, name, password, confirm;
    ProgressBar mProgress;

    public VolleyRequest mRequest;
    public JSONParser mJSONParser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mJSONParser = new JSONParser(this);
        mRequest = new VolleyRequest(this);

        initObject();
    }

    private void initObject() {
        nisnBox = (EditText) findViewById(R.id.nisn_box);
        nameBox = (EditText) findViewById(R.id.nama_box);
        passwordBox = (EditText) findViewById(R.id.password_box);
        confirmBox = (EditText) findViewById(R.id.confirm_box);
        //mProgress = (ProgressBar) findViewById(R.id.login_progress);

        signupBtn = (Button) findViewById(R.id.signup_btn);

        signupBtn.setOnClickListener(this);
    }

    private void checkForm() {
        nisn = nisnBox.getText().toString();
        name = nameBox.getText().toString();
        password = passwordBox.getText().toString();
        confirm = confirmBox.getText().toString();
    }

    private void signUp() {
        checkForm();
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.SIGNUP);
        map.put(Template.Query.NISN, nisn);
        map.put(Template.Query.NAMA, name);
        map.put(Template.Query.PASSWORD, password);
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST, map);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_btn:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging In...");
                progressDialog.setIndeterminate(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mRequest.cancelAllRequest();
                    }
                });
                signUp();
        }
    }

    private void onProgress(boolean isLoading) {
//        if(isLoading == true) {
//            mProgress.setVisibility(ProgressBar.VISIBLE);
//        } else {
//            mProgress.setVisibility(ProgressBar.GONE);
//        }
    }

    private void switchToApplication() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPrepare() {
        onProgress(false);
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        onProgress(false);
        progressDialog.dismiss();
        mJSONParser.isSuccess(jsonObject, this);
        //Toast.makeText(this, Data.notification, Toast.LENGTH_LONG).show();
        if (Data.notification.equals("Sign Up Successful")) {
            switchToApplication();
        }

    }

    @Override
    public void onFailed(VolleyError errorListener) {
        onProgress(false);
        Toast.makeText(this, "Sign Up gagal! Pastikan anda terhubung dengan internet!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
