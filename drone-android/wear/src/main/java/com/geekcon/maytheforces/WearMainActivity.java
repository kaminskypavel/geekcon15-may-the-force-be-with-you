package com.geekcon.maytheforces;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WearMainActivity extends WearableActivity {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private SensorEventListener sensorListener;
    private DroneProxyService droneService;

    @OnClick(R.id.land_button)
    public void land() {
        droneService.message("land");
    }

    @OnClick(R.id.takeoff_button)
    public void takeoff() {
        droneService.message("takeoff");
    }

    @OnClick(R.id.stop_button)
    public void stop() {
        droneService.message("stop");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.bind(this);
        droneService = new DroneProxyService(this);
        sensorListener = new AccEventListener(droneService);

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
