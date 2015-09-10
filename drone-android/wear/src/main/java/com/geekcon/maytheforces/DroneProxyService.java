package com.geekcon.maytheforces;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.POST;

/**
 * Created by Pavel 'PK' Kaminsky on 09/2015.
 */
public interface DroneProxyService {
    String SERVER_URL = "https://api.github.com";

    @POST("/takeoff")
    Call takeoff();

    @POST("/land")
    Call land();

    @POST("/stop")
    Call stop();
}
