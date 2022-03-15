package com.miguel_lm.app_barcode.ui.activities;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_RADIUS_LOGIN;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_WHITE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_EMAIL;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_FICHERO;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_PASSWORD;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PREF_SESION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.almacen.api.client.model.DetalleUsuarioDto;
import com.almacen.api.client.model.LoginDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.model.management.waves.GestionOndasView;
import com.miguel_lm.app_barcode.model.singletons.client_api.ClienteApiFactoria;
import com.miguel_lm.app_barcode.model.management.color_app.GestionarColorApp;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.singletons.sesion.Sesion;
import org.openapitools.client.api.UsuariosControllerApi;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    public EditText emailEdit, passwordEdit;
    public RadioButton rb_sesion;
    public TextView tv_titulo_login;
    private boolean isActivate_RB;
    public View ondaInfLogin;
    private long tiempoParaSalir = 0;
    ImageButton btn_configuracion;
    public Button btn_login;
    LottieAnimationView lottieAnimationViewLogin;
    ImageView fondoDark;

    private UsuariosControllerApi usuariosControllerApi;

    GestionOndasView gestionOndasView = new GestionOndasView();
    GestionarColorApp gestionarColorApp = new GestionarColorApp();
    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gestionarColorApp.colorDefault(this);
        super.onCreate(savedInstanceState);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        usuariosControllerApi = ClienteApiFactoria.getInstance().createService(UsuariosControllerApi.class);
        inits();
        comprobarModoOscuro();
        isActivate_RB = rb_sesion.isChecked();
        btn_configuracion.setOnClickListener(v -> configuracion());
        btn_login.setOnClickListener(v -> accionBotonLogin());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onPause() {
        gestionarColorApp.colorDefault(this);
        super.onPause();
        seleccionFuente.gestionBotonLogin(this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, this, null,
                null, null, null, null);
        gestionOndasView.gestionarOndasViewLoginActivity(this, this);
        seleccionSize.cambioDeTextSizeEnComponentesDeLoginActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        comprobarModoOscuro();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        gestionarColorApp.colorDefault(this);
        super.onResume();
        seleccionFuente.gestionBotonLogin(this);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, this, null,
                null, null, null, null);
        gestionOndasView.gestionarOndasViewLoginActivity(this, this);
        seleccionSize.cambioDeTextSizeEnComponentesDeLoginActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        comprobarModoOscuro();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onRestart() {
        gestionarColorApp.colorDefault(this);
        super.onRestart();
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, this, null,
                null, null, null, null);
        gestionOndasView.gestionarOndasViewLoginActivity(this, this);
        seleccionSize.cambioDeTextSizeEnComponentesDeLoginActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        seleccionFuente.gestionBotonLogin(this);
        gestionarColorApp.gestionColorTema(this, null, null, null);
        comprobarModoOscuro();
    }

    //Método para inicializar los componentes de la vista.
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void inits() {

        lottieAnimationViewLogin = findViewById(R.id.lottieAnimationView2);
        lottieAnimationViewLogin.setSpeed(0.4F);
        lottieAnimationViewLogin.loop(false);
        emailEdit = findViewById(R.id.ed_email);
        passwordEdit = findViewById(R.id.ed_password);
        rb_sesion = findViewById(R.id.radioButton_sesion_iniciada);
        btn_configuracion = findViewById(R.id.btn_configuracion);
        fondoDark = findViewById(R.id.imageViewDark);
        tv_titulo_login = findViewById(R.id.tv_titulo_login);
        btn_login = findViewById(R.id.btn_login);
        ondaInfLogin = findViewById(R.id.bottom_view_login);
        seleccionFuente.gestionarTipoDeFuente(this, null, null,
                null, null, this, null,
                null, null, null, null);
        gestionOndasView.gestionarOndasViewLoginActivity(this, this);
        seleccionSize.cambioDeTextSizeEnComponentesDeLoginActivity(
                seleccionSize.getValuePreferenceSize(this), this);
        seleccionFuente.gestionBotonLogin(this);
    }

    @SuppressLint("ResourceAsColor")
    public void comprobarModoOscuro(){
        if (gestionarColorApp.recibiendoDatosTema(this)) {
            fondoDark.setVisibility(View.VISIBLE);
            rb_sesion.setTextColor(getResources().getColor(COLOR_RADIUS_LOGIN,getTheme()));
            rb_sesion.setButtonTintList(ColorStateList.valueOf(getResources().getColor(COLOR_RADIUS_LOGIN,getTheme())));
            btn_configuracion.setImageTintList(ColorStateList.valueOf(getResources().getColor(COLOR_WHITE, getTheme())));
        }
    }

    //Método para hacer login.
    public void doLoginPost(String email, String password){

        LoginDto loginDto = new LoginDto().email(email).password(password);
        usuariosControllerApi.iniciarSesion(loginDto).enqueue(new Callback<DetalleUsuarioDto>() {
            @Override
            public void onResponse(@NonNull Call<DetalleUsuarioDto> call, @NonNull Response<DetalleUsuarioDto> response) {
                DetalleUsuarioDto usuarioDto = response.body();
                Sesion.getInstance().setUsuario(usuarioDto);
                gestionarEmailYPassword(usuarioDto, password);
            }

            @Override
            public void onFailure(@NonNull Call<DetalleUsuarioDto> call, @NonNull Throwable t) {
                if(!email.contentEquals(Objects.requireNonNull(loginDto.getEmail())) ||
                        password.contentEquals(Objects.requireNonNull(loginDto.getPassword()))) {
                    Toast.makeText(getApplicationContext(), "Los datos introducidos no son correctos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Método para gestionar el inicio de sesión comprobando el token del usuario.
    private void gestionarEmailYPassword(DetalleUsuarioDto usuarioDto, String password) {

        if((usuarioDto != null ? usuarioDto.getApiKey() : null) == null){
            Toast.makeText(LoginActivity.this, "La contraseña o el email no es correcto", Toast.LENGTH_SHORT).show();

        } else{
            ClienteApiFactoria.getInstance().setApiKey(Objects.requireNonNull(usuarioDto).getApiKey());
            guardarEmailYPassword(usuarioDto, password);
            guardarEstado();

            Intent intent = new Intent(LoginActivity.this, ScannerActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Login sucess", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    //Método para guardar email y password del usuario que inicia sesión en la app.
    private void guardarEmailYPassword(DetalleUsuarioDto usuarioDto, String password) {

        SharedPreferences preferencias = LoginActivity.this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        editor.putString("email", usuarioDto.getEmail());
        editor.putString("password",password );
        editor.apply();
    }

    //Método para a¡mostrar mensaje a usuario cuando los campos están vacios o cubiertos de forma incorrecta.
    public void accionBotonLogin(){

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        if (email.contentEquals("") && !password.contentEquals("")) {
            Toast.makeText(this, "El campo 'email' está vacío", Toast.LENGTH_SHORT).show();

        } else if (!email.contentEquals("") && password.contentEquals("")) {
            Toast.makeText(this, "El campo 'password' está vacío", Toast.LENGTH_SHORT).show();

        } else if (email.contentEquals("") || password.contentEquals("")){
            Toast.makeText(this, "Los campos están vacios", Toast.LENGTH_SHORT).show();

        } else if (!email.contentEquals("") || !password.contentEquals("")) {
            doLoginPost(email, password);
        }
    }

    //Metodo para guardar el estado de la sesión con el email y password del usuario activo.
    public void guardarEstado(){

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()){
            SharedPreferences preferencias = this.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString(PREF_EMAIL, email);
            editor.putString(PREF_PASSWORD, password);
            editor.putBoolean(PREF_SESION, rb_sesion.isChecked());
            editor.apply();

        } else {
            System.out.println("[LoginActivity]" + "error en el guardarEstado");
            Toast.makeText(this,"Error, los datos no han sido guardados.",Toast.LENGTH_SHORT).show();
        }
    }

    //Método para mantener la sesión iniciada con el radius activado.
     public void mantenerSesionIniciada(View view){
         rb_sesion.setChecked(!isActivate_RB);
         isActivate_RB = rb_sesion.isChecked();
     }

     //Método para cambiar el estado de la sesión.
    public static void cambiarEstado(Context context,boolean estadoCambiado){

        SharedPreferences preferencias = context.getSharedPreferences(PREF_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        editor.putBoolean(PREF_SESION, estadoCambiado);
        editor.apply();
    }

    //Método para ir a ConfiguracionActivity.
    public void configuracion(){
        Intent intent = new Intent(this, ConfiguracionActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        long tiempo = System.currentTimeMillis();
        if (tiempo - tiempoParaSalir > 3000) {
            tiempoParaSalir = tiempo;
            Toast.makeText(this, "Presione de nuevo 'Atrás' si desea salir",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }
}