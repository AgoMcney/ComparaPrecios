package com.example.agoney.comparaprecios;

import android.app.Application;

import com.example.agoney.comparaprecios.pojos.Producto;

import java.util.ArrayList;

/**
 * clase para controlar archivos comunes a toda la app
 * Created by Agoney on 06/10/2017.
 */

public class ClaseComun extends Application {
    public ArrayList<Producto> productos; // array donde guardara los productos.
    public  ClaseComun(){
        // productos=new ArrayList<Producto>();
        // if (Productos.size()<1) {
        /*
        if (productos.isEmpty()) {
            productos=new ArrayList<Producto>();
        }
        */
        // productos=new ArrayList<Producto>();
        productos=new ArrayList<>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
}
