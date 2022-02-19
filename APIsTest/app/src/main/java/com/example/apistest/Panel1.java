package com.example.apistest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
@SuppressLint("DrawAllocation")

public class Panel1 extends View {

    public static Timer timer = new Timer();
    protected static TimerTask flash;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);   // Paint.ANTI_ALIAS_FLAG vẽ mượt cho các biên của các đối tượng (khử răng cưa).

    public Panel1(Context context) {
        this(context, null);
    }
    public Panel1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Panel1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        // center
        float left = (getWidth() - width) / 2.0f;
        float top = (getHeight() - height) / 2.0f;

        new Handler().postDelayed(() -> {
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256),random.nextInt(256));
            mPaint.setColor(color);
            invalidate();
        }, 900);
        canvas.drawRect(left, top, left + width, top + height, mPaint);
    }

    private void initPaint() {
        mPaint.setStrokeWidth(30);
    }
}
