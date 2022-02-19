package com.example.apistest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
@SuppressLint("SetTextI18n")


public class Test3 extends AppCompatActivity implements SensorEventListener{

    private TextView coor;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        Button button_back = findViewById(R.id.back);
        button_back.setOnClickListener(v -> onBackPressed());

        coor = findViewById(R.id.coor);

        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener( this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        coor.setText("x: " + event.values[0] + ", y: " + event.values[1] + ", z: " + event.values[2] );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}