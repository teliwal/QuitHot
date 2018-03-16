package fr.quithot.com.quithot.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

import fr.quithot.com.quithot.domain.TiltType;

/**
 * Created by telly on 16/03/18.
 */

public class OrientationListener implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private long mShakeTimestamp;
    private int mShakeCount;
    private TiltType tiltType;

    OrientationConsumer consumer;
    private final float[] mAccelerometerReading = new float[3];

    public OrientationListener(OrientationConsumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
           /* System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);*/
           if(isShake(event.values)){
               consumer.notifierShake();
               mShakeCount = 0;
           }
        } else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            if(isTilt(event.values)){
                consumer.notifierTilt(tiltType);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private boolean isTilt(float[] tab){
        float x = tab[0];
        float y = tab[1];
        if (x > 0.5f && y > 0.5f) {
            tiltType = TiltType.HAUT_DROITE;
            return true;
        }
        else if(x > 0.5f && y < -0.5f){
            tiltType = TiltType.BAS_DROIT;
            return true;
        }
        if(x < -0.5f && y > 0.5f){
            tiltType = TiltType.HAUT_GAUCHE;
            return true;
        }
        if(x < -0.5f && y < -0.5f){
            tiltType = TiltType.HAUT_DROITE;
            return true;
        }
        if (y > 0.5f) {
            tiltType = TiltType.HAUT;
            return  true;
        }
        if(y < -0.5f){
            tiltType = TiltType.BAS;
            return true;
        }
        if (x > 0.5f) {
            tiltType = TiltType.DROITE;
            return true;
        }
        if (x < -0.5f) {
            tiltType = TiltType.GAUCHE;
            return true;
        }
        return  false;
    }

    private boolean isShake(float[] tab) {
        float x = tab[0];
        float y = tab[1];
        float z = tab[2];
        float gX = x / SensorManager.GRAVITY_EARTH;
        float gY = y / SensorManager.GRAVITY_EARTH;
        float gZ = z / SensorManager.GRAVITY_EARTH;
        float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            final long now = System.currentTimeMillis();
            // ignore shake events too close to each other (500ms)
            if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                return false;
            }
            // reset the shake count after 3 seconds of no shakes
            if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                mShakeCount = 0;
            }
            mShakeTimestamp = now;
            mShakeCount++;
        }
        return  mShakeCount > 5;
    }
}
