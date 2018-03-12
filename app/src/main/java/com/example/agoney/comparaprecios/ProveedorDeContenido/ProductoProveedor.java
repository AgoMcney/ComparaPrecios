package com.example.agoney.comparaprecios.ProveedorDeContenido;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.agoney.comparaprecios.pojos.Producto;

/**
 * Created by Agoney on 30/10/2017.
 */
// para realizar inserciones en el proveedor
public class ProductoProveedor {
    static public void  insert (ContentResolver resolvedor, Producto producto){
        Uri uri =Contrato.Producto.CONTENT_URI;
            // Uri.parse("content://"+ AUTHORITY + "/"+NOMBRE_TABLA);
        ContentValues values = new ContentValues(); // calse para ir introduciendo valores
        values.put (Contrato.Producto.NOMBRE, producto.getNombre());
        values.put (Contrato.Producto.FAMILIA, producto.getFamilia());
        values.put (Contrato.Producto.PRECIO1, producto.getPrecio1());
        values.put (Contrato.Producto.PRECIO2, producto.getPrecio2());
        values.put (Contrato.Producto.PRECIO3, producto.getPrecio3());
        values.put (Contrato.Producto.PRECIO4, producto.getPrecio4());
        values.put (Contrato.Producto.PRECIO5, producto.getPrecio5());
        values.put (Contrato.Producto.PRECIO6, producto.getPrecio6());
        // resolver es para encontrar el proveedor de contenido.
        resolvedor.insert(uri, values); //  objetivo final.
    }
    static public void delete (ContentResolver resolver, int productoId){
        Uri uri = Uri.parse(Contrato.Producto.CONTENT_URI+"/"+ productoId);
        //content://com.example.agoney.comparaprecios.ProveedorDeContenido.Proveedor/Producto/#
        resolver.delete(uri, null, null); // borramos
    }
    static public void update (ContentResolver resolver, Producto producto){
        Uri uri = Uri.parse(Contrato.Producto.CONTENT_URI+"/"+ producto.getID());
        //content://com.example.agoney.comparaprecios.ProveedorDeContenido.Proveedor/Producto/#
        ContentValues values = new ContentValues(); // contenedor de valores
        values.put(Contrato.Producto.NOMBRE, producto.getNombre());
        values.put(Contrato.Producto.FAMILIA, producto.getFamilia());
        values.put(Contrato.Producto.PRECIO1, producto.getPrecio1());
        values.put(Contrato.Producto.PRECIO2, producto.getPrecio2());
        values.put(Contrato.Producto.PRECIO3, producto.getPrecio3());
        values.put(Contrato.Producto.PRECIO4, producto.getPrecio4());
        values.put(Contrato.Producto.PRECIO5, producto.getPrecio5());
        values.put(Contrato.Producto.PRECIO6, producto.getPrecio6());
       resolver.update(uri, values, null, null); // actualizamos
    }
    static public Producto readRecord(ContentResolver resolver, int productoId){  // leer un registro
        // que registro? el que paso por parametros
        Uri uri = Uri.parse(Contrato.Producto.CONTENT_URI+"/"+ productoId);
        // que campos quiero de devuelva
        String[] projection = {
                Contrato.Producto.NOMBRE,
                Contrato.Producto.FAMILIA,
                Contrato.Producto.PRECIO1,
                Contrato.Producto.PRECIO2,
                Contrato.Producto.PRECIO3,
                Contrato.Producto.PRECIO4,
                Contrato.Producto.PRECIO5,
                Contrato.Producto.PRECIO6
        };
        // query(Uri uri, String[] projection, String selection, String[] selectionArgs, String )
        Cursor cursor= resolver.query (uri, projection, null, null, null);

        if (cursor.moveToFirst()){  // me va mover el cursor al primero, si es verdad, lo leo
            Producto producto = new Producto();
            // asigno al producto los valores, cada uno del campo correspondiente del cursor
            producto.setID(productoId);
            producto.setNombre(cursor.getString(cursor.getColumnIndex(Contrato.Producto.NOMBRE)));
            producto.setFamilia(cursor.getString(cursor.getColumnIndex(Contrato.Producto.FAMILIA)));
            producto.setPrecio1(cursor.getFloat(cursor.getColumnIndex(Contrato.Producto.PRECIO1)));
            producto.setPrecio2(cursor.getFloat(cursor.getColumnIndex(Contrato.Producto.PRECIO2)));
            producto.setPrecio3(cursor.getFloat(cursor.getColumnIndex(Contrato.Producto.PRECIO3)));
            producto.setPrecio4(cursor.getFloat(cursor.getColumnIndex(Contrato.Producto.PRECIO4)));
            producto.setPrecio5(cursor.getFloat(cursor.getColumnIndex(Contrato.Producto.PRECIO5)));
            producto.setPrecio6(cursor.getFloat(cursor.getColumnIndex(Contrato.Producto.PRECIO6)));
            return producto;
        }
        return null;  // devuelve producto vacio si no lo encuentra
    }
}
