package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_HEADER_NAV_RED;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BOOKINGS;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_BLUE_BASIC;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GRAY_LIGHT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RED;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RED_LIGHT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_WHITE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.FILE_THEME_PREFERENCES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_LIGHT;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_LIGHT_NAV;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_DARK_NAV;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NAME_APP;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PROJECT_HISTORY;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.THEME_ID;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.navigation.NavigationView;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.ui.adapters.ViewPagerAdapter;
import com.miguel_lm.app_barcode.ui.fragments.FragmentContenedor;
import com.miguel_lm.app_barcode.ui.fragments.FragmentWebDescarga;
import com.miguel_lm.app_barcode.ui.interfaces.OnScreenChange;
import com.miguel_lm.app_barcode.ui.interfaces.OnTitleChange;
import java.io.File;
import me.relex.circleindicator.CircleIndicator3;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class ScannerActivity extends AppCompatActivity implements FragmentContenedor.OnBackListener,
        NavigationView.OnNavigationItemSelectedListener, Switch.OnCheckedChangeListener,
        OnScreenChange, OnTitleChange {

    //Declaración de variables
    private long tiempoParaSalir = 0;
    public static ViewPager2 viewPager2;
    CircleIndicator3 indicator;

    @SuppressLint("StaticFieldLeak")
    public TextView tv_titulo;
    Toolbar toolbar;
    public static DrawerLayout drawerLayout;
    public static ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;
    ViewPagerAdapter adapter;
    public Switch switchDarkMode;
    public View ondaInfScannerActivity;
    MenuItem custom;

    public TextView navHeaderTv;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    GestionOndasView gestionOndasView = new GestionOndasView();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    @SuppressLint("SdCardPath")
    File preferenciasTema = new File(FILE_THEME_PREFERENCES);


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(this);
        super.onCreate(savedInstanceState);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        setContentView(R.layout.activity_scanner);
        inits();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, null,
                this, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeScannerActivity(this,
                seleccionSize.getValuePreferenceSize(this), this);

        comprobarModoOscuro();
        gestionarTituloToolbar();
        gestionOndasView.gestionarOndasViewScannerActivity(this, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(this);
        super.onPause();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        comprobarModoOscuro();
        gestionarTituloToolbar();
        gestionOndasView.gestionarOndasViewScannerActivity(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, null,
                this, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeScannerActivity(this,
                seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        gestionarColorApp.colorDefault(this);
        super.onResume();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        comprobarModoOscuro();
        gestionarTituloToolbar();
        gestionOndasView.gestionarOndasViewScannerActivity(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, null,
                this, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeScannerActivity(this,
                seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        gestionarColorApp.colorDefault(this);
        super.onRestart();
        gestionarColorApp.gestionColorTema(this, null, null, null);
        comprobarModoOscuro();
        gestionarTituloToolbar();
        gestionOndasView.gestionarOndasViewScannerActivity(this, this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, null, null,
                this, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeScannerActivity(this,
                seleccionSize.getValuePreferenceSize(this), this);
    }

    //Método que mapea e inicializa los diferentees componentes de la vista.
    @SuppressLint("RestrictedApi")
    public void inits() {

        ondaInfScannerActivity = findViewById(R.id.waveBottom3);
        viewPager2 = findViewById(R.id.viewPage2_admin);
        tv_titulo = findViewById(R.id.tv_titulo2);
        tv_titulo.setSelected(true);
        adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager2);
        indicator.getSolidColor();
        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        drawerLayout = findViewById(R.id.drawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        switchDarkMode = (Switch) navigationView.getMenu().findItem(R.id.modo_oscuro).getActionView();
        switchDarkMode.setOnCheckedChangeListener(this);

        View headerView = navigationView.getHeaderView(0);
        navHeaderTv = headerView.findViewById(R.id.tv_header);

        ocultarItemPersonalizacion();
    }

    //Método para ocultar o mostrar la visibilidad del ítem del NavigationView 'Personalizacion'.
    private void ocultarItemPersonalizacion() {

        custom = navigationView.getMenu().findItem(R.id.personalizacion);
        View header = navigationView.getHeaderView(0);
        ImageView icono = header.findViewById(R.id.imageView_logo);
        icono.setOnLongClickListener(v -> {
            if(custom.isVisible()){
                custom.setVisible(false);

            } else if(!custom.isVisible()){
                custom.setVisible(true);
            }
            return false;
        });
    }

    //Método para acceder a la pantalla de personalización de la app.
    public void customizarApp(){

        Intent intent = new Intent(ScannerActivity.this, CustomActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    //Método para comprobar que modo está guardado (Dark Mode o Light Mode) y activarlo.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void comprobarModoOscuro(){

        if (recibiendoDatosTema()) {
            modoOscuro();

        } else {
            modoClaro();
        }
    }

    //Método que implementa el modo oscuro en los componentes de la vista.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void modoOscuro(){

        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        getWindow().setBackgroundDrawableResource(BACKGROUND_DARK);
        gestorColoresNavigationDrawer();
        navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(COLOR_WHITE, getTheme())));
        navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(COLOR_WHITE, getTheme())));
        navigationView.setBackgroundTintMode(PorterDuff.Mode.SCREEN);
        navigationView.setBackgroundColor(getResources().getColor(BACKGROUND_DARK_NAV, getTheme()));
        navigationView.getMenu().getItem(4).setIcon(R.drawable.ic_baseline_dark_mode_24);
        navigationView.getMenu().getItem(4).setTitle("Modo Oscuro");
        seleccionFuente.navigationFuente(this, this, navigationView.getMenu());
        seleccionSize.navigationTextSize(this, seleccionSize.getValuePreferenceSize(this));
        switchDarkMode.setThumbTintList(ColorStateList.valueOf(getResources().getColor(COLOR_WHITE, getTheme())));
        switchDarkMode.setTrackTintList(ColorStateList.valueOf(getResources().getColor(COLOR_GRAY_LIGHT, getTheme())));
        switchDarkMode.setChecked(true);
    }

    //Método que implementa el modo claro en los componentes de la vista.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void modoClaro(){

        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        getWindow().setBackgroundDrawableResource(BACKGROUND_LIGHT);
        gestorColoresNavigationDrawer();
        navigationView.setBackgroundTintMode(PorterDuff.Mode.SCREEN);
        navigationView.setBackgroundColor(getResources().getColor(BACKGROUND_LIGHT_NAV, getTheme()));
        navigationView.getMenu().getItem(4).setIcon(R.drawable.ic_baseline_light_mode_24);
        navigationView.getMenu().getItem(4).setTitle("Modo Claro");
        seleccionFuente.navigationFuente(this, this, navigationView.getMenu());
        seleccionSize.navigationTextSize(this, seleccionSize.getValuePreferenceSize(this));
        switchDarkMode.setChecked(false);
    }

    //Método que gestiona los colores del texto, los iconos y el switch del NavigationDrawer para el modo claro.
    private void gestorColoresNavigationDrawer() {

        if(gestionarColorApp.getValuePreferenceAzul(this)){
            navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(COLOR_BLUE_BASIC, getTheme())));
            navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(COLOR_BLUE_BASIC, getTheme())));
            switchDarkMode.setThumbTintList(ColorStateList.valueOf(getResources().getColor(COLOR_BLUE_BASIC, getTheme())));
            switchDarkMode.setTrackTintList(ColorStateList.valueOf(getResources().getColor(COLOR_BLUE_BASIC, getTheme())));

        } else {
            navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(COLOR_RED, getTheme())));
            navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(COLOR_RED, getTheme())));
            switchDarkMode.setThumbTintList(ColorStateList.valueOf(getResources().getColor(COLOR_RED, getTheme())));
            switchDarkMode.setTrackTintList(ColorStateList.valueOf(getResources().getColor(COLOR_RED_LIGHT, getTheme())));

            View header = navigationView.getHeaderView(0);
            LinearLayout sideNavLayout = header.findViewById(R.id.header);
            sideNavLayout.setBackgroundResource(BACKGROUND_HEADER_NAV_RED);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Método para la gestionar el título de la toolbar al desplazarse lateralmente entre fragments.
    public void gestionarTituloToolbar(){

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 0) {
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                    actionBarDrawerToggle.syncState();
                    tv_titulo.setText("Barcode Manager");
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

                } else if (position == 1) {
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        });
    }

    //Método para acceder al manual de usuario de la app..
    public void accionManualUsuario(){

        Intent intent = new Intent(this, ManualPDFActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    //Método para ir a la web de descarga de la app..
    public void accionWebDescarga(){

        Fragment fragWebDescarga = new FragmentWebDescarga();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frameLayoutFragments, fragWebDescarga).commit();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //Método para ir a la pantalla de cierre de sesión.
    public void accionCerrarSesion(){

        Intent intent = new Intent(this, CerrarSesionActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    //Método para guardar el tema que se ha activado en las Shared Preferences del dispositivo.
    public void guardandoDatosTema(boolean modoDark) {

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MODO_DARK, modoDark);
        editor.apply();
    }

    //Método donde se lee el tema guardado para aplicarlo a la vista.
    public boolean recibiendoDatosTema() {

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MODO_DARK, false);
    }

    //Método para seleccionar un item de la listra del Navigation View (Menú lateral).
    @SuppressLint("ResourceType")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);

        if(item.getItemId() == R.id.principal){
            viewPager2.setAdapter(adapter);
        }
        if(item.getItemId() == R.id.manual){
            accionManualUsuario();
        }
        if(item.getItemId() == R.id.web_descarga){
            accionWebDescarga();
        }
        if(item.getItemId() == R.id.modo_oscuro){
            ((Switch)item.getActionView()).toggle();
            return false;
        }
        if(item.getItemId() == R.id.cerrar_sesion){
            accionCerrarSesion();
        }
        if(item.getItemId() == R.id.personalizacion){
            customizarApp();
        }
        return false;
    }

    //Método para mostrar item seleccionado
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Método para aplicar tema oscuro o claro al mover el switch y guardar,
    // a su vez, la posición de este en las Shared Preferences.
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(buttonView.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int themeID;

        if (isChecked) {
            themeID = AppCompatDelegate.MODE_NIGHT_YES;
            guardandoDatosTema(true);

        } else {
            themeID = AppCompatDelegate.MODE_NIGHT_NO;
            guardandoDatosTema(false);
        }

        getDelegate().setLocalNightMode(themeID);
        editor.putInt(THEME_ID, themeID);
        editor.apply();
    }

    //Método para volver a la activity anterior.
    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed(){

        if(viewPager2.getCurrentItem() == 0) {
            long tiempo = System.currentTimeMillis();
            if (tiempo - tiempoParaSalir > 3000) {
                tiempoParaSalir = tiempo;
                Toast.makeText(this, "Presione de nuevo 'Atrás' si desea salir", Toast.LENGTH_SHORT).show();

            } else {
                super.onBackPressed();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }
        else {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            gestionNavigationView();
        }
    }

    //Método para, según la posición del ViewPager, activar o desactivcar el foco del icono de hamburguesa
    //para deslizar lateralmente el navigationView, y bloquearlo según convenga y que no se pueda deslizar.
    //También se cambia el titulo de la toolbar según la posición del ViewPager.
    @SuppressLint("SetTextI18n")
    private void gestionNavigationView() {

        if(viewPager2.getCurrentItem() == 0){
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.syncState();
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            tv_titulo.setText(NAME_APP);


        } else {
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            tv_titulo.setText(PROJECT_HISTORY);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBackRequest() {

        if(viewPager2.getCurrentItem() > 0) {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }

    @Override
    public void onChange(String screenName) {
        tv_titulo.setText(screenName);
        tv_titulo.setSelected(true);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void titleChange(String nom) {
        if(nom == null){
            tv_titulo.setText(PROJECT_HISTORY);
        } else {
            tv_titulo.setText(BOOKINGS + nom);
        }
    }
}