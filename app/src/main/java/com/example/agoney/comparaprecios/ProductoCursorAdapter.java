package com.example.agoney.comparaprecios;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.agoney.comparaprecios.ProveedorDeContenido.Contrato;


/**
 * Created by Agoney on 18/10/2017.
 */

public class ProductoCursorAdapter extends CursorAdapter {
    // listado con todas las galletas, y vamos a tener un item para cada galleta
    public ProductoCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }
    public ProductoCursorAdapter(Context context) {  // segundo constructor
        super(context, null, false);
    }

    @Override // implementador por el Cursoradapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) { // cada vez que crea un item es un newView
        LayoutInflater inflater =LayoutInflater.from(context);
        // asignamos un layout al item
        View v = inflater.inflate(R.layout.producto_list_item, parent, false);
        bindView(v, context, cursor);
        return v;
    }

    @Override // implementador por el CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        //atributos
        int ID = cursor.getInt(cursor.getColumnIndex(Contrato.Producto._ID));
        String nombre = cursor.getString(cursor.getColumnIndex(Contrato.Producto.NOMBRE));
        String familia = cursor.getString(cursor.getColumnIndex(Contrato.Producto.FAMILIA));

        // views
        TextView textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
        TextView textViewFamilia = (TextView) view.findViewById(R.id.textViewFamilia);
        // asigno valores a los views
        textViewNombre.setText(nombre);
        textViewFamilia.setText(String.valueOf(familia));
        // letras de colores
        ColorGenerator generator = ColorGenerator.MATERIAL; // o usa el default
        int color = generator.getColor(familia); // genera un color según el campo dado
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(nombre.substring(0,1), color);
        ImageView image = (ImageView) view.findViewById(R.id.imageViewResultado);
        image.setImageDrawable(drawable);
        view.setTag(ID);  // ponemos una etiqueta a cada item del listView
    }
}
