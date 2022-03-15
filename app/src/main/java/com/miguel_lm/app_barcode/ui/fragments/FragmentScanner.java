package com.miguel_lm.app_barcode.ui.fragments;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CANCEL;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.LETRAS;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MULTIPLE_REQUEST_CODE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PRODUCTO_EXTRA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.REGISTRAR_RESERVA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.SCAN_AGAIN;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.almacen.api.client.model.DetalleProductoDto;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.activities.CaptureActivityPortrait;
import com.miguel_lm.app_barcode.ui.activities.CustomScannerActivity;
import com.miguel_lm.app_barcode.ui.activities.ReservaActivity;
import org.openapitools.client.api.ProductosControllerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentScanner extends Fragment {

    private static final String LOG_TAG = "log_json";

    public Button btn_scan, btn_cargar_codigo, btn_aceptar, btn_aceptarDialogBarcode;
    public TextView tv_dialog, tv_dialog_barcode;
    public EditText ed_result;
    private ImageButton btn_eliminarCodigo;
    private View root;
    public View ondaSupToolbar;
    AlertDialog dialog;

    SeleccionFuente seleccionFuente = new SeleccionFuente();
    GestionOndasView gestionOndasView = new GestionOndasView();
    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    SeleccionSize seleccionSize = new SeleccionSize();


    //Instancia del cliente API para productos.
    private final ProductosControllerApi productosControllerApi = ClienteApiFactoria.getInstance()
            .createService(ProductosControllerApi.class);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(requireContext());
        root = inflater.inflate(R.layout.fragment__scanner, container, false);
        init();
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                null, this);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentScanner(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionOndasView.gestionarOndasViewFragmentScanner(getContext(), this);
        conectividad();
        cambiosAlIntroducurTextoEnEditText();
        btn_cargar_codigo.setOnClickListener(v -> cargarCodigo(ed_result.getText().toString()));
        btn_scan.setOnClickListener(v -> { if (conectividad()) { scannerCreate(); }});
        btn_eliminarCodigo.setOnClickListener(v -> ed_result.getText().clear());

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        super.onPause();
        gestionarColorApp.colorDefault(requireContext());
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                null, this);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentScanner(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
         gestionOndasView.gestionarOndasViewFragmentScanner(getContext(), this);
     }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onResume(){
        super.onResume();
        gestionarColorApp.colorDefault(requireContext());
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        seleccionFuente.gestionarTipoDeFuente(getContext(), null, null, null,
                null, null, null, null, null,
                null, this);

        seleccionSize.cambioDeTextSizeEnComponentesDeFragmentScanner(
                seleccionSize.getValuePreferenceSize(requireContext()), this);
        gestionOndasView.gestionarOndasViewFragmentScanner(getContext(), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialogLecturaCancelada();
        }
    }

    //Método para evento de escucha que activa el botón de carga manual y botón de eliminar
    //el contenido del EditText al introducir texto en él.
    private void cambiosAlIntroducurTextoEnEditText() {

        ed_result.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                visibilidadBotonesFragmentScanner(s, null);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               vivibilidadBotonesFragmentScanner2(s);
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visibilidadBotonesFragmentScanner(null, s);
            }
        });
    }

    //Método para dar mostrar u ocultar los botones 'btn_cargar_codigo' y 'btn_eliminarCodigo'
    //si no hay caracteres en EditText y lo mismo para 'btn_scan'.
    private void vivibilidadBotonesFragmentScanner2(CharSequence s) {

        if (s.toString().length() == 0) {
            btn_scan.setVisibility(View.GONE);
            btn_cargar_codigo.setVisibility(View.VISIBLE);
            btn_eliminarCodigo.setVisibility(View.VISIBLE);

        } else {
            btn_scan.setVisibility(View.VISIBLE);
            btn_cargar_codigo.setVisibility(View.GONE);
            btn_eliminarCodigo.setVisibility(View.GONE);
        }
    }

    //Método para dar mostrar u ocultar los botones 'btn_cargar_codigo' y 'btn_eliminarCodigo'
    //si no hay caracteres en EditText y lo mismo para 'btn_scan'. Además hace la misma operación
    //para el Editable comprobando sí es mayor que 0 o no y mostrando u ocultando según el caso.
    private void visibilidadBotonesFragmentScanner(Editable s, CharSequence charSequence) {

        if(charSequence != null){
            if (charSequence.toString().length() > 0) {
                btn_scan.setVisibility(View.GONE);
                btn_cargar_codigo.setVisibility(View.VISIBLE);
                btn_eliminarCodigo.setVisibility(View.VISIBLE);

            } else {
                btn_scan.setVisibility(View.VISIBLE);
                btn_cargar_codigo.setVisibility(View.GONE);
                btn_eliminarCodigo.setVisibility(View.GONE);
            }

        } else {
            if(s != null){
                if (s.toString().length() > 0) {
                    btn_scan.setVisibility(View.GONE);
                    btn_cargar_codigo.setVisibility(View.VISIBLE);
                    btn_eliminarCodigo.setVisibility(View.VISIBLE);

                } else {
                    btn_scan.setVisibility(View.VISIBLE);
                    btn_cargar_codigo.setVisibility(View.GONE);
                    btn_eliminarCodigo.setVisibility(View.GONE);
                }
            }
        }
    }

    //Método para comprobar sí hay o no conexión a la red.
    public boolean conectividad() {

        Log.d(LOG_TAG, "Comprobando conexión");
        ConnectivityManager connMgr = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean connected = (networkInfo != null && networkInfo.isConnected());

        if (!connected) {
            Toast.makeText(getContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
        }

        return connected;
    }

    //Se crea el Scanner y sus funcionalidades
    public void scannerCreate() {

        IntentIntegrator integrador = IntentIntegrator.forSupportFragment(this);
        integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrador.setPrompt("Lector - Scanner");
        integrador.setCameraId(0);
        integrador.setOrientationLocked(false);
        integrador.setBeepEnabled(true);
        integrador.setCaptureActivity(CaptureActivityPortrait.class);
        integrador.setBarcodeImageEnabled(true);
        integrador.setOrientationLocked(false).setCaptureActivity(CustomScannerActivity.class).initiateScan();
    }

    //Método para el mapeado de componentes.
    public void init() {

        ondaSupToolbar = root.findViewById(R.id.waveTop4);
        btn_scan = root.findViewById(R.id.btn_scan);
        btn_cargar_codigo = root.findViewById(R.id.btn_cargar_codigo);
        ed_result = root.findViewById(R.id.ed_result);
        btn_eliminarCodigo = root.findViewById(R.id.btn_eliminarCodigo);
    }

    //Se recoge el resultado del scanner.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == MULTIPLE_REQUEST_CODE) {
            if (resultCode == SCAN_AGAIN)
                scannerCreate();
            if(resultCode == CANCEL)
                ed_result.setText("");
        } else {
            if(result == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            if (result.getContents() == null) {
                dialogLecturaCancelada();
                return;
            }
            if (isNotABarcode(result.getContents())) {
                dialogBarcode("Código de barras no reconocido");
                return;
            }
            Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_LONG).show();
            cargarCodigo(result.getContents());
            ed_result.setText(result.getContents());
        }
    }

    //Comprueba sí el contenido del código de barras contiene digitos o letras.
    private boolean isNotABarcode(String contents) {
        try {
            Long.parseLong(contents);
            return false;
        }
        catch (NumberFormatException ignored) {
            return true;
        }
    }

    //Dialogo para código de barras no encontrado.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void dialogBarcode(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View dialogLayout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_barcode_no_encontrado, null);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();

        btn_aceptarDialogBarcode = dialogLayout.findViewById(R.id.bt_aceptar);
        tv_dialog_barcode = dialogLayout.findViewById(R.id.tv_dialog_barcode);

        tv_dialog_barcode.setText(msg);

        btn_aceptarDialogBarcode.setOnClickListener(v -> dialog.dismiss());

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        seleccionFuente.gestionFuenteEnDialogBarcode(requireContext(), this);
        seleccionSize.gestionTextSizeEnDialogBarcode(requireContext(), this);
    }

    //Dialogo para lectura de código cancelada.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void dialogLecturaCancelada() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View dialogLayout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_lectura_cancelada, null);
        builder.setView(dialogLayout);
        dialog = builder.create();

        tv_dialog = dialogLayout.findViewById(R.id.tv_dialog_lectura_cancelada);
        btn_aceptar = dialogLayout.findViewById(R.id.bt_aceptar_lectura_cancelada);

        btn_aceptar.setOnClickListener(v -> dialog.dismiss());

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        seleccionFuente.gestionFuenteEnDialogLecturaCancelada(requireContext(), this);
        seleccionSize.gestionTextSizeEnDialogLecturaCancelada(requireContext(), this);
    }

    //Método para la gestión de los códigos cargados a mano.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void cargarCodigo(String codigoBarras) {

        conectividad();
        for (String letra : LETRAS) {
            if (codigoBarras.trim().contains(letra.toLowerCase())) {
                dialogBarcode("Código de barras no reconocido");
                return;
            }
        }
        if (codigoBarras.trim().isEmpty()) {
            dialogBarcode("Código de barras no reconocido");
            return;
        }
       obtenerProductoSegunCodigoBarras(codigoBarras);
    }

    //Método para obtener el nombre del producto según el código de barras escaneado.
    private void obtenerProductoSegunCodigoBarras(String codigoBarras) {

        productosControllerApi.obtenerProductoPorBarcode(Long.valueOf(codigoBarras)).enqueue(new Callback<DetalleProductoDto>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onResponse(@NonNull Call<DetalleProductoDto> call, @NonNull Response<DetalleProductoDto> response) {
                if (!response.isSuccessful()) {
                    dialogBarcode("Código de barras no reconocido");
                } else {
                    irAReserva(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull Call<DetalleProductoDto> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //Método que envía a la pantalla de reservas los datos del producto y el modo en que se va a mostrar,
    // en este caso, el modo Reserva.
    private void irAReserva(DetalleProductoDto productoDto) {
        Intent intent = new Intent(getContext(), ReservaActivity.class);
        intent.putExtra(PRODUCTO_EXTRA, productoDto);
        intent.putExtra(MODO_EXTRA, REGISTRAR_RESERVA);
        startActivityForResult(intent, MULTIPLE_REQUEST_CODE);
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}