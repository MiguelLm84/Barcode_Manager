package com.miguel_lm.app_barcode.model.singletons.error_api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ErroresClienteInterceptor implements Interceptor {

    private final Context ctx;

    //Constructor de la clase.
    public ErroresClienteInterceptor(Context applicationContext) {
        this.ctx = applicationContext;
    }

    //Método para generar feedback al usuario cuando se produzca un error en el cliente de la API.
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = chain.proceed(chain.request());
        if (hayError4XX(response)) {
            response = chain.proceed(chain.request());
            if(!response.isSuccessful()){
                response = chain.proceed(chain.request());
                throw new IOException("Código inesperado " + response);
            }  else {
                Optional.ofNullable(new Gson().fromJson(Objects.requireNonNull(response.body()).charStream(), ErrorApi.class))
                        .filter(it -> !it.mensaje.isEmpty())
                        .ifPresent(error -> new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(ctx,error.mensaje,
                                Toast.LENGTH_SHORT).show()));
            }
        }
        return response;
    }

    //Método para capturar y mostrar feedback al usuario cuando se produzca
    // un error del cliente en el servidor (del 400 al 499).
    private boolean hayError4XX(Response response) {
        return response.code() >= 400 && response.code() <= 499;
    }
}
