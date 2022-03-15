package com.miguel_lm.app_barcode.model.common.constatnts;

import android.annotation.SuppressLint;

import com.miguel_lm.app_barcode.R;

public class StaticVariablesApp {

    //VARIABLES FOR NOTIFICATIONS

    //Declaración de variables estáticas ActivitySplash.
    public final static String CHANNEL_ID = "NOTIFICACION";
    public final static int NOTIFICACION_ID = 0;


    //SHARED PREFERENCES CONFIGURACION

    //Variables estáticas para las SheredPreferences.
    public static final String PREF_FICHERO = "preferencias";
    public static final String PREF_DIRECCION_IP = "Direccion_Ip";
    public static final String PREF_PUERTO = "Puerto";

    public static final String PREF_EMAIL = "Email";
    public static final String PREF_PASSWORD = "Password";
    public static final String PREF_SESION = "Sesion";


    //VARIABLES FOR RESERVA_ACTIVITY

    //Declaración de variables estáticas en ReservaActivity.
    public static final String REGISTRAR_RESERVA = "OP_1";
    public static final String MODIFICAR_RESERVA = "OP_2";
    public static final String MODO_EXTRA = "OP";

    public static final String RESERVA_EXTRA = "1234";
    public static final String PRODUCTO_EXTRA = "1235";

    public static final String PREF_RESERVA = "proyecto_op";
    public static final int MULTIPLE_REQUEST_CODE = 100;


    //VARIABLES FOR ADAPTER_PROYECTO

    //Declaración de variables estáticas de AdapterProyecto.
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_PROGRESS = 2;


    //VARIABLES FOR ADAPTER_RESERVAS_PROYECTO

    //Declaración de variables estáticas en AdapterReservasProyecto.
    public static final int TYPE_OPERACION = 0;
    public static final int TYPE_FOOTER = 1;


    //VARIABLES FOR ADAPTER_SELECTOR

    //Declaración de variables estáticas en AdapterSelector.
    public static final int CARGA = 1;
    public static final int ITEM = 2;


    //VARIABLES FOR ENDLESS_SCROLL_LISTENER

    //Declaración de variables estáticas en EndlessScrollListener.
    public static final int MIN_POSITION_TO_END = 2;


    //VARIABLES FOR FRAGMENT_SCANNER

    //Declaración de variables estáticas en FragmentScanner.
    public static final int SCAN_AGAIN = 965;
    public static final int CANCEL = 966;
    public static final String RESERVA_RESULT_EXTRA = "966";
    public static final String[] LETRAS = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};


    //VARIABLES FOR LISTA_RESERVAS_FRAGMENT

    //Declaración de variables estáticas en ListaReservasFragment.
    public static final String EXTRA_PROYECTO = "PROYECTO_PARAM";
    public static final int REPLACE = 324;
    public static final String EXTRA_RESERVA = "R";


    //FONTS APP

    //Declaración de variables estáticas en CustomActivity.
    public static final String BARLOW = "fonts/barlow.ttf";
    public static final String BEBAS_NEUE = "fonts/bebas_neue.ttf";
    public static final String RUSSO_ONE = "fonts/RussoOne-Regular.ttf";
    public static final String BASE_ONE = "fonts/base_one.otf";
    public static final String TAXICAB = "fonts/taxicab_bold.ttf";
    public static final String VIGA = "fonts/viga.ttf";


    //WALLPAPER LIGHT APP

    //Declaración de variable estática para el cambio de fondo en modo claro
    public static final int BACKGROUND_LIGHT = R.drawable.fondo_blanco_gradiant;


    //WALLPAPER DARK APP

    //Declaración de variable estática para el cambio de fondo en modo oscuro
    public static final int BACKGROUND_DARK = R.drawable.fondo_negro_rayas_diagonales;


    //BACKGROUND SCREEN LIGHT NAVIGATION

    //Declaración de variable estática para el cambio de fondo en modo claro en navigation view
    public static final int BACKGROUND_LIGHT_NAV = R.color.blancoPorDefecto;


    //BACKGROUND SCREEN DARK NAVIGATION

    //Declaración de variable estática para el cambio de fondo en modo oscuro en navigation view
    public static final int BACKGROUND_DARK_NAV = R.color.backgroundItemOp;


    //BACKGROUND DARK SNACKBAR

    //Declaración de variable estática para elcambio de fondo en modo oscuro en snackbar
    public static final int BACKGROUND_DARK_SNACKBAR = R.color.backgroundItemOp;


    //VARIABLES FOR CHANGING THE FONT SIZE OF THE APP

    //Declaración de variable estática para el cambio de tamaño de la fuente en todos
    //los componentes de la app menos los titulos de la pantalla.
    public static final int NORMAL_SIZE = 14;
    public static final int LARGE_SIZE = 16;
    public static final int EXTRA_LARGE_SIZE = 18;

    //Declaración de variable estática para el cambio de tamaño de la fuente en todos
    //los titulos de las pantallas de la app.
    public static final int TITLE_NORMAL_SIZE = 18;
    public static final int TITLE_LARGE_SIZE = 20;
    public static final int TITLE_EXTRA_LARGE_SIZE = 22;

    //Declaración de variable estática para el cambio de tamaño de la fuente
    //del titulo de la pantalla LoginActivity.
    public static final int TITLE_LOGIN_NORMAL_SIZE = 24;
    public static final int TITLE_LOGIN_LARGE_SIZE = 26;
    public static final int TITLE_EXTRA_LOGIN_LARGE_SIZE = 28;


    //VARIABLES FOR SHARED PREFERENCES

    //Declaración de variable estática para SharedPreferences.
    public static String PREFERENCIAS_TEMA = "preferenciasTema";
    public static final String SIZE_FONT = "size_font";
    public static final String MODO_DARK = "modoDark";
    public static final String THEME_ID = "themeID";
    public static final String CHANGE_COLOR_APP = "cambiar_color_app";
    public static final String CHANGE_FONT = "cambio_de_fuente";
    public static final String VISIBILITY_WAVES = "ondas_ocultadas";


    //WEB DOWNLOAD ADDRESS

    //Declaración de variable estática para la dirección web de la página
    //de descarga de las nuevas versones de la app.
    public static final String WEB_DOWNLOAD = "http://45.76.81.254/spica";


    //FILE SHARED_PREFERENCES

    //Declaración de variable estática para la ruta de las SharedPreferences en dispositivo.
    @SuppressLint("SdCardPath")
    public static final String FILE_THEME_PREFERENCES = "/data/data/com.miguel_lm.app_barcode/shared_prefs/preferenciasTema.xml";


    //THEMES APP

    //Declaración de variable estática para el tema azul oscuro.
    public static final int THEME_APP_BARCODE_DARK_BLUE = R.style.Theme_App_Barcode_DARK_BLUE;

    //Declaración de variable estática para el tema rojo oscuro.
    public static final int THEME_APP_BARCODE_DARK_RED = R.style.Theme_App_Barcode_DARK_RED;

    //Declaración de variable estática para el tema azul claro.
    public static final int THEME_APP_BARCODE_BLUE = R.style.Theme_App_Barcode_Azul;

    //Declaración de variable estática para el tema rojo claro.
    public static final int THEME_APP_BARCODE_RED = R.style.Theme_App_Barcode;

    //Declaración de variables estáticas para el texto a mostrar en switch al cambiar de color
    public static final int BLUE = R.string.color_azul;
    public static final int RED = R.string.color_rojo;


    //COLORS APP//

    //Declaración de variable estática para el color azul.
    public static final int COLOR_BLUE = R.color.azul_prueba;

    //Declaración de variable estática para un color azul un poco más claro.
    public static final int COLOR_BLUE_BASIC = R.color.azul;

    //Declaración de variable estática para el color rojo.
    public static final int COLOR_RED = R.color.rojo2;

    //Declaración de variable estática para el color rojo oscuro.
    public static final int COLOR_RED_DARK = R.color.rojo_oscuro;

    //Declaración de variable estática para el color rojo claro (color hint).
    public static final int COLOR_RED_LIGHT = R.color.rojo_claro;

    //Declaración de variable estática para el color azul claro (color hint).
    public static final int COLOR_BLUE_LIGHT = R.color.azul_claro;

    //Declaración de variable estática para el color blanco.
    public static final int COLOR_WHITE = R.color.white;

    //Declaración de variable estática para el color gris claro para el background del CardView de CustomActivity.
    public static final int COLOR_GRAY_LIGHT = R.color.gris_claro;

    //Declaración de variable estática para el color naranja oscuro.
    public static String COLOR_ORANGE_DARK = "#D66D01";

    //Declaración de variable estática para el color verde oscuro.
    public static String COLOR_GREEN_DARK = "#FF02A545";

    //Declaración de variable estática para el color que se le va asignar al texto de la notificación.
    public static String COLOR_NOTIFICATION = "#FF30343A";

    //Declaración de variable estática para el color mostaza del Radius de la pantalla del login.
    public static final int COLOR_RADIUS_LOGIN = R.color.colorTextoRadiusLogin;

    //Declaración de variable estática para el color que se le va asignar al texto de las fechas
    //cuando ya han sido confirmadas.
    public static String COLOR_GREEN = "#00C853";

    //Declaración de variable estática para el color que se le va asignar al texto de las fechas
    //cuando están pendientes de confirmar.
    public static String COLOR_ORANGE = "#F57C00";

    //Declaración de variable estática para el color que se le va asignar al texto de las fechas
    //cuando recién se han registrado y aún no se han ningún cambio en ellas.
    public static String COLOR_GRAY = "#FF939292";

    //Declaración de variable estática para el color granate.
    public static String COLOR_GARNET = "#650000";

    //Declaración de variable estática para el color azul oscuro.
    public static String COLOR_BLUE_DARK = "#1565c0";


    //STYLES NAV ABOUT TEXT SIZE

    //Declaración de variable estática para el estilo del NavigationView con el tamaño de fuente
    //normal aplicado através del textApparence().
    public static final int NAV_NORMAL_SIZE = R.style.NavigationNormalSize;

    //Declaración de variable estática para el estilo del NavigationView con el tamaño de fuente
    //grande aplicado através del textApparence().
    public static final int NAV_LARGE_SIZE = R.style.NavigationLargeSize;

    //Declaración de variable estática para el estilo del NavigationView con el tamaño de fuente
    //extra grande aplicado através del textApparence().
    public static final int NAV_EXTRA_LARGE_SIZE = R.style.NavigationExtraLargeSize;


    //TEXT SWITCH WAVES

    //Declaración de variables estáticas para el texto a mostrar en switch para ocultar o mostrar ondas.
    public static final int NO_WAVES = R.string.sin_ondas_toolbar;
    public static final int WAVES = R.string.ondas_toolbar;


    //SPLASH BACKGROUND SCREEN

    //Declaración de variable estática para el fondo rojo en SplashActivity.
    public static final int SCREEN_SPLASH_RED = R.drawable.fondo_granate_4k;

    //Declaración de variable estática para el fondo azul en SplashActivity.
    public static final int SCREEN_SPLASH_BLUE = R.drawable.fondo_azuldark;


    //BACKGROUND_HEADER_NAVIGATIONVIEW

    //Declaración de variable estática para el fondo rojo en el encabezado del NavigationView.
    public static final int BACKGROUND_HEADER_NAV_RED =  R.drawable.fondo_4k_rojo_estructura;


    //TEXT FOR THE TEXTVIEWS IN FRAGMENT_CONTENEDOR

    //Declaración de variable estática para el título de la lista de proyectos en FragmentContenedor.
    public static String PROJECT_HISTORY = "Historial Proyectos";

    //Declaración de variable estática para el título de la pantalla principal en FragmentContenedor.
    public static String NAME_APP = "Barcode Manager";

    //Declaración de variable estática para el título de la de la lista de reservas en FragmentContenedor.
    public static String BOOKINGS = "Reservas ";


    //ACCOMPANYING TEXT ON THE DATES OF THE RESERVATION CARDS

    //Declaración de variable estática para el título que va acompañando a las fechas que faltan por confirmar.
    public static String PENDING = "PENDIENTE";

    //Declaración de variable estática para el título que va acompañando a las fechas que se acaban de registrar
    //y todavía no se ha hecho ningún cambio.
    public static String NO_CHANGES = "SIN CAMBIOS";


    //TITLES FOR RESERVA_ACTIVITY SCREENS

    //Declaración de variable estática para el titulo de la pantalla de modificar reservas en ReservaActivity.
    public static String TITLE_BOOKING_EDIT = "Editar Reserva";

    //Declaración de variable estática para el titulo de la pantalla de nueva reserva en ReservaActivity.
    public static String TITLE_BOOKING_NEW = "Nueva Reserva";



}
