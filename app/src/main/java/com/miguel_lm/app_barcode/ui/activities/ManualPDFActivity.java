package com.miguel_lm.app_barcode.ui.activities;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;

public class ManualPDFActivity extends AppCompatActivity {

    GestionarColorApp gestionarColorApp = new GestionarColorApp();

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(this);
        super.onCreate(savedInstanceState);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        setContentView(R.layout.activity_manual_pdf);

        //Se crea un objeto de la clase PDFView y se llama al método 'fromAsset()' para mostrar el archivo PDF.
        //Para mejorar la experiencia del visor PDF se añaden diferentes métodos.
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("Manual de Usuario Barcode Manager.pdf")
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .spacing(MATCH_PARENT)
                .enableAntialiasing(true)
                .enableAnnotationRendering(false)
                .load();
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, ScannerActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}