package com.example.agoney.comparaprecios;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link producto_precios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link producto_precios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class producto_precios extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private EditText editTextTienda1, editTextTienda2, editTextTienda3, editTextTienda4, editTextTienda5, editTextTienda6;
    private OnFragmentInteractionListener mListener;
    private Button aceptarPrecios;
    public producto_precios() {
        // Requiere un constructor publico vacio
    }


    // TODO: Rename and change types and number of parameters
    public static producto_precios newInstance() {
        producto_precios fragment = new producto_precios();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View inflaterPrecios=inflater.inflate(R.layout.fragment_producto_precios, container, false);
        editTextTienda1 = (EditText) inflaterPrecios.findViewById(R.id.editTextTienda1);
        editTextTienda2 = (EditText) inflaterPrecios.findViewById(R.id.editTextTienda2);
        editTextTienda3 = (EditText) inflaterPrecios.findViewById(R.id.editTextTienda3);
        editTextTienda4 = (EditText) inflaterPrecios.findViewById(R.id.editTextTienda4);
        editTextTienda5 = (EditText) inflaterPrecios.findViewById(R.id.editTextTienda5);
        editTextTienda6 = (EditText) inflaterPrecios.findViewById(R.id.editTextTienda6);
        aceptarPrecios = (Button) inflaterPrecios.findViewById((R.id.btnAceptarPrecios));
        aceptarPrecios.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ((OnFragmentInteractionListener) getActivity()).precio1 (CompruebaPrecio(editTextTienda1.getText().toString()));
               ((producto_precios.OnFragmentInteractionListener) getActivity()).precio2( CompruebaPrecio(editTextTienda2.getText().toString()));
               ((producto_precios.OnFragmentInteractionListener) getActivity()).precio3( CompruebaPrecio(editTextTienda3.getText().toString()));
               ((producto_precios.OnFragmentInteractionListener) getActivity()).precio4( CompruebaPrecio(editTextTienda4.getText().toString()));
               ((producto_precios.OnFragmentInteractionListener) getActivity()).precio5( CompruebaPrecio(editTextTienda5.getText().toString()));
               ((producto_precios.OnFragmentInteractionListener) getActivity()).precio6( CompruebaPrecio(editTextTienda6.getText().toString()));
            }
           });
        return inflaterPrecios;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void precio1 (float precio1);
        void precio2 (float precio2);
        void precio3 (float precio3);
        void precio4 (float precio4);
        void precio5 (float precio5);
        void precio6 (float precio6);

    }
    public float CompruebaPrecio (String precio){
       // Log.e("CompruebaPrecio vale: ", precio);
        if (precio==null|precio==""|precio.isEmpty()) {
            return 0.0f;
        } else if(precio.equals(".")){
            return 0.0f;
        } else if(Float.parseFloat(precio)<0.001){
            return 0.0f;
        }   else {
            return  Float.parseFloat(precio);
        }


    }
}
