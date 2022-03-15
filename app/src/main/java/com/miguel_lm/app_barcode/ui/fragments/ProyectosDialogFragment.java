package com.miguel_lm.app_barcode.ui.fragments;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GRAY_LIGHT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_WHITE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.almacen.api.client.model.DetalleProyectoDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.ui.adapters.AdapterSelector;
import com.miguel_lm.app_barcode.ui.adapters.EndlessScrollListener;
import org.openapitools.client.api.ProyectosControllerApi;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProyectosDialogFragment extends DialogFragment implements EndlessScrollListener.ScrollerClient {

    private static final String EXTRA_ID = "ïd";
    private AdapterSelector<DetalleProyectoDto> listaAdapter;
    public RecyclerView listaProyectos;
    private ProgressBar carga;
    private OnItemClick listener;
    private int pagina = 1;
    ConstraintLayout contenedor;

    //Método para crear una instancia del cliente API para proyectos.
    private final ProyectosControllerApi proyectosControllerApi = ClienteApiFactoria.getInstance()
            .createService(ProyectosControllerApi.class);
    Long idPorDefecto;

    public interface OnItemClick {
        void onClick(DetalleProyectoDto usuario);
    }

    //Método para cargar una nueva página en la lista de proyectos (Paginación).
    @Override
    public void loadData() {
        listaAdapter.habilitarBarraProgreso();
        obtenerProyectos(++pagina);
    }

    //Método para obtener los datos de los proyectos mediante paginación.
    private void obtenerProyectos(int pag) {
        proyectosControllerApi.obtenerProyectos(pag, 10).enqueue(new Callback<List<DetalleProyectoDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetalleProyectoDto>> call, @NonNull Response<List<DetalleProyectoDto>> response) {
                listaAdapter.addAll(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<DetalleProyectoDto>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //Método para crear dialogo con el id del proyecto.
    public static ProyectosDialogFragment create(Long idProyecto) {
        Bundle bundle = new Bundle();
        if(idProyecto != null)
            bundle.putLong(EXTRA_ID, idProyecto);
        ProyectosDialogFragment proyectoDialogFragment = new ProyectosDialogFragment();
        proyectoDialogFragment.setArguments(bundle);
        return proyectoDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_selector_proyectos, container);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(
                getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels/3
        );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idPorDefecto = requireArguments().getLong(EXTRA_ID);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.listaAdapter = new AdapterSelector<>(item -> {
            listener.onClick(item);
            dismiss();
        }, it -> String.format("%s", it.getCliente()));

        this.listaProyectos = view.findViewById(R.id.proyectosRv);
        this.listaProyectos.setAdapter(listaAdapter);
        this.listaProyectos.setLayoutManager(new LinearLayoutManager(getContext()));
        this.listaProyectos.addOnScrollListener(new EndlessScrollListener(this));
        comprobarModoOscuro(view);
        this.carga = view.findViewById(R.id.cargaPb);
        obtenerLosProyectos();
    }

    //Método para obtener los proyectos de la BD.
    private void obtenerLosProyectos() {

        proyectosControllerApi.obtenerProyectos(pagina, 10).enqueue(new Callback<List<DetalleProyectoDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetalleProyectoDto>> call, @NonNull Response<List<DetalleProyectoDto>> response) {
                List<DetalleProyectoDto> items = response.body();
                listaProyectos.setVisibility(View.VISIBLE);
                carga.setVisibility(View.GONE);
                listaAdapter.addAll(items);
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
        this.listener = (OnItemClick) context;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void comprobarModoOscuro(View view){

        this.contenedor = view.findViewById(R.id.contenedorDialogProyectos);

        if (recibiendoDatosTema()) {
            contenedor.setBackground(getResources().getDrawable(COLOR_GRAY_LIGHT, requireActivity().getTheme()));

        } else {
            contenedor.setBackgroundColor(getResources().getColor(COLOR_WHITE, requireActivity().getTheme()));
        }
    }

    public boolean recibiendoDatosTema() {

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MODO_DARK, false);
    }
}
