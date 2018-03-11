package com.example.agoney.comparaprecios;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Agoney on 02/10/2017.
 * Pojo de los productos
 */

public class Producto implements Parcelable {
    String nombre;
    String familia;
    float precio1, precio2, precio3, precio4, precio5, precio6 ;

    public Producto() { // constuctor vacío
    }

    public Producto(String nombre, String familia, float precio1, float precio2, float precio3, float precio4, float precio5, float precio6) {
        this.nombre = nombre;
        this.familia = familia;
        this.precio1 = precio1;
        this.precio2 = precio2;
        this.precio3 = precio3;
        this.precio4 = precio4;
        this.precio5 = precio5;
        this.precio6 = precio6;
    }

    protected Producto(Parcel in) {  // metodos implementados por el Parcelable
        nombre = in.readString();
        familia = in.readString();
        precio1 = in.readFloat();
        precio2 = in.readFloat();
        precio3 = in.readFloat();
        precio4 = in.readFloat();
        precio5 = in.readFloat();
        precio6 = in.readFloat();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override // metodos implementados por el Parcelable
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override // metodos implementados por el Parcelable
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public float getPrecio1() {
        return precio1;
    }

    public void setPrecio1(float precio1) {
        this.precio1 = precio1;
    }

    public float getPrecio2() {
        return precio2;
    }

    public void setPrecio2(float precio2) {
        this.precio2 = precio2;
    }

    public float getPrecio3() {
        return precio3;
    }

    public void setPrecio3(float precio3) {
        this.precio3 = precio3;
    }

    public float getPrecio4() {
        return precio4;
    }

    public void setPrecio4(float precio4) {
        this.precio4 = precio4;
    }

    public float getPrecio5() {
        return precio5;
    }

    public void setPrecio5(float precio5) {
        this.precio5 = precio5;
    }

    public float getPrecio6() {
        return precio6;
    }

    public void setPrecio6(float precio6) {
        this.precio6 = precio6;
    }

    @Override // metodos implementados por el Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // metodos implementados por el Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        // serializamos el objeto
        // metiendo en el parcel de destino la información de las propiedades que vamos a pasar
        parcel.writeString(nombre);
        parcel.writeString(familia);
        parcel.writeFloat(precio1);
        parcel.writeFloat(precio2);
        parcel.writeFloat(precio3);
        parcel.writeFloat(precio4);
        parcel.writeFloat(precio5);
        parcel.writeFloat(precio6);
    }
}
