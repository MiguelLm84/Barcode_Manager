package com.miguel_lm.app_barcode.model.management.fonts_manager;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CHANGE_FONT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.FILE_THEME_PREFERENCES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.RUSSO_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_AZUL_RANGEPICKER_TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_AZUL_RANGEPICKER_VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_AZUL_RANGEPICKER_BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_AZUL_RANGEPICKER_BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_AZUL_RANGEPICKER_BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_AZUL_RANGEPICKER_RUSSO_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_AZUL_RANGEPICKER_TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_AZUL_RANGEPICKER_VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_AZUL_RANGEPICKER_BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_AZUL_RANGEPICKER_BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_AZUL_RANGEPICKER_BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_AZUL_RANGEPICKER_RUSSO_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_DATEPICKER_TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_DATEPICKER_VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_DATEPICKER_BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_DATEPICKER_BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_DATEPICKER_BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_DATEPICKER_RUSSO_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_ROJO_RANGEPICKER_TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_ROJO_RANGEPICKER_VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_ROJO_RANGEPICKER_BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_ROJO_RANGEPICKER_BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_ROJO_RANGEPICKER_BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DARK_ROJO_RANGEPICKER_RUSSO_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DATEPICKER_TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DATEPICKER_VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DATEPICKER_BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DATEPICKER_BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DATEPICKER_BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_DATEPICKER_RUSSO_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_ROJO_RANGEPICKER_TAXICAB;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_ROJO_RANGEPICKER_VIGA;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_ROJO_RANGEPICKER_BASE_ONE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_ROJO_RANGEPICKER_BARLOW;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_ROJO_RANGEPICKER_BEBAS_NEUE;
import static com.miguel_lm.app_barcode.model.common.constatnts_dp.StylesDatePickers.STYLE_ROJO_RANGEPICKER_RUSSO_ONE;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
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
import com.miguel_lm.app_barcode.ui.adapters.CustomTypefaceSpan;
import com.miguel_lm.app_barcode.ui.fragments.FragmentListaProyectos;
import com.miguel_lm.app_barcode.ui.fragments.FragmentScanner;
import com.miguel_lm.app_barcode.ui.fragments.ListaReservasFragment;
import java.io.File;
import java.util.List;
import java.util.Objects;


public class SeleccionFuente {

    List<CharSequence> listaFuentes;
    Typeface externalFont, font;
    int seleccionItemSpinner;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    SeleccionSize seleccionSize = new SeleccionSize();
    @SuppressLint("SdCardPath")
    File preferenciasTema = new File(FILE_THEME_PREFERENCES);


    //Método donde se llena la lista del Spinner con los nombres de los tipos de fuentes a seleccionar.
    public void elementosDeLaListaSpinner(List<CharSequence> lista){
        listaFuentes = lista;
        listaFuentes.add("Barlow");
        listaFuentes.add("Bebas Neue");
        listaFuentes.add("Russo One");
        listaFuentes.add("Base One");
        listaFuentes.add("Taxicab");
        listaFuentes.add("Viga");
    }

    //Método donde al seleccionar una opción del Spinner se ejecuta un cambio de fuente en la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void posicionSpinner(Context context, CustomActivity customActivity, int position, View view){

        gestionarPosicionSpinner(context, position, customActivity, view);
        seleccionItemSpinner = customActivity.spinner_fuentes.getSelectedItemPosition();
        guardarCambiosTipoFuente(seleccionItemSpinner, context, customActivity);
    }

    //Método para crear los tipos de fuentes pasándole el contexto y el path de la fuente.
    public Typeface createTypeface(Context context, String pathFuente){

        return Typeface.createFromAsset(context.getAssets(), pathFuente);
    }

    //Método para aplicar el tipo de fuente que sea seleccionado en la actvividad que corresponda.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarFuenteEnActividadCorrespondiente(ActivitySplash activitySplash, CustomActivity customActivity,
                                                        CerrarSesionActivity cerrarSesionActivity, ConfiguracionActivity configuracionActivity,
                                                        LoginActivity loginActivity, ReservaActivity reservaActivity, ScannerActivity scannerActivity,
                                                        FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag,
                                                        FragmentScanner fragScanner){

        if(activitySplash != null){
            cambioDeFuenteEnComponentesDeSplashActivity(externalFont, activitySplash);
        }
        if(customActivity != null){
            cambioDeFuenteEnComponentesDeCustomActivity(externalFont, customActivity);
        }
        if(cerrarSesionActivity != null){
            cambioDeFuenteEnComponentesDeCerrarSesionActivity(externalFont, cerrarSesionActivity);
        }
        if(configuracionActivity != null){
            cambioDeFuenteEnComponentesDeConfiguracionActivity(externalFont, configuracionActivity);
        }
        if(loginActivity != null){
            cambioDeFuenteEnComponentesDeLoginActivity(externalFont, loginActivity);
        }
        if(reservaActivity != null){
            cambioDeFuenteEnComponentesDeReservaActivity(externalFont, reservaActivity);
        }
        if(scannerActivity != null){
            cambioDeFuenteEnComponentesDeScannerActivity(externalFont, scannerActivity);
        }
        if(fragScanner != null) {
            cambioDeFuenteEnComponentesDeFragmentScanner(externalFont, fragScanner);
        }
        if(fragListaProyectos != null){
            cambioDeFuenteEnComponentesDeFragmentListaProyectos(externalFont, fragListaProyectos);
        }
        if(listaReservasFrag != null){
            cambioDeFuenteEnComponentesDeFragmentListaReservasProyectos(externalFont, listaReservasFrag);
        }
    }

    //Método para aplicar el tipo de fuente en cada Activity o Fragment de la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuente(Context context, ActivitySplash activitySplash, CustomActivity customActivity,
                               CerrarSesionActivity cerrarSesionActivity, ConfiguracionActivity configuracionActivity,
                               LoginActivity loginActivity, ReservaActivity reservaActivity, ScannerActivity scannerActivity,
                               FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag,
                               FragmentScanner fragScanner, final String pathFuente){

        externalFont = createTypeface(context, pathFuente);
        aplicarFuenteEnActividadCorrespondiente(activitySplash, customActivity, cerrarSesionActivity, configuracionActivity,
                loginActivity, reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner);
    }

    //Método para aplicar el tipo de fuente seleccionado y su tamaño en lista spinner, switches y titulo en CustomActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarFuenteEnListaSpinner(CustomActivity customActivity, Typeface externalFont, View view, int position){

        componentesCustomActivity(customActivity, externalFont);
        if(view != null){
            seleccionSize.gestionarTextSizeEnListaSpinner(customActivity, view);
            gestionDeFuenteEnListaSpinner(customActivity, view, position);

        } else {
            Log.e("TAG_ERROR","Error, el view recibido es null");
        }
    }

    //Método para pasarle los argumentos necesarios para poder aplicar el cambio de fuente en la lista del spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void gestionDeFuenteEnListaSpinner(CustomActivity customActivity, View view, int position) {

        cambioDeFuenteEnItemsSpinner(customActivity, view, position, 0, BARLOW);
        cambioDeFuenteEnItemsSpinner(customActivity, view, position, 1, BEBAS_NEUE);
        cambioDeFuenteEnItemsSpinner(customActivity, view, position, 2, RUSSO_ONE);
        cambioDeFuenteEnItemsSpinner(customActivity, view, position, 3, BASE_ONE);
        cambioDeFuenteEnItemsSpinner(customActivity, view, position, 4, TAXICAB);
        cambioDeFuenteEnItemsSpinner(customActivity, view, position, 5, VIGA);
    }

    //Método para cambiar la fuente en los items de la lista del spinner sí el número asignado coincide
    //con la posición. Sí es así, se creará un Typeface con la fuente correspondiente.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void cambioDeFuenteEnItemsSpinner(CustomActivity customActivity, View view, int position, int num, String pathFuente) {

        if(position == num){
            Typeface font = createTypeface(customActivity, pathFuente);
            ((TextView) view).setText(typeface(font, ((TextView) view).getText()));
        }
    }

    //Método para aplicar el tipo de fuente en lista del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnListaSpinner(Context context, View view, CustomActivity customActivity, String pathFuente, int position){

        Typeface font = createTypeface(context, pathFuente);
        aplicarFuenteEnListaSpinner(customActivity, font, view, position);
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla SplashActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeSplashActivity(Typeface externalFont, ActivitySplash activitySplash) {

        if(activitySplash.tv_version_apk != null){
            activitySplash.tv_version_apk.setText(typeface(externalFont, activitySplash.tv_version_apk.getText()));
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla CustomActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeCustomActivity(Typeface externalFont, CustomActivity customActivity) {

        componentesCustomActivity(customActivity, externalFont);
        if(customActivity.spinner_fuentes.getSelectedItem() != null){
            posicionItemSeleccionado(customActivity.spinner_fuentes.getSelectedItemPosition(), customActivity);
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla CerrarSesionActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeCerrarSesionActivity(Typeface externalFont, CerrarSesionActivity cerrarSesionActivity) {

        if(cerrarSesionActivity.tv_email != null && cerrarSesionActivity.tv_password != null
                && cerrarSesionActivity.tv_titulo_cerrar_sesion != null && cerrarSesionActivity.btn_cerrarSesion != null){

            cerrarSesionActivity.tv_email.setText(typeface(externalFont, cerrarSesionActivity.tv_email.getText()));
            cerrarSesionActivity.tv_password.setTypeface(externalFont);
            cerrarSesionActivity.tv_titulo_cerrar_sesion.setTypeface(externalFont);
            cerrarSesionActivity.btn_cerrarSesion.setTypeface(externalFont);
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla ConfiguracionActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeConfiguracionActivity(Typeface externalFont, ConfiguracionActivity configuracionActivity) {

        if(configuracionActivity.ed_direccionIp != null && configuracionActivity.ed_puerto != null
                && configuracionActivity.tv_titulo_confg != null && configuracionActivity.btn_aceptar != null){

            configuracionActivity.ed_direccionIp.setTypeface(externalFont);
            configuracionActivity.ed_puerto.setTypeface(externalFont);
            configuracionActivity.tv_titulo_confg.setText(typeface(externalFont, configuracionActivity.tv_titulo_confg.getText()));
            configuracionActivity.btn_aceptar.setText(typeface(externalFont, configuracionActivity.btn_aceptar.getText()));

            if(configuracionActivity.btn_aceptarDialog != null){
                configuracionActivity.btn_aceptarDialog.setText(typeface(externalFont, configuracionActivity.btn_aceptarDialog.getText()));
            }
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla LoginActivity.
    public void cambioDeFuenteEnComponentesDeLoginActivity(Typeface externalFont, LoginActivity loginActivity) {

        if(loginActivity.emailEdit != null && loginActivity.passwordEdit != null
                && loginActivity.tv_titulo_login != null && loginActivity.rb_sesion != null){

            componentesLoginActivity(loginActivity, externalFont);
        }
    }

    //Método para la gestión de la fuente en el botón de Login de la pantalla LoginActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionBotonLogin(LoginActivity loginActivity){

        componentesLoginActivity2(loginActivity, 0, "barlow", BARLOW);
        componentesLoginActivity2(loginActivity, 1, "Bebas Neue", BEBAS_NEUE);
        componentesLoginActivity2(loginActivity, 2, "Russo One", RUSSO_ONE);
        componentesLoginActivity2(loginActivity, 3, "Base One", BASE_ONE);
        componentesLoginActivity2(loginActivity, 4, "Taxicab", TAXICAB);
        componentesLoginActivity2(loginActivity, 5, "Viga", VIGA);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public <T> void gestionarUsuariosDialogFragment(Context context, AdapterSelector<T>.ItemVH itemVH) {

        componentesDialogSpinner(context, itemVH, 0, BARLOW);
        componentesDialogSpinner(context, itemVH, 1, BEBAS_NEUE);
        componentesDialogSpinner(context, itemVH, 2, RUSSO_ONE);
        componentesDialogSpinner(context, itemVH, 3, BASE_ONE);
        componentesDialogSpinner(context, itemVH, 4, TAXICAB);
        componentesDialogSpinner(context, itemVH, 5, VIGA);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static SpannableString typeface(Typeface typeface, CharSequence chars) {

        if (chars != null) {
            SpannableString s = new SpannableString(chars);
            s.setSpan(new TypefaceSpan(typeface), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return s;

        } else {
            return null;
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogEliminar.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnDialogEliminar(Context context, ListaReservasFragment listaReservasFragment){

        componentesDialogEliminar(context, listaReservasFragment, 0, BARLOW);
        componentesDialogEliminar(context, listaReservasFragment, 1, BEBAS_NEUE);
        componentesDialogEliminar(context, listaReservasFragment, 2, RUSSO_ONE);
        componentesDialogEliminar(context, listaReservasFragment, 3, BASE_ONE);
        componentesDialogEliminar(context, listaReservasFragment, 4, TAXICAB);
        componentesDialogEliminar(context, listaReservasFragment, 5, VIGA);
    }

    //Metodo para aplicar el cambio de fuente en el dialog de eliminar una reserva.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesDialogEliminar(Context context, ListaReservasFragment listaReservasFragment, int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            font = createTypeface(context, pathFuente);
            if(listaReservasFragment.tv_fechaEntradaFinal != null && listaReservasFragment.tv_fechaSalidaFinal != null
                    && listaReservasFragment.tv_operacion != null && listaReservasFragment.btnCancelar != null
                    && listaReservasFragment.btnEliminar != null && listaReservasFragment.tv_guionEntreFechas != null
                    && listaReservasFragment.tv_tituloDialogEliminar != null){

                listaReservasFragment.tv_fechaEntradaFinal.setText(typeface(font, listaReservasFragment.tv_fechaEntradaFinal.getText()));
                listaReservasFragment.tv_fechaSalidaFinal.setText(typeface(font, listaReservasFragment.tv_fechaSalidaFinal.getText()));
                listaReservasFragment.tv_operacion.setText(typeface(font, listaReservasFragment.tv_operacion.getText()));
                listaReservasFragment.btnCancelar.setText(typeface(font, listaReservasFragment.btnCancelar.getText()));
                listaReservasFragment.btnEliminar.setText(typeface(font, listaReservasFragment.btnEliminar.getText()));
                listaReservasFragment.tv_guionEntreFechas.setText(typeface(font, listaReservasFragment.tv_guionEntreFechas.getText()));
                listaReservasFragment.tv_tituloDialogEliminar.setText(typeface(font, listaReservasFragment.tv_tituloDialogEliminar.getText()));
            }
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogLecturaCancelada en FragmentScanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnDialogLecturaCancelada(Context context, FragmentScanner fragmentScanner) {

        componentesDialogFragmentScanner(context, fragmentScanner, 0, BARLOW);
        componentesDialogFragmentScanner(context, fragmentScanner, 1, BEBAS_NEUE);
        componentesDialogFragmentScanner(context, fragmentScanner, 2, RUSSO_ONE);
        componentesDialogFragmentScanner(context, fragmentScanner, 3, BASE_ONE);
        componentesDialogFragmentScanner(context, fragmentScanner, 4, TAXICAB);
        componentesDialogFragmentScanner(context, fragmentScanner, 5, VIGA);
    }

    //Metodo para aplicar el cambio de fuente en el dialog de lectura cancelada al pulsar en btn_scan
    //y regresar sin escánear ningún producto.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesDialogFragmentScanner(Context context, FragmentScanner fragmentScanner, int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            font = createTypeface(context, pathFuente);
            if(fragmentScanner.btn_aceptar != null){
                fragmentScanner.btn_aceptar.setText(typeface(font, fragmentScanner.btn_aceptar.getText()));
            }
            if(fragmentScanner.tv_dialog != null){
                fragmentScanner.tv_dialog.setText(typeface(font, Objects.requireNonNull(fragmentScanner.tv_dialog).getText()));
            }
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogSinConexion en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnDialogSinConexion(Context context, ReservaActivity reservaActivity) {

        componentesDialogSinConexion(context, reservaActivity, 0, BARLOW);
        componentesDialogSinConexion(context, reservaActivity, 1, BEBAS_NEUE);
        componentesDialogSinConexion(context, reservaActivity, 2, RUSSO_ONE);
        componentesDialogSinConexion(context, reservaActivity, 3, BASE_ONE);
        componentesDialogSinConexion(context, reservaActivity, 4, TAXICAB);
        componentesDialogSinConexion(context, reservaActivity, 5, VIGA);
    }

    //Metodo para aplicar el cambio de fuente en el dialogContinuarEscaneando.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesDialogContinuarEscaneando(Context context, ReservaActivity reservaActivity,
                                                     int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            font = createTypeface(context, pathFuente);
            aplicarFuenteEnBotonesDialogContinuarEscaneando(reservaActivity.btn_aceptarDialogContinuarEscaneando, font);
            aplicarFuenteEnBotonesDialogContinuarEscaneando(reservaActivity.btn_cancelarDialogContinuarEscaneando, font);

            if(reservaActivity.tv_nuevo_escaneo != null){
                reservaActivity.tv_nuevo_escaneo.setText(typeface(font, reservaActivity.tv_nuevo_escaneo.getText()));
            }
        }
    }

    //Método para aplicar el cambio de fuente en botones del DialogContinuarEscaneando.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void aplicarFuenteEnBotonesDialogContinuarEscaneando(Button button, Typeface newFont){

        if(button != null) {
            button.setText(typeface(newFont, button.getText()));
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogContinuarEscaneando en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnDialogContinuarEscaneando(Context context, ReservaActivity reservaActivity) {

        componentesDialogContinuarEscaneando(context, reservaActivity, 0, BARLOW);
        componentesDialogContinuarEscaneando(context, reservaActivity, 1, BEBAS_NEUE);
        componentesDialogContinuarEscaneando(context, reservaActivity, 2, RUSSO_ONE);
        componentesDialogContinuarEscaneando(context, reservaActivity, 3, BASE_ONE);
        componentesDialogContinuarEscaneando(context, reservaActivity, 4, TAXICAB);
        componentesDialogContinuarEscaneando(context, reservaActivity, 5, VIGA);
    }

    //Metodo para la gestión del tipo de fuente en dialogEliminar en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnDialogEliminarReserva(Context context, ReservaActivity reservaActivity){

        componentesDialogEliminarReserva(context, reservaActivity, 0, BARLOW);
        componentesDialogEliminarReserva(context, reservaActivity, 1, BEBAS_NEUE);
        componentesDialogEliminarReserva(context, reservaActivity, 2, RUSSO_ONE);
        componentesDialogEliminarReserva(context, reservaActivity, 3, BASE_ONE);
        componentesDialogEliminarReserva(context, reservaActivity, 4, TAXICAB);
        componentesDialogEliminarReserva(context, reservaActivity, 5, VIGA);
    }

    //Metodo para aplicar el cambio de fuente en el dialog de eliminar una reserva en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesDialogEliminarReserva(Context context, ReservaActivity reservaActivity, int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            font = createTypeface(context, pathFuente);
            if(reservaActivity.tv_fechaEntradaFinal != null && reservaActivity.tv_fechaSalidaFinal != null
                    && reservaActivity.tv_operacion != null && reservaActivity.btnCancelar != null
                    && reservaActivity.btnEliminar != null && reservaActivity.tv_guionEntreFechas != null
                    && reservaActivity.tv_tituloDialogEliminar != null){

                reservaActivity.tv_fechaEntradaFinal.setText(typeface(font, reservaActivity.tv_fechaEntradaFinal.getText()));
                reservaActivity.tv_fechaSalidaFinal.setText(typeface(font, reservaActivity.tv_fechaSalidaFinal.getText()));
                reservaActivity.tv_operacion.setText(typeface(font, reservaActivity.tv_operacion.getText()));
                reservaActivity.btnCancelar.setText(typeface(font, reservaActivity.btnCancelar.getText()));
                reservaActivity.btnEliminar.setText(typeface(font, reservaActivity.btnEliminar.getText()));
                reservaActivity.tv_guionEntreFechas.setText(typeface(font, reservaActivity.tv_guionEntreFechas.getText()));
                reservaActivity.tv_tituloDialogEliminar.setText(typeface(font, reservaActivity.tv_tituloDialogEliminar.getText()));
            }
        }
    }


    //Metodo para aplicar el cambio de fuente en el dialogSinConexion.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesDialogSinConexion(Context context, ReservaActivity reservaActivity, int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            font = createTypeface(context, pathFuente);
            if(reservaActivity.btn_aceptar != null){
                reservaActivity.btn_aceptar.setText(typeface(font, reservaActivity.btn_aceptar.getText()));
            }
            if(reservaActivity.tv_dialog_barcode != null){
                reservaActivity.tv_dialog_barcode.setText(typeface(font, Objects.requireNonNull(reservaActivity.tv_dialog_barcode).getText()));
            }
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogContinuarEscaneando en ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnSnackbar(Context context, ReservaActivity reservaActivity) {

        componentesSnackbar(context, reservaActivity, 0, BARLOW);
        componentesSnackbar(context, reservaActivity, 1, BEBAS_NEUE);
        componentesSnackbar(context, reservaActivity, 2, RUSSO_ONE);
        componentesSnackbar(context, reservaActivity, 3, BASE_ONE);
        componentesSnackbar(context, reservaActivity, 4, TAXICAB);
        componentesSnackbar(context, reservaActivity, 5, VIGA);
    }

    //Metodo para aplicar el cambio de fuente en el dialogSinConexion.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesSnackbar(Context context, ReservaActivity reservaActivity, int num, final String pathFuente){

        font = createTypeface(context, pathFuente);
        Typeface barlow = createTypeface(context, BARLOW);

        if(getValuePreferenceTypeface(context) == num){
            gestionFuenteSnackbar(reservaActivity, font);

        } else {
            gestionFuenteSnackbar(reservaActivity, barlow);
        }
    }

    //Metodo para aplicar el tipo de fuente guardado en las SharedPreferences.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void gestionFuenteSnackbar(ReservaActivity reservaActivity, Typeface newFont){

        if(reservaActivity.tv_snackbar != null){
            reservaActivity.tv_snackbar.setTypeface(newFont,Typeface.BOLD);
        }
        if(reservaActivity.snackbarTextView != null){
            reservaActivity.snackbarTextView.setTypeface(newFont,Typeface.BOLD);
        }
        if(reservaActivity.tv_dialog_barcode != null){
            reservaActivity.tv_dialog_barcode.setText(typeface(newFont,
                    Objects.requireNonNull(reservaActivity.tv_dialog_barcode).getText()));
        }
    }

    //Metodo para la gestión del tipo de fuente en dialogBarcode en FragmentScanner fragmentScanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnDialogBarcode(Context context, FragmentScanner fragmentScanner) {

        componentesDialogBarcode(context, fragmentScanner, 0, BARLOW);
        componentesDialogBarcode(context, fragmentScanner, 1, BEBAS_NEUE);
        componentesDialogBarcode(context, fragmentScanner, 2, RUSSO_ONE);
        componentesDialogBarcode(context, fragmentScanner, 3, BASE_ONE);
        componentesDialogBarcode(context, fragmentScanner, 4, TAXICAB);
        componentesDialogBarcode(context, fragmentScanner, 5, VIGA);
    }

    //Metodo para aplicar el cambio de fuente en el dialogSinConexion.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesDialogBarcode(Context context, FragmentScanner fragmentScanner, int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            font = createTypeface(context, pathFuente);
            if(fragmentScanner.btn_aceptarDialogBarcode != null){
                fragmentScanner.btn_aceptarDialogBarcode.setText(typeface(font, fragmentScanner.btn_aceptarDialogBarcode.getText()));
            }
            if(fragmentScanner.tv_dialog_barcode != null){
                fragmentScanner.tv_dialog_barcode.setText(typeface(font, Objects.requireNonNull(fragmentScanner.tv_dialog_barcode).getText()));
            }
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeReservaActivity(Typeface externalFont, ReservaActivity reservaActivity) {

        componentesReservaActivity(reservaActivity, externalFont);
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla ScannerActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeScannerActivity(Typeface externalFont, ScannerActivity scannerActivity) {

        if(scannerActivity.tv_titulo != null && scannerActivity.navigationView != null && scannerActivity.switchDarkMode != null){
            scannerActivity.tv_titulo.setTypeface(externalFont);
             navigation(externalFont, scannerActivity);
            scannerActivity.switchDarkMode.setTypeface(externalFont);
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla FragmentScanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cambioDeFuenteEnComponentesDeFragmentScanner(Typeface externalFont, FragmentScanner fragmentScanner) {

        if(fragmentScanner.ed_result != null){
            fragmentScanner.ed_result.setTypeface(externalFont);
        }
        if(fragmentScanner.btn_scan != null && fragmentScanner.btn_cargar_codigo != null) {
            fragmentScanner.btn_scan.setText(typeface(externalFont, fragmentScanner.btn_scan.getText()));
            fragmentScanner.btn_cargar_codigo.setText(typeface(externalFont, fragmentScanner.btn_cargar_codigo.getText()));
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla FragmentListaProyectos.
    public void cambioDeFuenteEnComponentesDeFragmentListaProyectos(Typeface externalFont, FragmentListaProyectos fragmentListaProyectos) {

        if(fragmentListaProyectos.buscadorListaPro != null && fragmentListaProyectos.tv_proyectos_no_encontrados != null){
            TextView searchText = fragmentListaProyectos.buscadorListaPro.findViewById(androidx.appcompat.R.id.search_src_text);
            searchText.setTypeface(externalFont);
            fragmentListaProyectos.tv_proyectos_no_encontrados.setTypeface(externalFont);
        }
    }

    //Método para gestionar el cambio de fuente en los componentes de texto de la pantalla ListaReservasFragment.
    public void cambioDeFuenteEnComponentesDeFragmentListaReservasProyectos(Typeface externalFont, ListaReservasFragment listaReservasFragment) {

        if(listaReservasFragment.buscadorLista != null && listaReservasFragment.datosNoEncontrados != null){
            TextView searchText = listaReservasFragment.buscadorLista.findViewById(androidx.appcompat.R.id.search_src_text);
            searchText.setTypeface(externalFont);
            listaReservasFragment.datosNoEncontrados.setTypeface(externalFont);
        }
    }

    //Método para cambiar el tipo de fuente tanto en RecyclerView de Proyectos como en RecyclerView de ReservasProyectos..
    public void cambioDeFuenteEnLosRecyclerView(
            Context context,
            AdapterProyecto.ProyectoViewHolder proyectoViewHolder,
            AdapterReservasProyecto.OperacionViewHolder opViewHolder){

        componentesAdapterRecycler(context, 0, BARLOW, proyectoViewHolder, opViewHolder);
        componentesAdapterRecycler(context, 1, BEBAS_NEUE, proyectoViewHolder, opViewHolder);
        componentesAdapterRecycler(context, 2, RUSSO_ONE, proyectoViewHolder, opViewHolder);
        componentesAdapterRecycler(context, 3, BASE_ONE, proyectoViewHolder, opViewHolder);
        componentesAdapterRecycler(context, 4, TAXICAB, proyectoViewHolder, opViewHolder);
        componentesAdapterRecycler(context, 5, VIGA, proyectoViewHolder, opViewHolder);
    }

    //Método para realizar el cambio de fuente en los componentes de la lista del Recycler de las reservas.
    public void gestionarFuenteEnAdapterReservas(AdapterReservasProyecto.OperacionViewHolder opViewHolder){

        opViewHolder.tv_nomProd.setTypeface(externalFont);
        opViewHolder.fechasReserva.setTypeface(externalFont);
        opViewHolder.tv_fechaEntrada.setTypeface(externalFont);
        opViewHolder.tv_fechaSalida.setTypeface(externalFont);
        opViewHolder.tv_salida.setTypeface(externalFont);
        opViewHolder.tv_entrada.setTypeface(externalFont);
    }

    //Método donde se aplica a los distintos items del NavigationView (menos la posición 4
    // del switch que activa o desactiva el modo oscuro) el tipo de fuente seleccionado.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void navigation(Typeface externalFont, ScannerActivity scannerActivity){

        Menu m = scannerActivity.navigationView.getMenu();

        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    SpannableString s = new SpannableString(subMenuItem.getTitle());
                    s.setSpan(externalFont, 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    subMenuItem.setTitle(s);
                }
            }
        }
    }

    //Método para la gestión del tipo de fuente en NavigationView.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void navigationFuente(Context context, ScannerActivity scannerActivity, Menu m){

        gestionNavigation(context, scannerActivity, 0, BARLOW, m);
        gestionNavigation(context, scannerActivity, 1, BEBAS_NEUE, m);
        gestionNavigation(context, scannerActivity, 2, RUSSO_ONE, m);
        gestionNavigation(context, scannerActivity, 3, BASE_ONE, m);
        gestionNavigation(context, scannerActivity, 4, TAXICAB, m);
        gestionNavigation(context, scannerActivity, 5, VIGA, m);
    }

    //Método para gestionar y aplicar el tipo de fuente almacenada en NavigationDrawer (Menú lateral).
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionNavigation(Context context, ScannerActivity scannerActivity, int num, final String pathFuente, Menu m){

        if(getValuePreferenceTypeface(context) == num){
            externalFont = createTypeface(context, pathFuente);
            menuItemNavigation(externalFont, m);

            View headerView = scannerActivity.navigationView.getHeaderView(0);
            TextView navHeaderTv = headerView.findViewById(R.id.tv_header);
            navHeaderTv.setText(typeface(externalFont, navHeaderTv.getText()));
        }
    }

    //Método donde se aplica al item del Switch que acvtiva o desactiva el modo oscuro en el NavigationView el tipo de fuente seleccionado.
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private void menuItemNavigation(Typeface font, Menu m) {

        for (int i = 1; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            if(mi == m.getItem(4)){
                SpannableString ss = new SpannableString( m.getItem(4).getTitle());
                ss.setSpan(new CustomTypefaceSpan("", font), 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                m.getItem(4).setTitle(ss);

            } else {
                SpannableString s = new SpannableString(mi.getTitle());
                s.setSpan(new CustomTypefaceSpan("", font), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mi.setTitle(s);
            }
        }
    }

    //Método para guardar los cambios al seleccionar una fuente de la lista del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void guardarCambiosTipoFuente(int position, Context context, CustomActivity customActivity) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        gestionarPosicionSpinner(context, position, customActivity, null);
        customActivity.spinner_fuentes.getItemAtPosition(position);

        editor.putInt(CHANGE_FONT, position);
        editor.apply();

        gestionFuenteListaSpinner(context, customActivity.spinner_fuentes.getSelectedView(), customActivity);
        gestionarTipoDeFuente(context, null, customActivity, null, null,
                null, null, null, null, null, null);

    }

    //Método para aplicar fuente en CustomActivity y a la lista Spinner según la posición seleccionada en el propio Sinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void posicionSpinnerSeleccionada(Context context, int position, CustomActivity customActivity,
                                            View view, int num, String pathFuente){

        if (position == num) {
            cambioDeFuente(context,null, customActivity, null, null, null,
                    null, null, null, null, null, pathFuente);

            if(view != null){
                cambioDeFuenteEnListaSpinner(context, view, customActivity, pathFuente, position);
            }
        }
    }

    //Método para gestionar la posición seleccionada en spinner y aplicar la fuente en CustomActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void gestionarPosicionSpinner(Context context, int position, CustomActivity customActivity, View view) {

        posicionSpinnerSeleccionada(context, position, customActivity, view, 0, BARLOW);
        posicionSpinnerSeleccionada(context, position, customActivity, view, 1, BEBAS_NEUE);
        posicionSpinnerSeleccionada(context, position, customActivity, view, 2, RUSSO_ONE);
        posicionSpinnerSeleccionada(context, position, customActivity, view, 3, BASE_ONE);
        posicionSpinnerSeleccionada(context, position, customActivity, view, 4, TAXICAB);
        posicionSpinnerSeleccionada(context, position, customActivity, view, 5, VIGA);
    }

    //Método para comprobar el tipo de fuente almacenado en las Shared Preferences del dispositivo y poder así, aplicarlo a la app.
    public int getValuePreferenceTypeface(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_TEMA,  Context.MODE_PRIVATE);
        return  preferences.getInt(CHANGE_FONT, -1);
    }

    //Método para cambiar la fuente correspondiente en todas las Activities y Fragments.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarFuenteEnPantallas(Context context, ActivitySplash activitySplash, CustomActivity customActivity,
                                         CerrarSesionActivity cerrarSesionActivity, ConfiguracionActivity configuracionActivity,
                                         LoginActivity loginActivity, ReservaActivity reservaActivity, ScannerActivity scannerActivity,
                                         FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag,
                                         FragmentScanner fragScanner, int num, final String pathFuente){

        if(getValuePreferenceTypeface(context) == num){
            cambioDeFuente(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                    reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, pathFuente);
        }
    }

    //Método para aplicar la fuente seleccionada en las distintas panatallas de la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionarTipoDeFuente(Context context, ActivitySplash activitySplash, CustomActivity customActivity,
                                      CerrarSesionActivity cerrarSesionActivity, ConfiguracionActivity configuracionActivity,
                                      LoginActivity loginActivity, ReservaActivity reservaActivity, ScannerActivity scannerActivity,
                                      FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag,
                                      FragmentScanner fragScanner){

        aplicarFuenteEnPantallas(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, 0, BARLOW);

        aplicarFuenteEnPantallas(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, 1, BEBAS_NEUE);

        aplicarFuenteEnPantallas(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, 2, RUSSO_ONE);

        aplicarFuenteEnPantallas(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, 3, BASE_ONE);

        aplicarFuenteEnPantallas(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, 4, TAXICAB);

        aplicarFuenteEnPantallas(context,activitySplash, customActivity, cerrarSesionActivity, configuracionActivity, loginActivity,
                reservaActivity, scannerActivity, fragListaProyectos, listaReservasFrag, fragScanner, 5, VIGA);
    }

    //Método para cargar en la posición del item seleccionado en el Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void posicionItemSeleccionado(int pos, CustomActivity customActivity){

        if(customActivity != null){
            if(customActivity.spinner_fuentes != null){
                customActivity.spinner_fuentes.setSelection(pos);

                aplicarCambiosFuenteEnCustomActivity(customActivity, 0, pos, BARLOW);
                aplicarCambiosFuenteEnCustomActivity(customActivity, 1, pos, BEBAS_NEUE);
                aplicarCambiosFuenteEnCustomActivity(customActivity, 2, pos, RUSSO_ONE);
                aplicarCambiosFuenteEnCustomActivity(customActivity, 3, pos, BASE_ONE);
                aplicarCambiosFuenteEnCustomActivity(customActivity, 4, pos, TAXICAB);
                aplicarCambiosFuenteEnCustomActivity(customActivity, 5, pos, VIGA);
            }
        }
    }

    //Método para coprobar la posición del item almacenado y la posición seleccionada en el spinner son la misma
    //y aplicar los cambios de fuente.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarCambiosFuenteEnCustomActivity(CustomActivity customActivity, int num, int pos, final String pathFuente){

        externalFont = createTypeface(customActivity, pathFuente);

        if(getValuePreferenceTypeface(customActivity) == num && pos == num) {
            componentesCustomActivity(customActivity, externalFont);
            if(customActivity.spinner_fuentes.getSelectedView() != null){
                ((TextView) customActivity.spinner_fuentes.getSelectedView()).setTypeface(externalFont);
            }

            if(customActivity.spinner_size.getSelectedView() != null){
                ((TextView) customActivity.spinner_size.getSelectedView()).setTypeface(externalFont);
            }
        }
    }

    //Método aplicar el cambio de fuente en Spinner según la opción que esté almacenada en las Shared Preferences.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void aplicarCambioFuenteEnSpinner(Context context, View view,
                                              CustomActivity customActivity, int num, String pathFuente){

        if (getValuePreferenceTypeface(context) == num){
            cambioDeFuenteEnListaSpinner(context, view, customActivity, pathFuente, num);
        }
    }

    //Método para cambiar el tipo de fuente a la lista del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public View gestionFuenteListaSpinner(Context context, View view, CustomActivity customActivity){

        aplicarCambioFuenteEnSpinner(context, view, customActivity, 0, BARLOW);
        aplicarCambioFuenteEnSpinner(context, view, customActivity, 1, BEBAS_NEUE);
        aplicarCambioFuenteEnSpinner(context, view, customActivity, 2, RUSSO_ONE);
        aplicarCambioFuenteEnSpinner(context, view, customActivity, 3, BASE_ONE);
        aplicarCambioFuenteEnSpinner(context, view, customActivity, 4, TAXICAB);
        aplicarCambioFuenteEnSpinner(context, view, customActivity, 5, VIGA);

        return view;
    }

    //Método para aplicar el cambio de fuente en los distintos componentes de CustomActivity.
    public void componentesCustomActivity(CustomActivity customActivity, Typeface externalFont){

        if(customActivity.switchOndas != null && customActivity.switchColor != null
                && customActivity.tv_titulo_personalizacion != null){

            customActivity.switchOndas.setTypeface(externalFont);
            customActivity.switchColor.setTypeface(externalFont);
            customActivity.tv_titulo_personalizacion.setTypeface(externalFont);
        }
    }

    //Método para aplicar el cambio de fuente en los distintos componentes de LoginActivity.
    public void componentesLoginActivity(LoginActivity loginActivity, Typeface externalFont){

        if(loginActivity.emailEdit != null && loginActivity.passwordEdit != null){
            loginActivity.emailEdit.setTypeface(externalFont);
            loginActivity.passwordEdit.setTypeface(externalFont);
        }

        if(loginActivity.tv_titulo_login != null){
            loginActivity.tv_titulo_login.setTypeface(externalFont);
        }

        if(loginActivity.btn_login != null && loginActivity.rb_sesion != null){
            loginActivity.btn_login.setTypeface(externalFont);
            loginActivity.rb_sesion.setTypeface(externalFont);
        }
    }

    //Método para aplicar el cambio de fuente en los distintos componentes de LoginActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesLoginActivity2(LoginActivity loginActivity, int num, String nombreFuente, final String pathFuente){

        if(loginActivity.btn_login != null && loginActivity.rb_sesion != null) {
            if (getValuePreferenceTypeface(loginActivity) == num) {
                if (!loginActivity.btn_login.getTypeface().toString().equals(nombreFuente)
                        || !loginActivity.rb_sesion.getTypeface().toString().equals(nombreFuente)) {

                    externalFont = createTypeface(loginActivity, pathFuente);

                    if(loginActivity.emailEdit != null && loginActivity.passwordEdit != null){
                        loginActivity.emailEdit.setTypeface(externalFont);
                        loginActivity.passwordEdit.setTypeface(externalFont);
                    }

                    if(loginActivity.tv_titulo_login != null){
                        loginActivity.tv_titulo_login.setTypeface(externalFont);
                    }

                    if(loginActivity.btn_login != null && loginActivity.rb_sesion != null){
                        loginActivity.btn_login.setText(typeface(externalFont, loginActivity.btn_login.getText()));
                        loginActivity.rb_sesion.setText(typeface(externalFont, loginActivity.rb_sesion.getText()));
                    }
                }
            }
        }
    }

    //Método para aplicar el cambio de fuente en los distintos componentes de ResevasActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void componentesReservaActivity(ReservaActivity reservaActivity, Typeface font){

        componentesDelModoEditarDeReservaActivity(reservaActivity, font);
        componentesDelModoRegistrarDeReservaActivity(reservaActivity, font);
    }

    //Método para aplicar el cambio de fuente en los distintos componentes del modo editar de ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void componentesDelModoEditarDeReservaActivity(ReservaActivity reservaActivity, Typeface font){

        cambioFuenteEnBotonesReservaActivity(reservaActivity, font);
    }

    //Método para gestionar el cambio de fuente en los botones del modo editar de ReservaACTIVITY.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void cambioFuenteEnBotonesReservaActivity(ReservaActivity reservaActivity, Typeface f) {

        if(reservaActivity.anular != null){
            reservaActivity.anular.setTypeface(f);
        }
        if(reservaActivity.aceptar != null){
            reservaActivity.aceptar.setTypeface(f);
        }
        if(reservaActivity.btn_volverAreservar != null){
            reservaActivity.btn_volverAreservar.setTypeface(f);
        }
        if(reservaActivity.fechasPrevistas != null){
            reservaActivity.fechasPrevistas.setTypeface(f);
        }
        if(reservaActivity.registrarEntrada != null){
            reservaActivity.registrarEntrada.setTypeface(f);
        }
        if( reservaActivity.registrarSalida != null){
            reservaActivity.registrarSalida.setTypeface(f);
        }
        if(reservaActivity.fechaOperacion != null){
            reservaActivity.fechaOperacion.setTypeface(f);
        }
        if(reservaActivity.tv_dialog_barcode != null){
            reservaActivity.tv_dialog_barcode.setTypeface(f);
        }
    }

    //Método para aplicar el cambio de fuente en los distintos componentes del modo registrar de ReservaActivity.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void componentesDelModoRegistrarDeReservaActivity(ReservaActivity reservaActivity, Typeface font){

        if(reservaActivity.codigoBarras != null && reservaActivity.nombreMaterial != null
                && reservaActivity.seleccionarFechas != null &&  reservaActivity.tv_titulo_operacion != null
                && reservaActivity.usuario != null && reservaActivity.seleccionarProyecto != null
                && reservaActivity.seleccionarEncargado != null){

            reservaActivity.codigoBarras.setTypeface(font);
            reservaActivity.nombreMaterial.setTypeface(font);
            reservaActivity.seleccionarFechas.setTypeface(font);
            reservaActivity.tv_titulo_operacion.setTypeface(font);
            reservaActivity.usuario.setTypeface(font);
            reservaActivity.seleccionarProyecto.setTypeface(font);
            reservaActivity.seleccionarEncargado.setTypeface(font);

            if(reservaActivity.btn_registrar != null) {
                reservaActivity.btn_registrar.setTypeface(font);
            }
        }
    }

    //Método para gestionar la fuente en el Calendario del selector de rangos de fechas (rangePicker) para el modo oscuro.
    public void gestionFuenteRangePickerDark(Context context, MaterialDatePicker.Builder<Pair<Long,
            Long>> mdtRange, boolean getValuePreferenceAzul){

        if(getValuePreferenceAzul){
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 0, STYLE_DARK_AZUL_RANGEPICKER_BARLOW);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 1, STYLE_DARK_AZUL_RANGEPICKER_BEBAS_NEUE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 2, STYLE_DARK_AZUL_RANGEPICKER_RUSSO_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 3, STYLE_DARK_AZUL_RANGEPICKER_BASE_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 4, STYLE_DARK_AZUL_RANGEPICKER_TAXICAB);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 5, STYLE_DARK_AZUL_RANGEPICKER_VIGA);

        } else {
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 0, STYLE_DARK_ROJO_RANGEPICKER_BARLOW);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 1, STYLE_DARK_ROJO_RANGEPICKER_BEBAS_NEUE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 2, STYLE_DARK_ROJO_RANGEPICKER_RUSSO_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 3, STYLE_DARK_ROJO_RANGEPICKER_BASE_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 4, STYLE_DARK_ROJO_RANGEPICKER_TAXICAB);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 5, STYLE_DARK_ROJO_RANGEPICKER_VIGA);
        }
    }

    //Método para gestionar la fuente en el Calendario del selector de rangos de fechas (rangePicker) para el modo claro.
    public void gestionFuenteRangePicker(Context context, MaterialDatePicker.Builder<Pair<Long, Long>> mdtRange, boolean getValuePreferenceAzul){

        if(getValuePreferenceAzul){
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 0, STYLE_AZUL_RANGEPICKER_BARLOW);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 1, STYLE_AZUL_RANGEPICKER_BEBAS_NEUE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 2, STYLE_AZUL_RANGEPICKER_RUSSO_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 3, STYLE_AZUL_RANGEPICKER_BASE_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 4, STYLE_AZUL_RANGEPICKER_TAXICAB);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 5, STYLE_AZUL_RANGEPICKER_VIGA);

        } else {
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 0, STYLE_ROJO_RANGEPICKER_BARLOW);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 1, STYLE_ROJO_RANGEPICKER_BEBAS_NEUE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 2, STYLE_ROJO_RANGEPICKER_RUSSO_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 3, STYLE_ROJO_RANGEPICKER_BASE_ONE);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 4, STYLE_ROJO_RANGEPICKER_TAXICAB);
            aplicarTemaEnMaterialDatePicker(context, null, mdtRange, 5, STYLE_ROJO_RANGEPICKER_VIGA);
        }
    }

    //Método para gestionar la fuente en el Calendario del selector de fechas (datePicker).
    public void gestionFuenteDatePicker(Context context, MaterialDatePicker.Builder<Long> mdt, boolean recibiendoDatosTema) {

        if (recibiendoDatosTema) {
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 0, STYLE_DARK_DATEPICKER_BARLOW);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 1, STYLE_DARK_DATEPICKER_BEBAS_NEUE);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 2, STYLE_DARK_DATEPICKER_RUSSO_ONE);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 3, STYLE_DARK_DATEPICKER_BASE_ONE);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 4, STYLE_DARK_DATEPICKER_TAXICAB);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 5, STYLE_DARK_DATEPICKER_VIGA);

        } else {
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 0, STYLE_DATEPICKER_BARLOW);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 1, STYLE_DATEPICKER_BEBAS_NEUE);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 2, STYLE_DATEPICKER_RUSSO_ONE);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 3, STYLE_DATEPICKER_BASE_ONE);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 4, STYLE_DATEPICKER_TAXICAB);
            aplicarTemaEnMaterialDatePicker(context, mdt, null, 5, STYLE_DATEPICKER_VIGA);
        }
    }

    //Método para aplicar al datePicker el tema correspondiente según la fuente almacenada en las Shared Preferences.
    public void aplicarTemaEnMaterialDatePicker(Context context, MaterialDatePicker.Builder<Long> mdt,
                                        MaterialDatePicker.Builder<Pair<Long, Long>> mdtRange, int num, final int pathStyle){

        if(mdt != null){
            if (getValuePreferenceTypeface(context) == num) {
                mdt.setTheme(pathStyle);

            } else {
                if(gestionarColorApp.recibiendoDatosTema(context)){
                    mdt.setTheme(STYLE_DARK_DATEPICKER_BARLOW);

                } else {
                    mdt.setTheme(STYLE_DATEPICKER_BARLOW);
                }
            }
        }
        if(mdtRange != null){
            if (getValuePreferenceTypeface(context) == num) {
                mdtRange.setTheme(pathStyle);

            } else {
                if(gestionarColorApp.recibiendoDatosTema(context)){
                    if(gestionarColorApp.getValuePreferenceAzul(context)){
                        mdtRange.setTheme(STYLE_DARK_AZUL_RANGEPICKER_BARLOW);
                    } else {
                        mdtRange.setTheme(STYLE_DARK_ROJO_RANGEPICKER_BARLOW);
                    }

                } else {
                    if(gestionarColorApp.getValuePreferenceAzul(context)){
                        mdtRange.setTheme(STYLE_AZUL_RANGEPICKER_BARLOW);
                    } else {
                        mdtRange.setTheme(STYLE_ROJO_RANGEPICKER_BARLOW);
                    }
                }
            }
        }
    }

    //Método para aplicar la fuente seleccionada en el dialogo del Spinner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private <T> void componentesDialogSpinner(Context context, AdapterSelector<T>.ItemVH itemVH, int num, final String pathFuente) {

        if(itemVH != null) {
            if (getValuePreferenceTypeface(context) == num) {
                font = createTypeface(context, pathFuente);
                ((TextView)itemVH.itemView).setText(typeface(font, ((TextView)itemVH.itemView).getText()));
            }
        }
    }

    //Método para aplicar la fuente seleccionada en los Adapter de los RecyclerView de Proyectos y ReservasProyecto.
    public void componentesAdapterRecycler(Context context, int num, final String pathFuente,
                                           AdapterProyecto.ProyectoViewHolder proyectoViewHolder,
                                           AdapterReservasProyecto.OperacionViewHolder opViewHolder){

        if(getValuePreferenceTypeface(context) == num){
            externalFont = createTypeface(context, pathFuente);

            if(proyectoViewHolder != null){
                proyectoViewHolder.nomCliente.setTypeface(externalFont);
            }
            if(opViewHolder != null){
                gestionarFuenteEnAdapterReservas(opViewHolder);
            }
        }
    }

    //Método para aplicar fuente en texto del botón de la linterna y texto de información.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionFuenteEnScanner(Context context, CustomScannerActivity customScannerActivity){

        if(customScannerActivity.switchFlashlightButton != null){
            customScannerActivity.switchFlashlightButton.setText(typeface(font(context), customScannerActivity.switchFlashlightButton.getText()));
        }
        if(customScannerActivity.barcodeScannerView != null){
            customScannerActivity.barcodeScannerView.getStatusView().setText(typeface(font(context), Objects.requireNonNull(customScannerActivity.barcodeScannerView.getStatusView()).getText()));
        }
    }

    //Método para crear el objeto Typeface con la fuente guardada y hacer que devuelva ese objeto
    //para utilizarlo en el método de 'gestionFuenteEnSpinner'.
    private Typeface font(Context context){

        if(getValuePreferenceTypeface(context) == 0){
            externalFont = createTypeface(context, BARLOW);
        }
        if(getValuePreferenceTypeface(context) == 1){
            externalFont = createTypeface(context, BEBAS_NEUE);
        }
        if(getValuePreferenceTypeface(context) == 2){
            externalFont = createTypeface(context, RUSSO_ONE);
        }
        if(getValuePreferenceTypeface(context) == 3){
            externalFont = createTypeface(context, BASE_ONE);
        }
        if(getValuePreferenceTypeface(context) == 4){
            externalFont = createTypeface(context, TAXICAB);
        }
        if(getValuePreferenceTypeface(context) == 5){
            externalFont = createTypeface(context, VIGA);
        }

        return externalFont;
    }
}
