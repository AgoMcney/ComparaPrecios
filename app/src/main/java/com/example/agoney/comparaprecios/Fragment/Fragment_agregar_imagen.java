package com.example.agoney.comparaprecios.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agoney.comparaprecios.R;
import com.example.agoney.comparaprecios.utilImagen;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_agregar_imagen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_agregar_imagen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_agregar_imagen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private OnFragmentInteractionListener mListener;
    final int PETICION_SACAR_FOTO= 1 ;  //da igual el número
    final int PETICION_GALERIA= 2 ;
    ImageButton btnImagen;
    Bitmap imagen;
    public Fragment_agregar_imagen() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment_agregar_imagen newInstance() {
        Fragment_agregar_imagen fragment = new Fragment_agregar_imagen();
//        Bundle args = new Bundle();
////        args.putString(ARG_PARAM1, param1);
////        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
       }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflater1 =inflater.inflate(R.layout.fragment_producto_imagen, container, false);
        btnImagen = (ImageButton) inflater1.findViewById(R.id.imageProducto);
        btnImagen.setScaleType(ImageView.ScaleType.FIT_CENTER);
        try {
            utilImagen.loadImageFromStorage(getContext(), "img_" + this.getArguments().getInt ("id")  + ".jpg", btnImagen);
            imagen=((BitmapDrawable) btnImagen.getDrawable()).getBitmap();
        } catch (FileNotFoundException e){
            // No existe la imagen par este producto
        }catch (Exception e){
            // No existe la imagen par este producto
            e.getStackTrace();
        }
        btnImagen.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                sacarFoto();
             }
         }
        );
        FloatingActionButton fabAdjuntar = (FloatingActionButton) inflater1.findViewById(R.id.fab_Adjuntar);
        fabAdjuntar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                elegirDeGaleria();
             }
         }
        );

        return inflater1;
//        return inflater.inflate(R.layout.fragment_producto_imagen, container, false);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void IntercambioImagen (Bitmap img);
    }

    void sacarFoto() {
        Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentFoto, PETICION_SACAR_FOTO ); // arranca una actividad y le envia un código
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // resultado del intent
        // 1- Codigo de peticion, 2- El resultado 3- los datos en sí
        switch (requestCode){  // en función de la petición
            case PETICION_SACAR_FOTO:
                if ((resultCode==RESULT_OK)){
                    imagen = (Bitmap) data.getExtras().get("data");
//                    btnImagen.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    btnImagen.setImageBitmap(imagen);
//                    try {  // intento guardar la imagen
//                        utilImagen.storeImage(foto, getContext() , "imagen"+".jpg" );
//                    } catch (IOException e) {
//                        Toast.makeText(getContext(), "Error: No se pudo guardar la imagen", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//
                } else {
                    // Se cancelo la foto
                }
                break;
            case PETICION_GALERIA:
                if ((resultCode==RESULT_OK)){
                    Uri uri = data.getData();
                    btnImagen.setImageURI(uri);
                    imagen = ((BitmapDrawable) btnImagen.getDrawable()).getBitmap();
                } else {
                    // se ha cancelado
                }
                break;
        }
        ((Fragment_agregar_imagen.OnFragmentInteractionListener) getActivity()).IntercambioImagen (imagen);
        super.onActivityResult(requestCode, resultCode, data);
    }
    void elegirDeGaleria() {
        Intent intentFoto = new Intent(Intent.ACTION_GET_CONTENT);
        intentFoto.setType("image/*");
        intentFoto.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intentFoto, PETICION_GALERIA ); // arranca una actividad y le envia un código
    }
}
