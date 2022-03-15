package com.miguel_lm.app_barcode.model.singletons.sesion;

import com.almacen.api.client.model.DetalleUsuarioDto;

public class Sesion {

    private static Sesion instance = null;

    private DetalleUsuarioDto usuarioDto;

    private Sesion() { }

    //Método para crear una instancia de la sesión.
    public static Sesion getInstance() {
        if(instance == null) instance = new Sesion();
        return instance;
    }

    //Método para modificar un Usuario.
    public void setUsuario(DetalleUsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    //Método para mostrar un Usuario.
    public DetalleUsuarioDto getUsuario() {
        return usuarioDto;
    }
}
