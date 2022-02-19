package com.example.apistest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Random;

public class Panel2 extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Panel2(Context context) {
        super(context);
        initPaint();
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.parseColor("#6bb5fc"));
        mPaint2.setStrokeWidth(30);

        mPaint3.setColor(Color.parseColor("#f51d1d"));
        mPaint3.setStrokeWidth(15);
    }

    public Panel2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Panel2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float radius = 50.0f;
        float cx = (getWidth ()) / 2.0f;
        float cy = (getHeight()) / 2.0f;
        canvas.drawCircle(cx, cy, radius, mPaint);
        canvas.drawRect(150,150, 350, 350, mPaint2);
        canvas.drawLine(0, 0, getWidth(), getHeight(), mPaint3);
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#ffc212"));
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
