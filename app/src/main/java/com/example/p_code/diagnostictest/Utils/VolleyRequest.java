package com.example.p_code.diagnostictest.Utils;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.Template;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by P-CODE on 2/13/2016.
 */
public class VolleyRequest {
    private RequestQueue mRequestQueue;
    private CustomRequest mCustomRequest;
    private JsonObjectRequest mJsonObjectRequest;
    private VolleyInterface mVolleyInterface;

    public VolleyRequest(VolleyInterface volleyInterface) {
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        mVolleyInterface = volleyInterface;
    }

    public void sendGetRequest(String endPointAPI) {
        mVolleyInterface.onPrepare();
        mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                endPointAPI,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mVolleyInterface.onSuccess(response);
                        Log.i("Success", response + "");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mVolleyInterface.onFailed(error);

            }
        });

        mJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(Template.VolleyRetryPolicy.SOCKET_TIMEOUT,
                Template.VolleyRetryPolicy.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mJsonObjectRequest);
    }

    public void sendPostRequest(String endPointAPI, Map<String, String> params) {
        mVolleyInterface.onPrepare();
        mCustomRequest = new CustomRequest(Request.Method.POST, endPointAPI, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mVolleyInterface.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mVolleyInterface.onFailed(error);
            }
        });
        mCustomRequest.setRetryPolicy(new DefaultRetryPolicy(Template.VolleyRetryPolicy.SOCKET_TIMEOUT,
                Template.VolleyRetryPolicy.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mCustomRequest);
    }

    public void cancelAllRequest() {
        if (mCustomRequest != null)
            mRequestQueue.cancelAll(mCustomRequest);
        if (mJsonObjectRequest != null)
            mRequestQueue.cancelAll(mJsonObjectRequest);
    }
}
