package com.example.agoney.comparaprecios;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Agoney on 19/11/2017.
 */

public class utilImagen {
    static public void loadImageFromStorage (Context contexto, String imagenFichero, ImageView img)
        throws FileNotFoundException { // carga una imagen
        File f = contexto.getFileStreamPath (imagenFichero);
        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
        img.setImageBitmap(b);
    }

    public static void storeImage(Bitmap image, Context contexto, String fileName)
        throws IOException{  // guarda una imagen
        FileOutputStream fos = contexto.openFileOutput(fileName,Context.MODE_PRIVATE);
        // con modo Privado, solo el programa podra acceder a las fotos.
        image.compress(Bitmap.CompressFormat.PNG, 100, fos); // comprime l aimagen
        fos.close();
    }

}
