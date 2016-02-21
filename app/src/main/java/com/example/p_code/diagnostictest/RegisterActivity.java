package com.example.p_code.diagnostictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nisnBox, nameBox, passwordBox, confirmBox;
    Button signupBtn;
    String nisn, name, password, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initObject();
    }

    private void initObject() {
        nisnBox = (EditText) findViewById(R.id.nisn_box);
        nameBox = (EditText) findViewById(R.id.nama_box);
        passwordBox = (EditText) findViewById(R.id.password_box);
        confirmBox = (EditText) findViewById(R.id.confirm_box);

        signupBtn = (Button) findViewById(R.id.signup_btn);
    }

    private void checkForm() {
        nisn = nisnBox.getText().toString();
        name = nameBox.getText().toString();
        password = passwordBox.getText().toString();
        confirm = confirmBox.getText().toString();

        if (nisn == "" && name == "" && password == "" && confirm == "") {
            Toast.makeText(this, "Please complete your registration by filling the form", Toast.LENGTH_SHORT).show();
        } else if (name == "" && password == "" && confirm == "") {
            Toast.makeText(this, "Please complete your registration by filling the form", Toast.LENGTH_SHORT).show();
        } else if (password == "" && confirm == "") {
            Toast.makeText(this, "Please complete your registration by filling the form", Toast.LENGTH_SHORT).show();
        } else if (password != "" && confirm != ""){
            if (password != confirm) {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Signing Up", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_btn:
                checkForm();
        }
    }
}
