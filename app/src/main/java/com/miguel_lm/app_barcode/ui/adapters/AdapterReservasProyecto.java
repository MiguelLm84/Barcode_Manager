package com.miguel_lm.app_barcode.ui.adapters;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GRAY;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_GREEN;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.COLOR_ORANGE;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.NO_CHANGES;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.PENDING;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TYPE_FOOTER;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TYPE_OPERACION;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.almacen.api.client.model.DetalleProductoDto;
import com.almacen.api.client.model.DetalleReservaDto;
import com.almacen.api.client.model.ReservaProyectoDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;
import com.miguel_lm.app_barcode.ui.interfaces.OnReservaProyectoClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AdapterReservasProyecto extends RecyclerView.Adapter<AdapterReservasProyecto.OperacionCommonViewHolder> {

    private final List<ReservaProyectoDto> reservas;
    private final List<ReservaProyectoDto> reservasOriginal;
    private final OnReservaProyectoClickListener onReservaProyectoClickListener;

    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();


    //Constructor de la clase.
    public AdapterReservasProyecto(OnReservaProyectoClickListener onReservaProyectoClickListener) {
        this.reservas = new ArrayList<>();
        this.onReservaProyectoClickListener = onReservaProyectoClickListener;
        this.reservasOriginal = new ArrayList<>(reservas);
    }

    //Método para mostar el item correspondiente a la posición.
    @Override
    public int getItemViewType(int position) {
        ReservaProyectoDto item = reservas.get(position);
        if (item != null) {
            return TYPE_OPERACION;
        } else {
            return TYPE_FOOTER;
        }
    }

    //Método para crear e inflar lista.
    @NonNull
    @Override
    public OperacionCommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_OPERACION) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva, parent, false);
            return new OperacionViewHolder(v, onReservaProyectoClickListener);

        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_footer, parent, false);
            return new FooterViewHolder(v);
        }
    }

    //Método para cargar los datos en los item del RecyclerView.
    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull OperacionCommonViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) return;

        OperacionViewHolder opViewHolder = (OperacionViewHolder) holder;

        ReservaProyectoDto op = reservas.get(position);
        DetalleProductoDto producto = op.getProducto();
        DetalleReservaDto reserva = op.getReserva();

        opViewHolder.tv_nomProd.setText(Objects.requireNonNull(producto).getNombreProducto());
        opViewHolder.fechasReserva.setText(String.format("%s — %s", Objects.requireNonNull(reserva).getFechaSalidaPrevista(), reserva.getFechaEntradaPrevista()));

        if(reserva.getFechaSalida() == null && reserva.getFechaEntrada() == null){
            sinCambios(opViewHolder);
        } else {
            cambiosPendientes(opViewHolder, reserva);
        }
        seleccionFuente.cambioDeFuenteEnLosRecyclerView(holder.itemView.getContext(), null, opViewHolder);
        seleccionSize.cambioDeTextSizeEnLosRecyclerView(holder.itemView.getContext(), null, opViewHolder);
    }

    //Método para mostrar en los TextView 'tv_fechaEntrada' y 'tv_fecha_salida' el texto 'PENDIENTE'
    //en color naranja para cuando haya cambios pendientes de realizar en las fechas..
    @SuppressLint("SetTextI18n")
    private void cambiosPendientes(OperacionViewHolder opViewHolder, DetalleReservaDto reserva) {

        if (reserva.getFechaEntrada() == null) {
            opViewHolder.tv_fechaEntrada.setText(PENDING);
            opViewHolder.tv_fechaEntrada.setTextColor(Color.parseColor(COLOR_ORANGE));
        } else {
            opViewHolder.tv_fechaEntrada.setText(String.format("%s", reserva.getFechaEntrada()));
            opViewHolder.tv_fechaEntrada.setTextColor(Color.parseColor(COLOR_GREEN));
        }

        if (reserva.getFechaSalida() == null) {
            opViewHolder.tv_fechaSalida.setText(PENDING);
            opViewHolder.tv_fechaSalida.setTextColor(Color.parseColor(COLOR_ORANGE));
        } else{
            opViewHolder.tv_fechaSalida.setText(String.format("%s", reserva.getFechaSalida()));
            opViewHolder.tv_fechaSalida.setTextColor(Color.parseColor(COLOR_GREEN));
        }
    }

    //Método para mostrar en los TextView 'tv_fechaEntrada' y 'tv_fecha_salida' el texto 'SIN CAMBIOS'
    //en color gris cuando no se hayan producido ningún cambio en las fechas desde su registro.
    @SuppressLint("SetTextI18n")
    private void sinCambios(OperacionViewHolder opViewHolder) {

        opViewHolder.tv_fechaEntrada.setText(NO_CHANGES);
        opViewHolder.tv_fechaEntrada.setTextColor(Color.parseColor(COLOR_GRAY));

        opViewHolder.tv_fechaSalida.setText(NO_CHANGES);
        opViewHolder.tv_fechaSalida.setTextColor(Color.parseColor(COLOR_GRAY));

    }

    //Método que contiene la lista total de items a mostrar.
    @Override
    public int getItemCount() {
        return reservas.size();
    }

    //Método para filtrar en el buscador.
    @SuppressLint("NotifyDataSetChanged")
    public void filter(String strSearch) {
        reservas.clear();
        if (strSearch.trim().isEmpty()) {
            reservas.addAll(reservasOriginal);
        } else {
            for (ReservaProyectoDto op : reservasOriginal) {
                if (Objects.requireNonNull(Objects.requireNonNull(op.getProducto())
                        .getNombreProducto()).toLowerCase().contains(strSearch.toLowerCase())) {
                    reservas.add(op);
                }
            }
        }
        notifyDataSetChanged();
    }

    //Método para añadir las reservas a las listas .
    @SuppressLint("NotifyDataSetChanged")
    public void add(ReservaProyectoDto op) {
        this.reservas.add(op);
        this.reservasOriginal.add(op);
        notifyDataSetChanged();
    }

    //Método para eliminar una reserva de las listas.
    @SuppressLint("NotifyDataSetChanged")
    public void remove(ReservaProyectoDto op) {
        this.reservas.remove(op);
        this.reservasOriginal.remove(op);
        notifyDataSetChanged();
    }

    public List<ReservaProyectoDto> items() {
        return new ArrayList<>(reservas);
    }

    //Método para añadir todas las reservas.
    public void addAll(List<ReservaProyectoDto> operacionesProyecto) {
        this.reservasOriginal.addAll(operacionesProyecto);
        int size = this.reservas.size();
        this.reservas.addAll(operacionesProyecto);
        notifyItemRangeInserted(size, operacionesProyecto.size());
    }

    //Método para prdenar las reservas según la fecha de reserva.
    @SuppressLint("NotifyDataSetChanged")
    public void reverseOrder(Comparator<ReservaProyectoDto> operacionComparator) {
        this.reservas.sort(Collections.reverseOrder(operacionComparator));
        notifyDataSetChanged();
    }

    //Método para actualizar las listas.
    public void replace(List<ReservaProyectoDto> operacionesProyecto) {
        int size = reservas.size();
        this.reservas.clear();
        this.reservasOriginal.clear();
        this.addAll(operacionesProyecto);
        notifyItemRangeChanged(0, size);
    }

    //Método para habilitar progressBar al cargar nueva página en la lista.
    public void habilitarBarraProgreso() {
        this.reservas.add(null);
        notifyItemInserted(this.reservas.size() - 1);
    }

    //Método para deshabilitar progressBar en la lista.
    public void deshabilitarBarraProgreso() {
        if(this.reservas.isEmpty()) return;

        int idx = ultimoIndice();

        if(this.reservas.get(idx) == null)
            this.reservas.remove(idx);

        notifyItemRemoved(idx);
    }

    //Método para gestionar el último indice de la lista.
    private int ultimoIndice() {
        return this.reservas.size() - 1;
    }

    //Método para comprobar sí la lista de items está vacía.
    public boolean isEmpty() {
        return items().isEmpty();
    }

    public void replace(DetalleReservaDto dto) {
        int idx = buscarEnReservas(dto.getId(), reservas);
        int idxOrig = buscarEnReservas(dto.getId(), reservasOriginal);

        if(idx < reservas.size()) {
            ReservaProyectoDto item = reservas.get(idx);
            item.setReserva(dto);
            reservas.set(idx, item);
            notifyItemChanged(idx);
        }
        if(idxOrig < reservasOriginal.size()) {
            ReservaProyectoDto item = reservasOriginal.get(idx);
            item.setReserva(dto);
            reservasOriginal.set(idx, item);
        }
    }

    private int buscarEnReservas(long idReserva, List<ReservaProyectoDto> reservas) {
        int idx = 0;
        while(idx < reservas.size() && !Objects.equals(Objects.requireNonNull(reservas.get(idx).getReserva()).getId(), idReserva)) {
            idx++;
        }
        return idx;
    }

    public void remove(DetalleReservaDto dto) {
        int idx = buscarEnReservas(dto.getId(), reservas);
        int idxOrig = buscarEnReservas(dto.getId(), reservasOriginal);

        if(idx < reservas.size()) {
            ReservaProyectoDto item = reservas.get(idx);
            reservas.remove(item);
            notifyItemRemoved(idx);
        }
        if(idxOrig < reservasOriginal.size()) {
            ReservaProyectoDto item = reservasOriginal.get(idx);
            reservasOriginal.remove(item);
        }

    }

    public abstract static class OperacionCommonViewHolder extends RecyclerView.ViewHolder {

        public OperacionCommonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class OperacionViewHolder extends OperacionCommonViewHolder {

        public final TextView tv_entrada, tv_salida,tv_nomProd, fechasReserva, tv_fechaEntrada, tv_fechaSalida;
        ImageButton btn_modificar_op, btn_eliminar_op;
        CardView cardViewOp;

        public OperacionViewHolder(@NonNull View itemView, OnReservaProyectoClickListener onReservaProyectoClickListener) {
            super(itemView);

            tv_salida = itemView.findViewById(R.id.tv_salida);
            tv_entrada = itemView.findViewById(R.id.tv_entrada);
            tv_nomProd = itemView.findViewById(R.id.tv_nom_producto);
            fechasReserva = itemView.findViewById(R.id.seleccionarFechasTv);
            tv_fechaEntrada = itemView.findViewById(R.id.tv_fecha_entrada);
            tv_fechaSalida = itemView.findViewById(R.id.tv_fecha_salida);
            cardViewOp = itemView.findViewById(R.id.cardViewOp);
            btn_modificar_op = itemView.findViewById(R.id.btn_modificar);
            btn_eliminar_op = itemView.findViewById(R.id.btn_eliminar);
            tv_nomProd.setSelected(true);

            cardViewOp.setOnClickListener(view -> onReservaProyectoClickListener
                    .onModificarClick(reservas.get(getAdapterPosition())));

            btn_modificar_op.setOnClickListener(view -> onReservaProyectoClickListener
                    .onModificarClick(reservas.get(getAdapterPosition())));

            btn_eliminar_op.setOnClickListener(v -> onReservaProyectoClickListener
                    .onEliminarClick(reservas.get(getAdapterPosition())));
        }
    }

    public static class FooterViewHolder extends OperacionCommonViewHolder {

        ProgressBar loading;

        public FooterViewHolder(View itemView) {
            super(itemView);
            this.loading = itemView.findViewById(R.id.loadingPb);
        }
    }
}
