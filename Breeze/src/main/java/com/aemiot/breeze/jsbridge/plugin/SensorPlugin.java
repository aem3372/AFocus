package com.aemiot.breeze.jsbridge.plugin;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aemiot.breeze.jsbridge.HybridPlugin;
import com.aemiot.breeze.jsbridge.CallMethodContext;

public class SensorPlugin implements HybridPlugin {
    @NonNull
    Context mContext;
    @Nullable
    Sensor mAccelerometerSensor;
    @Nullable
    SensorEventListener mAccelerometerSensorListener;

    private float mAccelerometerX;
    private float mAccelerometerY;
    private float mAccelerometerZ;

    public SensorPlugin(@NonNull Context context) {
        mContext = context;
    }

    public void openAccelerometerSensor() {
        SensorManager sm = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccelerometerSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                mAccelerometerX = event.values[0];
                mAccelerometerY = event.values[1];
                mAccelerometerZ = event.values[2];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sm.registerListener(mAccelerometerSensorListener, sensor, SensorManager.SENSOR_DELAY_GAME);
        mAccelerometerSensor =  sensor;
    }

    public void closeAccelerometerSensor() {
        if(mAccelerometerSensor != null && mAccelerometerSensorListener != null) {
            SensorManager sm = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
            sm.unregisterListener(mAccelerometerSensorListener, mAccelerometerSensor);
        }
    }

    public float getAccelerometerX() {
        return mAccelerometerX;
    }

    public float getAccelerometerY() {
        return mAccelerometerY;
    }

    public float getAccelerometerZ() {
        return mAccelerometerZ;
    }

    @Override
    public void execute(String method, String params, CallMethodContext jsContext) {
        if("openAccelerometerSensor".equals(method)) {
            openAccelerometerSensor();
        } else if("closeAccelerometerSensor".equals(method)) {
            closeAccelerometerSensor();
        } else if("getAccelerometerX".equals(method)) {
            getAccelerometerX();
        } else if("getAccelerometerY".equals(method)) {
            getAccelerometerY();
        } else if("getAccelerometerZ".equals(method)) {
            getAccelerometerZ();
        }
    }
}
