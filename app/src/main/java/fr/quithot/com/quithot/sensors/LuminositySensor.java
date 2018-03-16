package fr.quithot.com.quithot.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by telly on 16/03/18.
 */

public class LuminositySensor implements SensorEventListener{

    LuminosityConsumer consumer;
    int currentLuminosity = -1;

    public LuminositySensor(LuminosityConsumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            if(currentLuminosity != -1){
                int temp = (int)sensorEvent.values[0];
                if((currentLuminosity-temp)/100 >= 0.7){
                    consumer.notifierLuminosity();
                }
            }
            currentLuminosity = (int)sensorEvent.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
