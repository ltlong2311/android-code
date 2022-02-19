package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_next = (Button) findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next();
            }
        });
    }
    public void Next(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}