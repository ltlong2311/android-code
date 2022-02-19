package com.example.apistest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class Test4 extends AppCompatActivity {

    String str = "";
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        text = findViewById(R.id.text1);
        Button button_back = findViewById(R.id.back);
        button_back.setOnClickListener(v -> onBackPressed());

        try {
            InputStream inputStream = getAssets().open("text.txt");
            int size = inputStream.available();
            byte[] buffer =  new byte[size];
            inputStream.read(buffer);

            str = new String(buffer);
        } catch (Exception e){
            e.printStackTrace();
        }

        text.setText(str);

    }
}