package com.example.apistest;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Panel3 extends View  {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Panel3(Context context) {
        super(context);
    }

    public Panel3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Panel3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.parseColor("#000"));
        mPaint.setStrokeWidth(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        // center
        float left = (getWidth() - width) / 2.0f;
        float top = (getHeight() - height) / 2.0f;

        canvas.drawRect(left, top, left + width, top + height, mPaint);
        drawText(canvas);
        drawImage(canvas);
        invalidate();
    }

    private void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(90);
        Typeface type = Typeface.createFromAsset(getContext().getAssets(),"LuxuriousScript-Regular.ttf");
        paint.setTypeface(type);
        paint.setColor(Color.parseColor("#cf152d"));
        canvas.drawText("Thử nghiệm lớp Typeface", 200, 200, paint);

        Paint paint1 = new Paint();
        paint1.setTextSize(80);
        paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint1.setColor(Color.parseColor("#1f87ff"));
        canvas.drawText("Vẽ văn bản trên dòng khác!", 290, 390, paint1);
    }

    private void drawImage(Canvas canvas) {
        Paint paint = new Paint();
        Resources res = getResources();
        Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.bobrgb888);
        canvas.drawBitmap(bitmap1, 300,800, paint);
//        canvas.drawBitmap(bitmap1, new Rect(0,0,100, 200), paint1, paint);
        AssetManager assetManager = getContext().getAssets();
        try {
            InputStream inputStream = assetManager.open("bobargb8888.png");
            Bitmap bitmap2 = BitmapFactory.decodeStream(inputStream);
            canvas.drawBitmap(bitmap2, 300,800, paint);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
