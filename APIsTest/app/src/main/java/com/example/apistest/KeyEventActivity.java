package com.example.apistest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("SetTextI18n")
public class KeyEventActivity extends AppCompatActivity {

    TextView key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        key = findViewById(R.id.keyInfo);


        Button button_back = findViewById(R.id.back);
        button_back.setOnClickListener(v -> onBackPressed());
        key.setText("press key!");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        StringBuilder sb = new StringBuilder(" ");
        String st = "Key up: " + event.getKeyCode() + ", " + event.getUnicodeChar() + ", " + (char) event.getUnicodeChar();
        sb.append(st);
        String txt = sb.toString();
        key.setText(txt);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        StringBuilder sb = new StringBuilder(" ");
        String st = "Key down: " + event.getKeyCode() + ", " + event.getUnicodeChar() + ", " + (char) event.getUnicodeChar();
        sb.append(st);
        String txt = sb.toString();
        key.setText(txt);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        String txt = " " + "Key long press: ";
        key.setText(txt);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        String txt = " " + "Key multiple: ";
        key.setText(txt);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        String txt = " " + "Key shortcut: ";
        key.setText(txt);
        return super.onKeyShortcut(keyCode, event);
    }
}