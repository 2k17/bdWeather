package com.example.abuzafarnewton.weatherapp;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Abu Zafar Newton on 11/11/2016.
 */

public class AppController extends Application{
    private static AppController instance;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static AppController getInstance(){
        return instance;
    }
    private RequestQueue getRequestQueue(){
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(this);
        return requestQueue;
    }

    public synchronized void addToRequestQueue(Request request){
        getRequestQueue().add(request);
    }
}
