package com.example.ascensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MiEdificio extends View {
    private Paint paint;
    private Paint grayPaint;
    private int width, height;
    private int numPisos = 4; // Cambiado a 4 pisos
    private int pisoHeight;

    private float currentHeight; // Altura actual del círculo

    public MiEdificio(Context context) {
        super(context);
        init();
    }

    public MiEdificio(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        grayPaint = new Paint();
        grayPaint.setColor(Color.GRAY);
        grayPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Dibuja el contorno del edificio
        canvas.drawRect(50, 50, width - 50, height - 50, paint);

        // Rellena la planta que coincide con la altura del círculo con color gris
        int currentFloor = (int) ((currentHeight - 50) / (height - 100) * numPisos);
        if (currentFloor >= 0 && currentFloor < numPisos) {
            int startY = (int) (50 + currentFloor * pisoHeight);
            canvas.drawRect(50, startY, width - 50, startY + pisoHeight, grayPaint);
        }

        // Dibuja las divisiones de los pisos
        for (int i = 1; i < numPisos; i++) {
            int y = 50 + i * pisoHeight;
            canvas.drawLine(50, y, width - 50, y, paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        pisoHeight = (height - 100) / numPisos;
    }

    public void updateCircleHeight(float height) {
        this.currentHeight = height;
        invalidate();
    }
}
