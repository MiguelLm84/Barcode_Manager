package com.miguel_lm.app_barcode.model.management.color_app;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CHANGE_COLOR_APP;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BLUE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_BLUE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_BLUE_LIGHT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RED;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RED_LIGHT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RED_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_WHITE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.RED;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.FILE_THEME_PREFERENCES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.THEME_APP_BARCODE_BLUE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.THEME_APP_BARCODE_DARK_BLUE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.THEME_APP_BARCODE_DARK_RED;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.THEME_APP_BARCODE_RED;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.activities.CustomActivity;
import com.miguel_lm.app_barcode.ui.activities.ReservaActivity;
import com.miguel_lm.app_barcode.ui.fragments.FragmentListaProyectos;
import com.miguel_lm.app_barcode.ui.fragments.ListaReservasFragment;
import java.io.File;


public class GestionarColorApp {

    @SuppressLint("SdCardPath") File preferenciasTema = new File(FILE_THEME_PREFERENCES);

    //Método para comprobar el color almacenado en las Shared Preferences del dispositivo y poder así, aplicarlo a la app.
    public boolean getValuePreferenceAzul(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_TEMA,  Context.MODE_PRIVATE);
        return  preferences.getBoolean(CHANGE_COLOR_APP, false);
    }

    //Método que deveuelve el modo del tema guardado en Shared Preferences.
    public boolean recibiendoDatosTema(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MODO_DARK, false);
    }

    //Método para aplicar el tema que corresponda en las
    //distintas pantallas de la app según el modo que esté establecido.
    public void colorDefault(Context context){

        if(getValuePreferenceAzul(context)){
            if(recibiendoDatosTema(context)){
                context.setTheme(THEME_APP_BARCODE_DARK_BLUE);
            } else {
                context.setTheme(THEME_APP_BARCODE_BLUE);
            }
        } else {
            if(recibiendoDatosTema(context)){
                context.setTheme(THEME_APP_BARCODE_DARK_RED);
            } else {
                context.setTheme(THEME_APP_BARCODE_RED);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void aplicarTemaPorDefecto(Context context, CustomActivity customActivity,
                                      FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag){

        if(customActivity != null){
            if(getValuePreferenceAzul(context)){
                if(recibiendoDatosTema(context)){
                    customActivity.setTheme(THEME_APP_BARCODE_DARK_BLUE);
                } else {
                    customActivity.setTheme(THEME_APP_BARCODE_BLUE);
                }

                gestionarCambioDeTema(context, fragListaProyectos, listaReservasFrag,
                        THEME_APP_BARCODE_DARK_BLUE, THEME_APP_BARCODE_BLUE);

            } else {
                if(recibiendoDatosTema(context)){
                    customActivity.setTheme(THEME_APP_BARCODE_DARK_RED);
                } else {
                    customActivity.setTheme(THEME_APP_BARCODE_RED);
                }
                gestionarCambioDeTema(context, fragListaProyectos, listaReservasFrag,
                        THEME_APP_BARCODE_DARK_RED, THEME_APP_BARCODE_RED);
            }
        }
    }

    //Método para la gestión del color del texto que acompaña a las animaciones Lottie
    // en las listas de Proyectos y Reservas cuando no hay datos que mostrar.
    public void gestionarColorTextoLottieEnListas(Context context, FragmentListaProyectos fragmentListaProyectos,
                                                  ListaReservasFragment listaReservasFragment){

        if(recibiendoDatosTema(context)){
            if(fragmentListaProyectos != null  && listaReservasFragment == null){
                if(fragmentListaProyectos.tv_proyectos_no_encontrados != null){
                    fragmentListaProyectos.tv_proyectos_no_encontrados.setTextColor(context.getResources()
                            .getColor(COLOR_WHITE, context.getTheme()));
                }
            }

           if(fragmentListaProyectos == null  && listaReservasFragment != null){
               if(listaReservasFragment.datosNoEncontrados != null){
                   listaReservasFragment.datosNoEncontrados.setTextColor(context.getResources()
                           .getColor(COLOR_WHITE,context.getTheme()));
               }
           }
        }
    }

    //Método para comprobar y aplicar el tema según esté guardado en las Shared Preferences en las distintas Activitys.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void gestionColorTema(Context context, CustomActivity customActivity,
                                 FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag){

        if(customActivity != null){
            gestionTextoSwitch(customActivity);
            aplicarTemaPorDefecto(context, customActivity, fragListaProyectos, listaReservasFrag);
        }
    }

    //Método para mostrar el tema rojo o azul aplicando, a su vez, su variante oscura o clara según la opción guardada.
    private void gestionarCambioDeTema(Context context, FragmentListaProyectos fragListaProyectos,
                                       ListaReservasFragment listaReservasFrag, int styleDark, int syleLight) {

        if(recibiendoDatosTema(context)){
            context.setTheme(styleDark);
            if(fragListaProyectos != null || listaReservasFrag != null){
                gestionarColorTextoLottieEnListas(context, fragListaProyectos, listaReservasFrag);
            }

        } else {
            context.setTheme(syleLight);
        }
    }

    //Método para la gestión del texto a mostrar en el Switch al activar o desactivar.
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void gestionTextoSwitch(CustomActivity customActivity) {

        if(customActivity.switchColor != null){
            if(recibiendoDatosTema(customActivity)){
                if(getValuePreferenceAzul(customActivity)){
                    customActivity.switchColor.setText(customActivity.getResources().getText(BLUE));

                } else {
                    customActivity.switchColor.setText(customActivity.getResources().getText(RED));
                }

            } else {
                if(!getValuePreferenceAzul(customActivity)){
                    customActivity.switchColor.setText(customActivity.getResources().getText(RED));

                } else {
                    customActivity.switchColor.setText(customActivity.getResources().getText(BLUE));
                }
            }
        }
    }

    //Método para cambiar el color del icono de selección de Spinner de fuentes según el modo y el color seleccionado.
    public void gestionColorIconoSelector(CustomActivity customActivity) {

        if(getValuePreferenceAzul(customActivity)){
            customActivity.spinner_fuentes.getBackground().setColorFilter(customActivity.getResources()
                    .getColor(COLOR_BLUE, customActivity.getTheme()), PorterDuff.Mode.SRC_ATOP);

        } else {
            if (recibiendoDatosTema(customActivity)) {
                customActivity.spinner_fuentes.getBackground().setColorFilter(customActivity.getResources()
                        .getColor(COLOR_RED_DARK, customActivity.getTheme()), PorterDuff.Mode.SRC_ATOP);
            } else {
                customActivity.spinner_fuentes.getBackground().setColorFilter(customActivity.getResources()
                        .getColor(COLOR_RED, customActivity.getTheme()), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    //Método para cambiar el color del icono de selección de Spinner de fuentes según el modo y el color seleccionado.
    public void gestionColorIconoSelectorSpinnerTextSize(CustomActivity customActivity) {

        if(getValuePreferenceAzul(customActivity)){
            customActivity.spinner_size.getBackground().setColorFilter(customActivity.getResources()
                    .getColor(COLOR_BLUE, customActivity.getTheme()), PorterDuff.Mode.SRC_ATOP);

        } else {
            if(recibiendoDatosTema(customActivity)) {
                customActivity.spinner_size.getBackground().setColorFilter(customActivity.getResources()
                        .getColor(COLOR_RED_DARK, customActivity.getTheme()), PorterDuff.Mode.SRC_ATOP);
            } else {
                customActivity.spinner_size.getBackground().setColorFilter(customActivity.getResources()
                        .getColor(COLOR_RED, customActivity.getTheme()), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    //Método para gestionar el modo (oscuro o claro) y el color del RangePicker.
    public void gestionarColorRangePicker(Context context, ReservaActivity reservaActivity,
                                          MaterialDatePicker.Builder<Pair<Long, Long>> mdt, SeleccionFuente seleccionFuente) {

        if (recibiendoDatosTema(context)) {
            seleccionFuente.gestionFuenteRangePickerDark(context, mdt, getValuePreferenceAzul(context));

        } else {
            seleccionFuente.gestionFuenteRangePicker(context, mdt, getValuePreferenceAzul(context));
        }
        reservaActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //Método para gestionar el modo (oscuro o claro) y el color del DatePicker.
    public void gestionarColorDatePicker(Context context, ReservaActivity reservaActivity, MaterialDatePicker.Builder<Long> mdt,
                                         SeleccionFuente seleccionFuente, boolean recibiendoDatosTema) {

        seleccionFuente.gestionFuenteDatePicker(context, mdt, recibiendoDatosTema);
        reservaActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //Método para la gestión de colores del buscador de la vista.
    public void gestionColorBuscador(Context context, FragmentListaProyectos fragListaProyectos,
                                      ListaReservasFragment listaReservasFrag){

        if(getValuePreferenceAzul(context)) {
            aplicarColorAzulABuscador(context, fragListaProyectos, null);
            aplicarColorAzulABuscador(context, null, listaReservasFrag);

        } else {
            aplicarColorRojoABuscador(context, fragListaProyectos, null);
            aplicarColorRojoABuscador(context,null, listaReservasFrag);
        }
    }

    //Método para aplicar el color Rojo a los componentes del buscardor de la lista, teniendo en cuenta
    //si el fichero de las SharedPreferences donde se almacena el dato del color existe o no.
    private void aplicarColorRojoABuscador(Context context, FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag){

        if(fragListaProyectos != null){

            ImageView searchIcon = fragListaProyectos.buscadorListaPro.findViewById(androidx.appcompat.R.id.search_mag_icon);
            searchIcon.setImageDrawable(ContextCompat.getDrawable(fragListaProyectos.requireActivity(),R.drawable.ic_baseline_search_24));
            SearchView.SearchAutoComplete searchAutoComplete = fragListaProyectos.buscadorListaPro
                    .findViewById(androidx.appcompat.R.id.search_src_text);

            aplicarColorRojoEnBuscadorLista(context, searchIcon, searchAutoComplete);
        }

        if(listaReservasFrag != null){

            ImageView searchIcon = listaReservasFrag.buscadorLista.findViewById(androidx.appcompat.R.id.search_mag_icon);
            searchIcon.setImageDrawable(ContextCompat.getDrawable(listaReservasFrag.requireActivity(),R.drawable.ic_baseline_search_24));
            SearchView.SearchAutoComplete searchAutoComplete = listaReservasFrag.buscadorLista
                    .findViewById(androidx.appcompat.R.id.search_src_text);

            aplicarColorRojoEnBuscadorLista(context, searchIcon, searchAutoComplete);
        }
    }

    //Método para aplicar el color Rojo en los elementos que componenen el buscador.
    private void aplicarColorRojoEnBuscadorLista(Context context, ImageView searchIcon,
                                                 SearchView.SearchAutoComplete searchAutoComplete){

        if(recibiendoDatosTema(context)){
            searchIcon.setColorFilter(ContextCompat.getColor(context, COLOR_RED_DARK), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setHintTextColor(context.getResources().getColor(COLOR_RED_LIGHT, context.getTheme()));
            searchAutoComplete.setTextColor(context.getResources().getColor(COLOR_RED_DARK, context.getTheme()));

        } else {
            searchIcon.setColorFilter(ContextCompat.getColor(context, COLOR_RED), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setHintTextColor(context.getResources().getColor(COLOR_RED_LIGHT, context.getTheme()));
            searchAutoComplete.setTextColor(context.getResources().getColor(COLOR_RED, context.getTheme()));
        }
    }

    //Método para aplicar el color Azul a los componentes del buscardor de la lista.
    private void aplicarColorAzulABuscador(Context context, FragmentListaProyectos fragListaProyectos, ListaReservasFragment listaReservasFrag){

        if(fragListaProyectos != null){

            ImageView searchIcon = fragListaProyectos.buscadorListaPro.findViewById(androidx.appcompat.R.id.search_mag_icon);
            searchIcon.setImageDrawable(ContextCompat.getDrawable(fragListaProyectos.requireActivity(),R.drawable.ic_baseline_search_24));
            SearchView.SearchAutoComplete searchAutoComplete = fragListaProyectos.buscadorListaPro
                    .findViewById(androidx.appcompat.R.id.search_src_text);

            aplicarColorAzulEnBuscadorLista(context, searchIcon, searchAutoComplete);
            fragListaProyectos.buscadorListaPro.setBackgroundResource(R.drawable.round_border_v2);
        }
        if(listaReservasFrag != null){

            ImageView searchIcon = listaReservasFrag.buscadorLista.findViewById(androidx.appcompat.R.id.search_mag_icon);
            searchIcon.setImageDrawable(ContextCompat.getDrawable(listaReservasFrag.requireActivity(),R.drawable.ic_baseline_search_24));
            SearchView.SearchAutoComplete searchAutoComplete = listaReservasFrag.buscadorLista
                    .findViewById(androidx.appcompat.R.id.search_src_text);

            aplicarColorAzulEnBuscadorLista(context, searchIcon, searchAutoComplete);
            listaReservasFrag.buscadorLista.setBackgroundResource(R.drawable.round_border_v2);
        }
    }

    //Método para aplicar el color Azul en los elementos que componenen el buscador.
    private void aplicarColorAzulEnBuscadorLista(Context context, ImageView searchIcon,
                                                 SearchView.SearchAutoComplete searchAutoComplete){

        searchIcon.setColorFilter(ContextCompat.getColor(context, COLOR_BLUE), PorterDuff.Mode.SRC_IN);
        searchAutoComplete.setHintTextColor(context.getResources().getColor(COLOR_BLUE_LIGHT, context.getTheme()));
        searchAutoComplete.setTextColor(context.getResources().getColor(COLOR_RED_LIGHT, context.getTheme()));
    }
}