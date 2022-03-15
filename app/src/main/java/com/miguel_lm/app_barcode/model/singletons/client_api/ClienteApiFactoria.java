package com.miguel_lm.app_barcode.model.singletons.client_api;

import com.almacen.api.client.ApiClient;
import com.miguel_lm.app_barcode.BuildConfig;
import okhttp3.Interceptor;

public class ClienteApiFactoria {

    //Declaración de variables estáticas.
    private static final String NULL_API_KEY = "";
    private static ClienteApiFactoria instance = null;

    public static final String PUERTO = BuildConfig.PORT;
    public static final String IP = BuildConfig.IP;

    private static final String SERVER_URL = String.format("http://%s:%s", IP, PUERTO);

    private final ApiClient apiClient;

    //Método para que la app lea la dirección del servidor y el token de la API para acceder a ella de forma segura.
    private ClienteApiFactoria() {
        this.apiClient = new ApiClient(new String[]{"apiKey"});
        setBaseUrl(SERVER_URL);
        setApiKey(NULL_API_KEY);
    }

    //Instancia del cliente API.
    public static ClienteApiFactoria getInstance() {
        if(instance == null){
            instance = new ClienteApiFactoria();
        }
        return instance;
    }

    //Método para modificar token de acceso seguro a la API.
    public void setApiKey(String apiKey) {
        this.apiClient.setApiKey(apiKey);
    }

    //Método para añadir un 'interceptor' que permite pre-procesar
    //y post-procesar(modificar la request antes de que se envíe al la API) la request.
    public void addInterceptor(Interceptor interceptor) {
        apiClient.getOkBuilder()
                .addInterceptor(interceptor);
    }

    //Método para modificar la dirección por defecto del servidor.
    public void setBaseUrl(String baseUrl) {
        apiClient.getAdapterBuilder()
                .baseUrl(baseUrl);
    }

    //Metodo para crear servicio del cliente API.
    public <T> T createService(Class<T> clazz) {
        return apiClient.createService(clazz);
    }
}
