package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_DIRECCION_IP;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_FICHERO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_PUERTO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.InetAddresses;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;


public class ConfiguracionActivity extends AppCompatActivity {

    private static final String LOG_TAG = "log_json";

    //Declaración de variables.
    public EditText ed_direccionIp, ed_puerto;
    ImageView btn_volver;
    public TextView tv_titulo_confg;
    public Button btn_aceptarDialog, btn_aceptar;
    public View ondaSupConfiguracion;
    public View ondaInfConfiguracion;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    GestionOndasView gestionOndasView = new GestionOndasView();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(this);
        super.onCreate(savedInstanceState);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        setContentView(R.layout.activity_configuracion);
        conectividad();
        inits();
        gestionOndasView.gestionarOndasViewConfiguracionActivity(this, this);
        btn_volver.setOnClickListener(v -> onBackPressed());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(this);
        super.onPause();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        gestionOndasView.gestionarOndasViewConfiguracionActivity(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, ConfiguracionActivity.this, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeConfiguracionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        gestionarColorApp.colorDefault(this);
        super.onResume();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        gestionOndasView.gestionarOndasViewConfiguracionActivity(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, ConfiguracionActivity.this, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeConfiguracionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        gestionarColorApp.colorDefault(this);
        super.onRestart();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        gestionOndasView.gestionarOndasViewConfiguracionActivity(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, ConfiguracionActivity.this, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeConfiguracionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    //Método que mapea e inicializa los diferentees componentes de la vista.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void inits() {

        tv_titulo_confg = findViewById(R.id.tv_titulo_confg);
        ed_direccionIp = findViewById(R.id.ed_direccion_ip);
        ed_puerto = findViewById(R.id.ed_puerto);
        btn_volver = findViewById(R.id.btn_volver_confg);
        btn_aceptar = findViewById(R.id.btn_aceptar);
        ondaSupConfiguracion = findViewById(R.id.waveTopConfg);
        ondaInfConfiguracion = findViewById(R.id.waveBottom_confg);

        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, ConfiguracionActivity.this, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeConfiguracionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    //Método onClick para guardar la configuración desde el botón 'aceptar'.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void onClickGuardarCofig(View view){

        if(conectividad()){
            if(ed_direccionIp.getText().toString().isEmpty() && ed_puerto.getText().toString().isEmpty()){
                Toast.makeText(this, "Los campos están vacíos", Toast.LENGTH_SHORT).show();

            } else if(ed_direccionIp.getText().toString().isEmpty()){
                Toast.makeText(this, "El campo 'Dirección IP' está vacío", Toast.LENGTH_SHORT).show();

            } else if(ed_puerto.getText().toString().isEmpty()){
                Toast.makeText(this, "El campo 'Puerto' está vacío", Toast.LENGTH_SHORT).show();

            } else {
               if(validacionIp(ed_direccionIp.getText().toString())){
                  alerDialogConfig();
               } else {
                   Toast.makeText(this, "La dirección IP no es valida.", Toast.LENGTH_SHORT).show();
               }
            }
        }
    }

    //AlertDialog para aceptar los datos guardados.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void alerDialogConfig() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_config, null);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();

        btn_aceptarDialog = dialogLayout.findViewById(R.id.btn_aceptarConfig);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, ConfiguracionActivity.this, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeConfiguracionActivity(
                seleccionSize.getValuePreferenceSize(this), this);

        btn_aceptarDialog.setOnClickListener(v -> {
            guardarConfiguracion();
            dialog.dismiss();
            onBackPressed();
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //Método para la validación de la dirección IP. Sí lo intoducido en el campo 'ed_direccionIp'
    //no se corresponde con una dirección IP se mostrará un mensaje por pantalla.
    public boolean validacionIp(String ip) {

        boolean direccion;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            direccion = InetAddresses.isNumericAddress(ip);

        } else {
            direccion = Patterns.IP_ADDRESS.matcher(ip).matches();
        }

        return direccion;
    }

    //Método donde se guardan los datos de ip y puerto de la configuración.
    public void guardarConfiguracion(){
        String ip = ed_direccionIp.getText().toString();
        String puerto = ed_puerto.getText().toString();

        if(!ip.isEmpty() && !puerto.isEmpty()){
            SharedPreferences preferencias = this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString(PREF_DIRECCION_IP, ip);
            editor.putString(PREF_PUERTO, puerto);
            editor.apply();

            ClienteApiFactoria.getInstance().setBaseUrl("http://" + ip + ":" + puerto);

            startActivity(getIntent());
            finish();
            overridePendingTransition(0, 0);

        } else {
            Toast.makeText(this,"Error, los datos no han sido guardados.",Toast.LENGTH_SHORT).show();
        }
    }

    //Método para comprobar sí hay o no conexión a la red.
    public boolean conectividad() {

        Log.d(LOG_TAG, "Comprobando conexión");
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean connected = (networkInfo != null && networkInfo.isConnected());

        if (!connected) {
            Toast.makeText(this, "Sin conexión", Toast.LENGTH_SHORT).show();
        }

        return connected;
    }

    //Método para volver a la activity anterior.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBackPressed() {

        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, ConfiguracionActivity.this, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeConfiguracionActivity(
                seleccionSize.getValuePreferenceSize(this), this);

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}