package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.agoney.comparaprecios.Fragment.ProductoListFragment;

public class ActivityResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        // añadimos la barra
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // que se vea el botón de home

        // lanzamos el fragmento
        // ProductoListFragment productoListFragment = ProductoListFragment.newInstance();
        ProductoListFragment productoListFragment = new ProductoListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_producto, productoListFragment);
        transaction.commit();
    }

    @Override // metodo para aplicar un app bar
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override // implementar acciones al pulsar en un item del appbar
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_ayuda) { // botón de ayuda de la app bar
            Intent intent = new Intent(getApplicationContext(), ActivityAyuda.class); // creamos el intento, de donde viene y a donde va
            intent.putExtra("origen", "resultados");
            startActivity(intent); // ejecuta el intento.
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
