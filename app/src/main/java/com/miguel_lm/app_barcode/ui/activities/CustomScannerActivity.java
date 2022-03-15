package com.miguel_lm.app_barcode.ui.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;


public class CustomScannerActivity extends Activity implements DecoratedBarcodeView.TorchListener {

    private CaptureManager capture;
    public DecoratedBarcodeView barcodeScannerView;
    public Button switchFlashlightButton;
    private ViewfinderView viewfinderView;

    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scanner);

        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);

        switchFlashlightButton = findViewById(R.id.switch_flashlight);
        switchFlashlightButton.setText("ON");

        viewfinderView = findViewById(R.id.zxing_viewfinder_view);

        //si el dispositivo no tiene linterna en su cámara,
        //entoces quita el botón del interruptor de la linterna ...
        if (!hasFlash()) {
            switchFlashlightButton.setVisibility(View.GONE);
        }

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.setShowMissingCameraPermissionDialog(false);
        capture.decode();

        changeLaserVisibility(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
        seleccionFuente.gestionFuenteEnScanner(this, this);
        seleccionSize.gestionarTextSizeScanner(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
        seleccionFuente.gestionFuenteEnScanner(this, this);
        seleccionSize.gestionarTextSizeScanner(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
        seleccionFuente.gestionFuenteEnScanner(this, this);
        seleccionSize.gestionarTextSizeScanner(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    //Comprueba sí la cámara del dispositivo tiene una linterna.
    // Retornará verdadero si hay linterna, de lo contrario retornará falso.
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    //Activar linterna
    public void switchFlashlight(View view) {
        if (getString(R.string.turn_on_flashlight).contentEquals(switchFlashlightButton.getText())) {
            barcodeScannerView.setTorchOn();
        } else {
            barcodeScannerView.setTorchOff();
        }
    }

    //Visiabilidad laser scaner
    public void changeLaserVisibility(boolean visible) {
        viewfinderView.setLaserVisibility(visible);
    }

    //Linterna activada
    @Override
    public void onTorchOn() {
        switchFlashlightButton.setText(R.string.turn_off_flashlight);
    }

    //Linterna desactivada
    @Override
    public void onTorchOff() {
        switchFlashlightButton.setText(R.string.turn_on_flashlight);
    }

    //Requerimiento de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
