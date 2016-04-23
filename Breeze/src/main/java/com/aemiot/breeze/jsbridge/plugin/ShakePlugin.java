package com.aemiot.breeze.jsbridge.plugin;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aemiot.breeze.jsbridge.BREvent;
import com.aemiot.breeze.jsbridge.HybridPlugin;
import com.aemiot.breeze.jsbridge.CallMethodContext;
import com.aemiot.breeze.jsbridge.JSBridge;

public class ShakePlugin extends HybridPlugin {

    Sensor mAccelerometerSensor;
    @Nullable
    SensorEventListener mAccelerometerSensorListener;

    private static final double SPEED_THRESHOLD = 10.0D;
    protected long TIME_INTERVAL_THRESHOLD = 1000L;
    private float mLastX;
    private float mLastY;
    private float mLastZ;
    private long mLastUpdateTime;

    public void open() {
        SensorManager sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccelerometerSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                long currentTime = System.currentTimeMillis();
                long timeInterval = currentTime - mLastUpdateTime;
                if(timeInterval >= TIME_INTERVAL_THRESHOLD) {
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];
                    float deltaX = x - mLastX;
                    float deltaY = y - mLastY;
                    float deltaZ = z - mLastZ;
                    double speed = Math.sqrt((double)
                            (deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ));
                    if(speed > SPEED_THRESHOLD && Math.abs(mLastX) > 0.0F && Math.abs(mLastY) > 0.0F
                            && Math.abs(mLastZ) > 0.0F) {
                        getBridge().fire(new BREvent("BR_SHAKE"));
                    }
                    // update
                    mLastUpdateTime = currentTime;
                    mLastX = x;
                    mLastY = y;
                    mLastZ = z;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sm.registerListener(mAccelerometerSensorListener, sensor, SensorManager.SENSOR_DELAY_GAME);
        mAccelerometerSensor =  sensor;
    }

    public void close() {
        if(mAccelerometerSensor != null && mAccelerometerSensorListener != null) {
            SensorManager sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
            sm.unregisterListener(mAccelerometerSensorListener, mAccelerometerSensor);
            mAccelerometerSensor = null;
            mAccelerometerSensorListener = null;
        }
    }

    @Override
    public void onDetach() {
        if(mAccelerometerSensor != null && mAccelerometerSensorListener != null) {
            SensorManager sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
            sm.unregisterListener(mAccelerometerSensorListener, mAccelerometerSensor);
            mAccelerometerSensor = null;
            mAccelerometerSensorListener = null;
        }
    }

    @Override
    public void execute(String method, String params, CallMethodContext jsContext) {
        if("open".equals(method)) {
            open();
        } else if("close".equals(method)) {
            close();
        }
        jsContext.success();
    }
}
