package com.example.ascensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MiCirculo extends View {
    private Paint paint;
    private float centerX, centerY;
    private float radius;
    private boolean isDragging = false;
    private float lastY;
    private float currentHeight;
    private MainActivity activity;

    public MiCirculo(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (MainActivity) context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
    }

    public float getRadius() {
        return radius;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2f;
        centerY = h / 2f;
        radius = Math.min(w, h) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchY = event.getY();
        currentHeight = centerY + touchY;
        activity.updateCircleHeight(currentHeight);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float distance = (float) Math.sqrt(Math.pow(centerX - event.getX(), 2) + Math.pow(centerY - touchY, 2));
                if (distance < radius) {
                    isDragging = true;
                    lastY = touchY;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    float deltaY = touchY - lastY;
                    centerY += deltaY;
                    lastY = touchY;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                break;
        }
        return true;
    }
}
