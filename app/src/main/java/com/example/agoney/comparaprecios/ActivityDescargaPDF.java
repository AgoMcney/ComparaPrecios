package com.example.agoney.comparaprecios;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ActivityDescargaPDF extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarga_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Min 36, no entra" , Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.image_descarga:
                descargarPDF();
                break;
        }
    }
    void descargarPDF(){
        String urlADescargar = "https://www.lidl.es/statics/lidl-offering-es/ds_doc/Recetario-5aldia.pdf"; // archivo a descargar
        new DescargarPDFAsyncTask().execute(urlADescargar); // le digo que se ejecute
    }
    class DescargarPDFAsyncTask extends AsyncTask<String, Void, String> {
        // el primer parametro es el doinBackground, el segundo el inProgress, y el tercero lo que se ejecuta al final.
        @Override
        protected void onPreExecute() { // lo que quiero que se ejecute antes de la descarga
            super.onPreExecute();
        }
        @Override
        protected String doInBackground (String... urlPDF){ // que ocurre mientras se hace
            // los puntos es un array de dimension desconocida
            String urlADescargar = urlPDF[0];
            HttpURLConnection conexion=null;
            InputStream input=null;
            OutputStream output=null;
            try {
                URL url = new URL(urlADescargar);
                //abrimos una conexión
                conexion =(HttpURLConnection) url.openConnection();
                // comprobar si ha ido bien
                if (conexion.getResponseCode()!=RESULT_OK){
                    return "Conexión no realizada correctamente"; // este valor va al onPostExecute
                }
                // de donde lo voy a descargar
                 input = conexion.getInputStream(); // indico que el inputstream es de la url conexion
                // directorio de mi aplicación en la memoria interna
                // /data/data/com.example.agoney.comparaprecios/Recetas.pdf
                String rutaFichero = getFilesDir()+"/Recetas.pdf";
                 output = new FileOutputStream(rutaFichero)  ; // para escribir
                byte[] data = new byte[1024];  // cantidad en la que voy descargando
                // descargar
                int count;
    Toast.makeText(getApplicationContext(), "Se esta descargando" , Toast.LENGTH_SHORT).show();
                while ((count= input.read(data))!=-1) { // mientras hay data que descargar
                    output.write(data, 0, count);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null) input.close();  // si has terminado cierralo
                    if (output != null) output.close();  // si has terminado cierralo
                    if (conexion != null) conexion.disconnect();  // si has terminado cierralo
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String mensaje) { // lo que se ejecuta después de la descarga
            super.onPostExecute(mensaje);
        }

        @Override
        protected void onProgressUpdate(Void... values) { // para preguntar por donde va la descarga
            super.onProgressUpdate(values);
        }


        }
    }
