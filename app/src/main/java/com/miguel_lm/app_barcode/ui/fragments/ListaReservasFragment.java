package com.miguel_lm.app_barcode.ui.fragments;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GRAY;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_ORANGE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GREEN;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.EXTRA_PROYECTO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.EXTRA_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODIFICAR_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PENDING;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PRODUCTO_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.REPLACE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.RESERVA_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NO_CHANGES;
import static java.util.Collections.emptyList;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.almacen.api.client.model.DetalleReservaDto;
import com.almacen.api.client.model.ReservaProyectoDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.activities.ScannerActivity;
import com.miguel_lm.app_barcode.ui.adapters.AdapterReservasProyecto;
import com.miguel_lm.app_barcode.ui.adapters.EndlessScrollListener;
import com.miguel_lm.app_barcode.ui.interfaces.OnReservaProyectoClickListener;
import com.miguel_lm.app_barcode.ui.activities.ReservaActivity;
import org.openapitools.client.api.ProyectosControllerApi;
import org.openapitools.client.api.ReservasControllerApi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaReservasFragment extends Fragment implements OnReservaProyectoClickListener,
        SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener, EndlessScrollListener.ScrollerClient {

    private static final int RESERVA_MODIFICAR_REQUEST_CODE = 92;
    private static final String LOG_TAG = "log_json";

    private final String KEY_RECYCLER_ESTADO_RESERVAS = "estado_recycler_reservas";

    private AdapterReservasProyecto adapterReservasProyecto;
    SwipeRefreshLayout swipeRefreshLayout;
    public SearchView buscadorLista;
    private Long idProyecto;

    public Button btnEliminar;
    public Button btnCancelar;
    public TextView tv_operacion;
    public TextView tv_fechaSalidaFinal;
    public TextView tv_fechaEntradaFinal;
    public TextView tv_guionEntreFechas;
    public TextView tv_tituloDialogEliminar;

    private EndlessScrollListener endlessScrollListener;
    LottieAnimationView noHayOperacionesParaMostrar;
    public TextView datosNoEncontrados;

    private int pagina = 1;
    private RecyclerView reservasProyecto;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    Parcelable listState;


    //Instancias del cliente API para proyectos y reservas.
    private final ProyectosControllerApi proyectosControllerApi = ClienteApiFactoria.getInstance()
            .createService(ProyectosControllerApi.class);

    private final ReservasControllerApi reservasControllerApi = ClienteApiFactoria.getInstance()
            .createService(ReservasControllerApi.class);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        Bundle params = getArguments();
        View root = inflater.inflate(R.layout.fragment_lista, container, false);
        inits(root, Objects.requireNonNull(params));
        ScannerActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        swipeRefreshLayout = root.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (savedInstanceState != null) {
            Parcelable listState = savedInstanceState.getParcelable(KEY_RECYCLER_ESTADO_RESERVAS);
            Objects.requireNonNull(reservasProyecto.getLayoutManager()).onRestoreInstanceState(listState);
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(requireContext());
        super.onPause();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                ListaReservasFragment.this, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaReservasProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), null, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onResume() {
        gestionarColorApp.colorDefault(requireContext());
        super.onResume();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                ListaReservasFragment.this, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaReservasProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), null, this);
        conectividad();
        onRefresh();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onStart() {
        gestionarColorApp.colorDefault(requireContext());
        super.onStart();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                ListaReservasFragment.this, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaReservasProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), null, this);
        onRefresh();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void onStop() {
        gestionarColorApp.colorDefault(requireContext());
        super.onStop();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                ListaReservasFragment.this, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaReservasProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), null, this);
        onRefresh();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        listState = Objects.requireNonNull(reservasProyecto.getLayoutManager()).onSaveInstanceState();
        outState.putParcelable(KEY_RECYCLER_ESTADO_RESERVAS, listState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(KEY_RECYCLER_ESTADO_RESERVAS);
            Objects.requireNonNull(reservasProyecto.getLayoutManager()).onRestoreInstanceState(listState);
        }
    }

    //Método que mapea e inicializa los diferentees componentes de la vista.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void inits(View root, Bundle params){
        idProyecto = params.getLong(EXTRA_PROYECTO);
        buscadorLista = root.findViewById(R.id.buscadorListaPro);

        reservasProyecto = root.findViewById(R.id.recyclerPro);
        reservasProyecto.setLayoutManager(new LinearLayoutManager(getContext()));
        endlessScrollListener = new EndlessScrollListener(this);
        reservasProyecto.addOnScrollListener(endlessScrollListener);

        initListener();

        noHayOperacionesParaMostrar = root.findViewById(R.id.lottie_no_hay_proyectos);
        datosNoEncontrados = root.findViewById(R.id.tv_proyectos_no_encontrados);

        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                ListaReservasFragment.this, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaReservasProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        obtenerDatosOperacionIniciales();
        gestionarColorApp.gestionColorTema(getContext(), null, null, this);
        gestionarColorApp.gestionColorBuscador(requireContext(), null, this);
    }

    //Método para comprobar sí hay o no conexión a la red.
    public void conectividad() {

        Log.d(LOG_TAG, "Comprobando conexión");
        ConnectivityManager connMgr = (ConnectivityManager) requireContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean connected = (networkInfo != null && networkInfo.isConnected());

        if (connected) {
            refrescarVisibilidadLista();

        } else {
            Toast.makeText(getContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
            noHayOperacionesParaMostrar.setVisibility(View.VISIBLE);
            datosNoEncontrados.setVisibility(View.VISIBLE);
            reservasProyecto.setVisibility(View.GONE);
            buscadorLista.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    //Método para refrescar la visibilidad de la lista cuando no hay conexión a internet.
    private void refrescarVisibilidadLista() {

        noHayOperacionesParaMostrar.setVisibility(adapterReservasProyecto.isEmpty() ? View.VISIBLE : View.GONE);
        datosNoEncontrados.setVisibility(adapterReservasProyecto.isEmpty() ? View.VISIBLE : View.GONE);
        reservasProyecto.setVisibility(adapterReservasProyecto.isEmpty() ? View.GONE : View.VISIBLE);
        buscadorLista.setVisibility(adapterReservasProyecto.isEmpty() ? View.GONE : View.VISIBLE);
    }

    //Método para refrescar la visibilidad de la lista sin quitatr visibilidad al buscador
    //cuando no se encuentre una reserva en la lista.
    private void refrescarVisibilidadListaConBuscador() {

        noHayOperacionesParaMostrar.setVisibility(adapterReservasProyecto.isEmpty() ? View.VISIBLE : View.GONE);
        datosNoEncontrados.setVisibility(adapterReservasProyecto.isEmpty() ? View.VISIBLE : View.GONE);
        reservasProyecto.setVisibility(adapterReservasProyecto.isEmpty() ? View.GONE : View.VISIBLE);
    }

    //Método para eliminar una reserva desde el botón de eliminar.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    @Override
    public void onEliminarClick(ReservaProyectoDto reserva) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View dialogLayout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_eliminar, null);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();
        initDialogEliminar(dialogLayout);
        mostrarInfoReservaAeliminar(reserva);
        gestionColorFechasReservas(reserva, tv_fechaSalidaFinal, tv_fechaEntradaFinal, tv_guionEntreFechas);
        btnCancelar.setOnClickListener(v -> dialog.dismiss());
        onClickListenerDelBotonEliminarEnDialog(reserva, dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        seleccionFuente.gestionFuenteEnDialogEliminar(requireContext(), this);
        seleccionSize.gestionTextSizeEnDialogEliminar(requireContext(), this);
    }

    //Método para mostrar los datos de la reserva a eliminar.
    @SuppressLint("SetTextI18n")
    private void mostrarInfoReservaAeliminar(ReservaProyectoDto reserva) {

        tv_operacion.setText("\n" + Objects.requireNonNull(reserva.getProducto()).getNombreProducto()
                + "\n" + reserva.getCliente() +
                "\n" + Objects.requireNonNull(reserva.getReserva()).getFechaSalidaPrevista()
                + " — " + reserva.getReserva().getFechaEntradaPrevista());
    }

    //Método para mapear los componentes del dialogo eliminar.
    private void initDialogEliminar(View dialogLayout) {

        btnEliminar = dialogLayout.findViewById(R.id.btn_eliminar_dialog);
        btnCancelar = dialogLayout.findViewById(R.id.btn_cancelar_dialog);
        tv_operacion = dialogLayout.findViewById(R.id.tv_operacion_dialog);
        tv_fechaSalidaFinal = dialogLayout.findViewById(R.id.tv_fechaSalidaFinal);
        tv_fechaEntradaFinal = dialogLayout.findViewById(R.id.tv_fechaEntradaFinal);
        tv_guionEntreFechas = dialogLayout.findViewById(R.id.tv_guionFechas);
        tv_tituloDialogEliminar = dialogLayout.findViewById(R.id.tv_txt_dialog);
    }

    //Método para la gestión del evento onClick del botón eliminar del Dialog Eliminar.
    private void onClickListenerDelBotonEliminarEnDialog(ReservaProyectoDto reserva, AlertDialog dialog) {

        btnEliminar.setOnClickListener(v -> reservasControllerApi.eliminarReserva(Objects.requireNonNull(reserva.getReserva())
                .getId()).enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if (response.isSuccessful()) {
                            adapterReservasProyecto.remove(reserva);
                            Toast.makeText(getContext(), "Reserva eliminada", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getContext(), "Reserva no eliminada. No se pudo encontrar",
                                    Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        dialog.dismiss();
                    }
                })
        );
    }

    //Método para la gestión del color de las fechas según sí es una fecha Pendiente(Naranja) de concretar o
    // una fecha ya definitiva (Verde).
    @SuppressLint("SetTextI18n")
    private void gestionColorFechasReservas(ReservaProyectoDto reserva, TextView tv_fechaSalidaFinal,
                                            TextView tv_fechaEntradaFinal, TextView tv_guionEntreFechas) {

        tv_fechaSalidaFinal.setText(Objects.requireNonNull(reserva.getReserva()).getFechaSalida());
        tv_fechaEntradaFinal.setText(reserva.getReserva().getFechaEntrada());

        if (tv_fechaSalidaFinal.getText().toString().isEmpty() && tv_fechaEntradaFinal.getText().toString().isEmpty()) {
          gestionSinCambiosEnFechas();

        } else {
           gestionCambiosPendientesEnFechas(reserva);
        }
    }

    //Método para mostrar tanto en la fecha de entrada como en la de salida el texto 'SIN CAMBIOS' en color gris,
    //cuando no se hallan realizado ninguna modificación en las fechas registradas.
    @SuppressLint("SetTextI18n")
    private void gestionSinCambiosEnFechas() {

        tv_fechaSalidaFinal.setText(NO_CHANGES);
        tv_fechaEntradaFinal.setText(NO_CHANGES);
        tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_GRAY));
        tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_GRAY));
        tv_guionEntreFechas.setTextColor(Color.parseColor(COLOR_GRAY));
    }

    //Método para la gestión de los cambios pendientes de realizar en las fechas de entrada o salida.
    @SuppressLint("SetTextI18n")
    private void gestionCambiosPendientesEnFechas(ReservaProyectoDto reserva) {

        if (tv_fechaSalidaFinal.getText().equals(Objects.requireNonNull(reserva.getReserva()).getFechaSalida()) &&
                tv_fechaEntradaFinal.getText().toString().isEmpty()) {

            tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_GREEN));
            tv_fechaEntradaFinal.setText(PENDING);
            tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_ORANGE));

        } else if (tv_fechaSalidaFinal.getText().toString().isEmpty() &&
                tv_fechaEntradaFinal.getText().equals(reserva.getReserva().getFechaEntrada())) {

            tv_fechaSalidaFinal.setText(PENDING);
            tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_ORANGE));
            tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_GREEN));

        } else {
            tv_fechaSalidaFinal.setTextColor(Color.parseColor(COLOR_GREEN));
            tv_fechaEntradaFinal.setTextColor(Color.parseColor(COLOR_GREEN));
            tv_guionEntreFechas.setTextColor(Color.parseColor(COLOR_GREEN));
        }
    }

    //Método para modificar resrva al hacer click en botón modificar.
    @Override
    public void onModificarClick(ReservaProyectoDto reserva) {

        Intent intent = new Intent(getActivity(), ReservaActivity.class);
        intent.putExtra(RESERVA_EXTRA, reserva.getReserva());
        intent.putExtra(PRODUCTO_EXTRA, reserva.getProducto());
        intent.putExtra(MODO_EXTRA, MODIFICAR_RESERVA);

        startActivityForResult(intent, RESERVA_MODIFICAR_REQUEST_CODE);
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //Método para obtener las operaciones de la BD para gestionar en el buscador. .
    public void obtenerDatosOperacion(int pagina) {

        proyectosControllerApi.obtenerReservasPorIdProyecto(idProyecto, pagina, 10)
                .enqueue(new Callback<List<ReservaProyectoDto>>() {

            @Override
            public void onResponse(@NonNull Call<List<ReservaProyectoDto>> call,
                                   @NonNull Response<List<ReservaProyectoDto>> response) {

                adapterReservasProyecto.deshabilitarBarraProgreso();
                List<ReservaProyectoDto> operacionesProyecto = response.body();

                swipeRefreshLayout.setRefreshing(false);
                adapterReservasProyecto.addAll(operacionesProyecto);

                if (Objects.requireNonNull(operacionesProyecto).size() == 10)
                    endlessScrollListener.loadMore();
            }

            @Override
            public void onFailure(@NonNull Call<List<ReservaProyectoDto>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //Método para extraer las operaciones de la BD.
    public void obtenerDatosOperacionIniciales() {

        adapterReservasProyecto = new AdapterReservasProyecto(this);
        reservasProyecto.setAdapter(adapterReservasProyecto);
        obtenerReservasPorId();
    }

    //Método para obtener las reservas por id y ordenarlas de forma inversa,
    //con lo que las más actuales se mostrarán primero.
    private void obtenerReservasPorId() {

        proyectosControllerApi.obtenerReservasPorIdProyecto(idProyecto, 1, 10)
                .enqueue(new Callback<List<ReservaProyectoDto>>() {

                    @Override
                    public void onResponse(@NonNull Call<List<ReservaProyectoDto>> call,
                                           @NonNull Response<List<ReservaProyectoDto>> response) {

                        adapterReservasProyecto.deshabilitarBarraProgreso();
                        List<ReservaProyectoDto> operacionesProyecto = Optional.ofNullable(response.body()).orElse(emptyList());
                        adapterReservasProyecto.addAll(operacionesProyecto);
                        swipeRefreshLayout.setRefreshing(false);
                        refrescarVisibilidadLista();
                        adapterReservasProyecto.replace(operacionesProyecto);
                        ordenarFechas();
                        if (Objects.requireNonNull(operacionesProyecto).size() == 10)
                            endlessScrollListener.loadMore();
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<ReservaProyectoDto>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    //Método para ordenar las fechas de las reservas.
    private void ordenarFechas() {

        adapterReservasProyecto.reverseOrder((op1, op2) -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date fechaSalidaOp1 = format.parse(Objects.requireNonNull(Objects.requireNonNull(op1.getReserva())
                        .getFechaEntradaPrevista()));
                Date fechaSalidaOp2 = format.parse(Objects.requireNonNull(Objects.requireNonNull(op2.getReserva())
                        .getFechaSalidaPrevista()));
                return Objects.requireNonNull(fechaSalidaOp1).compareTo(fechaSalidaOp2);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    //Método para iniciar el buscador.
    public void initListener() {

        buscadorLista.setOnQueryTextListener(this);
    }

    //Método para el envío de texto de consulta.
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    //Método que al detectar los cambios del texto introducido en el buscador va mostrando los datos encontrados.
    @Override
    public boolean onQueryTextChange(String newText) {

        adapterReservasProyecto.filter(newText);
        refrescarVisibilidadListaConBuscador();
        return false;
    }

    //Método para cargar una nueva página en la lista de reservas (Paginación).
    @Override
    public void loadData() {

        adapterReservasProyecto.habilitarBarraProgreso();
        obtenerDatosOperacion(++pagina);
    }

    //Método para refrescar el Swipe Refresh.
    @Override
    public void onRefresh() {

        pagina = 1;
        obtenerDatosOperacionIniciales();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == REPLACE && requestCode == RESERVA_MODIFICAR_REQUEST_CODE) {
            DetalleReservaDto dto = (DetalleReservaDto) Objects.requireNonNull(data).getSerializableExtra(EXTRA_RESERVA);
            if(Boolean.TRUE.equals(dto.getAnulada()))
                adapterReservasProyecto.remove(dto);
            else
                adapterReservasProyecto.replace(dto);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}