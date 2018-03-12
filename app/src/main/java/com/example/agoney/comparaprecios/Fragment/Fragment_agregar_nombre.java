package com.example.agoney.comparaprecios.Fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.agoney.comparaprecios.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_agregar_nombre.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_agregar_nombre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_agregar_nombre extends Fragment  {
    private EditText txtInputNombre;
    private RadioGroup radioFamilia;
    private String familiaSeleccionadaAKI;
    private OnFragmentInteractionListener mListener;
    public Fragment_agregar_nombre() {
        // Requiere un constructor publico vacio
    }
    public static Fragment_agregar_nombre newInstance()  {
        Fragment_agregar_nombre fragment = new Fragment_agregar_nombre();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
      //      mParam1 = getArguments().getString(ARG_PARAM1);
       //     mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        View inflaterNombre = inflater.inflate(R.layout.fragment_producto_nombre, container, false);
        txtInputNombre = (EditText) inflaterNombre.findViewById(R.id.TxtInputNombre);
        radioFamilia = (RadioGroup) inflaterNombre.findViewById(R.id.RadioGroupFamilia);
        radioFamilia.clearCheck();
        if (this.getArguments().getString ("familia") == null){
            familiaSeleccionadaAKI=getString(R.string.familia12); // es de otros por default
        } else {
            familiaSeleccionadaAKI=(this.getArguments().getString ("familia"));
            ((OnFragmentInteractionListener) getActivity()).FamiliaProducto (familiaSeleccionadaAKI);
        }
        RadioButton rb;
        for (int i=0;i<radioFamilia.getChildCount();i++){
            rb = (RadioButton)  radioFamilia.getChildAt(i); //capturo el radioButton
            if (rb.getText().toString().equals( familiaSeleccionadaAKI.toString() )){
                rb.setChecked(true);
            }
        }
        radioFamilia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.RadioBtnFamilia1:
                    familiaSeleccionadaAKI=getString(R.string.familia1);
                    break;
                case R.id.RadioBtnFamilia2:
                    familiaSeleccionadaAKI=getString(R.string.familia2);
                    break;
                case R.id.RadioBtnFamilia3:
                    familiaSeleccionadaAKI=getString(R.string.familia3);
                    break;
                case R.id.RadioBtnFamilia4:
                    familiaSeleccionadaAKI=getString(R.string.familia4);
                    break;
                case R.id.RadioBtnFamilia5:
                    familiaSeleccionadaAKI=getString(R.string.familia5);
                    break;
                case R.id.RadioBtnFamilia6:
                    familiaSeleccionadaAKI=getString(R.string.familia6);
                    break;
                case R.id.RadioBtnFamilia7:
                    familiaSeleccionadaAKI=getString(R.string.familia7);
                    break;
                case R.id.RadioBtnFamilia8:
                    familiaSeleccionadaAKI=getString(R.string.familia8);
                    break;
                case R.id.RadioBtnFamilia9:
                    familiaSeleccionadaAKI=getString(R.string.familia9);
                    break;
                case R.id.RadioBtnFamilia10:
                    familiaSeleccionadaAKI=getString(R.string.familia10);
                    break;
                case R.id.RadioBtnFamilia11:
                    familiaSeleccionadaAKI=getString(R.string.familia11);
                    break;
                case R.id.RadioBtnFamilia12:
                    familiaSeleccionadaAKI=getString(R.string.familia12);
                    break;
            }
            ((OnFragmentInteractionListener) getActivity()).FamiliaProducto (familiaSeleccionadaAKI);
      //      Toast.makeText(getContext(), familiaSeleccionadaAKI, Toast.LENGTH_LONG).show();

            }
        });
        if (this.getArguments()!=null){
            txtInputNombre.setText (this.getArguments().getString ("nombre"));
        }
        return inflaterNombre;
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
        void FamiliaProducto (String FamiliaProducto);
        /*
        desde la activity:
                ((TextListener) fragment).sendText("texto a enviar al fragment");
        desde el fragment
                ((TextListener) getActivity()).sendText("texto a enviar al activity");
        */
    }
    public void setTextTxtInputNombre(String texto){
        txtInputNombre.setText(texto);
    }
    public String getTextTxtInputNombre(){
        return txtInputNombre.getText().toString();
    }
    public void setErrorTxtInputNombre(String texto){
        txtInputNombre.setError(texto);
    }
    public void FocusTxtInputNombre(){
        txtInputNombre.requestFocus();
    }

}
