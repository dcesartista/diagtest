package com.example.p_code.diagnostictest.Utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by P-CODE on 2/13/2016.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MainApplication.getContext());
    }

    public static VolleySingleton getInstance() {
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
