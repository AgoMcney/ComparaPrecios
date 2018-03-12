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
                txtAyuda.setText(getString(R.string.ayuda_main));
//                txtAyuda.setText("Ayuda del menú Principal\n"
//                + "\nEste es el menú principal de la aplicación para crear listas de compras y comparar precios. "
//                + "Además tiene un menú lateral para elegir con que lista desea trabajar."
//                );
                break;
            case "productos":
                txtAyuda.setText(getString(R.string.ayuda_productos));
//                txtAyuda.setText("Ayuda del menú Productos"
//                + "\nEn esta pantalla podrá buscar productos y añadirlos a su lista. "
//                + "Así como editarlos o eliminarlos."
//                );
                break;
            case "agregar":
                txtAyuda.setText(getString(R.string.ayuda_agregar));
//                txtAyuda.setText("Ayuda del menú Agregar \n"
//                + "\nEn esta pantalla podrá agregar productos, precios y hasta añadir una foto al artículo."
//                );
                break;
            case "lista":
                    txtAyuda.setText(getString(R.string.ayuda_lista));
//                txtAyuda.setText("Ayuda del menú Listas \n"
//                + "\nEn esta pantalla podrá consultar su lista de la compra y comparar los precios."
//                );
                break;
            case "info":
                txtAyuda.setText("Información de la app. \n"
                        +"\nControl de versiones\n" +
                        "-------------------------------\n" +
                 " ver 0.4\n" +
                "--------------- \n" +
                "Cuarta Tarea\n" +
                "--------------- \n" +
                "- Añadido Floating Button \"Adjuntar imagen\" al fragment de imagen\n" +
                "- Cambiado el botón \"Editar\" de Activity Productos por un botón para mostrar\n" +
                "  todos los resultados.\n" +
                "- Añadida funcionalidad para realizar y adjuntar fotos.\n" +
                "- Iconos ahora con la foto\n" +
                "- Modificar foto de producto\n" +

                        " ver 0.3   \n" +
                        "--------------- \n" +
                        "Tercera Tarea \n" +
                        "--------------- \n" +
                        "- Añadido actitivy Language\n" +
                        "- Implementar multidioma\n" +
                            "- Añadido lenguge en el navigation bar\n" +
                            "- Arreglos en el menú navigation bar (iconos, nombres en string, etc)\n" +
                            "- Banderas\n" +
                            "- Implementados idiomas (español, ingles, canario)\n" +
                            "- Implementar Proveedor de Contenido\n" +
                            "- Cursor\n" +
                            "- Librería 'com.amulyakhare:com.amullyakhare.textdrawable:1.0.1' (Para letras de color)\n" +
                        "- Metodo insertar en el Proveedor de contenido. \n" +
                        "- Contextual Action Bar, con editar y borrar\n" +
                            "- añadido Activity Modificar y Actitivy Resultados\n" +
                            "- corregidos fallos en modificar/agregar\n" +
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
            case "language":
                txtAyuda.setText(getString(R.string.ayuda_language));
//                txtAyuda.setText("Ayuda del menú Cambiar lenguaje \n"
//                                + "\n En esta pantalla podrá cambiar el lenguaje del programa\n");
                break;
            case "resultados":
                txtAyuda.setText(getString(R.string.ayuda_resultados));
                break;
            case "modificar":
                txtAyuda.setText(getString(R.string.ayuda_modificar));
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
                    case "language":
                        intent = new Intent(this, ActivityLanguage.class);
                        startActivity(intent);
                        return true;
                    case "resultados":
                        intent = new Intent(this, ActivityResultado.class);
                        startActivity(intent);
                        return true;
                    case "modificar":
                        intent = new Intent(this, ActivityResultado.class);
                        startActivity(intent);
                        return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
