package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.agoney.comparaprecios.Fragment.Fragment_agregar_imagen;
import com.example.agoney.comparaprecios.Fragment.Fragment_agregar_nombre;
import com.example.agoney.comparaprecios.Fragment.Fragment_agregar_precios;
import com.example.agoney.comparaprecios.ProveedorDeContenido.Contrato;
import com.example.agoney.comparaprecios.ProveedorDeContenido.ProductoProveedor;
import com.example.agoney.comparaprecios.pojos.Producto;

import java.io.FileNotFoundException;


public class ActivityModificar extends AppCompatActivity
        implements Fragment_agregar_nombre.OnFragmentInteractionListener,
        Fragment_agregar_precios.OnFragmentInteractionListener,
        Fragment_agregar_imagen.OnFragmentInteractionListener
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
    private ViewPager mViewPager;
    String familiaSeleccionada=null;
    static RadioGroup radioFamilia;
    boolean error=false; // controladores de errores
    EditText editTextTienda1, editTextTienda2, editTextTienda3, editTextTienda4, editTextTienda5, editTextTienda6;
    Float precio1, precio2, precio3, precio4, precio5, precio6;
    Fragment_agregar_nombre fragmentoAgregarNombre;
    Fragment_agregar_precios fragment_agregar_precios;
    Fragment_agregar_imagen  fragment_agregar_imagen;
    Bundle BundleProducto;
    int ProductoId;
    Bitmap imagen;
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
    //  views
        editTextTienda1 = (EditText) findViewById(R.id.editTextTienda1);
        editTextTienda2 = (EditText) findViewById(R.id.editTextTienda2);
        editTextTienda3 = (EditText) findViewById(R.id.editTextTienda3);
        editTextTienda4 = (EditText) findViewById(R.id.editTextTienda4);
        editTextTienda5 = (EditText) findViewById(R.id.editTextTienda5);
        editTextTienda6 = (EditText) findViewById(R.id.editTextTienda6);

    //recupero los datos
        ProductoId = this.getIntent().getExtras().getInt(Contrato.Producto._ID); // recupero el id
        Producto producto=  ProductoProveedor.readRecord(getContentResolver(), ProductoId); // recupero el producto de ese ID

        BundleProducto = new Bundle();
        BundleProducto.putInt("id", producto.getID() );
        BundleProducto.putString("nombre", producto.getNombre() );
        BundleProducto.putString("familia", producto.getFamilia() );
        BundleProducto.putFloat("precio1",producto.getPrecio1());
        BundleProducto.putFloat("precio2",producto.getPrecio2());
        BundleProducto.putFloat("precio3",producto.getPrecio3());
        BundleProducto.putFloat("precio4",producto.getPrecio4());
        BundleProducto.putFloat("precio5",producto.getPrecio5());
        BundleProducto.putFloat("precio6",producto.getPrecio6());

//        fragmentoAgregarNombre.setRadioFamilia(producto.getFamilia());
//        fragmentoAgregarNombre.setRadioFamilia(producto.getFamilia());
//        familiaSeleccionada = getString(R.string.familia12);
//        fragment_agregar_precios.seteditTextTienda1(producto.getPrecio1());
//        fragment_agregar_precios.seteditTextTienda2(producto.getPrecio2());
//        fragment_agregar_precios.seteditTextTienda3(producto.getPrecio3());
//        fragment_agregar_precios.seteditTextTienda4(producto.getPrecio4());
//        fragment_agregar_precios.seteditTextTienda5(producto.getPrecio5());
//        fragment_agregar_precios.seteditTextTienda6(producto.getPrecio6());

        FloatingActionButton fabGuardar = (FloatingActionButton) findViewById(R.id.fab_Guardar);
    // botón Guardar
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            validarNombre();
            if (error==false) {// si no hay errores
                AgregarProducto();
                Snackbar.make(view, "Producto Modificado \n ", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
                finish();
            }
            }
        });
        FloatingActionButton fabCancelar = (FloatingActionButton) findViewById(R.id.fab_Cancelar);
        fabCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cancelar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
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
            intent.putExtra("origen",  "modificar" );
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
    public void IntercambioImagen(Bitmap img) {
        imagen=img;
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

            switch (position) {
                case 0:
                    fragmentoAgregarNombre = Fragment_agregar_nombre.newInstance();
                    fragmentoAgregarNombre.setArguments (BundleProducto);
//                    fragmentoAgregarNombre.setTextTxtInputNombre(BundleProducto.getString ("nombre")  ); // asigno el nombre al campo
                    return fragmentoAgregarNombre;
                case 1:
                    fragment_agregar_precios =Fragment_agregar_precios.newInstance();
                    fragment_agregar_precios.setArguments (BundleProducto);
                    return fragment_agregar_precios;
                case 2:
                    fragment_agregar_imagen= Fragment_agregar_imagen.newInstance();
                    fragment_agregar_imagen.setArguments (BundleProducto);
                    return  fragment_agregar_imagen;
            }
            return Fragment_agregar_nombre.newInstance();
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
                    return getString(R.string.tab1);
                case 1:
                    return getString(R.string.tab2);
                case 2:
                    return getString(R.string.tab3);
            }
            return null;
        }
    }

    public void AgregarProducto(){
//        Producto nuevo = new Producto(txtInputNombre.getText().toString(), familiaSeleccionada,
//                precio1, precio2, precio3, precio4, precio5, precio6);
//    Toast.makeText(getApplication(), txtInputNombre.getText().toString(), Toast.LENGTH_SHORT).show();
    // creo el producto
        Producto nuevo = new Producto(
                ProductoId,
                fragmentoAgregarNombre.getTextTxtInputNombre(),
                familiaSeleccionada,
                ((precio1==null)? 0:precio1 ),
                ((precio2==null)? 0:precio2 ),
                ((precio3==null)? 0:precio3 ),
                ((precio4==null)? 0:precio4 ),
                ((precio5==null)? 0:precio5 ),
                ((precio6==null)? 0:precio6 ),
                imagen
        );

    // añado el producto a la lista
        ProductoProveedor.update(getContentResolver(), nuevo, this);  // lo inserto en el proveedor de contenido
// muestro resultados
       Toast toast_agregado= Toast.makeText (getApplicationContext(), "El producto se ha modificado. "
                        + "\n - "+ getString(R.string.toast_name) + nuevo.getNombre()
                        + "\n"+ getString(R.string.toast_family)+ nuevo.getFamilia()
                        + "\n"+ getString(R.string.tienda1) + " "+ getString(R.string.toast_price) +nuevo.getPrecio1()
                        + "\n"+ getString(R.string.tienda2) + " "+ getString(R.string.toast_price) +nuevo.getPrecio2()
                        + "\n"+ getString(R.string.tienda3) + " "+ getString(R.string.toast_price) +nuevo.getPrecio3()
                        + "\n"+ getString(R.string.tienda4) + " "+ getString(R.string.toast_price) +nuevo.getPrecio4()
                        + "\n"+ getString(R.string.tienda5) + " "+ getString(R.string.toast_price) +nuevo.getPrecio5()
                        + "\n"+ getString(R.string.tienda6) + " "+ getString(R.string.toast_price) +nuevo.getPrecio6()
                ,Toast.LENGTH_LONG);
       toast_agregado.setGravity(Gravity.CENTER, 0,0); // asigno gravedad al toast
       toast_agregado.show(); // muestro el toast
    }
    public void validarNombre(){
        error =false; // reseta el error
        fragmentoAgregarNombre.setErrorTxtInputNombre(null); // resetea el error
        // if (fragmentoAgregarNombre.getTextTxtInputNombre()== null|fragmentoAgregarNombre.getTextTxtInputNombre() ==""|fragmentoAgregarNombre.getTextTxtInputNombre().isEmpty()) {
        if (fragmentoAgregarNombre.getTextTxtInputNombre().isEmpty()) {
            fragmentoAgregarNombre.setErrorTxtInputNombre(getString(R.string.error_obligatorio));
            fragmentoAgregarNombre.FocusTxtInputNombre(); // llevamos el foco al error
            error=true;
        }
    }
    public int DetectarFamilia (String familiaBuscada){
        int idFamilia=0;
        int cont =0;
      String[] familias = getResources().getStringArray(R.array.familias);
//        ArrayList<String> ArrayFamilias = new ArrayList<String>( getResources().getStringArray(R.array.familias) ) ;
//        String[][] familias ={ getResources().getStringArray(R.array.familias)};

        for (String s : familias ){
            if (familiaBuscada==s.toString()){
//                idFamilia = familias.
            }

        }


        return idFamilia;
    }
}
