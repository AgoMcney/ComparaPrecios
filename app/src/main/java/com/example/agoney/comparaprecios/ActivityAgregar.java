package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ActivityAgregar extends AppCompatActivity
        implements producto_nombre.OnFragmentInteractionListener,
        producto_precios.OnFragmentInteractionListener,
        producto_imagen.OnFragmentInteractionListener
{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    String familiaSeleccionada=null;
    static RadioGroup radioFamilia;
    static ClaseComun comun;
    boolean error=false; // controladores de errores
    static ArrayList<Producto> productos;
     EditText txtInputNombre;
    EditText editTextTienda1, editTextTienda2, editTextTienda3, editTextTienda4, editTextTienda5, editTextTienda6;
    Float precio1, precio2, precio3, precio4, precio5, precio6;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // activar el soporte para botón home
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager()); //  clase (mas abajo) que fragmenta la actividad
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container); // creamos el Paginador
        mViewPager.setAdapter(mSectionsPagerAdapter); // tiene que estar asociado a un adaptador, que es una clase que nos creamos
    // añade el menú de Tabs a la AppBar
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);  // tiene que estar asociada a un viewPager
        familiaSeleccionada = getString(R.string.familia12);
        FloatingActionButton fabGuardar = (FloatingActionButton) findViewById(R.id.fab_Guardar);
    // objetos comunes
         comun = (ClaseComun) getApplication();  // referencia a ese objeto que no desaparece.
         productos = comun.getProductos(); // creo arrayList temporal
    //  views
 //       txtInputNombre = (EditText)  findViewById(R.id.TxtInputNombre);
//        radioFamilia = (RadioGroup) findViewById(R.id.RadioGroupFamilia);
        editTextTienda1 = (EditText)  findViewById(R.id.editTextTienda1);
        editTextTienda2 = (EditText) findViewById(R.id.editTextTienda2);
        editTextTienda3 = (EditText) findViewById(R.id.editTextTienda3);
        editTextTienda4 = (EditText)  findViewById(R.id.editTextTienda4);
        editTextTienda5 = (EditText) findViewById(R.id.editTextTienda5);
        editTextTienda6 = (EditText)  findViewById(R.id.editTextTienda6);

        // botón Guardar
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    //      validar();
            if (error==false) // si no hay errores
                AgregarProducto();
    //      Toast.makeText (getApplicationContext(), "Hay "+ comun .getProductos().size()+" productos.", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Producto Guardado \n Hay "+ productos.size()+" productos. ", Snackbar.LENGTH_SHORT)
                   .setAction("Action", null).show();
            }
        });
        FloatingActionButton fabCancelar = (FloatingActionButton) findViewById(R.id.fab_Cancelar);
        fabCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cancelar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_agregar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ayuda) { // botón de ayuda de la app bar
            Intent intent= new Intent(getApplicationContext(), ActivityAyuda.class); // creamos el intento, de donde viene y a donde va
            intent.putExtra("origen",  "agregar" );
            startActivity(intent); // ejecuta el intento.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void precio1(float p1) {
            precio1 = p1;
    }

    @Override
    public void precio2(float p2) {
        precio2=p2;
    }

    @Override
    public void precio3(float p3) {
        precio3=p3;
    }

    @Override
    public void precio4(float p4) {
        precio4=p4;
    }

    @Override
    public void precio5(float p5) {
        precio5=p5;
    }

    @Override
    public void precio6(float p6) {
        precio6=p6;
    }

    @Override
    public void FamiliaProducto(String FamiliaProducto) {
        familiaSeleccionada=FamiliaProducto;
    }

    @Override
    public void CampoTexto(EditText CampoTexto) {
        txtInputNombre=CampoTexto;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_producto_nombre, container, false);
            // View rootView = inflater.inflate(R.layout.fragment_producto_nombre, container, false);
            //  TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {  // según posición devuelve un fragmento.
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                   return producto_nombre.newInstance(familiaSeleccionada);
        //           return PlaceholderFragment.newInstance(position + 1);
                case 1:
                  return producto_precios.newInstance();
        //          return PlaceholderFragment.newInstance(position + 1);
                case 2:
                  return producto_imagen.newInstance();
        //          return PlaceholderFragment.newInstance(position + 1);
            }

            // return PlaceholderFragment.newInstance(position + 1);
            return producto_nombre.newInstance(familiaSeleccionada);

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;  // pestañas que va a controlar el paginador
        }

        @Override
        public CharSequence getPageTitle(int position) {  // metodo que devuelve nombre de las páginas
            switch (position) {
                case 0:
                    return "Producto";
                case 1:
                    return "Precios";
                case 2:
                    return "Imagen";
            }
            return null;
        }
    }

    public void AgregarProducto(){
//        Producto nuevo = new Producto(txtInputNombre.getText().toString(), familiaSeleccionada,
//                precio1, precio2, precio3, precio4, precio5, precio6);
//    Toast.makeText(getApplication(), txtInputNombre.getText().toString(), Toast.LENGTH_SHORT).show();
    // creo el producto
        Producto nuevo = new Producto();
        nuevo.setFamilia(familiaSeleccionada);
        nuevo.setPrecio1(((precio1==null)? 0:precio1 ));
        nuevo.setPrecio2(((precio2==null)? 0:precio2 ));
        nuevo.setPrecio3(((precio3==null)? 0:precio3 ));
        nuevo.setPrecio4(((precio4==null)? 0:precio4 ));
        nuevo.setPrecio5(((precio5==null)? 0:precio5 ));
        nuevo.setPrecio6(((precio6==null)? 0:precio6 ));
    // añado el producto a la lista
        productos.add(nuevo); // añado nuevo producto al array de productos
        comun.setProductos(productos); // añado al array fijo el array temporal.
// muestro resultados
//        Toast.makeText(getApplication(),
//                "Familia: "+familiaSeleccionada
//                        + "\n"+ getString(R.string.tienda1) +  " precio: " + ((precio1==null)? "vacio":precio1 )
//                        + "\n"+ getString(R.string.tienda2) +  " precio: " + ((precio2==null)? "vacio":precio2 )
//                        + "\n"+ getString(R.string.tienda3) +  " precio: " + ((precio3==null)? "vacio":precio3 )
//                        + "\n"+ getString(R.string.tienda4) +  " precio: " + ((precio4==null)? "vacio":precio4 )
//                        + "\n"+ getString(R.string.tienda5) +  " precio: " + ((precio5==null)? "vacio":precio5 )
//                        + "\n"+ getString(R.string.tienda6) +  " precio: " + ((precio6==null)? "vacio":precio6 ),
//                Toast.LENGTH_LONG).show();

        // Log.e("Nombre nuevo:", nuevo.getNombre()); // control de errores

        Toast.makeText (getApplicationContext(), "El producto se ha guardado. "
    //                  + nuevo.nombre
                        + "\n Familia: "+nuevo.familia
                        + "\n"+ getString(R.string.tienda1) +  " precio: " +nuevo.getPrecio1()
                        + "\n"+ getString(R.string.tienda2) +  " precio: " +nuevo.getPrecio2()
                        + "\n"+ getString(R.string.tienda3) +  " precio: " +nuevo.getPrecio3()
                        + "\n"+ getString(R.string.tienda4) +  " precio: " +nuevo.getPrecio4()
                        + "\n"+ getString(R.string.tienda5) +  " precio: " +nuevo.getPrecio5()
                        + "\n"+ getString(R.string.tienda6) +  " precio: " +nuevo.getPrecio6()
                ,Toast.LENGTH_LONG).show();
    }
    public void validar(){
        // vacio los errores
        txtInputNombre.setError(null);
        editTextTienda1.setError(null);
        editTextTienda2.setError(null);
        editTextTienda3.setError(null);
        editTextTienda4.setError(null);
        editTextTienda5.setError(null);
        editTextTienda6.setError(null);
        error=false;
        // compruebo cada campo
        if (TextUtils.isEmpty( txtInputNombre.getText())){
            txtInputNombre.setError(getString(R.string.error_obligatorio)); // marcamos el error
            txtInputNombre.requestFocus(); // llevamos el foco al error
            error=true;
        }
        if (TextUtils.isEmpty(editTextTienda1.getText().toString())){
            precio1=0f; // si esta vacío le asigno un 0
                /*
                editTextTienda1.setError(getString(R.string.error_obligatorio)); // marcamos el error
                editTextTienda1.requestFocus(); // llevamos el foco al error
                error=true;
                */
        } else precio1 = Float.parseFloat(editTextTienda1.getText().toString()); // pasar el valor editable a float
        if (TextUtils.isEmpty(editTextTienda2.getText().toString())){
            precio2=0f; // si esta vacío le asigno un 0
                /*
                editTextTienda2.setError(getString(R.string.error_obligatorio)); // marcamos el error
                editTextTienda2.requestFocus(); // llevamos el foco al error
                error=true;
                */
        } else precio2 = Float.parseFloat(editTextTienda2.getText().toString()); // pasar el valor editable a float
        if (TextUtils.isEmpty(editTextTienda3.getText().toString())){
            precio3=0f; // si esta vacío le asigno un 0
                /*
                editTextTienda3.setError(getString(R.string.error_obligatorio)); // marcamos el error
                editTextTienda3.requestFocus(); // llevamos el foco al error
                error=true;
                */
        } else precio3 = Float.parseFloat(editTextTienda3.getText().toString()); // pasar el valor editable a float
        if (TextUtils.isEmpty(editTextTienda4.getText().toString())){
            precio4=0f; // si esta vacío le asigno un 0
                /*
                editTextTienda4.setError(getString(R.string.error_obligatorio)); // marcamos el error
                editTextTienda4.requestFocus(); // llevamos el foco al error
                error=true;
                */
        } else precio4 = Float.parseFloat(editTextTienda4.getText().toString()); // pasar el valor editable a float
        if (TextUtils.isEmpty(editTextTienda5.getText().toString())){
            precio5=0f; // si esta vacío le asigno un 0
                /*
                editTextTienda5.setError(getString(R.string.error_obligatorio)); // marcamos el error
                editTextTienda5.requestFocus(); // llevamos el foco al error
                error=true;
                */
        } else precio5 = Float.parseFloat(editTextTienda5.getText().toString()); // pasar el valor editable a float
        if (TextUtils.isEmpty(editTextTienda6.getText().toString())){
            precio6=0f; // si esta vacío le asigno un 0
                /*
                editTextTienda6.setError(getString(R.string.error_obligatorio)); // marcamos el error
                editTextTienda6.requestFocus(); // llevamos el foco al error
                error=true;
                */
        } else precio6 = Float.parseFloat(editTextTienda6.getText().toString()); // pasar el valor editable a float

    }













}
