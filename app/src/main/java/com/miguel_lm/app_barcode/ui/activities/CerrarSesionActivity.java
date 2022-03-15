package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_EMAIL;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_FICHERO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_PASSWORD;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;

public class CerrarSesionActivity extends AppCompatActivity {

    //Declaración de variables
    public TextView tv_email, tv_password, tv_titulo_cerrar_sesion;
    ImageView btn_volver;
    public Button btn_cerrarSesion;
    String correo;
    String pswrd;
    public View ondaSupCerrarSesion, ondaInfCerrarSesion;
    View top_view_cerrar_sesion, bottom_view_cerrar_sesion;

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
        setContentView(R.layout.activity_cerrar_sesion);
        init();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                this, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeCerrarSesionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionOndasView.gestionarOndasViewCerrarSesion(this, this);
        obtenerDatosSesionUsuario();

        correo = tv_email.getText().toString();
        pswrd = tv_password.getText().toString();

        tv_email.setSelected(true);
        tv_password.setSelected(true);

        btn_volver.setOnClickListener(v -> onBackPressed());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(this);
        super.onPause();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        gestionOndasView.gestionarOndasViewCerrarSesion(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                this, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeCerrarSesionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        gestionarColorApp.colorDefault(this);
        super.onResume();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        gestionOndasView.gestionarOndasViewCerrarSesion(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                this, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeCerrarSesionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        gestionarColorApp.colorDefault(this);
        super.onRestart();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        gestionOndasView.gestionarOndasViewCerrarSesion(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                this, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeCerrarSesionActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    //Método que inicializa los componentes.
    public void  init(){

        tv_titulo_cerrar_sesion = findViewById(R.id.tv_titulo_cerrar_sesion);
        tv_email = findViewById(R.id.tv_email);
        tv_password = findViewById(R.id.tv_password);
        btn_volver = findViewById(R.id.btn_Volver);
        btn_cerrarSesion = findViewById(R.id.btn_cerrarSesion);
        top_view_cerrar_sesion = findViewById(R.id.top_view_cerrar_sesion);
        bottom_view_cerrar_sesion = findViewById(R.id.bottom_view_cerrar_sesion);
        ondaSupCerrarSesion = findViewById(R.id.waveTop_cerrarSesion);
        ondaInfCerrarSesion = findViewById(R.id.waveBottom_cerrar_sesion);
    }

    //Método onClick que cierra sesión y vuelve a la activity de login.
    public void onClickCerrarSesion(View view){

        LoginActivity.cambiarEstado(CerrarSesionActivity.this,false);
        Intent intent = new Intent(CerrarSesionActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //Método para obtener los datos del email y password de las SheredPreferences.
    public void obtenerDatosSesionUsuario(){

        SharedPreferences preferencias = this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);

        tv_email.setText(preferencias.getString(PREF_EMAIL, ""));
        tv_password.setText(preferencias.getString(PREF_PASSWORD, ""));
    }

    //Método para volver a la activity anterior.
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, ScannerActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}