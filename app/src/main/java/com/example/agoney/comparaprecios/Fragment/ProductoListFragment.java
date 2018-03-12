package com.example.agoney.comparaprecios.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
//  import android.support.v7.view.ActionMode;  // para versiones más nuevas
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.agoney.comparaprecios.ActivityModificar;
import com.example.agoney.comparaprecios.ProductoCursorAdapter;
import com.example.agoney.comparaprecios.ProveedorDeContenido.Contrato;
import com.example.agoney.comparaprecios.ProveedorDeContenido.ProductoProveedor;
import com.example.agoney.comparaprecios.R;



public class ProductoListFragment extends ListFragment
    implements LoaderManager.LoaderCallbacks<Cursor>{
    ProductoCursorAdapter mAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private OnFragmentInteractionListener mListener;
    ActionMode  mActionMode;  // dice en que modo esta el Contextual Accion Bar
    View viewSeleccionado;
    public ProductoListFragment() {
        // Required empty public constructor
    }
    public static ProductoListFragment newInstance() {
        ProductoListFragment listFragment = new ProductoListFragment();
        // en caso que tuviera argumentos
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        listFragment.setArguments(args);
        return listFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_producto_list, container, false);
        v.setBackgroundColor(Color.WHITE);
        // crea un Adaptador necesario para decir que en el list view habra una información
        mAdapter = new ProductoCursorAdapter (getActivity());
        setListAdapter(mAdapter);
        setHasOptionsMenu(true);
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCallbacks = this;
        // cargamos la información correspondiente al cursor
        getLoaderManager().initLoader(0, null, mCallbacks);
        // Le decimos que cuando haga un Clic Largo en un ITEM importante
        // getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        //    @SuppressLint("ResourceAsColor") para que se vea tipo sombra, como una capa encima
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mActionMode!=null){ // para que no se cree siempre, sino cuando tenga un valor nuevo
                    return false;
                }
                // llamar al menú contextual, estamos en un fragmento y hay que buscar la actividad
                mActionMode = getActivity().startActionMode(mActionModeCallback); // mostramos el menú contextual al hacer click
                view.setSelected(true);
                viewSeleccionado = view; // para saber cual es el view del item seleccionado.
                viewSeleccionado.setBackgroundColor(ContextCompat.getColor( getContext(), R.color.colorAccent));  // #bb62ca malva
                return true;  // verdadero para que funcione
            }

        });

    }
// para el menú contextual, al presionar sobre un item
    ActionMode.Callback mActionModeCallback =  new ActionMode.Callback() {
        @Override  // metodo para el ActionModeCallback
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // cuando se crea el contextual
            MenuInflater inflater = mode.getMenuInflater();
            // se puede hacer por codigo o por xml
            inflater.inflate(R.menu.menu_contextual, menu);  // ponemos este menú
            return true;
        }

        @Override  // metodo para el ActionModeCallback
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override  // metodo para el ActionModeCallback
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            int ProductoID=(Integer) viewSeleccionado.getTag() ;  // item.getItemId()? viewSeleccionado.getId() ?
            int ProductoID;
            switch (item.getItemId()){
                case R.id.menu_borrar:
                    ProductoID=(Integer) viewSeleccionado.getTag();
                    ProductoProveedor.delete(getActivity().getContentResolver(), ProductoID );  // solicito que borre
                    Toast.makeText(getContext(), "Ha eliminado un producto", Toast.LENGTH_SHORT ).show();
                    mActionMode.finish(); // cierro el actionMode cuando termino
                    break;
                case R.id.menu_editar:
                     ProductoID=(Integer) viewSeleccionado.getTag();
                    Intent intent = new Intent(getActivity(), ActivityModificar.class);
                    intent.putExtra(Contrato.Producto._ID, ProductoID);
//                    intent.putExtra( Contrato.Producto.NOMBRE,  );
//                    intent.putExtra( Contrato.Producto.FAMILIA, );
//                    intent.putExtra( Contrato.Producto.PRECIO1, );
//                    intent.putExtra( Contrato.Producto.PRECIO2,  );
//                    intent.putExtra( Contrato.Producto.PRECIO3,  );
//                    intent.putExtra( Contrato.Producto.PRECIO4, );
//                    intent.putExtra( Contrato.Producto.PRECIO5,  );
//                    intent.putExtra( Contrato.Producto.PRECIO6, );
                    startActivity(intent);
                    mActionMode.finish(); // cierro el actionMode cuando termino
                    break;
            }
            return false;
        }

        @Override  // metodo para el ActionModeCallback
        public void onDestroyActionMode(ActionMode mode) {
            viewSeleccionado.setBackgroundColor(Color.WHITE);// getContext(),
            mActionMode = null; // cuando se destruye la ponemos a null
            viewSeleccionado=null; // vacio el viewSeleccionado
        }
    };
    // fin del ActionMode

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override // hace falta el OnFragmentInteractionListener
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {  // Cursor
        String columns[]=new String[] {
                Contrato.Producto._ID,
                Contrato.Producto.NOMBRE,
                Contrato.Producto.FAMILIA
               , Contrato.Producto.PRECIO1,
                Contrato.Producto.PRECIO2,
                Contrato.Producto.PRECIO3,
                Contrato.Producto.PRECIO4,
                Contrato.Producto.PRECIO5,
                Contrato.Producto.PRECIO6
        };
        Uri baseUri = Contrato.Producto.CONTENT_URI; // uri que vas a llamar, misma que el contrato
        String selection = null;
        // devolvemos de la uri las columnas, y todos los datos
        return  new CursorLoader(getActivity(), baseUri, columns, selection, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Cuando termine de cargarse
        Uri laUriBase = Uri.parse("content://"+Contrato.AUTHORITY+"/"+  Contrato.Producto.NOMBRE_TABLA);
        data.setNotificationUri(getActivity().getContentResolver(), laUriBase); // suscripción a la Uri
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
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
    }
}
