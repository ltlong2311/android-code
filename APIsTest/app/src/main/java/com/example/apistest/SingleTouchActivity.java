package com.example.apistest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class SingleTouchActivity extends AppCompatActivity {
    TextView touchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        touchInfo= findViewById(R.id.touchInfo);
        Button button_back = findViewById(R.id.back);
        button_back.setOnClickListener(v -> onBackPressed());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case (MotionEvent.ACTION_DOWN) :
                touchInfo.setText("Action was DOWN: " + event.getX() + ", " + event.getY());
                return true;
            case (MotionEvent.ACTION_MOVE) :
                touchInfo.setText("Action was MOVE: "+ event.getX()+ ", " + event.getY());
                return true;
            case (MotionEvent.ACTION_UP) :
                touchInfo.setText("Action was UP: "+ event.getX()+ ", " + event.getY());
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                touchInfo.setText("Action was CANCEL: "+ event.getX()+ ", " + event.getY());
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                touchInfo.setText("Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}