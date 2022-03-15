package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CHANNEL_ID;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_NOTIFICATION;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.BACKGROUND_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MODO_DARK;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NOTIFICACION_ID;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREFERENCIAS_TEMA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_DIRECCION_IP;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_FICHERO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_PUERTO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_SESION;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.SCREEN_SPLASH_BLUE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.SCREEN_SPLASH_RED;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.WEB_DOWNLOAD;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.almacen.api.client.model.DetalleUsuarioDto;
import com.almacen.api.client.model.InfoDto;
import com.almacen.api.client.model.LoginDto;
import com.miguel_lm.app_barcode.BuildConfig;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.singletons.sesion.Sesion;
import com.miguel_lm.app_barcode.model.singletons.error_api.ErroresClienteInterceptor;
import org.openapitools.client.api.AppControllerApi;
import org.openapitools.client.api.UsuariosControllerApi;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySplash extends AppCompatActivity {

    private SharedPreferences preferencias;
    private UsuariosControllerApi usuariosControllerApi;
    private AppControllerApi appControllerApi;

    public TextView tv_version_apk;
    private ImageView imageViewLogo;
    ConstraintLayout constarintLayoutSplash;

    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(this);
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        init();
        animacionLogoSplash();
        mostrarVersion();
        comprobarConfig();
        comprobarModoOscuro();

        preferencias = this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);

        usuariosControllerApi = ClienteApiFactoria.getInstance().createService(UsuariosControllerApi.class);
        appControllerApi = ClienteApiFactoria.getInstance().createService(AppControllerApi.class);
        ClienteApiFactoria.getInstance().addInterceptor(new ErroresClienteInterceptor(getApplicationContext()));

        comprobarActualizaciones();
        comprobarSesion();
    }

    //Método para comprobar si hay alguna sesión guardada.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void comprobarSesion(){
        String[] Permissions = {Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, Permissions, 1);

        boolean haySesion = preferencias.getBoolean(PREF_SESION, false);

        if (haySesion) {
            new Handler().postDelayed(() -> {
                String email = preferencias.getString("email", "");
                String password = preferencias.getString("password", "");
                if (!email.contentEquals("") && !password.equals(""))
                    doLoginPost(email, password);
            }, 4000);

        } else {
            new Handler().postDelayed(() -> startActivity(new Intent(
                    ActivitySplash.this, LoginActivity.class)), 2500);
        }

        animacionTextoVersionSplashIn();
        Explode explode = new Explode();
        explode.setDuration(3500);
        getWindow().setExitTransition(explode);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        super.onPause();
        comprobarModoOscuro();
        seleccionFuente.gestionarTipoDeFuente(this, this, null,
                null, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeSplashActivity(seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();
        comprobarModoOscuro();
        seleccionFuente.gestionarTipoDeFuente(this, this, null,
                null, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeSplashActivity(seleccionSize.getValuePreferenceSize(this), this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        super.onRestart();
        comprobarModoOscuro();
        seleccionFuente.gestionarTipoDeFuente(this, this, null,
                null, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeSplashActivity(seleccionSize.getValuePreferenceSize(this), this);
    }

    @SuppressLint("ResourceAsColor")
    public void comprobarModoOscuro(){

        if (recibiendoDatosTema()) {
            getWindow().setBackgroundDrawableResource(BACKGROUND_DARK);
        }
    }

    public boolean recibiendoDatosTema() {

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCIAS_TEMA, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MODO_DARK, false);
    }

    //Método para comprabar la dirección y el puerto guardado en Shared Preferences.
    private void comprobarConfig() {
        SharedPreferences preferencias = this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);

        String ip = preferencias.getString(PREF_DIRECCION_IP, ClienteApiFactoria.IP);
        String puerto = preferencias.getString(PREF_PUERTO, ClienteApiFactoria.PUERTO);

        ClienteApiFactoria.getInstance().setBaseUrl("http://" + ip + ":" + puerto);
    }

    //Método para realizar el login.
    public void doLoginPost(String email, String password) {
        LoginDto loginDto = new LoginDto().email(email).password(password);

        usuariosControllerApi.iniciarSesion(loginDto).enqueue(new Callback<DetalleUsuarioDto>() {
            @Override
            public void onResponse(@NonNull Call<DetalleUsuarioDto> call, @NonNull Response<DetalleUsuarioDto> response) {
                DetalleUsuarioDto detalleUsuarioDto = response.body();
                Log.i("Login success", response.toString());
                Toast.makeText(ActivitySplash.this, "Login sucess", Toast.LENGTH_SHORT).show();
                Sesion.getInstance().setUsuario(detalleUsuarioDto);
                ClienteApiFactoria.getInstance().setApiKey(Objects.requireNonNull(detalleUsuarioDto).getApiKey());
                guardarCredencialesUsuario(detalleUsuarioDto, password);
                scannerActivity();
            }

            @Override
            public void onFailure(@NonNull Call<DetalleUsuarioDto> call, @NonNull Throwable t) {
                Log.i("Login error", Arrays.toString(t.getStackTrace()));
                Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método para ir a ScannerActivity.
    private void scannerActivity() {

        Intent intent = new Intent(ActivitySplash.this, ScannerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    //Método para almacenar el email y password del usuario.
    private void guardarCredencialesUsuario(DetalleUsuarioDto detalleUsuarioDto, String password) {

        SharedPreferences preferencias = ActivitySplash.this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("email", detalleUsuarioDto.getEmail());
        editor.putString("password", password);
        editor.apply();
    }

    //Método para comprobar sí existe una nueva actualización de la app.
    private void comprobarActualizaciones()  {
        appControllerApi.obtenerInfo().enqueue(new Callback<InfoDto>() {

            @Override
            public void onResponse(@NonNull Call<InfoDto> call, @NonNull Response<InfoDto> response) {
                String hash = Objects.requireNonNull(response.body()).getAndroidHash();
                String ultimoHashGuardado = preferencias.getString("INDEX_HASH",null);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("INDEX_HASH", hash);
                editor.apply();
                if(ultimoHashGuardado == null) return;
                if(!Objects.requireNonNull(hash).equalsIgnoreCase(ultimoHashGuardado)) createNotification();
            }

            @Override
            public void onFailure(@NonNull Call<InfoDto> call, @NonNull Throwable t) {

            }
        });
    }

    //Método de mapeo de componentes de la Splash.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void init(){
        imageViewLogo = findViewById(R.id.imageViewLogo);
        tv_version_apk = findViewById(R.id.tv_version_apk);
        seleccionFuente.gestionarTipoDeFuente(this, this, null,
                null, null, null, null,
                null, null, null, null);

        seleccionSize.cambioDeTextSizeEnComponentesDeSplashActivity(seleccionSize.getValuePreferenceSize(this), this);
        constarintLayoutSplash = findViewById(R.id.constarintLayoutSplash);
    }

    //Método para mostrar por pantalla la versión y la fecha de la APK.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    public void mostrarVersion(){

        animacionTextoVersionSplashIn();
        String versionName = BuildConfig.VERSION_NAME;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDeActualizacion = sdf.format(new Date(BuildConfig.BUILD_DATE));
        tv_version_apk.setText("Barcode Manager  V" + versionName + "\nFecha: " + fechaDeActualizacion);

        createNotificationChannel();
    }

    //Método para crear un canal de notificación.
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    //Método para crear una notificación.
    private void createNotification(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WEB_DOWNLOAD));

        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setContentTitle("Actualización");
        builder.setContentText("Pulsa en esta notificación para la descarga.");
        builder.setColor(Color.parseColor(COLOR_NOTIFICATION));
        builder.setSmallIcon(R.drawable.logo_notificacion);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLights(Color.MAGENTA,1000,1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());
    }

    //Método para la animación del logo de la app y splash.
    public void animacionLogoSplash(){

        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);

        if(gestionarColorApp.getValuePreferenceAzul(this) == Boolean.parseBoolean(null)){
            constarintLayoutSplash.setBackgroundResource(SCREEN_SPLASH_RED);

        } else {
            if(gestionarColorApp.getValuePreferenceAzul(this)){
                constarintLayoutSplash.setBackgroundResource(SCREEN_SPLASH_RED);

            } else {
                constarintLayoutSplash.setBackgroundResource(SCREEN_SPLASH_BLUE);
            }
        }

        imageViewLogo.setAnimation(animacion1);
    }

    //Método para la animación del texto referente a las versiones.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void animacionTextoVersionSplashIn() {
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.fade_in_2);
        tv_version_apk.setAnimation(animacion2);
    }
}