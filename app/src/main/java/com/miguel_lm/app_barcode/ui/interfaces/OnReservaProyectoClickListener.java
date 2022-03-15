package com.miguel_lm.app_barcode.ui.interfaces;

import com.almacen.api.client.model.ReservaProyectoDto;

public interface OnReservaProyectoClickListener {
    void onEliminarClick(ReservaProyectoDto reserva);
    void onModificarClick(ReservaProyectoDto reserva);
}
