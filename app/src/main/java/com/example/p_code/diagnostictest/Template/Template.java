package com.example.p_code.diagnostictest.Template;

/**
 * Created by P-CODE on 2/13/2016.
 */
public interface Template {
    interface VolleyRetryPolicy {
        int SOCKET_TIMEOUT = 10000;
        int RETRIES = 1;
    }

    interface Query {
        String TAG = "tag";
        String NISN = "nisn";
        String NAMA = "nama";
        String PASSWORD = "password";

        String LOGIN = "login";
        String SIGNUP = "signUp";
        String JAWABAN = "jawaban";
        String SOAL = "soal";
        String UPDATE = "update";
        String DELETE = "delete";
        String ANSWER = "answer";
    }
}
