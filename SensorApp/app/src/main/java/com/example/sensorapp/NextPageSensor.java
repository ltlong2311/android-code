package com.example.sensorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class NextPageSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isAccSensorAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page_sensor);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        }else {
            Toast.makeText(getApplicationContext(),"Lỗi cảm biến!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Intent intent= new Intent(NextPageSensor.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isAccSensorAvailable){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isAccSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}