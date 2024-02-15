package com.example.ascensor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MiCirculo miCirculo; // Referencia al círculo personalizado
    private MiEdificio miEdificio; // Referencia al edificio personalizado

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad

        // Busca las vistas de MiCirculo y MiEdificio en el layout
        miCirculo = findViewById(R.id.miCirculo);
        miEdificio = findViewById(R.id.miEdificio);

        // Establece un OnTouchListener para el círculo personalizado
        miCirculo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Actualiza la altura del círculo y del edificio cuando se arrastra el círculo
                float currentHeight = event.getY() + miCirculo.getRadius();
                miCirculo.setCurrentHeight(currentHeight); // Actualiza la altura del círculo
                miEdificio.setCurrentHeight(currentHeight); // Actualiza la altura del edificio
                return false; // Devuelve false para indicar que no se ha consumido el evento
            }
        });
    }
}
