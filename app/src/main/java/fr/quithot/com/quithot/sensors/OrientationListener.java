package fr.quithot.com.quithot.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by telly on 16/03/18.
 */

public class OrientationListener implements SensorEventListener {

    OrientationConsumer consumer;
    private final float[] mAccelerometerReading = new float[3];

    OrientationListener(OrientationConsumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
            consumer.notifierOrientation();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
