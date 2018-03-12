package com.example.agoney.comparaprecios.ProveedorDeContenido;

import android.content.ContentProvider;
import android.content.ContentUris;  // para el metodo insertar
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;

public class Proveedor extends ContentProvider {
    final static int DATABASE_VERSION = 1; // es la version de la base de datos
    final static String DATABASE_NAME = "Productos.db";  // nombre de la base de datos real
    // la uri 1 devuelve todos los registras galletas, la 2 solo 1
    final static int PRODUCTOS_ALL_REGS = 1;    //para que este bien organizado
    final static int PRODUCTOS_ONE_REG =2 ;    //para que este bien organizado
    // las variables que son globales se ponen una m minuscula delante
    static UriMatcher mUriMatcher =  new UriMatcher(0);// crea un matcher para crear coincidencias
    private SQLiteDatabase sqlDB; // base de datos
    DBHelperProducto dbHelper;  // variable para trabajar con db
    // Mime es para pedir un tipo de contenido
    // Array ahorrador, almacena el tipo MIME servido por este proveedor
    private  static final SparseArray<String> sMimeTypes;


    static {  // statico para meter muchas cosas
        mUriMatcher.addURI(  // content la autoridad, el nombre de la tabla, y  eso es la uri número 1
                Contrato.AUTHORITY,
                Contrato.Producto.NOMBRE_TABLA,
                PRODUCTOS_ALL_REGS
        );
        // es una uri que devuelve el valor de un solo producto
        mUriMatcher.addURI(  // content la autoridad, el nombre de la tabla, y  eso es la uri número 2
                Contrato.AUTHORITY,
                Contrato.Producto.NOMBRE_TABLA + "/#",
                PRODUCTOS_ONE_REG
        );
        sMimeTypes=new SparseArray<String>();
        // decimos que el identificador de todos/un registro, se identifica con el tipo mime siguiente
        sMimeTypes.put(PRODUCTOS_ALL_REGS,
                "vnd.android.cursor.dir/vnd." +  // dir, directorio, varios registros
                    Contrato.AUTHORITY +"."+Contrato.Producto.NOMBRE_TABLA);
        sMimeTypes.put(PRODUCTOS_ONE_REG,
                "vnd.android.cursor.item/vnd." +  // item, un solo registro
                        Contrato.AUTHORITY +"."+Contrato.Producto.NOMBRE_TABLA);
    }

    public Proveedor() {
    }

    @Override // Borrar
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        // throw new UnsupportedOperationException("Not yet implemented");
        sqlDB=dbHelper.getWritableDatabase();
        String table ="";
        switch (mUriMatcher.match(uri)){
            case PRODUCTOS_ONE_REG:
                if (null == selection) selection = "";
                selection += Contrato.Producto._ID +" = "
                        + uri.getLastPathSegment();  // la id del objeto es el último segmento de la URI
                table = Contrato.Producto.NOMBRE_TABLA ;
                break;
            case PRODUCTOS_ALL_REGS:
                table = Contrato.Producto.NOMBRE_TABLA ;
                break;
        }
        int rows = sqlDB.delete(table, selection, selectionArgs); // aqui se realiza el borrado
        if (rows >0){
            getContext().getContentResolver().notifyChange(uri,null);
            return rows;
        }
        throw new SQLException("Failed to delete row into " + uri);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override  // Insertar
    public Uri insert(Uri uri, ContentValues values) {
       // throw new UnsupportedOperationException("Not yet implemented");
        sqlDB=dbHelper.getWritableDatabase();
        String table ="";
        switch (mUriMatcher.match(uri)){
            case PRODUCTOS_ALL_REGS:
                table = Contrato.Producto.NOMBRE_TABLA;
                break;
        }
        long rowId = sqlDB.insert(table, "", values);  // aqui ejecuto realmente la inserción
        if (rowId >0){
            Uri rowUri = ContentUris.appendId(uri.buildUpon(),rowId).build();
            getContext().getContentResolver().notifyChange(rowUri,null); // notifica que ha habido un cambio
            return rowUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override  // cuando se crea la base de datos
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new DBHelperProducto(getContext(), DATABASE_NAME, null, DATABASE_VERSION); // null porque no usamos Factory
        // return false;
        return (dbHelper==null?false:true); // si es null devuelve falso, si esta bien devuelve true
    }

    @Override // Consultar
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // abro la base de datos para poderla leer
        String query = null;  // para que coño???
        // Con el UriMatcher le decimos que Uri usar en cada caso
        switch (mUriMatcher.match(uri)){   // con el uri matcher busca la uri dada
            case PRODUCTOS_ONE_REG:
                if (null == selection) selection="";  //busqueda que coincida con el último segmento de la uri
                selection += Contrato.Producto._ID + " = "
                        + uri.getLastPathSegment();
                qb.setTables(Contrato.Producto.NOMBRE_TABLA);
                break;
            case PRODUCTOS_ALL_REGS:
                if (TextUtils.isEmpty(sortOrder))sortOrder=  //  lo quiero ordenado
                        Contrato.Producto._ID + " ASC";
                qb.setTables(Contrato.Producto.NOMBRE_TABLA);
                break;
        }
        // para ahacer la consulta
        Cursor c = qb.query (db, projection, selection, selectionArgs, null, null, sortOrder);
        // projection es campos, selection es el where, selectionARGs con los argumentos que sea,   null agrupado por, null having, y ordenado
         // por esta linea es todo lo anterior, los moviles que han solicitado la consulta con uri apareceta este resultado
        c.setNotificationUri(getContext().getContentResolver(), uri); // caundo actualice devuevle todos los cambios
        return c;
    }

    @Override  // Actualizar
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
       //  throw new UnsupportedOperationException("Not yet implemented");
        sqlDB=dbHelper.getWritableDatabase();
        String table ="";
        switch (mUriMatcher.match(uri)){
            case PRODUCTOS_ONE_REG:
                if (null == selection) selection="";  //busqueda que coincida con el último segmento de la uri
                selection +=Contrato.Producto._ID+" = "
                        + uri.getLastPathSegment();
                table=Contrato.Producto.NOMBRE_TABLA;
                break;
            case PRODUCTOS_ALL_REGS:
                table = Contrato.Producto.NOMBRE_TABLA ;
                break;
        }
        int rows = sqlDB.update(table, values, selection,selectionArgs);
        if (rows >0){
            getContext().getContentResolver().notifyChange(uri,null); // notifica que ha habido un cambio
            return rows;
        }
        throw new SQLException("Failed to update row into " + uri);
    }
}
