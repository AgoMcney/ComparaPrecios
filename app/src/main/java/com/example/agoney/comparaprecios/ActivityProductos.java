package com.example.agoney.comparaprecios;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityProductos extends AppCompatActivity {
    Activity contexto;  // getApplicationContext()
    ClaseComun comun;  // referencia a ese objeto que no desaparece.
    boolean error=false; // control de errores
    String cadenaFamilia; // para probar los checkbox
    int contaCheck; // contador de Check
    // creo los objetos de los view
    Button btnAgregar, btnBuscar, btnEditar, btnEliminar ;
    EditText editTextNombre;
    CheckBox checkBoxFamilia1, checkBoxFamilia2, checkBoxFamilia3, checkBoxFamilia4,
            checkBoxFamilia5, checkBoxFamilia6, checkBoxFamilia7, checkBoxFamilia8,
            checkBoxFamilia9, checkBoxFamilia10, checkBoxFamilia11, checkBoxFamilia12 ;
    ArrayList<CheckBox> arrayFamilias = new ArrayList<CheckBox>();
//    ListView listViewResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        contexto=this; //  getApplicationContext()
        // creo los objetos de los view
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        editTextNombre = (EditText) findViewById(R.id.TxtInputNombre);
        checkBoxFamilia1= (CheckBox) findViewById(R.id.checkBoxFamilia1);
        checkBoxFamilia2= (CheckBox) findViewById(R.id.checkBoxFamilia2);
        checkBoxFamilia3= (CheckBox) findViewById(R.id.checkBoxFamilia3);
        checkBoxFamilia4= (CheckBox) findViewById(R.id.checkBoxFamilia4);
        checkBoxFamilia5= (CheckBox) findViewById(R.id.checkBoxFamilia5);
        checkBoxFamilia6= (CheckBox) findViewById(R.id.checkBoxFamilia6);
        checkBoxFamilia7= (CheckBox) findViewById(R.id.checkBoxFamilia7);
        checkBoxFamilia8= (CheckBox) findViewById(R.id.checkBoxFamilia8);
        checkBoxFamilia9= (CheckBox) findViewById(R.id.checkBoxFamilia9);
        checkBoxFamilia10= (CheckBox) findViewById(R.id.checkBoxFamilia10);
        checkBoxFamilia11= (CheckBox) findViewById(R.id.checkBoxFamilia11);
        checkBoxFamilia12= (CheckBox) findViewById(R.id.checkBoxFamilia12);
        arrayFamilias.add(checkBoxFamilia1);
        arrayFamilias.add(checkBoxFamilia2);
        arrayFamilias.add(checkBoxFamilia3);
        arrayFamilias.add(checkBoxFamilia4);
        arrayFamilias.add(checkBoxFamilia5);
        arrayFamilias.add(checkBoxFamilia6);
        arrayFamilias.add(checkBoxFamilia7);
        arrayFamilias.add(checkBoxFamilia8);
        arrayFamilias.add(checkBoxFamilia9);
        arrayFamilias.add(checkBoxFamilia10);
        arrayFamilias.add(checkBoxFamilia11);
        arrayFamilias.add(checkBoxFamilia12);
//        listViewResultados = (ListView) findViewById(R.id.listViewResultados);
        btnAgregar.setOnClickListener(new View.OnClickListener() {  // Listener del botón Agregar
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ActivityAgregar.class); // creamos el intento, de donde viene y a donde va
            startActivity(intent); // ejecuta el intento.
            }
        });  // cerramos el click listener

        btnBuscar.setOnClickListener(new View.OnClickListener() {  // Listener del botón Agregar
            @Override
            public void onClick(View view) {
                validar();
                if (error==false) {
                    leerBusqueda();
                }
            }
        });  // cerramos el click listener
        btnEditar.setOnClickListener(new View.OnClickListener() {  // Listener del botón Agregar
            @Override
            public void onClick(View view) {
                Toast.makeText (contexto, "Esto irá en el lista de resultados, cuando la implemente", Toast.LENGTH_SHORT).show();
            }
        });  // cerramos el click listener
        btnEliminar.setOnClickListener(new View.OnClickListener() {  // Listener del botón Agregar
            @Override
            public void onClick(View view) {
                Toast.makeText (contexto, "Esto irá en el lista de resultados, cuando la implemente", Toast.LENGTH_SHORT).show();
            }
        });  // cerramos el click listener
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
        if (id == R.id.action_ayuda) {  // botón de ayuda de la app bar
            Intent intent= new Intent(contexto, ActivityAyuda.class); // creamos el intento, de donde viene y a donde va
            intent.putExtra("origen",  "productos" );
            startActivity(intent); // ejecuta el intento.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void leerBusqueda() {
        cadenaFamilia();
        Toast.makeText(contexto, "Ha buscado "+editTextNombre.getText().toString()+" en "+cadenaFamilia, Toast.LENGTH_LONG).show();
        Intent intent = new Intent (this, ActivityResultado.class);
        startActivity(intent);
    }
    public void validar() {
        // vacio los errores
        editTextNombre.setError(null);
        error = false;
        contaCheck=0;
        // compruebo cada campo
        if (TextUtils.isEmpty(editTextNombre.getText())) {
            editTextNombre.setError(getString(R.string.error_obligatorio)); // marcamos el error
            editTextNombre.requestFocus(); // llevamos el foco al error
            error = true;
        }
        for (int i=0; i<arrayFamilias.size();i++) {
            if (arrayFamilias.get(i).isChecked()) {
                contaCheck++;
            }
        }
        if (contaCheck==0){
            Toast.makeText (contexto, "Debe seleccionar alguna familia para la búsqueda", Toast.LENGTH_SHORT).show();
            error = true;
        }

    }
    void cadenaFamilia(){
        if (contaCheck==12){
            cadenaFamilia = "todas las familias";
        }else {
            cadenaFamilia = "las familias: ";
            for (int i = 0; i < arrayFamilias.size(); i++) {
                if (arrayFamilias.get(i).isChecked()) {
                    cadenaFamilia = cadenaFamilia + arrayFamilias.get(i).getText().toString() + ", ";
                }
            }
        }

    }
}
