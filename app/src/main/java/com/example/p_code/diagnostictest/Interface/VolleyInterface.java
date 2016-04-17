package com.example.p_code.diagnostictest.Interface;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by P-CODE on 2/13/2016.
 */
public interface VolleyInterface {
    void onPrepare();
    void onSuccess(JSONObject jsonObject);
    void onFailed(VolleyError errorListener);
}
