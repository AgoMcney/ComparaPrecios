package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ActivityExtras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // que se vea el botón de home
        Button btnDescargaPdf = (Button) findViewById(R.id.btn_descarga_PDF);
        Button btnMaps = (Button) findViewById(R.id.btn_maps);

        btnDescargaPdf .setOnClickListener(new View.OnClickListener() {  // Listener del botón Agregar
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityDescargaPDF.class); // creamos el intento, de donde viene y a donde va
                startActivity(intent); // ejecuta el intento.
            }
        });  // cerramos el click listener

        btnMaps.setOnClickListener(new View.OnClickListener() {  // Listener del botón Agregar
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ActivityMaps.class); // creamos el intento, de donde viene y a donde va
//                startActivity(intent); // ejecuta el intento.
            }
        });  // cerramos el click listener


    }

}
