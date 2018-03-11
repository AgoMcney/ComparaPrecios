package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityAyuda extends AppCompatActivity {
    String origen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        TextView txtAyuda= (TextView) findViewById(R.id.TxtAyuda);
        origen = getIntent().getStringExtra("origen").toString();
        txtAyuda.setText(origen);  // prueba
        switch (origen){
            case "main":
                txtAyuda.setText("Ayuda del menú Principal\n"
                + "\nEste es el menú principal de la aplicación para crear listas de compras y comparar precios. "
                + "Además tiene un menú lateral para elegir con que lista desea trabajar."
                );
                break;
            case "productos":
                txtAyuda.setText("Ayuda del menú Productos"
                + "\nEn esta pantalla podrá buscar productos y añadirlos a su lista. "
                + "Así como editarlos o eliminarlos."
                );
                break;
            case "agregar":
                txtAyuda.setText("Ayuda del menú Agregar \n"
                + "\nEn esta pantalla podrá agregar productos, precios y hasta añadir una foto al artículo."
                );
                break;
            case "lista":
                txtAyuda.setText("Ayuda del menú Listas \n"
                + "\nEn esta pantalla podrá consultar su lista de la compra y comparar los precios."
                );
                break;
            case "info":
                txtAyuda.setText("Información de la app. \n"
                        +"\nControl de versiones\n" +
                        "-------------------------------\n" +
                        "ver 0.2.1\n"+
                        "---------------\n" +
                        "Segunda Tarea\n" +
                        "---------------\n" +
                        "- Cambiada la estetica del navigation bar (side_nav_bar)\n"+
                        "- Funcionalidad al envio de correo para contactar conmigo. \n" +
                        "- Reducida la foto de información \n" +
                        "- Retorno de ayuda corregido. \n" +
                        "- Implementados fragmentos en el TabActivity AgregarProductos.\n" +
                        "- Implementada ClaseComun y guardado en el array de Productos"+
                        "* botón para tibu" +
                        "\n" +
                        "ver 0.2\n" +
                        "---------------\n" +
                        "Segunda Tarea\n" +
                        "---------------\n" +
                        "- Reducción de las letras de la ventana Agregar Producto.\n" +
                        "- Agregar el Navigation Bar\n" +
                        "- Sustitución del ActivityAgregar por un TabActivity (pendiente implementar)\n" +
                        "- pasado a string values los titulos del menu\n" +
                        "- implementar botón ayuda en el appbar\n" +
                        "- boton flotante mandar correo en el menú principal\n" +
                        "- botones flotantes Guardar y cancelar en el ActivityAgregar\n" +
                        "- añadir colores como valores\n" +
                        "- Rehecho la activity de Información, con fondo propio.\n" +
                        "- botón salir funcional\n" +
                        "- nueva actividad ayuda (consultar tibu botón volver)\n" +
                        "- botones del buscador de productos con funcionalidad\n" +
                        "\n" +
                        "ver 0.1\n" +
                        "---------------\n" +
                        "Primera Tarea\n" +
                        "---------------"
                );
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:  // capturo el botón de vuelta de ayuda
                Intent intent;
                switch (origen){ // según el origen, le asigno un retorno
                    case "main":
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case "productos":
                        intent = new Intent(this, ActivityProductos.class);
                //      Log.i("tiburcio", "paso por aquí");
                /*NavUtils.navigateUpTo(this, intent);*/
                        startActivity(intent);
                        return true;
                    case "agregar":
                        intent = new Intent(this, ActivityAgregar.class);
                        startActivity(intent);
                        return true;
                    case "lista":
                        intent = new Intent(this, ActivityLista.class);
                        startActivity(intent);
                        return true;
                    case "info":
                        intent = new Intent(this, ActivityInfo.class);
                        startActivity(intent);
                        return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
