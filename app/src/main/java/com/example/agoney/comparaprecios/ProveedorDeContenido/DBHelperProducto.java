package com.example.agoney.comparaprecios.ProveedorDeContenido;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Agoney on 18/10/2017.
 */

public class DBHelperProducto extends SQLiteOpenHelper{
    // constructor
    public DBHelperProducto(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//    public  DababaseHelper (Context context){
//        super.(context, DATABASE_NAME, null, DATABASE_VERSION);
//
//    }

    @Override   // metodo implementado necesario
    public void onCreate(SQLiteDatabase db) {  // lo que se ejecuta cuando se crea
        String creacion = "CREATE TABLE "+  // ejecuta el Sql crear tabla
                Contrato.Producto.NOMBRE_TABLA +
                "( "+
//     Contrato.Producto._ID+" INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT, "  + // campo ID autoincrementado

                "_id INTEGER NOT NULL PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "  + // campo ID autoincrementado
                Contrato.Producto.NOMBRE+ " TEXT, " +
                Contrato.Producto.FAMILIA+ " TEXT, " +
                Contrato.Producto.PRECIO1+ " FLOAT, " +
                Contrato.Producto.PRECIO2+ " FLOAT, " +
                Contrato.Producto.PRECIO3+ " FLOAT, " +
                Contrato.Producto.PRECIO4+ " FLOAT, " +
                Contrato.Producto.PRECIO5+ " FLOAT, " +
                Contrato.Producto.PRECIO6+ " FLOAT " +
                Contrato.Producto.IMAGEN+ " IMG" +
                ")";
        db.execSQL(creacion);

        creacion = "CREATE TABLE "+  // ejecuta el Sql crear tabla
                Contrato.Lista.NOMBRE_TABLA +
                "( "+
//     Contrato.Producto._ID+" INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT, "  + // campo ID autoincrementado

                "_id INTEGER NOT NULL PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "  + // campo ID autoincrementado
                Contrato.Lista.NOMBRE+ " TEXT, " +
                Contrato.Producto.FAMILIA+ " TEXT, " +
                Contrato.Producto.PRECIO1+ " FLOAT, " +
                Contrato.Producto.PRECIO2+ " FLOAT, " +
                Contrato.Producto.PRECIO3+ " FLOAT, " +
                Contrato.Producto.PRECIO4+ " FLOAT, " +
                Contrato.Producto.PRECIO5+ " FLOAT, " +
                Contrato.Producto.PRECIO6+ " FLOAT " +
                Contrato.Producto.IMAGEN+ " IMG" +
                ")";
        db.execSQL(creacion);

        /*db.execSQL("CREATE TABLE "+  // ejecuta el Sql crear tabla
            Contrato.Producto.NOMBRE_TABLA +
                "( "+
//     Contrato.Producto._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "  + // campo ID autoincrementado

            "_id INTEGER NOT NULL PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT , "  + // campo ID autoincrementado
                Contrato.Producto.NOMBRE+ " TEXT, " +
                Contrato.Producto.FAMILIA+ " TEXT, " +
                Contrato.Producto.PRECIO1+ " FLOAT, " +
                Contrato.Producto.PRECIO2+ " FLOAT, " +
                Contrato.Producto.PRECIO3+ " FLOAT, " +
                Contrato.Producto.PRECIO4+ " FLOAT, " +
                Contrato.Producto.PRECIO5+ " FLOAT, " +
                Contrato.Producto.PRECIO6+ " FLOAT " +
                ")"

        );*/

        Log.i("tiburcio", creacion);
        inicializar(db);  // metodo nuestro para iniciar la base de datos
    }

    private void inicializar(SQLiteDatabase db) { // metodo nuestro para empezar a trabajar con unos valores
        /*db.execSQL("INSERT INTO "+  // insertamos valores
            Contrato.Producto.NOMBRE_TABLA +
              " ("+
       Contrato.Producto._ID+", "+
                *//*"_id, "+*//*
                Contrato.Producto.NOMBRE+ ", " +
                Contrato.Producto.FAMILIA+", " +
                Contrato.Producto.PRECIO1+", " +
                Contrato.Producto.PRECIO2+", " +
                Contrato.Producto.PRECIO3+", " +
                Contrato.Producto.PRECIO4+", " +
                Contrato.Producto.PRECIO5+", " +
                Contrato.Producto.PRECIO6+
                ") VALUES "
//                    + "('Bandama','Otros',23,42,53,11,33,44),"
//                    + "('Tirma','Otros', 2,5,2,6,8,5),"
//                    + "('Cubanitos','Otros', 4,5,6,3,5,6),"
//                    + "('Yogurt','Frescos', 2,3,5,2,4,3),"
//                    + "('CocaCola','Bebidas', 1,0.9,1,1.1,1,1),"
//                    + "('Croquetas congeladas','Congelados', 7,8,7,7,7.5,7.6)"
                        + "(1,'Bandama','Otros',23,42,53,11,33,44),"
                        + "(2,'Tirma','Otros', 2,5,2,6,8,5),"
                        + "(3,'Cubanitos','Otros', 4,5,6,3,5,6),"
                        + "(4,'Yogurt','Frescos', 2,3,5,2,4,3),"
                        + "(5,'CocaCola','Bebidas', 1,0.9,1,1.1,1,1),"
                        + "(6,'Croquetas congeladas','Congelados', 7,8,7,7,7.5,7.6)"
        );*/

        String metida = "INSERT INTO "+  // insertamos valores
                Contrato.Producto.NOMBRE_TABLA +
                " ("+
                Contrato.Producto._ID + ", " +
                Contrato.Producto.NOMBRE + ", " +
                Contrato.Producto.FAMILIA + ", " +
                Contrato.Producto.PRECIO1 + ", " +
                Contrato.Producto.PRECIO2 + ", " +
                Contrato.Producto.PRECIO3 + ", " +
                Contrato.Producto.PRECIO4 + ", " +
                Contrato.Producto.PRECIO5 + ", " +
                Contrato.Producto.PRECIO6 +
                ") VALUES "
//                    + "('Bandama','Otros',23,42,53,11,33,44),"
//                    + "('Tirma','Otros',2,5,2,6,8,5),"
//                    + "('Cubanitos','Otros',4,5,6,3,5,6),"
//                    + "('Yogurt','Frescos',2,3,5,2,4,3),"
//                    + "('CocaCola','Bebidas',1,0.9,1,1.1,1,1),"
//                    + "('Croquetas congeladas','Congelados',7,8,7,7,7.5,7.6)"
                + "(1,'Bandama','Otros',23,42,53,11,33,44),"
                + "(2,'Tirma','Otros', 2,5,2,6,8,5),"
                + "(3,'Cubanitos','Otros', 4,5,6,3,5,6),"
                + "(4,'Yogurt','Frescos', 2,3,5,2,4,3),"
                + "(5,'CocaCola','Bebidas', 1,0.9,1,1.1,1,1),"
                + "(6,'Croquetas congeladas','Congelados', 7,8,7,7,7.5,7.6)"
        ;
        db.execSQL(metida);
        Log.i("tiburcio", "metida: " + metida);

    }

    @Override   // metodo implementado necesario
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // lo que se ejecuta cuando modificamos la versión que le hemos puesto
        db.execSQL("DROP TABLE IF EXISTS " + Contrato.Producto.NOMBRE_TABLA);  // si existe la tabla la borramos
        onCreate(db); // realizamos el Create
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
//         db.execSQL("PRAGMA foreign_keys=ON;"); // habilita la integridad referencial
    }
}
