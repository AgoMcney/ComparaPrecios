package com.example.agoney.comparaprecios;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.agoney.comparaprecios.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link producto_nombre.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link producto_nombre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class producto_nombre extends Fragment  {
    private EditText txtInputNombre;
    private RadioGroup radioFamilia;
    private String familiaSeleccionadaAKI;
    private OnFragmentInteractionListener mListener;
    private Button btnTibu;
    public producto_nombre() {
        // Requiere un constructor publico vacio
    }

        public static producto_nombre newInstance(String familiaSeleccionada)  {
        producto_nombre fragment = new producto_nombre();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflaterNombre = inflater.inflate(R.layout.fragment_producto_nombre, container, false);
      //  txtInputNombre = (EditText) inflaterNombre.findViewById(R.id.TxtInputNombre);
        btnTibu = (Button) inflaterNombre.findViewById(R.id.btnTiburcio);
        radioFamilia = (RadioGroup) inflaterNombre.findViewById(R.id.RadioGroupFamilia);
        radioFamilia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RadioBtnFamilia1:
//                        getArguments().getString(getString(R.string.familia1));
                        familiaSeleccionadaAKI=getString(R.string.familia1);
                        break;
                    case R.id.RadioBtnFamilia2:
//                        getArguments().getString(getString(R.string.familia2));
                        familiaSeleccionadaAKI=getString(R.string.familia2);
                        break;
                    case R.id.RadioBtnFamilia3:
//                        getArguments().getString(getString(R.string.familia3));
                        familiaSeleccionadaAKI=getString(R.string.familia3);
                        break;
                    case R.id.RadioBtnFamilia4:
//                        getArguments().getString(getString(R.string.familia4));
                        familiaSeleccionadaAKI=getString(R.string.familia4);
                        break;
                    case R.id.RadioBtnFamilia5:
//                        getArguments().getString(getString(R.string.familia5));
                        familiaSeleccionadaAKI=getString(R.string.familia5);
                        break;
                    case R.id.RadioBtnFamilia6:
//                        getArguments().getString( getString(R.string.familia6));
                        familiaSeleccionadaAKI=getString(R.string.familia6);
                        break;
                    case R.id.RadioBtnFamilia7:
//                        getArguments().getString(getString(R.string.familia7));
                        familiaSeleccionadaAKI=getString(R.string.familia7);
                        break;
                    case R.id.RadioBtnFamilia8:
//                        getArguments().getString( getString(R.string.familia8));
                        familiaSeleccionadaAKI=getString(R.string.familia8);
                        break;
                    case R.id.RadioBtnFamilia9:
//                        getArguments().getString( getString(R.string.familia9));
                        familiaSeleccionadaAKI=getString(R.string.familia9);
                        break;
                    case R.id.RadioBtnFamilia10:
//                        getArguments().getString( getString(R.string.familia10));
                        familiaSeleccionadaAKI=getString(R.string.familia10);
                        break;
                    case R.id.RadioBtnFamilia11:
//                        getArguments().getString( getString(R.string.familia11));
                        familiaSeleccionadaAKI=getString(R.string.familia11);
                        break;
                    case R.id.RadioBtnFamilia12:
//                        getArguments().getString(getString(R.string.familia12));
                        familiaSeleccionadaAKI=getString(R.string.familia12);
                        break;
                }
                ((OnFragmentInteractionListener) getActivity()).FamiliaProducto (familiaSeleccionadaAKI);
                Toast.makeText(getContext(), familiaSeleccionadaAKI, Toast.LENGTH_LONG).show();
            }
        });
        ((OnFragmentInteractionListener) getActivity()).CampoTexto(this.getTxtInputNombre());
        btnTibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Como ves, puedo enviar los valores de los campos del fragmento a la actividad, " +
                        "pero el View en sí, me da problemas. Podría enviar el texto del InputText a la actividad desde este botón," +
                        " pero validar el error y tal, me gustaria operar con el view en sí. " +
                        "Lo he dejado así para que funcione, pero puedas ver la diferencia de que es lo que quiero hacer." +
                        "Espero explicarme bien, y si no que puedas ver la diferencia de esta forma", Toast.LENGTH_LONG).show();
            }
        });
        return inflaterNombre;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       // ((OnFragmentInteractionListener) getActivity()).CampoTexto(txtInputNombre);
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
       void CampoTexto (EditText CampoTexto);
        /*
        desde la activity:
                ((TextListener) fragment).sendText("texto a enviar al fragment");
        desde el fragment
                ((TextListener) getActivity()).sendText("texto a enviar al activity");
        */

    }

    public EditText getTxtInputNombre() {
        return txtInputNombre;
    }

    public void setTxtInputNombre(EditText txtInputNombre) {
        this.txtInputNombre = txtInputNombre;
    }
}
