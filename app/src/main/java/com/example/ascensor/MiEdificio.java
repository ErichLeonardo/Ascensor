package com.example.ascensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MiEdificio extends View {
    private Paint paint;

    public MiEdificio(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Dibuja el edificio
        canvas.drawRect(50, 50, width - 50, height - 50, paint);

        // Dibuja las divisiones de los pisos
        int numPisos = 4;
        int pisoHeight = (height - 100) / numPisos;
        paint.setColor(Color.BLACK);
        for (int i = 1; i < numPisos; i++) {
            int y = 50 + i * pisoHeight;
            canvas.drawLine(50, y, width - 50, y, paint);
        }
    }
}

