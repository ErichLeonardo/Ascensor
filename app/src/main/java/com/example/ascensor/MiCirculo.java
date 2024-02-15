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
    private float centerX, centerY; // Coordenadas del centro del círculo
    private float radius; // Radio del círculo
    private boolean isDragging = false; // Indica si el círculo está siendo arrastrado
    private float lastY; // Última posición Y del toque
    private float currentHeight; // Altura actual del círculo

    public MiCirculo(Context context, AttributeSet attrs) {
        super(context, attrs);
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

    public void setCurrentHeight(float currentHeight) {
        this.currentHeight = currentHeight; // Establece la altura actual del círculo
        invalidate(); // Invalida la vista para que se vuelva a dibujar
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2f; // Calcula el centro en el eje X
        centerY = h / 2f; // Calcula el centro en el eje Y
        radius = Math.min(w, h) / 2f; // Calcula el radio del círculo
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, currentHeight, radius, paint); // Dibuja el círculo en su posición actual
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchY = event.getY(); // Obtiene la posición Y del toque
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Verifica si se ha tocado dentro del círculo
                float distance = (float) Math.sqrt(Math.pow(centerX - event.getX(), 2) + Math.pow(currentHeight - touchY, 2));
                if (distance < radius) { // Si el toque está dentro del círculo
                    isDragging = true; // Inicia el arrastre
                    lastY = touchY; // Guarda la última posición Y del toque
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) { // Si se está arrastrando el círculo
                    float deltaY = touchY - lastY; // Calcula la diferencia de posición Y
                    currentHeight += deltaY; // Actualiza la altura del círculo
                    lastY = touchY; // Guarda la última posición Y del toque
                    invalidate(); // Invalida la vista para que se vuelva a dibujar
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false; // Finaliza el arrastre
                break;
        }
        return true; // Se consume el evento de toque
    }
}
