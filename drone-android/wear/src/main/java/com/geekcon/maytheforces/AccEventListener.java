package com.geekcon.maytheforces;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by Pavel 'PK' Kaminsky on 09/2015.
 */
public class AccEventListener implements SensorEventListener {

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private static final int SAMPLE_PER_SECOND = 3;
    private DroneProxyService droneService;


    public AccEventListener(DroneProxyService droneService) {
        this.droneService = droneService;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 1000 / SAMPLE_PER_SECOND) {
                String data = String.format("%.2f", x) + "/" + String.format("%.2f", y) + "/" + String.format("%.2f", z);
                droneService.message("xyz", data);

                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
