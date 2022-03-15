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
import com.almacen.api.client.model.DetalleUsuarioDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.ui.adapters.AdapterSelector;
import com.miguel_lm.app_barcode.ui.adapters.EndlessScrollListener;
import org.openapitools.client.api.UsuariosControllerApi;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosDialogFragment extends DialogFragment implements EndlessScrollListener.ScrollerClient {

    private static final String EXTRA_ID = "ïd";
    public AdapterSelector<DetalleUsuarioDto> listaAdapter;
    public RecyclerView listaProyectos;
    private ProgressBar carga;
    private OnUsuarioItemClick listener;
    private int pagina = 1;
    public ConstraintLayout contenedorDialog;

    //Método para crear una instancia del cliente API para usuarios.
    private final UsuariosControllerApi usuariosControllerApi = ClienteApiFactoria.getInstance()
            .createService(UsuariosControllerApi.class);
    Long idPorDefecto;

    public interface OnUsuarioItemClick {
        void onClick(DetalleUsuarioDto usuario);
    }

    //Método para cargar una nueva página en la lista de usuarios (Paginación).
    @Override
    public void loadData() {
        listaAdapter.habilitarBarraProgreso();
        obtenerUsuarios(++pagina);
    }

    //Método para obtener los datos de los usuarios mediante paginación.
    private void obtenerUsuarios(int pag) {
        usuariosControllerApi.obtenerUsuarios(pag, 10, "encargado").enqueue(new Callback<List<DetalleUsuarioDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetalleUsuarioDto>> call, @NonNull Response<List<DetalleUsuarioDto>> response) {
                listaAdapter.addAll(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<DetalleUsuarioDto>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //Método para crear dialogo con el id del usuario.
    public static UsuariosDialogFragment create(Long encargadoId) {
        Bundle bundle = new Bundle();
        if(encargadoId != null)
            bundle.putLong(EXTRA_ID, encargadoId);
        UsuariosDialogFragment proyectoDialogFragment = new UsuariosDialogFragment();
        proyectoDialogFragment.setArguments(bundle);
        return proyectoDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_selector_proyectos, container);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idPorDefecto = requireArguments().getLong(EXTRA_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(
                getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels/3
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.listaProyectos = view.findViewById(R.id.proyectosRv);

        this.carga = view.findViewById(R.id.cargaPb);

        usuariosControllerApi.obtenerUsuarios(pagina, 10, "encargado").enqueue(new Callback<List<DetalleUsuarioDto>>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onResponse(@NonNull Call<List<DetalleUsuarioDto>> call, @NonNull Response<List<DetalleUsuarioDto>> response) {
                listaAdapter = new AdapterSelector<>(item -> {
                    listener.onClick(item);
                    dismiss();
                }, it -> String.format("%s %s", it.getNombre(), it.getApellidos()));

                listaProyectos.setAdapter(listaAdapter);
                listaProyectos.setLayoutManager(new LinearLayoutManager(getContext()));
                listaProyectos.addOnScrollListener(new EndlessScrollListener(UsuariosDialogFragment.this));
                listaProyectos.setVisibility(View.VISIBLE);
                carga.setVisibility(View.GONE);
                comprobarModoOscuro(view);
                List<DetalleUsuarioDto> items = response.body();
                listaAdapter.addAll(items);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetalleUsuarioDto>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (OnUsuarioItemClick) context;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void comprobarModoOscuro(View view){

        this.contenedorDialog = view.findViewById(R.id.contenedorDialogProyectos);

        if (recibiendoDatosTema()) {
            contenedorDialog.setBackground(getResources().getDrawable(COLOR_GRAY_LIGHT, requireActivity().getTheme()));

        } else {
            contenedorDialog.setBackgroundColor(getResources().getColor(COLOR_WHITE, requireActivity().getTheme()));
        }
    }

    public boolean recibiendoDatosTema() {

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MODO_DARK, false);
    }
}
