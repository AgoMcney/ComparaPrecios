package com.example.agoney.comparaprecios.ProveedorDeContenido;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Agoney on 18/10/2017.
 * Clase contrato
 */
// para acceder a la URI y a los nombres de los campos
public class Contrato{  // clase absurda y precindible
    // Autoridad viene a ser donde vamos a acceder, la identificación del provedor de contenido
    public static final  String AUTHORITY = "com.example.agoney.comparaprecios.ProveedorDeContenido.Proveedor";
    public static class Producto  implements BaseColumns  { // recurso con el trabajamos
        public final static String NOMBRE_TABLA = "Producto"; // nombre tabla
        public final static Uri CONTENT_URI = Uri.parse("content://"+
                AUTHORITY + "/"+NOMBRE_TABLA); // crea la dirección
        // Nombres de los campos de los atributos
        /*public final static String _ID="_id";*/
        public final static String NOMBRE="nombre", FAMILIA="familia", IMAGEN="imagen";
        public final static String PRECIO1="precio1", PRECIO2="precio2", PRECIO3="precio3", PRECIO4="precio4", PRECIO5="precio5",  PRECIO6="precio6";

    }
    public static class Lista  implements BaseColumns  { // recurso con el trabajamos
        public final static String NOMBRE_TABLA = "Lista"; // nombre tabla
        public final static Uri CONTENT_URI = Uri.parse("content://"+
                AUTHORITY + "/"+NOMBRE_TABLA); // crea la dirección
        // Nombres de los campos de los atributos
        /* public final static String _ID="_id"; */
        public final static String NOMBRE="nombre";
      //  public final static String PRECIO1="precio1", PRECIO2="precio2", PRECIO3="precio3", PRECIO4="precio4", PRECIO5="precio5",  PRECIO6="precio6";

    }
}
