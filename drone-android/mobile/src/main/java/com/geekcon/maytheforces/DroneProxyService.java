package com.geekcon.maytheforces;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Pavel 'PK' Kaminsky on 09/2015.
 */
public class DroneProxyService {

    RequestQueue queue;
    String serverUrl;

    public DroneProxyService(Context ctx, String serverUrl) {
        queue = Volley.newRequestQueue(ctx);
        this.serverUrl = serverUrl;
    }


    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    private void call(int type, String url) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(type, serverUrl + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    private void getCall(String url) {
        call(Request.Method.GET, url);
    }

    private void postCall(String url) {
        call(Request.Method.POST, url);
    }

    public void test() {
        getCall("/");
    }

    public void takeoff() {
        postCall("/takeoff");
    }

    public void land() {
        postCall("/land");
    }

    public void stop() {
        postCall("/stop");
    }

    public void xyz(String data) {
        try {
            String arr[] = data.split("/");

            String x = arr[0];
            String y = arr[1];
            String z = arr[2];

            postCall("/watchControl/" + x + "/" + y + "/" + z);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
