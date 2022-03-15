package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CHANGE_COLOR_APP;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.FILE_THEME_PREFERENCES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.VISIBILITY_WAVES;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.fragments.FragmentContenedor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CustomActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public Switch switchOndas;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public Switch switchColor;
    public  View OndaSupPersonalizacion;
    public  View OndaInfPersonalizacion;
    ImageView btn_volver;
    List<CharSequence> listaFuentes;
    List<CharSequence> listaSize;
    public Spinner spinner_fuentes;
    public Spinner spinner_size;
    public TextView tv_spinner_item;
    public TextView tv_titulo_personalizacion;
    public View top_view, bottom_view_personalizacion;
    public CardView cardViewPersonalizacion;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    GestionOndasView gestionOndasView = new GestionOndasView();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    @SuppressLint("SdCardPath") File preferenciasTema = new File(FILE_THEME_PREFERENCES);


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        inits();
        gestionarColorApp.gestionColorTema(this, this, null, null);
        gestionOndasView.gestionarOndasViewCustomActivity(this, this);
        SwitchOndas();
        SwitchColorRojo();
        spinnerSeleccionFuente();
        spinnerSeleccionSize();
        btn_volver.setOnClickListener(v -> onBackPressed());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(this);
        super.onPause();
        spinner_fuentes.setSelection(seleccionFuente.getValuePreferenceTypeface(CustomActivity.this));
        spinner_size.setSelection(seleccionSize.getValuePreferenceSize(CustomActivity.this));
        gestionarColorApp.gestionColorTema(this, this, null, null);
        gestionOndasView.gestionarOndasViewCustomActivity(this, this);
        SwitchOndas();
        SwitchColorRojo();
        spinnerSeleccionFuente();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        gestionarColorApp.colorDefault(this);
        super.onResume();
        spinner_fuentes.setSelection(seleccionFuente.getValuePreferenceTypeface(CustomActivity.this));
        spinner_size.setSelection(seleccionSize.getValuePreferenceSize(CustomActivity.this));
        gestionarColorApp.gestionColorTema(this, this, null, null);
        gestionOndasView.gestionarOndasViewCustomActivity(this, this);
        SwitchOndas();
        SwitchColorRojo();
        spinnerSeleccionFuente();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        gestionarColorApp.colorDefault(this);
        super.onRestart();
        spinner_fuentes.setSelection(seleccionFuente.getValuePreferenceTypeface(CustomActivity.this));
        spinner_size.setSelection(seleccionSize.getValuePreferenceSize(CustomActivity.this));
        gestionarColorApp.gestionColorTema(this, this, null, null);
        gestionOndasView.gestionarOndasViewCustomActivity(this, this);
        SwitchOndas();
        SwitchColorRojo();
        spinnerSeleccionFuente();
    }

    //Método para inicializar los componentes de la Activity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void inits(){

        switchOndas = findViewById(R.id.ondas_toolbar);
        switchColor = findViewById(R.id.color_rojo);
        OndaSupPersonalizacion = findViewById(R.id.waveTop);
        OndaInfPersonalizacion = findViewById(R.id.waveBottom_personalizacion);
        btn_volver = findViewById(R.id.btn_volver_personalizacion);
        top_view = findViewById(R.id.top_view);
        bottom_view_personalizacion = findViewById(R.id.bottom_view_personalizacion);
        cardViewPersonalizacion = findViewById(R.id.cardViewPersonalizacion);

        switchOndas.setTypeface(ResourcesCompat.getFont(this, R.font.barlow));
        switchColor.setTypeface(ResourcesCompat.getFont(this, R.font.barlow));

        spinner_fuentes = findViewById(R.id.spinner_fuentes);
        spinner_size = findViewById(R.id.spinner_size);
        tv_spinner_item = findViewById(R.id.tv_spinner_item);
        tv_titulo_personalizacion = findViewById(R.id.tv_titulo_personalizacion);
        spinner_fuentes.setSelection(seleccionFuente.getValuePreferenceTypeface(CustomActivity.this));
    }

    //Método para guardar los cambios al mover los switch de posición.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void guardarCambiosSwitch() {

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(VISIBILITY_WAVES, switchOndasCheck());
        editor.putBoolean(CHANGE_COLOR_APP, switchColorRojoCheck());
        editor.apply();

        startActivity(getIntent());
        finish();
        overridePendingTransition(0, 0);

        spinner_fuentes.setSelection(seleccionFuente.getValuePreferenceTypeface(this));
        spinner_size.setSelection(seleccionSize.getValuePreferenceSize(this));
        seleccionSize.gestionTextSizeListaSpinner(this, spinner_size.getSelectedView(), this);
        seleccionFuente.gestionFuenteListaSpinner(this, spinner_fuentes.getSelectedView(), this);
    }

    //Método para ocultar o mostrar ondas en actionBar sí el switch esta activado o no y devolviendo true o false según el caso..
    private boolean switchOndasCheck(){

        boolean ocultado;
        if (switchOndas.isChecked()) {
            gestionOndasView.gestionarViewOndasFragmentContenedor(this, new FragmentContenedor());
            ocultado = true;
        } else {
            gestionOndasView.gestionarViewOndasFragmentContenedor(this, new FragmentContenedor());
            ocultado = false;
        }

        return ocultado;
    }

    //Método para cambiar el color dependiendo de si el switch está activado o no y devoñviendo true o false según el caso.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private boolean switchColorRojoCheck(){

        boolean activadoColorRojo;
        if(switchColor.isChecked()){
            gestionarColorApp.colorDefault(CustomActivity.this);
            gestionarColorApp.gestionColorTema(this, this, null, null);
            activadoColorRojo = true;

        } else {
            gestionarColorApp.colorDefault(CustomActivity.this);
            gestionarColorApp.gestionColorTema(this, this, null, null);
            activadoColorRojo = false;
        }

        return activadoColorRojo;
    }

    //Método para gestionar el listener del switch y guardar los cambios de este.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void SwitchOndas(){

        boolean estado_switch = gestionOndasView.getValuePreferenceOndas(getApplicationContext());
        switchOndas.setChecked(estado_switch);
        switchOndas.setOnCheckedChangeListener((buttonView, isChecked) -> guardarCambiosSwitch());
    }

    //Método para gestionar el listener del switch y guardar los cambios de este.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void SwitchColorRojo(){

        boolean estado_switch = gestionarColorApp.getValuePreferenceAzul(getApplicationContext());
        switchColor.setChecked(estado_switch);
        switchColor.setOnCheckedChangeListener((buttonView, isChecked) -> guardarCambiosSwitch());
    }

    //Método para mostrar y seleccionar elemento de la lista del Spinner de fuentes.
    public void spinnerSeleccionFuente() {

        listaFuentes = new ArrayList<>();
        seleccionFuente.elementosDeLaListaSpinner(listaFuentes);
        gestionarColorApp.gestionColorIconoSelector(this);
        spinner_fuentes.setAdapter(adapterSpinnerFuentes());
        spinner_fuentes.setSelection(seleccionFuente.getValuePreferenceTypeface(CustomActivity.this));
        eventoItemSeleccionado();
    }

    //Método para mostrar y seleccionar de la lista del Spinner el tamaño de la fuente.
    public void spinnerSeleccionSize() {

        listaSize = new ArrayList<>();
        seleccionSize.elementosDeLaListaSpinnerSize(listaSize);
        gestionarColorApp.gestionColorIconoSelectorSpinnerTextSize(this);
        spinner_size.setAdapter(adapterSpinnerSize());
        spinner_size.setSelection(seleccionSize.getValuePreferenceSize(CustomActivity.this));
        eventoItemSeleccionadoSize();
    }

    //Método para la creación y gestión del Adapter del Spinner.
    private ArrayAdapter<CharSequence> adapterSpinnerFuentes() {

        return new ArrayAdapter<CharSequence>(CustomActivity.this, R.layout.spinner_item_fonts, listaFuentes){

            @RequiresApi(api = Build.VERSION_CODES.P)
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                return seleccionFuente. gestionFuenteListaSpinner(CustomActivity.this, v, CustomActivity.this);
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                return seleccionFuente.gestionFuenteListaSpinner(CustomActivity.this, v, CustomActivity.this);
            }
        };
    }

    //Método para la creación y gestión del Adapter del Spinner de tamaños de letra.
    private ArrayAdapter<CharSequence> adapterSpinnerSize() {

        return new ArrayAdapter<CharSequence>(CustomActivity.this, R.layout.spinner_item_fonts, listaSize){

            @RequiresApi(api = Build.VERSION_CODES.P)
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                seleccionFuente.gestionFuenteListaSpinner(CustomActivity.this, v, CustomActivity.this);
                return seleccionSize.gestionTextSizeListaSpinner(CustomActivity.this, v, CustomActivity.this);
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                seleccionFuente.gestionFuenteListaSpinner(CustomActivity.this, v, CustomActivity.this);
                return seleccionSize.gestionTextSizeListaSpinner(CustomActivity.this, v, CustomActivity.this);
            }
        };
    }

    //Método para la gestión del item seleccionado de la lista del Spinner.
    private void eventoItemSeleccionado() {

        spinner_fuentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                seleccionFuente.posicionSpinner(CustomActivity.this, CustomActivity.this, position, view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CustomActivity.this, "Por favor, seleccione una opción.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método para la gestión del item seleccionado de la lista del Spinner de tamaños de letra.
    private void eventoItemSeleccionadoSize() {

        spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.P)
             @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                seleccionSize.posicionSpinnerSize(CustomActivity.this, CustomActivity.this, position, view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CustomActivity.this, "Por favor, seleccione una opción.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método para volver a la activity anterior.
    public void volverAtras(){

        Intent intent = new Intent(this, ScannerActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBackPressed() {
        gestionarColorApp.colorDefault(this);
        gestionOndasView.gestionarOndasViewCustomActivity(this, this);
        gestionarColorApp.gestionColorTema(this, this, null, null);
        seleccionFuente.guardarCambiosTipoFuente(spinner_fuentes.getSelectedItemPosition(), this, this);
        seleccionSize.guardarCambiosTextSize(spinner_size.getSelectedItemPosition(), this, this);
        volverAtras();
    }
}