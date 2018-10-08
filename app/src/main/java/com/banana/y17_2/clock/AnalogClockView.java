package com.banana.y17_2.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AnalogClockView extends View {

    public Context mContext;
    public int mWidth;
    public int mHeight;
    public float mCenterX;
    public float mCenterY;


    public Paint bgPaint;
    public Paint HUDPaint;
    public Paint SecondArrowPaint;
    public Paint ArrowPaint;

    public Calendar mCalendar;



//ПЕРЕДЕЛАТЬ
    public AnalogClockView(Context context) {
        super(context);
        mContext = getContext();
        final Handler handler = new Handler();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //TODO
                                initialize();
                            }
                        });
                    }
                }, 1000, 1000);
            }


    public AnalogClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public AnalogClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AnalogClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resize(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint(canvas);
    }


    public void initialize(){
        int colorClockMain = this.getResources().getColor(R.color.colorClockMain);
        int colorClockBg = this.getResources().getColor(R.color.colorClockBg);
        int colorSecondArrow = this.getResources().getColor(R.color.colorSecondArrow);
        mCalendar = Calendar.getInstance();


        HUDPaint = new Paint();
        bgPaint = new Paint();
        SecondArrowPaint = new Paint();
        ArrowPaint = new Paint();


        HUDPaint.setStyle(Paint.Style.STROKE);
        HUDPaint.setStrokeWidth(8);
        HUDPaint.setPathEffect(new DashPathEffect(new float[] {1, 5}, 0));
        HUDPaint.setAntiAlias(true);
        HUDPaint.setColor(colorClockMain);


        ArrowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ArrowPaint.setStrokeWidth(8);
        ArrowPaint.setAntiAlias(true);
        ArrowPaint.setColor(colorClockMain);

        SecondArrowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        SecondArrowPaint.setStrokeWidth(8);
        SecondArrowPaint.setAntiAlias(true);
        SecondArrowPaint.setColor(colorSecondArrow);

    }


    public void paint(Canvas canvas){
        final long now = System.currentTimeMillis();
        mCalendar.setTimeInMillis(now);

        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 - 64, HUDPaint);

        final int hours = mCalendar.get(Calendar.HOUR);
        final int minutes = mCalendar.get(Calendar.MINUTE);
        final int seconds = mCalendar.get(Calendar.SECOND);

        final float hourDegrees = 30 * hours + 0.5f * minutes;
        final float secondDegrees = 6 * seconds;

        canvas.save();
        canvas.rotate(-hourDegrees, mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY,  mCenterY, mCenterY - 54, ArrowPaint);
        canvas.rotate(-hourDegrees + secondDegrees, mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY,  mCenterY, mCenterY - 100, SecondArrowPaint);
        canvas.restore();


    }


    public void resize(int width, int height){
        mWidth = width;
        mHeight = height;
        mCenterX = width / 2;
        mCenterY = height / 2;
    }

}
