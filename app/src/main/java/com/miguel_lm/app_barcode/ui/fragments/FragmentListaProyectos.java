package com.miguel_lm.app_barcode.ui.fragments;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.FILE_THEME_PREFERENCES;
import static java.util.Collections.emptyList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
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
import com.almacen.api.client.model.DetalleProyectoDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.activities.ScannerActivity;
import com.miguel_lm.app_barcode.ui.adapters.AdapterProyecto;
import com.miguel_lm.app_barcode.ui.adapters.EndlessScrollListener;
import org.openapitools.client.api.ProyectosControllerApi;
import java.io.File;
import java.util.List;
import java.util.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentListaProyectos extends Fragment implements SearchView.OnQueryTextListener,
        SwipeRefreshLayout.OnRefreshListener, EndlessScrollListener.ScrollerClient, AdapterProyecto.OnProyectoItemClickListener {

    private static final String LOG_TAG = "log_json";
    @SuppressLint("SdCardPath")
    File preferenciasTema = new File(FILE_THEME_PREFERENCES);

    public interface OnBackRequestListener {
        void onBackRequest();
    }

    private AdapterProyecto adapterProyecto;
    private RecyclerView recyclerViewUsers;
    private SwipeRefreshLayout swipeRefreshLayout;
    public SearchView buscadorListaPro;
    private LottieAnimationView lottie_no_hay_proyectos;
    public TextView tv_proyectos_no_encontrados, nomClienteTv;
    private EndlessScrollListener endlessScrollListener;
    private ProyectosControllerApi proyectosControllerApi;
    OnBackRequestListener onBackRequestListener;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    private int pagina = 1;
    private AdapterProyecto.OnProyectoItemClickListener listener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        super.onCreate(savedInstanceState);
        proyectosControllerApi = ClienteApiFactoria.getInstance().createService(ProyectosControllerApi.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista, container, false);
        inits(root);

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        super.onPause();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, FragmentListaProyectos.this,
                null,  null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), this, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onResume() {
        super.onResume();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, FragmentListaProyectos.this,
                null,  null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), this, null);
        conectividad();
        backPressedCallback.setEnabled(false);
        onRefresh();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onStart() {
        super.onStart();
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, FragmentListaProyectos.this,
                null,  null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), this, null);
        backPressedCallback.setEnabled(false);
        onRefresh();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onStop() {
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, FragmentListaProyectos.this,
                null,  null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionarColorApp.gestionColorBuscador(requireContext(), this, null);
        backPressedCallback.setEnabled(false);
        onRefresh();

        super.onStop();
    }

    //Método que mapea e inicializa los diferentees componentes de la vista.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void inits(View root) {
        ScannerActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        buscadorListaPro = root.findViewById(R.id.buscadorListaPro);
        buscadorListaPro.setOnQueryTextListener(this);

        recyclerViewUsers = root.findViewById(R.id.recyclerPro);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        endlessScrollListener = new EndlessScrollListener(this);
        recyclerViewUsers.addOnScrollListener(endlessScrollListener);
        lottie_no_hay_proyectos = root.findViewById(R.id.lottie_no_hay_proyectos);
        tv_proyectos_no_encontrados = root.findViewById(R.id.tv_proyectos_no_encontrados);
        nomClienteTv = root.findViewById(R.id.nomClienteTv);

        adapterProyecto = new AdapterProyecto(this);
        swipeRefreshLayout = root.findViewById(R.id.swipe);
        recyclerViewUsers.setAdapter(adapterProyecto);

        swipeRefreshLayout.setOnRefreshListener(this);

        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, FragmentListaProyectos.this,
                null,  null);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentListaProyectos(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        obtenerDatosProyecto(1);
        gestionarColorApp.colorDefault(requireContext());
        gestionarColorApp.gestionColorTema(getContext(), null, this, null);
        gestionarColorApp.gestionColorBuscador(requireContext(), this, null);
    }

    //Callback para volver atrás entre fragments.
    private final OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if(getChildFragmentManager().getBackStackEntryCount() > 0) {
                getChildFragmentManager().popBackStack();
                recyclerViewUsers.setVisibility(View.VISIBLE);
                buscadorListaPro.setVisibility(View.VISIBLE);
            }
            else {
                onBackRequestListener.onBackRequest();
            }
        }
    };

    //Método para mostrar la lista de reservas de un proyecto pulsando su item en la lista.
    @Override
    public void onItemClick(DetalleProyectoDto proyecto) {
        swipeRefreshLayout.setEnabled(false);

        listener.onItemClick(proyecto);
    }

    //Método para el envío de texto de consulta.
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //Método que al detectar los cambios del texto introducido en el buscador va mostrando los datos encontrados.
    @Override
    public boolean onQueryTextChange(String newText) {
        adapterProyecto.filter(newText);
        refrescarVisibilidadListaConBuscador();
        return true;
    }

    //Método para refrescar el Swipe Refresh.
    @Override
    public void onRefresh() {
        pagina = 1;
        adapterProyecto = new AdapterProyecto(this);
        recyclerViewUsers.setAdapter(adapterProyecto);
        obtenerDatosProyecto(pagina);
    }

    //Método para comprobar sí hay o no conexión a la red.
    public void conectividad() {
        Log.d(LOG_TAG, "Comprobando conexión");
        ConnectivityManager connMgr = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean connected = (networkInfo != null && networkInfo.isConnected());

        backPressedCallback.setEnabled(true);

        if (connected) {
           refrescarVisibilidadLista();
        }
        else {
            Toast.makeText(getContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
            lottie_no_hay_proyectos.setVisibility(View.VISIBLE);
            tv_proyectos_no_encontrados.setVisibility(View.VISIBLE);
            recyclerViewUsers.setVisibility(View.GONE);
            buscadorListaPro.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    //Método para obtener los datos de los proyectos mediante paginación.
    public void obtenerDatosProyecto(int pagina) {
        proyectosControllerApi.obtenerProyectos(pagina, 10).enqueue(new Callback<List<DetalleProyectoDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetalleProyectoDto>> call,
                                   @NonNull Response<List<DetalleProyectoDto>> response) {

                adapterProyecto.deshabilitarBarraProgreso();
                List<DetalleProyectoDto> proyectos = Optional.ofNullable(response.body()).orElse(emptyList());
                adapterProyecto.addAll(proyectos);
                swipeRefreshLayout.setRefreshing(false);
                refrescarVisibilidadLista();

                if(proyectos.size() == 10)
                    endlessScrollListener.loadMore();
            }

            @Override
            public void onFailure(@NonNull Call<List<DetalleProyectoDto>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AdapterProyecto.OnProyectoItemClickListener)
            this.listener = (AdapterProyecto.OnProyectoItemClickListener) context;
        else
            this.listener = (AdapterProyecto.OnProyectoItemClickListener) getParentFragment();
    }

    //Método para cargar una nueva página en la lista de proyectos (Paginación).
    @Override
    public void loadData() {
        adapterProyecto.habilitarBarraProgreso();
        obtenerDatosProyecto(++pagina);
    }

    //Método para refrescar la visibilidad de la lista cuando no hay conexión a internet.
    private void refrescarVisibilidadLista() {
        lottie_no_hay_proyectos.setVisibility(adapterProyecto.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        lottie_no_hay_proyectos.setVisibility(adapterProyecto.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        tv_proyectos_no_encontrados.setVisibility(adapterProyecto.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        recyclerViewUsers.setVisibility(adapterProyecto.getItemCount() > 0 ? View.VISIBLE : View.GONE);
        buscadorListaPro.setVisibility((adapterProyecto.getItemCount() > 0 ? View.VISIBLE : View.GONE));
    }

    //Método para refrescar la visibilidad de la lista sin quitatr visibilidad al buscador
    // cuando no se encuentre un proyecto en la lista.
    private void refrescarVisibilidadListaConBuscador() {
        lottie_no_hay_proyectos.setVisibility(adapterProyecto.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        lottie_no_hay_proyectos.setVisibility(adapterProyecto.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        tv_proyectos_no_encontrados.setVisibility(adapterProyecto.getItemCount() > 0 ? View.GONE : View.VISIBLE);
        recyclerViewUsers.setVisibility(adapterProyecto.getItemCount() > 0 ? View.VISIBLE : View.GONE);
    }
}