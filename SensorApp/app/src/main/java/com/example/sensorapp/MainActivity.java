package com.example.sensorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

//public class MainActivity extends AppCompatActivity implements SensorEventListener {
public class MainActivity extends AppCompatActivity {
    private TextView txtX, txtY, txtZ;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isAccSensorAvailable, isNotFirstTime = false;
    private float currentX, currentY, currentZ, lastX, lastY, lastZ;
    private float xDifference, yDifference, zDifference;
    private float shakeThreshold = 5f;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView msg_txt = findViewById(R.id.txt_msg);
        Button login_btn = findViewById(R.id.login_btn);

//        txtX = findViewById(R.id.currentX);
//        txtY = findViewById(R.id.currentY);
//        txtZ = findViewById(R.id.currentZ);

        BiometricManager biometricManager =  BiometricManager.from(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_SUCCESS:
                    msg_txt.setText("Dùng vân tay để đăng nhập");
                    msg_txt.setTextColor(Color.parseColor("#fafafa"));
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    msg_txt.setText("Thiết bị của bạn không có cảm biến vân tay");
                    login_btn.setVisibility(View.GONE);
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    msg_txt.setText("Cảm biến không khả dụng");
                    login_btn.setVisibility(View.GONE);
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    msg_txt.setText("Thiết bị không có vân tay nào được lưu");
                    login_btn.setVisibility(View.GONE);
                    break;
            }
        }

        Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt =  new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),"Login success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Dùng vân tay để đăng nhập")
                .setNegativeButtonText("Cancel")
                .build();

        login_btn.setOnClickListener(v -> {
          biometricPrompt.authenticate(promptInfo);
        });

//        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//            txtY.setText("Lắc để chuyển trang!");
//        }else {
//            txtX.setText("Cảm biến gia tốc không có sẵn!");
//        }

    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        Toast.makeText(getApplicationContext(), String.valueOf(event.values[0]), Toast.LENGTH_SHORT).show();
////        txtX.setText(event.values[0] + "m/s2");
////        txtY.setText(event.values[1] + "m/s2");
////        txtZ.setText(event.values[2] + "m/s2");
//
////        if(isNotFirstTime) {
////            xDifference = Math.abs(lastX - currentX);
////            yDifference = Math.abs(lastY - currentY);
////            zDifference = Math.abs(lastZ - currentZ);
////            if((xDifference > shakeThreshold && yDifference > shakeThreshold ) ||
////                 (xDifference > shakeThreshold && zDifference > shakeThreshold) ||
////                    (yDifference > shakeThreshold && zDifference > shakeThreshold)){
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
////                    Intent intent = new Intent(this,NextPageSensor.class);
////                    startActivity(intent);
////                } else {
////                    vibrator.vibrate(500);
////                }
////            }
////
////        }
////
////        currentX = event.values[0];
////        currentY = event.values[1];
////        currentZ = event.values[2];
////
////        lastX = currentX;
////        lastY = currentY;
////        lastZ = currentZ;
////        isNotFirstTime = true;
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        Intent intent= new Intent(MainActivity.this,NextPageSensor.class);
//        startActivity(intent);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(isAccSensorAvailable){
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(isAccSensorAvailable){
//            sensorManager.unregisterListener(this);
//        }
//    }
}