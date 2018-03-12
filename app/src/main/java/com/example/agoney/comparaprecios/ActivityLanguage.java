package com.example.agoney.comparaprecios;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class ActivityLanguage extends AppCompatActivity {
// clase para cambiar el idioma
    final int SPANISH=1;
    final int ENGLISH=2;
    final int CANARY=3;
    ImageView imageViewLanguage;
    RadioGroup radioGroupIdioma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // activar el soporte para botón home
         imageViewLanguage = (ImageView) findViewById(R.id.ImageViewLanguage);

        radioGroupIdioma=(RadioGroup) findViewById(R.id.RadioGroupLanguage);
        radioGroupIdioma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){  // según el id del radio pulsado hacer...
                    case R.id.RadioBtnSpanish:
                        cambiarIdioma(SPANISH); // selecciona español
                        break;
                    case R.id.RadioBtnEnglish:
                        cambiarIdioma(ENGLISH); // selecciona ingles
                        break;
                    case R.id.RadioBtnCanario:
                        cambiarIdioma(CANARY); // selecciona canario
                        break;
                }
            }
        });
    }
    @Override // metodo para aplicar un app bar
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_language, menu); // cargo el menu general
        return true;

    }
    @Override // implementar acciones al pulsar en un item del appbar
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_ayuda:// botón de ayuda de la app bar
                Intent intent = new Intent(getApplicationContext(), ActivityAyuda.class); // creamos el intento, de donde viene y a donde va
                intent.putExtra("origen", "language");
                startActivity(intent); // ejecuta el intento.
                return true;
            case R.id.action_uk:// botón de ayuda de la app bar
                // cambiarIdioma(SPANISH);
                radioGroupIdioma.check(R.id.RadioBtnEnglish);
                return true;
            case R.id.action_spain:// botón de ayuda de la app bar
                radioGroupIdioma.check(R.id.RadioBtnSpanish);
                // cambiarIdioma(ENGLISH);
                return true;
            case R.id.action_cana:// botón de ayuda de la app bar
                radioGroupIdioma.check(R.id.RadioBtnCanario);
                // cambiarIdioma(CANARY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void cambiarIdioma(int idioma){  // metodo que cambia el idioma
        String language=null;
        switch (idioma){ // seleccionamos uno u otro lenguaje
            case 1:  // SPANISH
                language ="es";
                imageViewLanguage.setImageResource(R.mipmap.ic_spa);
                // @string/cana
                break;
            case 2:  //  ENGLISH
                language="en";
                imageViewLanguage.setImageResource(R.mipmap.ic_uk);
                break;
            case 3:  //  CANARY
                language="can";
                imageViewLanguage.setImageResource(R.mipmap.ic_canary);
                break;
        }
        Locale locale = new Locale (language);
        Locale.setDefault(locale);
        Resources resources = getApplication().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//        Intent intent = new Intent (this, ActivityLanguage.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // con este flag se refresca toda la información
//        startActivity(intent);
        actualizarViews();        // reinicio la actividad
    }
    void actualizarViews(){ // metodo que refresca esta activity
        TextView textView = (TextView) findViewById(R.id.txtLenguage);
        textView.setText(getString(R.string.elegir_languaje)); // que vuelva a escribir el textView
        RadioButton radioButtonSpa = (RadioButton)findViewById(R.id.RadioBtnSpanish);
        radioButtonSpa.setText(getString((R.string.spa))); // que vuelva a escribir el RadioButton
        RadioButton radioButtonEng = (RadioButton)findViewById(R.id.RadioBtnEnglish);
        radioButtonEng.setText(getString((R.string.eng))); // que vuelva a escribir el RadioButton
        RadioButton radioButtonCan = (RadioButton)findViewById(R.id.RadioBtnCanario);
        radioButtonCan.setText(getString((R.string.can))); // que vuelva a escribir el RadioButton

    }
}
