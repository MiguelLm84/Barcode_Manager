package com.miguel_lm.app_barcode.ui.fragments;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BOOKINGS;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.EXTRA_PROYECTO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PROJECT_HISTORY;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NAME_APP;
import static java.util.Collections.emptyList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.almacen.api.client.model.DetalleProyectoDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.ui.interfaces.OnScreenChange;
import com.miguel_lm.app_barcode.ui.activities.ScannerActivity;
import com.miguel_lm.app_barcode.ui.adapters.AdapterProyecto;
import com.miguel_lm.app_barcode.ui.interfaces.OnTitleChange;
import java.util.Objects;
import java.util.Optional;


public class FragmentContenedor extends Fragment implements AdapterProyecto.OnProyectoItemClickListener {

    private OnBackListener listener;
    private OnScreenChange screenChangeListener;
    public View ondaSupScannerActivity;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    GestionOndasView gestionOndasView = new GestionOndasView();
    ListaReservasFragment fragListaOp;
    OnTitleChange titulo;
    Bundle bundle = new Bundle();


    //Método para mostrar un proyecto al pulsar en un item de la lista de proyectos.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    @Override
    public void onItemClick(DetalleProyectoDto proyecto) {

        bundle.putString("proyecto", proyecto.getCliente());
        bundle.putLong(EXTRA_PROYECTO,Objects.requireNonNull(proyecto.getIdProyecto()));
        ScannerActivity.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        screenChangeListener.onChange(BOOKINGS + proyecto.getCliente());

        fragListaOp = new ListaReservasFragment();
        fragListaOp.setArguments(bundle);

        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.contenedor, fragListaOp,BOOKINGS + proyecto.getCliente()).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        View root = inflater.inflate(R.layout.fragment_contenedor, container, false);
        if(bundle != null && titulo != null){
            titulo.titleChange(BOOKINGS + bundle.getString("proyecto"));
        }
        ondaSupScannerActivity = root.findViewById(R.id.waveTop2);
        gestionOndasView.gestionarViewOndasFragmentContenedor(getContext(), this);

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onResume() {
        gestionarColorApp.colorDefault(requireContext());
        super.onResume();
        gestionOndasView.gestionarViewOndasFragmentContenedor(getContext(), this);
        gestionarColorApp.gestionColorTema(getContext(), null, null, null);

        mostrarTitulo();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (OnBackListener) context;
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getChildFragmentManager().getBackStackEntryCount() > 1) {
                    getChildFragmentManager().popBackStack();
                } else
                    listener.onBackRequest();
            }
        });
        if(context instanceof OnScreenChange) {
            screenChangeListener = (OnScreenChange) context;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        getChildFragmentManager().addOnBackStackChangedListener(() -> {
            String tag = Objects.requireNonNull(getChildFragmentManager().findFragmentById(R.id.contenedor)).getTag();
            screenChangeListener.onChange(tag);
        });

        gestionOndasView.gestionarViewOndasFragmentContenedor(getContext(), this);
        gestionarColorApp.gestionColorTema(getContext(), null, null, null);

        Fragment currentFragment = getChildFragmentManager().findFragmentById(R.id.contenedor);
        if(currentFragment == null) {
            mostrarTitulo();
            getChildFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.contenedor, new FragmentListaProyectos(), PROJECT_HISTORY)
                    .commit();
        }
    }

    @SuppressLint("SetTextI18n")
    private void mostrarTitulo() {

        if(ScannerActivity.viewPager2.getCurrentItem() != 0){
            ScannerActivity.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            String title = Optional.ofNullable(getChildFragmentManager().findFragmentById(R.id.contenedor))
                    .map(Fragment::getTag)
                    .orElse(PROJECT_HISTORY);
            screenChangeListener.onChange(title);

        } else {
            ScannerActivity.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            ScannerActivity.actionBarDrawerToggle.syncState();
            screenChangeListener.onChange(NAME_APP);
        }
    }

    public interface OnBackListener {
        void onBackRequest();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Optional.of(getChildFragmentManager().getFragments())
                .orElse(emptyList())
                .forEach(it -> it.onActivityResult(requestCode, resultCode, data));

        super.onActivityResult(requestCode, resultCode, data);
    }
}