package com.example.ascensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MiEdificio extends View {
    private Paint paint;
    private int numPisos = 4; // Número de pisos en el edificio
    private float currentHeight = 0; // Altura actual del círculo

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
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calcula el tamaño de un piso
        int pisoHeight = getHeight() / numPisos;

        // Dibuja los pisos del edificio
        for (int i = 0; i < numPisos; i++) {
            float startY = i * pisoHeight;
            float endY = startY + pisoHeight;
            if (currentHeight >= startY && currentHeight < endY) {
                paint.setColor(Color.RED); // Pinta el piso actual de rojo si el círculo está en este piso
            } else {
                paint.setColor(Color.GRAY); // Pinta los otros pisos de gris
            }
            canvas.drawRect(0, startY, getWidth(), endY, paint); // Dibuja un piso
        }
    }

    public void setCurrentHeight(float currentHeight) {
        this.currentHeight = currentHeight; // Establece la altura actual del círculo
        invalidate(); // Invalida la vista para que se vuelva a dibujar
    }
}
