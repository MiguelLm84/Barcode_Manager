package com.miguel_lm.app_barcode.model.management.size_fonts;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.EXTRA_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NAV_EXTRA_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NAV_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NAV_NORMAL_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NORMAL_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.SIZE_FONT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_EXTRA_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_EXTRA_LOGIN_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_LOGIN_LARGE_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_LOGIN_NORMAL_SIZE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_NORMAL_SIZE;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.google.android.material.navigation.NavigationView;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.activities.ActivitySplash;
import com.miguel_lm.app_barcode.ui.activities.CerrarSesionActivity;
import com.miguel_lm.app_barcode.ui.activities.ConfiguracionActivity;
import com.miguel_lm.app_barcode.ui.activities.CustomActivity;
import com.miguel_lm.app_barcode.ui.activities.CustomScannerActivity;
import com.miguel_lm.app_barcode.ui.activities.LoginActivity;
import com.miguel_lm.app_barcode.ui.activities.ReservaActivity;
import com.miguel_lm.app_barcode.ui.activities.ScannerActivity;
import com.miguel_lm.app_barcode.ui.adapters.AdapterProyecto;
import com.miguel_lm.app_barcode.ui.adapters.AdapterReservasProyecto;
import com.miguel_lm.app_barcode.ui.adapters.AdapterSelector;
import com.miguel_lm.app_barcode.ui.fragments.FragmentListaProyectos;
import com.miguel_lm.app_barcode.ui.fragments.FragmentScanner;
import com.miguel_lm.app_barcode.ui.fragments.ListaReservasFragment;
import java.util.List;


public class SeleccionSize {

    List<CharSequence> listaSize;
    int seleccionItemSpinnerSize;


    //Método donde se llena la lista del Spinner con los distintos tipos de tamaños para las fuentes.
    public void elementosDeLaListaSpinnerSize(List<CharSequence> lista){
        listaSize = lista;
        listaSize.add("Normal");
        listaSize.add("Grande");
        listaSize.add("Extra Grande");
    }

    //Método donde al seleccionar una opción del Spinner se ejecuta un cambio de fuente en la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void posicionSpinnerSize(Context context, CustomActivity customActivity, int position, View view){

        gestionarPosicionSpinnerSize(context, position, customActivity, view);
        seleccionItemSpinnerSize = customActivity.spinner_size.getSelectedItemPosition();
        guardarCambiosTextSize(seleccionItemSpinnerSize, context, customActivity);
    }

    //Método para gestionar la posición seleccionada en spinner y aplicar el tamaño de fuente en CustomActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void gestionarPosicionSpinnerSize(Context context, int position, CustomActivity customActivity, View view) {

        posicionSpinnerSizeSeleccionada(context, position, customActivity, view, 0, NORMAL_SIZE);
        posicionSpinnerSizeSeleccionada(context, position, customActivity, view, 1, LARGE_SIZE);
        posicionSpinnerSizeSeleccionada(context, position, customActivity, view, 2, EXTRA_LARGE_SIZE);
    }

    //Método para aplicar el nuevo tamaño de la fuente en CustomActivity y a la lista Spinner
    //según la posición seleccionada en el propio Sinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void posicionSpinnerSizeSeleccionada(Context context,int pos, CustomActivity customActivity, View view, int num, int size){

        if (pos == num) {
            cambioDeTextSize(context, customActivity);

            if(view != null){
                cambioDeTextSizeEnListaSpinner(view, customActivity, size);
            }
        }
    }

    //Método para aplicar el tipo tamaño de la fuente en CustomActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSize(Context context, CustomActivity customActivity){

        textSizeComponentesCustomActivity(customActivity, getValuePreferenceSize(context));
        if(customActivity.spinner_size.getSelectedItem() != null){
            posicionItemSeleccionado(customActivity.spinner_size.getSelectedItemPosition(), customActivity);
        }
    }

    //Método para aplicar el cambio de tamaño de fuente en los distintos componentes de CustomActivity.
    public void componentesTextSizeCustomActivity(CustomActivity customActivity, int size){

        if(customActivity.switchOndas != null && customActivity.switchColor != null
                && customActivity.tv_titulo_personalizacion != null){

            escalaDePixeles(customActivity.switchOndas, size);
            escalaDePixeles(customActivity.switchColor, size);
            gestionarTextSizeTitulosPantallas(customActivity.tv_titulo_personalizacion, size);
        }
    }

    //Método para aplicar el tipo de tamaño de fuente en lista del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnListaSpinner(View view, CustomActivity customActivity, int size){

        componentesTextSizeCustomActivity(customActivity, size);
        if(view != null){
            escalaDePixeles(view, size);

        } else {
            Log.e("TAG_ERROR","Error, el view recibido es null");
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla SplashActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnComponentesDeSplashActivity(int num, ActivitySplash activitySplash) {

        if(activitySplash.tv_version_apk != null){
            if(num == 0){
                escalaDePixeles(activitySplash.tv_version_apk, NORMAL_SIZE);
            }
            if(num == 1){
                escalaDePixeles(activitySplash.tv_version_apk, LARGE_SIZE);
            }
            if(num == 2){
                escalaDePixeles(activitySplash.tv_version_apk, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para gestionar el cambio de tamaño de la fuente en los componentes de texto de la pantalla CerrarSesionActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnComponentesDeCerrarSesionActivity(int num, CerrarSesionActivity cerrarSesionActivity) {

        if(cerrarSesionActivity.tv_email != null && cerrarSesionActivity.tv_password != null
                && cerrarSesionActivity.tv_titulo_cerrar_sesion != null && cerrarSesionActivity.btn_cerrarSesion != null){

            if(num== 0){
               gestionTextSizeCerrarSesion(cerrarSesionActivity, NORMAL_SIZE);
            }
            if(num== 1){
                gestionTextSizeCerrarSesion(cerrarSesionActivity, LARGE_SIZE);
            }
            if(num== 2){
                gestionTextSizeCerrarSesion(cerrarSesionActivity, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para aplicar un tamaño a los distintos componentes de CerrarSesionActivity.
    private void gestionTextSizeCerrarSesion(CerrarSesionActivity cerrarSesionActivity, int size) {

        escalaDePixeles(cerrarSesionActivity.tv_email, size);
        escalaDePixeles(cerrarSesionActivity.tv_password, size);
        gestionarTextSizeTitulosPantallas(cerrarSesionActivity.tv_titulo_cerrar_sesion, size);
        escalaDePixeles(cerrarSesionActivity.btn_cerrarSesion, size);
    }

    //Método para gestionar el cambio de tamaño de la fuente en los componentes de texto de la pantalla ConfiguracionActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnComponentesDeConfiguracionActivity(int num, ConfiguracionActivity configuracionActivity) {

        if(configuracionActivity.ed_direccionIp != null && configuracionActivity.ed_puerto != null
                && configuracionActivity.tv_titulo_confg != null && configuracionActivity.btn_aceptar != null){

            if(num == 0){
               gestionTextSizeConfiguracionActivity(configuracionActivity, NORMAL_SIZE);
            }
            if(num == 1){
                gestionTextSizeConfiguracionActivity(configuracionActivity, LARGE_SIZE);
            }
            if(num == 2){
                gestionTextSizeConfiguracionActivity(configuracionActivity, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para aplicar un tamaño a los distintos componentes de ConfiguracionActivity.
    private void gestionTextSizeConfiguracionActivity(ConfiguracionActivity configuracionActivity, int size) {

        escalaDePixeles(configuracionActivity.ed_direccionIp, size);
        escalaDePixeles(configuracionActivity.ed_puerto, size);
        escalaDePixeles(configuracionActivity.btn_aceptar, size);
        gestionarTextSizeTitulosPantallas(configuracionActivity.tv_titulo_confg, size);

        if(configuracionActivity.btn_aceptarDialog != null){
            escalaDePixeles(configuracionActivity.btn_aceptarDialog, NORMAL_SIZE);
        }
    }

    //Método para gestionar el cambio de tamaño de la fuente en los componentes de texto de la pantalla LoginActivity.
    public void cambioDeTextSizeEnComponentesDeLoginActivity(int num, LoginActivity loginActivity) {

        if(loginActivity.emailEdit != null && loginActivity.passwordEdit != null
                && loginActivity.tv_titulo_login != null && loginActivity.rb_sesion != null){

            if(num == 0) {
                textSizeComponentesLoginActivity(loginActivity, NORMAL_SIZE);
            }
            if(num == 1) {
                textSizeComponentesLoginActivity(loginActivity, LARGE_SIZE);
            }
            if(num == 2) {
                textSizeComponentesLoginActivity(loginActivity, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para la gestión del tamaño de los titulos de las pantallas de la app.
    private void gestionarTextSizeTitulosPantallas(TextView tvTitulo, int size){

        if(size == NORMAL_SIZE){
            escalaDePixeles(tvTitulo, TITLE_NORMAL_SIZE);
        }
        if(size == LARGE_SIZE){
            escalaDePixeles(tvTitulo, TITLE_LARGE_SIZE);
        }
        if(size == EXTRA_LARGE_SIZE){
            escalaDePixeles(tvTitulo, TITLE_EXTRA_LARGE_SIZE);
        }
    }

    //Método para la gestión del tamaño de los titulos de las pantallas de la app.
    private void gestionarTextSizeTituloLoginActivity(TextView tvTitulo, int size){

        if(size == NORMAL_SIZE){
            escalaDePixeles(tvTitulo, TITLE_LOGIN_NORMAL_SIZE);
        }
        if(size == LARGE_SIZE){
            escalaDePixeles(tvTitulo, TITLE_LOGIN_LARGE_SIZE);
        }
        if(size == EXTRA_LARGE_SIZE){
            escalaDePixeles(tvTitulo, TITLE_EXTRA_LOGIN_LARGE_SIZE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public <T> void gestionarTextSizeUsuariosDialogFragment(Context context, AdapterSelector<T>.ItemVH itemVH) {

        textSizeComponentesDialogSpinner(context, itemVH, 0, NORMAL_SIZE);
        textSizeComponentesDialogSpinner(context, itemVH, 1, LARGE_SIZE);
        textSizeComponentesDialogSpinner(context, itemVH, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para la gestión del tipo de fuente en dialogEliminar.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnDialogEliminar(Context context, ListaReservasFragment listaReservasFragment){

        textSizeComponentesDialogEliminar(context, listaReservasFragment, 0, NORMAL_SIZE);
        textSizeComponentesDialogEliminar(context, listaReservasFragment, 1, LARGE_SIZE);
        textSizeComponentesDialogEliminar(context, listaReservasFragment, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para aplicar el cambio de fuente en el dialog de eliminar una reserva.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesDialogEliminar(Context context, ListaReservasFragment listaReservasFragment, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(listaReservasFragment.tv_fechaEntradaFinal != null && listaReservasFragment.tv_fechaSalidaFinal != null
                    && listaReservasFragment.tv_operacion != null && listaReservasFragment.btnCancelar != null
                    && listaReservasFragment.btnEliminar != null && listaReservasFragment.tv_guionEntreFechas != null
                    && listaReservasFragment.tv_tituloDialogEliminar != null){

                escalaDePixeles(listaReservasFragment.tv_fechaEntradaFinal, size);
                escalaDePixeles(listaReservasFragment.tv_fechaSalidaFinal, size);
                escalaDePixeles(listaReservasFragment.tv_operacion, size);
                escalaDePixeles(listaReservasFragment.btnCancelar, size);
                escalaDePixeles(listaReservasFragment.btnEliminar, size);
                escalaDePixeles(listaReservasFragment.tv_guionEntreFechas, size);
                escalaDePixeles(listaReservasFragment.tv_tituloDialogEliminar, size);
            }
        }
    }

    //Método para transformar el tamaño de la fuente en escala de pixeles.
    private void escalaDePixeles(View view, int size){

        TextView textView = (TextView) view;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    //Metodo para la gestión del tipo de fuente en dialogLecturaCancelada en FragmentScanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnDialogLecturaCancelada(Context context, FragmentScanner fragmentScanner) {

        textSizeComponentesDialogFragmentScanner(context, fragmentScanner, 0, NORMAL_SIZE);
        textSizeComponentesDialogFragmentScanner(context, fragmentScanner, 1, LARGE_SIZE);
        textSizeComponentesDialogFragmentScanner(context, fragmentScanner, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para aplicar el cambio de tamaño de la fuente en el dialog de lectura cancelada al pulsar en btn_scan
    //y regresar sin escánear ningún producto.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesDialogFragmentScanner(Context context, FragmentScanner fragmentScanner, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(fragmentScanner.btn_aceptar != null){
                escalaDePixeles(fragmentScanner.btn_aceptar, size);
            }
            if(fragmentScanner.tv_dialog != null){
                escalaDePixeles(fragmentScanner.tv_dialog, size);
            }
        }
    }

    //Metodo para la gestión del tamaño de la fuente en dialogSinConexion en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnDialogSinConexion(Context context, ReservaActivity reservaActivity) {

        textSizeComponentesDialogSinConexion(context, reservaActivity, 0, NORMAL_SIZE);
        textSizeComponentesDialogSinConexion(context, reservaActivity, 1, LARGE_SIZE);
        textSizeComponentesDialogSinConexion(context, reservaActivity, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para aplicar el cambio de tamaño de la fuente en el dialogContinuarEscaneando.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesDialogContinuarEscaneando(Context context, ReservaActivity reservaActivity, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(reservaActivity.btn_aceptarDialogContinuarEscaneando != null){
                escalaDePixeles(reservaActivity.btn_aceptarDialogContinuarEscaneando, size);
            }
            if(reservaActivity.btn_cancelarDialogContinuarEscaneando != null){
                escalaDePixeles(reservaActivity.btn_cancelarDialogContinuarEscaneando, size);
            }
            if(reservaActivity.tv_nuevo_escaneo != null){
                escalaDePixeles(reservaActivity.tv_nuevo_escaneo, size);
            }
        }
    }

    //Metodo para la gestión del tamaño de la fuente en dialogContinuarEscaneando en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnDialogContinuarEscaneando(Context context, ReservaActivity reservaActivity) {

        textSizeComponentesDialogContinuarEscaneando(context, reservaActivity, 0, NORMAL_SIZE);
        textSizeComponentesDialogContinuarEscaneando(context, reservaActivity, 1, LARGE_SIZE);
        textSizeComponentesDialogContinuarEscaneando(context, reservaActivity, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para la gestión del tamaño de la fuente en dialogEliminar en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnDialogEliminarReserva(Context context, ReservaActivity reservaActivity){

        textSizeComponentesDialogEliminarReserva(context, reservaActivity, 0, NORMAL_SIZE);
        textSizeComponentesDialogEliminarReserva(context, reservaActivity, 1, LARGE_SIZE);
        textSizeComponentesDialogEliminarReserva(context, reservaActivity, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para aplicar el cambio de tamaño de la fuente en el dialog de eliminar una reserva en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesDialogEliminarReserva(Context context, ReservaActivity reservaActivity, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(reservaActivity.tv_fechaEntradaFinal != null && reservaActivity.tv_fechaSalidaFinal != null
                    && reservaActivity.tv_operacion != null && reservaActivity.btnCancelar != null
                    && reservaActivity.btnEliminar != null && reservaActivity.tv_guionEntreFechas != null
                    && reservaActivity.tv_tituloDialogEliminar != null){

                escalaDePixeles(reservaActivity.tv_fechaEntradaFinal, size);
                escalaDePixeles(reservaActivity.tv_fechaSalidaFinal, size);
                escalaDePixeles(reservaActivity.tv_operacion, size);
                escalaDePixeles(reservaActivity.btnCancelar, size);
                escalaDePixeles(reservaActivity.btnEliminar, size);
                escalaDePixeles(reservaActivity.tv_guionEntreFechas, size);
                escalaDePixeles(reservaActivity.tv_tituloDialogEliminar, size);
            }
        }
    }

    //Metodo para aplicar el cambio de tamaño de la fuente en el dialogSinConexion.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesDialogSinConexion(Context context, ReservaActivity reservaActivity, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(reservaActivity.btn_aceptar != null){
                escalaDePixeles(reservaActivity.btn_aceptar, size);
            }
            if(reservaActivity.tv_dialog_barcode != null){
                escalaDePixeles(reservaActivity.tv_dialog_barcode, size);
            }
        }
    }

    //Metodo para la gestión del tamaño de la fuente en dialogContinuarEscaneando en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnSnackbar(Context context, ReservaActivity reservaActivity) {

        textSizeComponentesSnackbar(context, reservaActivity, 0, NORMAL_SIZE);
        textSizeComponentesSnackbar(context, reservaActivity, 1, LARGE_SIZE);
        textSizeComponentesSnackbar(context, reservaActivity, 2, EXTRA_LARGE_SIZE);
    }


    //Metodo para aplicar el cambio de tamaño de la fuente en el dialogSinConexion.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesSnackbar(Context context, ReservaActivity reservaActivity, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(reservaActivity.tv_snackbar != null){
                escalaDePixeles(reservaActivity.tv_snackbar, size);
            }
            if(reservaActivity.snackbarTextView != null){
                escalaDePixeles(reservaActivity.snackbarTextView, size);
            }
            if(reservaActivity.tv_dialog_barcode != null){
                escalaDePixeles(reservaActivity.tv_dialog_barcode, size);
            }
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogBarcode en FragmentScanner fragmentScanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionTextSizeEnDialogBarcode(Context context, FragmentScanner fragmentScanner) {

        textSizeComponentesDialogBarcode(context, fragmentScanner, 0, NORMAL_SIZE);
        textSizeComponentesDialogBarcode(context, fragmentScanner, 1, LARGE_SIZE);
        textSizeComponentesDialogBarcode(context, fragmentScanner, 2, EXTRA_LARGE_SIZE);
    }

    //Metodo para aplicar el cambio de fuente en el dialogSinConexion.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesDialogBarcode(Context context, FragmentScanner fragmentScanner, int num, int size){

        if(getValuePreferenceSize(context) == num){
            if(fragmentScanner.btn_aceptarDialogBarcode != null){
                escalaDePixeles(fragmentScanner.btn_aceptarDialogBarcode, size);
            }
            if(fragmentScanner.tv_dialog_barcode != null){
                escalaDePixeles(fragmentScanner.tv_dialog_barcode, size);
            }
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnComponentesDeReservaActivity(int num, ReservaActivity reservaActivity) {

        if(num == 0){
            textSizeComponentesReservaActivity(reservaActivity, NORMAL_SIZE);
        }
        if(num == 1){
            textSizeComponentesReservaActivity(reservaActivity, LARGE_SIZE);
        }
        if(num == 2){
            textSizeComponentesReservaActivity(reservaActivity, EXTRA_LARGE_SIZE);
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla ScannerActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnComponentesDeScannerActivity(Context context, int num, ScannerActivity scannerActivity) {

        if(num == 0){
            gestionTextSizeScannerActivity(context, scannerActivity, NORMAL_SIZE);
        }
        if(num == 1){
            gestionTextSizeScannerActivity(context, scannerActivity, LARGE_SIZE);
        }
        if(num == 2){
            gestionTextSizeScannerActivity(context, scannerActivity, EXTRA_LARGE_SIZE);
        }
    }

    //Método para aplicar un tamaño a los distintos componentes de ScannerActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void gestionTextSizeScannerActivity(Context context, ScannerActivity scannerActivity, int size) {

        if(scannerActivity.navigationView != null){
            nav(context, scannerActivity.navigationView);
        }
        if(scannerActivity.switchDarkMode != null){
            escalaDePixeles(scannerActivity.switchDarkMode, size);
        }
        if(scannerActivity.tv_titulo != null){
            gestionarTextSizeTitulosPantallas(scannerActivity.tv_titulo, size);
        }
    }

    //Método donde se aplica a los distintos items del NavigationView (menos la posición 4
    //del switch que activa o desactiva el modo oscuro) el tipo de tamaño de la fuente seleccionada.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void nav(Context context, NavigationView navigationView){

        if(getValuePreferenceSize(context) == 0){
            navigationView.setItemTextAppearance(NAV_NORMAL_SIZE);
        }
        if(getValuePreferenceSize(context) == 1){
            navigationView.setItemTextAppearance(NAV_LARGE_SIZE);
        }
        if(getValuePreferenceSize(context) == 2){
            navigationView.setItemTextAppearance(NAV_EXTRA_LARGE_SIZE);
        }
    }

    //Método para la gestión del tamaño de la fuente en NavigationView.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void navigationTextSize(ScannerActivity scannerActivity, int num){

        gestionNavigationTextSize(scannerActivity, num);
        gestionNavigationTextSize(scannerActivity, num);
        gestionNavigationTextSize(scannerActivity, num);
    }

    //Método para gestionar y aplicar el tamaño de la fuente almacenada en NavigationDrawer (Menú lateral).
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionNavigationTextSize(ScannerActivity scannerActivity, int num){

        if(num == 0){
            gestionTextSizeHeaderNav(scannerActivity.navigationView.getHeaderView(0), NORMAL_SIZE);
        }
        if(num == 1){
            gestionTextSizeHeaderNav(scannerActivity.navigationView.getHeaderView(1), LARGE_SIZE);
        }
        if(num == 2){
            gestionTextSizeHeaderNav(scannerActivity.navigationView.getHeaderView(2), EXTRA_LARGE_SIZE);
        }
    }

    //Método para aplicar el tipo de tamaño de fuente en el encabezado del NavigationView.
    private void gestionTextSizeHeaderNav(View headerView, int sizeFont){

        if(headerView != null){
            TextView navHeaderTv = headerView.findViewById(R.id.tv_header);
            escalaDePixeles(navHeaderTv, sizeFont);
        }
    }

    //Método para gestionar el cambio de tamaño de la fuente en los componentes de texto de la pantalla FragmentScanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeTextSizeEnComponentesDeFragmentScanner(int num, FragmentScanner fragmentScanner) {

        if(num == 0){
            gestionTextSizeFragmentScanner(fragmentScanner, NORMAL_SIZE);
        }
        if(num == 1){
            gestionTextSizeFragmentScanner(fragmentScanner, LARGE_SIZE);
        }
        if(num == 2){
            gestionTextSizeFragmentScanner(fragmentScanner, EXTRA_LARGE_SIZE);
        }
    }

    //Método para aplicar un tamaño a los distintos componentes de FragmentScanner.
    private void gestionTextSizeFragmentScanner(FragmentScanner fragmentScanner, int size) {

        if(fragmentScanner.ed_result != null){
            escalaDePixeles(fragmentScanner.ed_result, size);
        }
        if(fragmentScanner.btn_scan != null && fragmentScanner.btn_cargar_codigo != null) {
            escalaDePixeles(fragmentScanner.btn_scan, size);
            escalaDePixeles(fragmentScanner.btn_cargar_codigo, size);
        }
    }

    //Método para gestionar el cambio de tamaño de la fuente en los componentes de texto de la pantalla FragmentListaProyectos.
    public void cambioDeTextSizeEnComponentesDeFragmentListaProyectos(int num, FragmentListaProyectos fragmentListaProyectos) {

        TextView searchText = fragmentListaProyectos.buscadorListaPro.findViewById(androidx.appcompat.R.id.search_src_text);

        if(fragmentListaProyectos.buscadorListaPro != null && fragmentListaProyectos.tv_proyectos_no_encontrados != null){
            if(num == 0) {
                gestionTextSizeFragmentListaProyectos(fragmentListaProyectos, searchText, NORMAL_SIZE);
            }
            if(num == 1) {
                gestionTextSizeFragmentListaProyectos(fragmentListaProyectos, searchText, LARGE_SIZE);
            }
            if(num == 2) {
                gestionTextSizeFragmentListaProyectos(fragmentListaProyectos, searchText, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para aplicar un tamaño a los distintos componentes de FragmentListaProyectos.
    private void gestionTextSizeFragmentListaProyectos(FragmentListaProyectos fragmentListaProyectos, TextView searchText, int size) {

        escalaDePixeles(searchText, size);
        escalaDePixeles(fragmentListaProyectos.tv_proyectos_no_encontrados, size);
    }

    //Método para gestionar el cambio de tamaño de la fuente en los componentes de texto de la pantalla ListaReservasFragment.
    public void cambioDeTextSizeEnComponentesDeFragmentListaReservasProyectos(int num, ListaReservasFragment listaReservasFragment) {

        TextView searchText = listaReservasFragment.buscadorLista.findViewById(androidx.appcompat.R.id.search_src_text);

        if(listaReservasFragment.buscadorLista != null && listaReservasFragment.datosNoEncontrados != null){
            if(num == 0){
                aplicarEscalaDePixeles(searchText, listaReservasFragment, NORMAL_SIZE);
            }
            if(num == 1){
                aplicarEscalaDePixeles(searchText, listaReservasFragment, LARGE_SIZE);
            }
            if(num == 2){
                aplicarEscalaDePixeles(searchText, listaReservasFragment, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para aplicar la escala de pixeles en el TextView del buscador y TextView de acompañamiento
    //de la animación Lottie de 'Datos no encontrados'.
    private void aplicarEscalaDePixeles(TextView searchText, ListaReservasFragment listaReservasFragment, int fontSize){

        escalaDePixeles(searchText, fontSize);
        escalaDePixeles(listaReservasFragment.datosNoEncontrados, fontSize);
    }

    //Método para cambiar el tamaño de la fuente tanto en RecyclerView de Proyectos como en RecyclerView de ReservasProyectos.
    public void cambioDeTextSizeEnLosRecyclerView(Context context,
            AdapterProyecto.ProyectoViewHolder proyectoViewHolder,
            AdapterReservasProyecto.OperacionViewHolder opViewHolder){

        textSizeComponentesAdapterRecycler(context, 0, NORMAL_SIZE, proyectoViewHolder, opViewHolder);
        textSizeComponentesAdapterRecycler(context, 1, LARGE_SIZE, proyectoViewHolder, opViewHolder);
        textSizeComponentesAdapterRecycler(context, 2, EXTRA_LARGE_SIZE, proyectoViewHolder, opViewHolder);
    }

    //Método para realizar el cambio del tamaño de la fuente en los componentes de la lista del Recycler de las reservas.
    public void gestionarTextSizeEnAdapterReservas(AdapterReservasProyecto.OperacionViewHolder opViewHolder, int size){

        escalaDePixeles(opViewHolder.tv_nomProd, size);
        escalaDePixeles(opViewHolder.fechasReserva, size);
        escalaDePixeles(opViewHolder.tv_fechaEntrada, size);
        escalaDePixeles(opViewHolder.tv_fechaSalida, size);
        escalaDePixeles(opViewHolder.tv_salida, size);
        escalaDePixeles(opViewHolder.tv_entrada, size);
    }

    //Método para guardar los cambios al seleccionar una fuente de la lista del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void guardarCambiosTextSize(int position, Context context, CustomActivity customActivity) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        gestionarPosicionSpinnerSize(context, position, customActivity, null);
        customActivity.spinner_size.getItemAtPosition(position);

        editor.putInt(SIZE_FONT, position);
        editor.apply();

        gestionTextSizeListaSpinner(context, customActivity.spinner_size.getSelectedView(), customActivity);
        gestionarTextSize(context, customActivity);
    }

    //Método para cambiar la fuente correspondiente en todas las Activities y Fragments.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarTextSizeEnPantallas(Context context, CustomActivity customActivity, int num){

        if(getValuePreferenceSize(context) == num){
            cambioDeTextSize(context, customActivity);
        }
    }

    //Método para aplicar la fuente seleccionada en las distintas panatallas de la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionarTextSize(Context context, CustomActivity customActivity){

        aplicarTextSizeEnPantallas(context, customActivity, 0);
        aplicarTextSizeEnPantallas(context, customActivity, 1);
        aplicarTextSizeEnPantallas(context, customActivity, 2);
    }

    //Método para aplicar la fuente seleccionada en las distintas panatallas de la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionarTextSizeScanner(CustomScannerActivity customScannerActivity){

        aplicarTextSizeEnPantallaCustomScanner(customScannerActivity, 0);
        aplicarTextSizeEnPantallaCustomScanner(customScannerActivity, 1);
        aplicarTextSizeEnPantallaCustomScanner(customScannerActivity, 2);
    }

    //Método para cambiar el tamaño de la fuente en CustomScannerActivity.
    private void aplicarTextSizeEnPantallaCustomScanner(CustomScannerActivity customScannerActivity, int num) {

        if(customScannerActivity != null){
            if(num == 0){
                cambioDeTextSizeScanner(customScannerActivity, NORMAL_SIZE);
            }
            if(num == 1){
                cambioDeTextSizeScanner(customScannerActivity, LARGE_SIZE);
            }
            if(num == 2){
                cambioDeTextSizeScanner(customScannerActivity, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para aplicar el tipo de tamaño en la fuente de CustomScannerActivity.
    private void cambioDeTextSizeScanner(CustomScannerActivity customScannerActivity, int size) {

        if(customScannerActivity != null){
            if(customScannerActivity.switchFlashlightButton != null){
                escalaDePixeles(customScannerActivity.switchFlashlightButton, size);
            }
            if(customScannerActivity.barcodeScannerView != null){
                escalaDePixeles(customScannerActivity.barcodeScannerView.getStatusView(), size);
            }
        }
    }

    //Método para cargar en la posición del item seleccionado en el Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void posicionItemSeleccionado(int pos, CustomActivity customActivity){

        if(customActivity.spinner_size != null){
            customActivity.spinner_size.setSelection(pos);

            aplicarCambiosEnTextSizeEnCustomActivity(customActivity, 0, pos, NORMAL_SIZE);
            aplicarCambiosEnTextSizeEnCustomActivity(customActivity, 1, pos, LARGE_SIZE);
            aplicarCambiosEnTextSizeEnCustomActivity(customActivity, 2, pos, EXTRA_LARGE_SIZE);
        }
    }

    //Método para coprobar la posición del item almacenado y poder aplicar el cambio de tamaño de la fuente.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarCambiosEnTextSizeEnCustomActivity(CustomActivity customActivity, int num, int pos, int size){

        if (getValuePreferenceSize(customActivity) == num && pos == num) {
            textSizeComponentesCustomActivity(customActivity, num);

            if(customActivity.spinner_fuentes.getSelectedView() != null){
                escalaDePixeles(((TextView) customActivity.spinner_fuentes.getSelectedView()), size);
            }

            if(customActivity.spinner_size.getSelectedView() != null){
                escalaDePixeles(((TextView) customActivity.spinner_size.getSelectedView()), size);

                new SeleccionFuente().gestionFuenteListaSpinner(customActivity,
                        customActivity.spinner_fuentes.getSelectedView(), customActivity);
            }
        }
    }

    //Método aplicar el cambio de tamaño en la fuente del Spinner según la opción que esté almacenada en las Shared Preferences.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void aplicarCambioTextSizeEnSpinner(Context context, View view, CustomActivity customActivity, int num){

        if (getValuePreferenceSize(context) == num && num == 0){
            cambioDeTextSizeEnListaSpinner(view, customActivity, NORMAL_SIZE);

            new SeleccionFuente().gestionFuenteListaSpinner(customActivity,
                    customActivity.spinner_size.getSelectedView(), customActivity);
        }
        if (getValuePreferenceSize(context) == num && num == 1){
            cambioDeTextSizeEnListaSpinner(view, customActivity, LARGE_SIZE);

            new SeleccionFuente().gestionFuenteListaSpinner(customActivity,
                    customActivity.spinner_size.getSelectedView(), customActivity);
        }
        if (getValuePreferenceSize(context) == num && num == 2){
            cambioDeTextSizeEnListaSpinner(view, customActivity, EXTRA_LARGE_SIZE);

            new SeleccionFuente().gestionFuenteListaSpinner(customActivity,
                    customActivity.spinner_size.getSelectedView(), customActivity);
        }
    }

    //Método para cambiar el tamaño de la fuente en la lista del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public View gestionTextSizeListaSpinner(Context context, View view, CustomActivity customActivity){

        aplicarCambioTextSizeEnSpinner(context, view, customActivity,getValuePreferenceSize(customActivity));

        return view;
    }

    //Método para aplicar el cambio de tamaño en la fuente de los distintos componentes de CustomActivity.
    public void textSizeComponentesCustomActivity(CustomActivity customActivity, int num){

        if(customActivity.switchOndas != null && customActivity.switchColor != null
                && customActivity.tv_titulo_personalizacion != null){

            if(num == 0){
                gestonTextSizeCustomActivity(customActivity, NORMAL_SIZE);
            }
            if(num == 1){
                gestonTextSizeCustomActivity(customActivity, LARGE_SIZE);
            }
            if(num == 2){
                gestonTextSizeCustomActivity(customActivity, EXTRA_LARGE_SIZE);
            }
        }
    }

    //Método para aplicar un tamaño a los distintos componentes de CustomActivity.
    private void gestonTextSizeCustomActivity(CustomActivity customActivity, int size) {

        escalaDePixeles(customActivity.switchOndas, size);
        escalaDePixeles(customActivity.switchColor, size);
        gestionarTextSizeTitulosPantallas(customActivity.tv_titulo_personalizacion, size);
    }


    //Método para aplicar el cambio de tamaño de la fuente en los distintos componentes de LoginActivity.
    public void textSizeComponentesLoginActivity(LoginActivity loginActivity, int size){

        if(loginActivity.emailEdit != null && loginActivity.passwordEdit != null){
            escalaDePixeles(loginActivity.emailEdit, size);
            escalaDePixeles(loginActivity.passwordEdit, size);
        }

        if(loginActivity.tv_titulo_login != null){
            gestionarTextSizeTituloLoginActivity(loginActivity.tv_titulo_login, size);
        }

        if(loginActivity.btn_login != null && loginActivity.rb_sesion != null){
            escalaDePixeles(loginActivity.btn_login, size);
            escalaDePixeles(loginActivity.rb_sesion, size);
        }
    }

    //Método para aplicar el cambio de tamaño de la fuente en los distintos componentes de ResevasActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void textSizeComponentesReservaActivity(ReservaActivity reservaActivity, int size){

        textSizeComponentesDelModoEditarDeReservaActivity(reservaActivity, size);
        textSizeComponentesDelModoRegistrarDeReservaActivity(reservaActivity, size);
    }

    //Método para aplicar el cambio de tamaño de la fuente en los distintos componentes del modo editar de ReservaActivity.
    private void textSizeComponentesDelModoEditarDeReservaActivity(ReservaActivity reservaActivity, int size){

        if(reservaActivity.anular != null){
            escalaDePixeles(reservaActivity.anular, size);
        }
        if(reservaActivity.aceptar != null){
            escalaDePixeles(reservaActivity.aceptar, size);
        }
        if(reservaActivity.btn_volverAreservar != null){
            escalaDePixeles(reservaActivity.btn_volverAreservar, size);
        }
        if(reservaActivity.fechasPrevistas != null){
            escalaDePixeles(reservaActivity.fechasPrevistas, size);
        }
        if(reservaActivity.registrarEntrada != null){
            escalaDePixeles(reservaActivity.registrarEntrada, size);
        }
        if( reservaActivity.registrarSalida != null){
            escalaDePixeles(reservaActivity.registrarSalida, size);
        }
        if(reservaActivity.fechaOperacion != null){
            escalaDePixeles(reservaActivity.fechaOperacion, size);
        }
        if(reservaActivity.tv_dialog_barcode != null){
            escalaDePixeles(reservaActivity.tv_dialog_barcode, size);
        }
    }

    //Método para aplicar el cambio de tamaño de la fuente en los distintos componentes del modo registrar de ReservaActivity.
    private void textSizeComponentesDelModoRegistrarDeReservaActivity(ReservaActivity reservaActivity,int size){

        if(reservaActivity.codigoBarras != null && reservaActivity.nombreMaterial != null
                && reservaActivity.seleccionarFechas != null &&  reservaActivity.tv_titulo_operacion != null
                && reservaActivity.usuario != null && reservaActivity.seleccionarProyecto != null
                && reservaActivity.seleccionarEncargado != null){

            escalaDePixeles(reservaActivity.codigoBarras, size);
            escalaDePixeles(reservaActivity.nombreMaterial, size);
            escalaDePixeles(reservaActivity.seleccionarFechas, size);
            gestionarTextSizeTitulosPantallas(reservaActivity.tv_titulo_operacion, size);
            escalaDePixeles(reservaActivity.usuario, size);
            escalaDePixeles(reservaActivity.seleccionarProyecto, size);
            escalaDePixeles(reservaActivity.seleccionarEncargado, size);
        }
        if(reservaActivity.btn_registrar != null) {
            escalaDePixeles(reservaActivity.btn_registrar, size);
        }
    }

    //Método para aplicar el tamaño de la fuente seleccionada en el dialogo del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private <T> void textSizeComponentesDialogSpinner(Context context, AdapterSelector<T>.ItemVH itemVH, int num, int size) {

        if(itemVH != null) {
            if (getValuePreferenceSize(context) == num) {
                escalaDePixeles(((TextView)itemVH.itemView), size);
            }
        }
    }

    //Método para aplicar el tamaño de la fuente seleccionada en los Adapter de los RecyclerView de Proyectos y ReservasProyecto.
    public void textSizeComponentesAdapterRecycler(Context context, int num, int size, AdapterProyecto.ProyectoViewHolder proyectoViewHolder,
                                           AdapterReservasProyecto.OperacionViewHolder opViewHolder){

        if(getValuePreferenceSize(context) == num){
            if(proyectoViewHolder != null){
                escalaDePixeles(proyectoViewHolder.nomCliente, size);
            }
            if(opViewHolder != null){
                gestionarTextSizeEnAdapterReservas(opViewHolder, size);
            }
        }
    }

    //Métodp para la gestión del tipo de tamaño de los items de la lista de los spinner.
    public void gestionarTextSizeEnListaSpinner(CustomActivity customActivity, View view) {

        if(getValuePreferenceSize(customActivity) == 0){
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, NORMAL_SIZE);
        }
        if(getValuePreferenceSize(customActivity) == 1){
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, LARGE_SIZE);
        }
        if(getValuePreferenceSize(customActivity) == 2){
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, EXTRA_LARGE_SIZE);
        }
    }

    //Método para comprobar el tipo de tamaño de la fuente almacenado en las Shared Preferences del dispositivo
    //y poder así, aplicarlo a la app.
    public int getValuePreferenceSize(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_TEMA,  Context.MODE_PRIVATE);
        return  preferences.getInt(SIZE_FONT, -1);
    }
}
