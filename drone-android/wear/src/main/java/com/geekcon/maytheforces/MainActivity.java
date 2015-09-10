package com.geekcon.maytheforces;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Retrofit;

public class MainActivity extends WearableActivity {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private SensorEventListener sensorListener;
    private DroneProxyService droneService;

    @OnClick(R.id.land_button)
    public void land() {
        droneService.land();
    }

    @OnClick(R.id.takeoff_button)
    public void takeoff() {
        droneService.takeoff();
    }

    @OnClick(R.id.stop_button)
    public void stop() {
        droneService.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DroneProxyService.SERVER_URL).build();
        droneService = retrofit.create(DroneProxyService.class);

        sensorListener = new AccEventListener();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(sensorListener, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        System.out.println("Started...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(sensorListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
