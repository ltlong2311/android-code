package com.example.apistest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Test8 extends AppCompatActivity {

    private SoundPool soundPool;
    Button button1;
    int sound1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test8);

        Button button_back = findViewById(R.id.back);
        button_back.setOnClickListener(v -> onBackPressed());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        sound1 = soundPool.load(this, R.raw.music, 1);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            soundPool.play(sound1, 1, 1, 1, 10, 1);
        }
        return super.onTouchEvent(event);
    }
}