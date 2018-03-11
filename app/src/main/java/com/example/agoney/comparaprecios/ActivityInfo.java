package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }
    @Override // metodo para aplicar un app bar
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    @Override // implementar acciones al pulsar en un item del appbar
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ayuda) { // botón de ayuda de la app bar
            Intent intent= new Intent(getApplicationContext(), ActivityAyuda.class); // creamos el intento, de donde viene y a donde va
            intent.putExtra("origen",  "info" );
            startActivity(intent); // ejecuta el intento.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
