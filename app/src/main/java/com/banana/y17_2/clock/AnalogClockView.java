package com.banana.y17_2.clock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class AnalogClockView extends View {

    int colorClockMain;
    int colorClockBg;
    int colorSecondArrow;

    public Context mContext;
    public int mWidth;
    public int mHeight;
    public float mCenterX;
    public float mCenterY;
    public float mRadius;


    public Paint bgPaint;
    public Paint HUDPaint;
    public Paint SecondArrowPaint;
    public Paint ArrowPaint;

    public Calendar mCalendar;



//ПЕРЕДЕЛАТЬ

    public AnalogClockView(Context context) {
        super(context);
        initialize(context, null);
    }


    public AnalogClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public AnalogClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AnalogClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
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


    public void initialize(Context context, AttributeSet attrs){
        mCalendar = Calendar.getInstance();
        //Считываем аргументы
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnalogClockView);

        for(int i = 0; i < typedArray.length(); i++) {
            final int id = typedArray.getIndex(i);
            switch (id) {
                case R.styleable.AnalogClockView_Tiget_timeZone:
                    final String timeZone = typedArray.getString(id);
                    setTimeZone(TimeZone.getTimeZone(timeZone));
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();

        colorClockMain = this.getResources().getColor(R.color.colorClockMain);
        colorClockBg = this.getResources().getColor(R.color.colorClockBg);
        colorSecondArrow = this.getResources().getColor(R.color.colorSecondArrow);


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


        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();

                    }
                });
            }
        };
        timer.schedule(timerTask, 1000,1000);

    }


    public void paint(Canvas canvas){
        final long now = System.currentTimeMillis();
        mCalendar.setTimeInMillis(now);

        canvas.drawCircle(mCenterX, mCenterY, mRadius, HUDPaint);

        final int hours = mCalendar.get(Calendar.HOUR);
        final int minutes = mCalendar.get(Calendar.MINUTE);
        final int seconds = mCalendar.get(Calendar.SECOND);

        final float hourDegrees = 30 * hours + 0.5f * minutes;
        final float minuteDegrees = 6 * minutes;
        final float secondDegrees = 6 * seconds;


        canvas.save();
        drawHand(canvas, HandType.HOURS_ARROW, hourDegrees);
        drawHand(canvas, HandType.SECOND_ARROW, -hourDegrees + secondDegrees);
        drawHand(canvas, HandType.MINUTE_ARROW, -secondDegrees + minuteDegrees);
        }

    public void setTimeZone(TimeZone value) {
        mCalendar.setTimeZone(value);
    }


    public enum HandType{

        HOURS_ARROW(50), MINUTE_ARROW(100), SECOND_ARROW(130);
        private final int mlenght;

        HandType(int lenght) {
            mlenght = lenght;
        }

        public float getLenght(){
            return mlenght;
        }
    }

    public void drawHand(Canvas canvas, HandType handType, float angle) {
        canvas.rotate(angle, mCenterX, mCenterY);


        if(handType.equals(HandType.HOURS_ARROW) | handType.equals(HandType.MINUTE_ARROW)) {
            canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - handType.getLenght(), ArrowPaint);
        } else if(handType.equals(HandType.SECOND_ARROW)){
            canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY - handType.getLenght(), SecondArrowPaint);
        }
    }


    public void resize(int width, int height){
        mWidth = width;
        mHeight = height;
        mCenterX = width / 2;
        mCenterY = height / 2;
        mRadius = Math.min(mCenterX, mCenterY) - 4;
    }


    
}
