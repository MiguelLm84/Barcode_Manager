package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CANCEL;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_BLUE_BASIC;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_BLUE_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GARNET;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GRAY;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GREEN_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_ORANGE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_ORANGE_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RED_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GREEN;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_WHITE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.EXTRA_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_DARK_SNACKBAR;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODIFICAR_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MULTIPLE_REQUEST_CODE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PENDING;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_FICHERO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PRODUCTO_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.REGISTRAR_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.REPLACE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.RESERVA_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.RESERVA_RESULT_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.SCAN_AGAIN;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NO_CHANGES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_BOOKING_EDIT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TITLE_BOOKING_NEW;
import static java.util.Arrays.asList;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.util.Pair;
import com.almacen.api.client.model.CrearReservaDto;
import com.almacen.api.client.model.DetalleProductoDto;
import com.almacen.api.client.model.DetalleProyectoDto;
import com.almacen.api.client.model.DetalleReservaDto;
import com.almacen.api.client.model.DetalleUsuarioDto;
import com.almacen.api.client.model.ModificarReservaDto;
import com.almacen.api.client.model.ReservaModificadaDto;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.singletons.sesion.Sesion;
import com.miguel_lm.app_barcode.ui.fragments.ProyectosDialogFragment;
import com.miguel_lm.app_barcode.ui.fragments.UsuariosDialogFragment;
import org.openapitools.client.api.ProyectosControllerApi;
import org.openapitools.client.api.ReservasControllerApi;
import org.openapitools.client.api.UsuariosControllerApi;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReservaActivity extends AppCompatActivity implements ProyectosDialogFragment.OnItemClick,
        UsuariosDialogFragment.OnUsuarioItemClick {

    private static final String LOG_TAG = "log_json";
    String colorTextoTv = "?attr/colorPrimaryVariant";

    //Declaración de variables
    public TextView codigoBarras, nombreMaterial, tv_titulo_operacion, usuario, fechasPrevistas, tv_nuevo_escaneo, tv_snackbar;
    private ImageView btn_volver;
    public Button btn_registrar, btn_aceptar, btn_aceptarDialogContinuarEscaneando, btn_cancelarDialogContinuarEscaneando;
    public TextView seleccionarProyecto, seleccionarEncargado, tv_tituloDialogEliminar, tv_dialog_barcode, snackbarTextView;
    private CoordinatorLayout coordinatorLayout;
    private SharedPreferences preferencias;
    private ReservasControllerApi reservasControllerApi;
    private DetalleProyectoDto proyectoSeleccionado;
    private DetalleReservaDto reservaActual;
    private DetalleUsuarioDto encargadoSeleccionado;
    private DetalleProductoDto productoSeleccionado;
    public Long fechaPrevistaEntradaSeleccionada;
    public Long fechaPrevistaSalidaSeleccionada;
    public TextView seleccionarFechas;
    public Button anular;
    public Button btn_volverAreservar;
    public Button registrarEntrada;
    public Button registrarSalida;
    private Long fechaSeleccionada = Instant.now().toEpochMilli();
    public TextView fechaOperacion;
    public Button aceptar;
    private Space espacio;
    public ImageButton guardar;
    public TextView tv_fechaEntradaFinal, tv_fechaSalidaFinal, tv_operacion, tv_guionEntreFechas;
    public Button btnCancelar, btnEliminar;
    private List<DetalleReservaDto> fechasReservadas = Collections.emptyList();
    MaterialDatePicker<Pair<Long, Long>> dateRangePicker;
    MaterialDatePicker<Long> dialog;
    public View ondaSupReservaActivity;
    public View ondaInfReservaActivity;
    public View viewSupReservaActivity;
    public View viewInfReservaActivity;
    public View dialogLayout;
    public Snackbar snackbar;

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
        setContentView(R.layout.activity_reserva);
        reservasControllerApi = ClienteApiFactoria.getInstance().createService(ReservasControllerApi.class);
        preferencias = this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);
        inits();
        gestionOndasView.gestionarOndasViewReservasActivity(this, this);
        adaptarSegunModo();
        obtenerFechasReservadas();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        btn_volver.setOnClickListener(v -> onBackPressed());
        conectividad();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(this);
        super.onPause();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, this,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeReservaActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        conectividad();
        gestionOndasView.gestionarOndasViewReservasActivity(this, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onStart() {
        gestionarColorApp.colorDefault(this);
        super.onStart();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, this,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeReservaActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        conectividad();
        gestionOndasView.gestionarOndasViewReservasActivity(this, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onResume() {
        gestionarColorApp.colorDefault(this);
        super.onResume();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, this,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeReservaActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        conectividad();
        gestionOndasView.gestionarOndasViewReservasActivity(this, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        gestionarColorApp.colorDefault(this);
        super.onRestart();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, this,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeReservaActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        conectividad();
        gestionOndasView.gestionarOndasViewReservasActivity(this, this);
    }

    //Método para obtener las fechas reservadas de los Productos.
    private void obtenerFechasReservadas() {

        reservasControllerApi.obtenerReservasPorIdProducto(productoSeleccionado.getIdProducto())
                .enqueue(new Callback<List<DetalleReservaDto>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<DetalleReservaDto>> call,
                                           @NonNull Response<List<DetalleReservaDto>> response) {
                        fechasReservadas = response.body();
                        initSeleccionarFechas();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<DetalleReservaDto>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    //Método para seleccionar fecha de registro de entrada o salida para la reserva.
    @SuppressLint("WrongConstant")
    public void datePicker(View.OnClickListener onClickListener) {

        MaterialDatePicker.Builder<Long> mdt = MaterialDatePicker.Builder.datePicker();
        gestionarColorApp.gestionarColorDatePicker(this, this, mdt, seleccionFuente,
                gestionarColorApp.recibiendoDatosTema(this));
        dialog = mdt.build();

        dialog.addOnPositiveButtonClickListener(selection -> {
            fechaSeleccionada = selection;
            onClickListener.onClick(null);
        });

        dialog.show(getSupportFragmentManager(), null);
    }

    //Método para seleccionar fechas através del DatePicker de Material.
    @SuppressLint({"WrongConstant", "UseCompatLoadingForDrawables", "ResourceType"})
    private void initSeleccionarFechas() {

        MaterialDatePicker.Builder<Pair<Long, Long>> mdt = MaterialDatePicker.Builder.dateRangePicker();
        seleccionarFechas.setOnClickListener(v -> {
            gestionarColorApp.gestionarColorRangePicker(this, this, mdt, seleccionFuente);
            dateRangePicker = mdt.setCalendarConstraints(
                    new CalendarConstraints.Builder().setValidator(
                            CompositeDateValidator.allOf(asList(DateValidatorPointForward.now(),
                                    validadorFechasReservadas()))).build())
                            .setTitleText("Seleccione rango de fechas").setSelection(Pair.create(
                                            Optional.ofNullable(fechaPrevistaSalidaSeleccionada)
                                                    .orElseGet(MaterialDatePicker::todayInUtcMilliseconds),
                                            Optional.ofNullable(fechaPrevistaEntradaSeleccionada)
                                                    .orElseGet(MaterialDatePicker::todayInUtcMilliseconds))
                    ).build();
            dateRangePicker.addOnPositiveButtonClickListener(selection -> {
                fechaPrevistaSalidaSeleccionada = selection.first;
                fechaPrevistaEntradaSeleccionada = selection.second;

                seleccionarFechas.setText(String.format("%s — %s",
                        new Date(fechaPrevistaSalidaSeleccionada).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        new Date(fechaPrevistaEntradaSeleccionada).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                ));
            });
            dateRangePicker.show(getSupportFragmentManager(), null);
        });
    }

    //Método para mostar vista y datos según el modo.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    private void adaptarSegunModo() {

        switch (getIntent().getStringExtra(MODO_EXTRA)) {
            case REGISTRAR_RESERVA:
                modoRegistrar();
                break;

            case MODIFICAR_RESERVA:
                modoModificar();
                break;
        }

        productoSeleccionado = recuperarProductoExtra();
        nombreMaterial.setText(productoSeleccionado.getNombreProducto());
        codigoBarras.setText(Objects.requireNonNull(productoSeleccionado.getCodigoBarras()).toString());

        eventosOnClickReserva();
    }

    //Método que contiene todos los métodos de los eventos onClickListener de la pantalla reserva.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void eventosOnClickReserva() {

        eventoSeleccionarProyecto();
        eventoSeleccionarEncargado();
        botonAnularReserva();
        botonRegistrarEntrada();
        botonRegistrarSalida();
        botonGuardarDatosDeLaReserva();
    }

    //Método que ejecuta el modo registrar.
    @SuppressLint("SetTextI18n")
    private void modoRegistrar() {

        tv_titulo_operacion.setText(TITLE_BOOKING_NEW);
        setVisibilityCreacion(View.VISIBLE);
        setVisibilityModificacion(View.GONE);
        reservaActual = recuperarCacheadaOCrear();
        adaptarLayoutRegistrar();
        if (isReservaCacheada())
            recuperarValoresCache();
        else
            marcarValoresPorDefecto();
    }

    //Método que ejecuta el modo modificar.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"SetTextI18n"})
    private void modoModificar() {

        tv_titulo_operacion.setText(TITLE_BOOKING_EDIT);
        reservaActual = recuperarReservaExtra();
        cargarFechasPrevistas(reservaActual);
        setVisibilityCreacion(View.GONE);
        setVisibilityModificacion(View.VISIBLE);
        actualizarFechaOperacion();
        gestionarVisibilidadFechaEntradaYSalida();

        anular.setVisibility((reservaActual.getFechaEntrada() == null || reservaActual.getFechaSalida() == null)
                && !Boolean.TRUE.equals(reservaActual.getAnulada()) ? View.VISIBLE : View.GONE);
        usuario.setText(String.format("%s %s", Sesion.getInstance().getUsuario().getNombre(),
                Sesion.getInstance().getUsuario().getApellidos()));

      gestionarColorEnSeleccionarEncargado();

        cargarEncargado(reservaActual);
        aceptar.setOnClickListener(v -> onBackPressed());
    }

    //Método para gestionar el color del TextView 'seleccionarEncargado'.
    private void gestionarColorEnSeleccionarEncargado() {

        if(colorTextoTv.contentEquals(COLOR_GARNET)){
            seleccionarEncargado.setTextColor(getResources().getColor(COLOR_RED_DARK, getTheme()));

        } else if(colorTextoTv.contentEquals(COLOR_BLUE_DARK)){
            seleccionarEncargado.setTextColor(getResources().getColor(COLOR_BLUE_BASIC, getTheme()));
        }
    }

    //Método para gestionar la visibilidad de las fecha de entrada y salida.
    private void gestionarVisibilidadFechaEntradaYSalida() {

        if (reservaActual.getFechaEntrada() != null) {
            espacio.setVisibility(View.GONE);
            registrarEntrada.setVisibility(View.GONE);
        }
        if (reservaActual.getFechaSalida() != null) {
            espacio.setVisibility(View.GONE);
            registrarSalida.setVisibility(View.GONE);
        }
    }

    //Método para actualizar la fecha de salida o de entrada final y mostrarla en TextView.
    //Según si hay una fecha introducida o no se mostrará con un color distinto.
    @SuppressLint("SetTextI18n")
    private void actualizarFechaOperacion() {

        if(reservaActual.getFechaEntrada() != null || reservaActual.getFechaSalida() != null){
            String salida = Optional.ofNullable(reservaActual.getFechaSalida()).orElseThrow(IllegalStateException::new);
            String entrada = Optional.ofNullable(reservaActual.getFechaEntrada()).orElse(PENDING);
            cambiarColorAfechas(salida, entrada);
            fechaOperacion.setVisibility(View.VISIBLE);

        } else {
            fechaOperacion.setVisibility(View.GONE);
        }
    }

    //Método para aplicar un cambio de color en las fechas de entrada y salida sí estas
    //han sido modificadas(verde) o no (naranja).
    private void cambiarColorAfechas(String salida, String entrada) {

        String fechasSeleccionadas = salida + " - " + entrada;
        SpannableString spannableStringFechas = new SpannableString(fechasSeleccionadas);
        ForegroundColorSpan orangeDark = new ForegroundColorSpan(Color.parseColor(COLOR_ORANGE_DARK));
        ForegroundColorSpan green = new ForegroundColorSpan(Color.parseColor(COLOR_GREEN_DARK));
        spannableStringFechas.setSpan(green, 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringFechas.setSpan(orangeDark,13, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spannableStringFechas2 = new SpannableString(fechasSeleccionadas);
        ForegroundColorSpan green2 = new ForegroundColorSpan(Color.parseColor(COLOR_GREEN_DARK));
        spannableStringFechas2.setSpan(green2, 0, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if(entrada.equals(PENDING)){
            fechaOperacion.setText(spannableStringFechas);
        } else {
            fechaOperacion.setText(spannableStringFechas2);
        }
    }

    //Método para seleccionar el encargado en la lista desplegable.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void eventoSeleccionarEncargado() {

        seleccionarEncargado.setOnClickListener(v -> {
            UsuariosDialogFragment usuariosDialogFragment = UsuariosDialogFragment.create(reservaActual.getEncargadoId());
            usuariosDialogFragment.show(getSupportFragmentManager(), null);
        });
    }

    //Método para seleccionar el proyecto en la lista desplegable.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void eventoSeleccionarProyecto() {

        seleccionarProyecto.setOnClickListener(v -> {
            ProyectosDialogFragment proyectoDialogFragment = ProyectosDialogFragment.create(reservaActual.getProyectoId());
            proyectoDialogFragment.show(getSupportFragmentManager(), null);
        });
    }

    //Método para registrar la fecha de salida del producto en una reserva.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void botonRegistrarSalida() {

        registrarSalida.setOnClickListener(v -> datePicker(__ -> {
            if (conectividad()) {
                reservasControllerApi.modificarReserva(reservaActual.getId(),
                        new ModificarReservaDto().fechaSalida(toISODate(fechaSeleccionada)))
                        .enqueue(new Callback<ReservaModificadaDto>() {
                            @Override
                            public void onResponse(@NonNull Call<ReservaModificadaDto> call,
                                                   @NonNull Response<ReservaModificadaDto> response) {

                                if (!response.isSuccessful()) return;
                                registrarSalida.setVisibility(View.GONE);
                                espacio.setVisibility(!response.isSuccessful() ? View.VISIBLE : View.GONE);
                                reservaActual.setFechaSalida(Objects.requireNonNull(response.body()).getFechaSalida());
                                actualizarFechaOperacion();
                            }

                            @Override
                            public void onFailure(@NonNull Call<ReservaModificadaDto> call, @NonNull Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error al conectar con el servidor, compruebe su conexión", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }));
    }

    //Método para registrar la fecha de entrada del producto en una reserva.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void botonRegistrarEntrada() {

        registrarEntrada.setOnClickListener(v -> datePicker(__ -> {
            if (conectividad()) {
                reservasControllerApi.modificarReserva(reservaActual.getId(), new ModificarReservaDto()
                        .fechaEntrada(toISODate(fechaSeleccionada)))
                        .enqueue(new Callback<ReservaModificadaDto>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onResponse(@NonNull Call<ReservaModificadaDto> call,
                                                   @NonNull Response<ReservaModificadaDto> response) {

                                if (!response.isSuccessful()) return;
                                registrarEntrada.setVisibility(View.GONE);
                                espacio.setVisibility(!response.isSuccessful() ? View.VISIBLE : View.GONE);
                                reservaActual.setFechaEntrada(Objects.requireNonNull(response.body()).getFechaEntrada());
                                actualizarFechaOperacion();
                            }

                            @Override
                            public void onFailure(@NonNull Call<ReservaModificadaDto> call, @NonNull Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error al conectar con el servidor, compruebe su conexión", Toast.LENGTH_SHORT).show();
                                registrarEntrada.setVisibility(View.VISIBLE);
                            }
                        });
            }
        }));
    }

    //Método para anular una reserva y poder cambiar sus fechas.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void botonAnularReserva() {

        anular.setOnClickListener(v -> dialogoEliminar((Void) -> reservasControllerApi.eliminarReserva(reservaActual.getId())
                .enqueue(new Callback<Void>() {

             @Override
             public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                 if (response.isSuccessful()) {
                     btn_volverAreservar.setVisibility(View.VISIBLE);
                     reservaActual.setAnulada(true);
                     Toast.makeText(ReservaActivity.this, "Reserva anulada", Toast.LENGTH_SHORT).show();

                 } else {
                     Toast.makeText(ReservaActivity.this, "La reserva no se ha podido anular",
                             Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                 t.printStackTrace();
                 Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                         Toast.LENGTH_SHORT).show();
             }
         })));
    }

    //Método para mapear los distintos elementos del dialogEliminar.
    private void initsDialogEliminar(){

        btnEliminar = dialogLayout.findViewById(R.id.btn_eliminar_dialog);
        btnCancelar = dialogLayout.findViewById(R.id.btn_cancelar_dialog);
        tv_operacion = dialogLayout.findViewById(R.id.tv_operacion_dialog);
        tv_fechaSalidaFinal = dialogLayout.findViewById(R.id.tv_fechaSalidaFinal);
        tv_fechaEntradaFinal = dialogLayout.findViewById(R.id.tv_fechaEntradaFinal);
        tv_guionEntreFechas = dialogLayout.findViewById(R.id.tv_guionFechas);
        tv_tituloDialogEliminar = dialogLayout.findViewById(R.id.tv_txt_dialog);
    }

    //Dialogo de feedback para mostar al usuario cuando vaya a eliminar una reserva.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"SetTextI18n", "InflateParams"})
    public void dialogoEliminar(Consumer<Void> callback){

        AlertDialog.Builder builder = new AlertDialog.Builder(ReservaActivity.this);
        dialogLayout = LayoutInflater.from(ReservaActivity.this).inflate(R.layout.dialog_eliminar, null);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();
        initsDialogEliminar();

        gestionColorFechasReservas(tv_fechaSalidaFinal, tv_fechaEntradaFinal, tv_guionEntreFechas);
        tv_operacion.setText("\n" + Objects.requireNonNull(productoSeleccionado.getNombreProducto()) +
                "\n" + reservaActual.getFechaSalidaPrevista() + " — " + reservaActual.getFechaEntradaPrevista());
        btnCancelar.setOnClickListener(v -> dialog.dismiss());
        btnEliminar.setOnClickListener(v -> {
            anular.setVisibility(View.GONE);
            btn_volverAreservar.setVisibility(View.VISIBLE);
            dialog.dismiss();
            callback.accept(null);
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        seleccionFuente.gestionFuenteEnDialogEliminarReserva(this, this);
        seleccionSize.gestionTextSizeEnDialogEliminarReserva(this, this);
    }

    //Método para volver a registrar una reserva después de anularla / eliminarla.
    @SuppressLint("SetTextI18n")
    public void onClicVolverAreservar(View view){

        Intent intent = new Intent(this,ReservaActivity.class);
        intent.putExtra(PRODUCTO_EXTRA, productoSeleccionado);
        intent.putExtra(MODO_EXTRA, REGISTRAR_RESERVA);
        startActivityForResult(intent, MULTIPLE_REQUEST_CODE);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    //Método para la gestión del color de las fechas según sí es una fecha Pendiente(Naranja) de concretar o
    // una fecha ya definitiva (Verde).
    @SuppressLint("SetTextI18n")
    private void gestionColorFechasReservas(TextView tv_fechaSalidaFinal, TextView tv_fechaEntradaFinal, TextView tv_guionEntreFechas) {

        tv_fechaSalidaFinal.setText(Optional.ofNullable(reservaActual.getFechaSalida()).orElse(""));
        tv_fechaEntradaFinal.setText(Optional.ofNullable(reservaActual.getFechaEntrada()).orElse(""));

        if (tv_fechaSalidaFinal.getText().toString().isEmpty() && tv_fechaEntradaFinal.getText().toString().isEmpty()) {
            gestionFechasSinCambiosEntradaYSalida(tv_fechaEntradaFinal, tv_fechaSalidaFinal);

        } else {
            gestionFechasPendientesEntradaYSalida(tv_fechaEntradaFinal, tv_fechaSalidaFinal);
        }
    }

    //Método para la gestión de las fechas de Entrada y Salida sin cambios, es decir, tal y como se
    //muestran en RecyclerView de reservas al ser registrados.
    @SuppressLint("SetTextI18n")
    private void gestionFechasSinCambiosEntradaYSalida(TextView tv_fechaEntradaFinal, TextView tv_fechaSalidaFinal) {

        tv_fechaSalidaFinal.setText(NO_CHANGES);
        tv_fechaEntradaFinal.setText(NO_CHANGES);
        tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_GRAY));
        tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_GRAY));
        tv_guionEntreFechas.setTextColor(Color.parseColor(COLOR_GRAY));
    }

    //Método para la gestión de las fechas de Entrada y Salida con cambios pendientes, es decir,
    //después de registrar un cambio de fecha de Salida, quedará 'Pendiente' registrar la fecha de Entrada.
    //Estos cambios se muestran en RecyclerView de reservas.
    @SuppressLint("SetTextI18n")
    private void gestionFechasPendientesEntradaYSalida(TextView tv_fechaEntradaFinal, TextView tv_fechaSalidaFinal) {

        if (tv_fechaSalidaFinal.getText().equals(reservaActual.getFechaSalida()) &&
                tv_fechaEntradaFinal.getText().toString().isEmpty()) {

            tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_GREEN));
            tv_fechaEntradaFinal.setText(PENDING);
            tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_ORANGE));

        } else if (tv_fechaSalidaFinal.getText().toString().isEmpty() &&
                tv_fechaEntradaFinal.getText().equals(reservaActual.getFechaEntrada())) {

            tv_fechaSalidaFinal.setText(PENDING);
            tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_ORANGE));
            tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_GREEN));

        } else {
            tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_GREEN));
            tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_GREEN));
            tv_guionEntreFechas.setTextColor(Color.parseColor(COLOR_GREEN));
        }
    }

    //Método que contiene vento de click para guardar los cambios de encargado en una reserva.
    private void botonGuardarDatosDeLaReserva() {

        guardar.setOnClickListener(view -> guardarDatosReserva());
    }

    //Método paraguardar el cambio de encargado en una reserva.
    private void guardarDatosReserva() {

        ModificarReservaDto modificarReservaDto = new ModificarReservaDto()
                .encargadoId(encargadoSeleccionado.getIdUsuario());

        ClienteApiFactoria.getInstance()
                .createService(ReservasControllerApi.class)
                .modificarReserva(reservaActual.getId(), modificarReservaDto)
                .enqueue(new Callback<ReservaModificadaDto>() {
                    @Override
                    public void onResponse(@NonNull Call<ReservaModificadaDto> call,
                                           @NonNull Response<ReservaModificadaDto> response) {

                        if (response.body() != null) {
                            Toast.makeText(ReservaActivity.this, "Encargado guardado con éxito",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ReservaModificadaDto> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Método para cargar las fechas previstas de una reserva.
    private void cargarFechasPrevistas(DetalleReservaDto reservaActual) {

        fechasPrevistas.setText(String.format("%s - %s", reservaActual.getFechaSalidaPrevista(),
                reservaActual.getFechaEntradaPrevista()));
    }

    //Método para cargar el encargado de una reserva.
    private void cargarEncargado(DetalleReservaDto reservaActual) {

        ClienteApiFactoria.getInstance()
                .createService(UsuariosControllerApi.class)
                .obtenerUsuarioPorId(reservaActual.getEncargadoId())
                .enqueue(new Callback<DetalleUsuarioDto>() {
                    @Override
                    public void onResponse(@NonNull Call<DetalleUsuarioDto> call, @NonNull Response<DetalleUsuarioDto> response) {
                        onClick(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<DetalleUsuarioDto> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Método para recuperar los valores almacenados de Proyectos y Usuarios en Api.
    private void recuperarValoresCache() {

        obtenerProyectoPorId();
        obtenerUsuarioPorId();
    }

    //Método para obtener un usuario en base su ID en la BD.
    private void obtenerUsuarioPorId() {

        ClienteApiFactoria.getInstance()
                .createService(UsuariosControllerApi.class)
                .obtenerUsuarioPorId(reservaActual.getEncargadoId())
                .enqueue(new Callback<DetalleUsuarioDto>() {
                    @Override
                    public void onResponse(@NonNull Call<DetalleUsuarioDto> call, @NonNull Response<DetalleUsuarioDto> response) {
                        onClick(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<DetalleUsuarioDto> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Método para obtener un proyecto en base su ID en la BD.
    private void obtenerProyectoPorId() {

        ClienteApiFactoria.getInstance()
                .createService(ProyectosControllerApi.class)
                .obtenerProyectPorId(reservaActual.getProyectoId())
                .enqueue(new Callback<DetalleProyectoDto>() {
                    @Override
                    public void onResponse(@NonNull Call<DetalleProyectoDto> call, @NonNull Response<DetalleProyectoDto> response) {
                        onClick(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<DetalleProyectoDto> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Método para saber sí la Reserva está guardada en SharedPreferences.
    private boolean isReservaCacheada() {
        return preferencias.contains(PREF_RESERVA);
    }

    //Introduce valores por defecto en Lista de Proyectos y Lista de Encargados.
    private void marcarValoresPorDefecto() {

        obtenerProyectos();
        obtenerEncargados();
    }

    //Método para obtener los proyectos de la BD.
    private void obtenerProyectos() {

        ClienteApiFactoria.getInstance()
                .createService(ProyectosControllerApi.class)
                .obtenerProyectos(1, 1)
                .enqueue(new Callback<List<DetalleProyectoDto>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<DetalleProyectoDto>> call,
                                           @NonNull Response<List<DetalleProyectoDto>> response) {
                        onClick(Objects.requireNonNull(response.body()).get(0));
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<DetalleProyectoDto>> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //Método para obtener los usuarios de la BD.
    private void obtenerEncargados() {

        ClienteApiFactoria.getInstance()
                .createService(UsuariosControllerApi.class)
                .obtenerUsuarios(1, 1, "encargado")
                .enqueue(new Callback<List<DetalleUsuarioDto>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<DetalleUsuarioDto>> call,
                                           @NonNull Response<List<DetalleUsuarioDto>> response) {
                        onClick(Objects.requireNonNull(response.body()).get(0));
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<DetalleUsuarioDto>> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //Método para transformar fecha de tipo Long en una fecha de tipo Date.
    private String toISODate(Long fechaSalidaSeleccionada) {

        return new Date(fechaSalidaSeleccionada).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    //Método para recuperar la información del Producto escaneado.
    private DetalleProductoDto recuperarProductoExtra() {

        return (DetalleProductoDto) getIntent().getSerializableExtra(PRODUCTO_EXTRA);
    }

    //Método para validar sí las fechas reservadas son válidas o ya están reservadas.
    private CalendarConstraints.DateValidator validadorFechasReservadas() {

        return new CalendarConstraints.DateValidator() {

            @Override
            public boolean isValid(long date) {
                LocalDate current = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
                if (LocalDate.now().isAfter(current)) return false;

                return fechasReservadas.stream().noneMatch(it -> {
                    LocalDate from = LocalDate.parse(it.getFechaSalidaPrevista());

                    LocalDate to = LocalDate.parse(it.getFechaEntradaPrevista());
                    return (current.isAfter(from) || current.isEqual(from)) && (current.isBefore(to) || current.isEqual(to));
                });
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
            }
        };
    }

    //Método para mostrar el nombre del Usuario registrado en la app en la ficha de Reserva.
    @SuppressLint("SetTextI18n")
    private void adaptarLayoutRegistrar() {

        usuario.setText(String.format("%s %s", Sesion.getInstance().getUsuario().getNombre(),
                Sesion.getInstance().getUsuario().getApellidos()));
    }

    //Método para mostrar visibilidad de elementos en la pantalla de Modificación.
    public void setVisibilityModificacion(int visibility) {

        registrarSalida.setVisibility(visibility);
        registrarEntrada.setVisibility(visibility);
        espacio.setVisibility(visibility);
        anular.setVisibility(visibility);
        guardar.setVisibility(visibility);
        fechasPrevistas.setVisibility(visibility);
        aceptar.setVisibility(visibility);
    }

    //Método para mostrar visibilidad de elementos en la pantalla de Creación.
    public void setVisibilityCreacion(int visibility) {

        seleccionarProyecto.setVisibility(visibility);
        seleccionarFechas.setVisibility(visibility);
        btn_registrar.setVisibility(visibility);
    }

    //Método para mapear los distintos elementos de la pantalla.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void inits() {

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        guardar = findViewById(R.id.btn_guardar);
        fechaOperacion = findViewById(R.id.fechaOperacionTv);
        anular = findViewById(R.id.anularBt);
        registrarEntrada = findViewById(R.id.registrarEntradaBt);
        registrarSalida = findViewById(R.id.registrarSalidaBt);
        seleccionarFechas = findViewById(R.id.tv_seleccionarFechas);
        tv_titulo_operacion = findViewById(R.id.nomClienteTv);
        nombreMaterial = findViewById(R.id.materialTv);
        codigoBarras = findViewById(R.id.tv_codigoBarras);
        seleccionarProyecto = findViewById(R.id.seleccionarProyectoTv);
        seleccionarEncargado = findViewById(R.id.seleccionarEncargadoTv);
        usuario = findViewById(R.id.registradorTv);
        btn_volver = findViewById(R.id.atrasBt);
        btn_registrar = findViewById(R.id.registrarBt);
        fechasPrevistas = findViewById(R.id.fechasPrevistasTv);
        aceptar = findViewById(R.id.aceptarBt);
        espacio = findViewById(R.id.espacioEntreBotones);
        btn_volverAreservar = findViewById(R.id.btn_volverAreservar);
        ondaSupReservaActivity = findViewById(R.id.waveTop_operacion);
        ondaInfReservaActivity = findViewById(R.id.waveBottom_operacion);
        viewInfReservaActivity = findViewById(R.id.bottom_view_operacion);
        viewSupReservaActivity = findViewById(R.id.top_view_operacion);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, this,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeReservaActivity(
                seleccionSize.getValuePreferenceSize(this), this);
    }

    //Método de confirmación de Reserva o realización de cambios en la misma a través de un Snackbar.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void onClickRegistrarOp(View view) {

        snackbarDialog();
        if (conectividad()) {
            if (seleccionarFechas.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe seleccionar las fechas", Toast.LENGTH_SHORT).show();
            } else {
                snackbar.setAction("CONFIRMAR", v -> registrarNuevaReserva()).show();
            }
        }
        seleccionFuente.gestionFuenteEnSnackbar(this, this);
        seleccionSize.gestionTextSizeEnSnackbar(this, this);
    }

    //Método para la creación de Snackbar para mostrar y preguntar si el usuario desea realizar
    //algún cambio o confirmar la raeserva.
    private void snackbarDialog() {

        snackbar = Snackbar.make(coordinatorLayout, "Desea hacer algún cambio?", Snackbar.LENGTH_LONG);

        tv_snackbar = (snackbar.getView()).findViewById(R.id.snackbar_text);
        snackbarTextView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_action);
        tv_snackbar.setTextColor(getColor(COLOR_WHITE));
        tv_snackbar.setTextSize(14);
        tv_snackbar.setMaxLines(3);

        snackbar.setActionTextColor(getColor(COLOR_WHITE));
        snackbar.getView().setBackgroundColor(getResources().getColor(BACKGROUND_DARK_SNACKBAR, getTheme()));
    }

    //Dialogo para cuando no haya conexión en la app.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void dialogSinConexion(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_barcode_no_encontrado, null);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();
        btn_aceptar = dialogLayout.findViewById(R.id.bt_aceptar);
        tv_dialog_barcode = dialogLayout.findViewById(R.id.tv_dialog_barcode);
        tv_dialog_barcode.setText(msg);
        btn_aceptar.setOnClickListener(v -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        seleccionFuente.gestionFuenteEnDialogSinConexion(this, this);
        seleccionSize.gestionTextSizeEnDialogSinConexion(this, this);
    }

    //Método para registrar una nueva reserva.
    public void registrarNuevaReserva() {

        CrearReservaDto nuevaReservaDto = new CrearReservaDto()
                .idEncargado(encargadoSeleccionado.getIdUsuario())
                .idProducto(productoSeleccionado.getIdProducto())
                .idUsuario(Sesion.getInstance().getUsuario().getIdUsuario())
                .idProyecto(proyectoSeleccionado.getIdProyecto())
                .fechaPrevistaEntrada(new Date(fechaPrevistaEntradaSeleccionada).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().toString())
                .fechaPrevistaSalida(new Date(fechaPrevistaSalidaSeleccionada).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate().toString());
       crearReserva(nuevaReservaDto);
    }

    //Método para crear una reserva.
    private void crearReserva( CrearReservaDto nuevaReservaDto) {

        reservasControllerApi.crearReserva(nuevaReservaDto).enqueue(new Callback<DetalleReservaDto>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onResponse(@NonNull Call<DetalleReservaDto> call, @NonNull Response<DetalleReservaDto> response) {
                if (response.isSuccessful()) {
                    reservaActual = response.body();
                    cachearReservaActual();
                    mostrarDialogContinuarEscaneando();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DetalleReservaDto> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error al conectar con el servidor, compruebe su conexión",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método para mostrar dialogo de confirmación de continuar escaneando productos para un mismo proyecto.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void mostrarDialogContinuarEscaneando() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_nuevo_escaneo, null);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();
        tv_nuevo_escaneo = dialogLayout.findViewById(R.id.tv_nuevo_escaneo);
        btn_aceptarDialogContinuarEscaneando = dialogLayout.findViewById(R.id.btn_scanear);
        btn_cancelarDialogContinuarEscaneando = dialogLayout.findViewById(R.id.btn_cancelar_escaneo);
        botonAceptarDialogoContinuarEscaneando(dialog);
        botonCancelarDialogContinuarEscaneando(dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        seleccionFuente.gestionFuenteEnDialogContinuarEscaneando(this, this);
        seleccionSize.gestionTextSizeEnDialogContinuarEscaneando(this, this);
    }

    //Evento onClick para botón aceptar del dialogo 'mostrarDialogContinuarEscaneando'.
    private void botonAceptarDialogoContinuarEscaneando(AlertDialog dialogAlert) {

        btn_aceptarDialogContinuarEscaneando.setOnClickListener(v -> {
            dialogAlert.dismiss();
            setResult(SCAN_AGAIN);
            finish();
        });
    }

    //Evento onClick para botón cancelar del dialogo 'mostrarDialogContinuarEscaneando'.
    private void botonCancelarDialogContinuarEscaneando(AlertDialog dialogAlert) {

        btn_cancelarDialogContinuarEscaneando.setOnClickListener(v -> {
            borrarCache();
            Intent extras = new Intent();
            extras.putExtra(RESERVA_RESULT_EXTRA, reservaActual);
            setResult(CANCEL, extras);
            finish();
            dialogAlert.dismiss();
        });
    }

    //Método para eliminar los datos almacenados de una reserva cuando
    // ya no se va a seguir escaneando productos para un mismo proyecto.
    public void borrarCache() {

        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove(PREF_RESERVA);
        editor.apply();
    }

    //Método para guardar en Shared Preferences los datos de la ultima
    // reserva para seguir escaneando productos para un mismo proyecto.
    public void cachearReservaActual() {

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(PREF_RESERVA, new Gson().toJson(reservaActual));
        editor.apply();
    }

    //Método para crear o recuperar datos en las Shared Preferences de alguna Reserva.ç
    private DetalleReservaDto recuperarCacheadaOCrear() {

        DetalleReservaDto opGuardada = recuperarOperacionGuardada();
        if (opGuardada == null) return new DetalleReservaDto();

        return opGuardada;
    }

    //Método para recuperar operación guardada en Shared Preferences.
    private DetalleReservaDto recuperarOperacionGuardada() {

        if (!preferencias.contains(PREF_RESERVA)) return null;
        return new Gson().fromJson(preferencias.getString(PREF_RESERVA, null), DetalleReservaDto.class);
    }

    //Método recuperar reserva para modificar.
    private DetalleReservaDto recuperarReservaExtra() {

        return (DetalleReservaDto) getIntent().getSerializableExtra(RESERVA_EXTRA);
    }

    //Método para mostrar proyecto seleccionado.
    private void mostrarSeleccionado(DetalleProyectoDto proyecto) {

        this.proyectoSeleccionado = proyecto;
        seleccionarProyecto.setText(proyecto.getCliente());

        if(colorTextoTv.contentEquals(COLOR_GARNET)){
            seleccionarProyecto.setTextColor(getResources().getColor(COLOR_RED_DARK, getTheme()));

        } else if(colorTextoTv.contentEquals(COLOR_BLUE_DARK)){
            seleccionarProyecto.setTextColor(getResources().getColor(COLOR_BLUE_BASIC, getTheme()));
        }
    }

    //Método para mostrar encargado seleccionado.
    private void mostrarSeleccionado(DetalleUsuarioDto usuario) {

        this.encargadoSeleccionado = usuario;
        seleccionarEncargado.setText(String.format("%s %s", usuario.getNombre(), usuario.getApellidos()));

        if(colorTextoTv.contentEquals(COLOR_GARNET)){
            seleccionarProyecto.setTextColor(getResources().getColor(COLOR_RED_DARK, getTheme()));

        } else if(colorTextoTv.contentEquals(COLOR_BLUE_DARK)){
            seleccionarProyecto.setTextColor(getResources().getColor(COLOR_BLUE_BASIC, getTheme()));
        }
    }

    //Método onClick para seleccionar proyecto en lista.
    @Override
    public void onClick(DetalleProyectoDto proyecto) {
        mostrarSeleccionado(proyecto);
    }

    //Método onClick para seleccionar encargado en lista.
    @Override
    public void onClick(DetalleUsuarioDto usuario) {
        mostrarSeleccionado(usuario);
    }

    //Método para comprobar sí hay o no conexión a la red.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public boolean conectividad() {

        Log.d(LOG_TAG, "Comprobando conexión");
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean connected = (networkInfo != null && networkInfo.isConnected());

        if (!connected) {
            dialogSinConexion("No hay conexión, Keep Calm!! ");
        }

        return connected;
    }

    //Método para pasar por Bundle la reserva modificada a la lista y actualizarla al regresar a ella al volver atrás.
    private Intent reservaActualBundle() {

        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESERVA, reservaActual);
        overridePendingTransition(0, 0);
        return intent;
    }

    //Método sobrescrito para regresar a la pantalla anterior (la lista de reservas, en este caso).
    //Al regresar se pasa la reserva modificada (o no) a la lista que a su vez se refresca mostrando los nuevos cambios.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBackPressed() {

        setResult(REPLACE, reservaActualBundle());
        Intent extras = new Intent();
        extras.putExtra(RESERVA_RESULT_EXTRA, reservaActual);
        setResult(CANCEL, extras);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}