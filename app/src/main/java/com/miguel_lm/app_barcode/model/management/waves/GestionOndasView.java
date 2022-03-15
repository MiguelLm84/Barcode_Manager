package com.miguel_lm.app_barcode.model.management.waves;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.WAVES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NO_WAVES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.VISIBILITY_WAVES;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Switch;

import com.miguel_lm.app_barcode.ui.activities.CerrarSesionActivity;
import com.miguel_lm.app_barcode.ui.activities.ConfiguracionActivity;
import com.miguel_lm.app_barcode.ui.activities.CustomActivity;
import com.miguel_lm.app_barcode.ui.activities.LoginActivity;
import com.miguel_lm.app_barcode.ui.activities.ReservaActivity;
import com.miguel_lm.app_barcode.ui.activities.ScannerActivity;
import com.miguel_lm.app_barcode.ui.fragments.FragmentContenedor;
import com.miguel_lm.app_barcode.ui.fragments.FragmentScanner;


public class GestionOndasView {

    //Método para comprobar sí el el modo almacenado en las Shared Preferences tiene que mostar o no
    //las Views con las Ondas en los ActionBar y NavigationBar de las pantallas.
    public boolean getValuePreferenceOndas(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        return  preferences.getBoolean(VISIBILITY_WAVES, false);
    }

    //Método para ocultar las ondas del diseño de la pantalla CustomActivity.
    public void gestionarOndasViewCustomActivity(Context context, CustomActivity customActivity){

        gestionarOndas(context, customActivity.OndaSupPersonalizacion,
                customActivity.OndaInfPersonalizacion, customActivity.switchOndas, NO_WAVES, WAVES);
    }

    //Método para mostrar las ondas del diseño de la pantalla FragmentContenedor.
    public void gestionarViewOndasFragmentContenedor(Context context, FragmentContenedor fragContenedor){

        gestionarOndaIndividual(context, fragContenedor.ondaSupScannerActivity);
    }

    //Método para mostrar las ondas del diseño de la pantalla FragmentScanner.
    public void gestionarOndasViewFragmentScanner(Context context, FragmentScanner fragmentScanner){

        if(getValuePreferenceOndas(context)){
            fragmentScanner.ondaSupToolbar.setVisibility(View.INVISIBLE);
        } else {
            fragmentScanner.ondaSupToolbar.setVisibility(View.VISIBLE);
        }
    }

    //Método para mostrar las ondas del diseño de la pantalla ScannerActivity.
    public void gestionarOndasViewScannerActivity(Context context, ScannerActivity scannerActivity) {

        gestionarOndaIndividual(context, scannerActivity.ondaInfScannerActivity);
    }

    //Método para mostrar las ondas del diseño de la pantalla LoginActivity.
    public void gestionarOndasViewLoginActivity(Context context, LoginActivity loginActivity) {

        gestionarOndaIndividual(context, loginActivity.ondaInfLogin);
    }

    //Método para mostrar las ondas del diseño de la pantalla ReservaActivity.
    public void gestionarOndasViewReservasActivity(Context context, ReservaActivity reservaActivity){

        gestionarOndas(context, reservaActivity.ondaSupReservaActivity,
                reservaActivity.ondaInfReservaActivity, null, 0, 0);
    }

    //Método para mostrar las ondas del diseño de la pantalla ConfiguraciónActivity.
    public void gestionarOndasViewConfiguracionActivity(Context context, ConfiguracionActivity configuracionActivity){

        gestionarOndas(context, configuracionActivity.ondaSupConfiguracion,
                configuracionActivity.ondaInfConfiguracion, null, 0, 0);
    }

    //Método para mostrar las ondas del diseño de la pantalla CerrarSesiónActivity.
    public void gestionarOndasViewCerrarSesion(Context context, CerrarSesionActivity cerrarSesionActivity){

        gestionarOndas(context, cerrarSesionActivity.ondaSupCerrarSesion,
                cerrarSesionActivity.ondaInfCerrarSesion, null, 0, 0);
    }

    //Método para gestionar la visibilidad de las ondas de todas las pantallas de la aplicación.
    private void gestionarOndas(Context context, View viewSup, View viewInf,
                                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchOndas,
                                int textoSinOndas, int textoConOndas) {

        if(getValuePreferenceOndas(context)){
            visibilidadOndasView(context, viewSup, viewInf, switchOndas, textoSinOndas, View.INVISIBLE, View.INVISIBLE);

        } else {
            visibilidadOndasView(context, viewSup, viewInf, switchOndas, textoConOndas, View.VISIBLE, View.VISIBLE);
        }
    }

    //Método para mostrar u ocultar las ondas de las pantallas según se establezca en los parámetros.
    private void visibilidadOndasView(Context context, View viewSup, View viewInf,
                                      @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchOndas,
                                      int textoOndas, int visibilidadSup, int visibilidadInf) {

        if(getValuePreferenceOndas(context)){
            if(viewSup != null && viewInf != null){
                viewSup.setVisibility(visibilidadSup);
                viewInf.setVisibility(visibilidadInf);
                if(switchOndas != null){
                    switchOndas.setText(context.getResources().getText(textoOndas));
                }
            }
        }
    }

    //Método para gestionar las ondas en la pantalla de ScannerActivity y su Fragment hijo (FragmentScanner)
    //ya que cada un gestiona la onda superior y el otro la inferior.
    private void gestionarOndaIndividual(Context context, View view) {

        if(getValuePreferenceOndas(context)){
            if(view != null){
                view.setVisibility(View.INVISIBLE);
            }

        } else {
            if(view != null){
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
