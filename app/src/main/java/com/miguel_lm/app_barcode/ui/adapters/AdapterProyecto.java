package com.miguel_lm.app_barcode.ui.adapters;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TYPE_ITEM;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.TYPE_PROGRESS;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.almacen.api.client.model.DetalleProyectoDto;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterProyecto extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final List<DetalleProyectoDto> listaProyectos;
    final List<DetalleProyectoDto> listaProyectosOriginales;
    final OnProyectoItemClickListener listener;

    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    public interface OnProyectoItemClickListener {
        void onItemClick(DetalleProyectoDto proyecto);
    }

    //Couctor de la clase.
    public AdapterProyecto(OnProyectoItemClickListener listener) {
        this.listaProyectos = new ArrayList<>();
        this.listaProyectosOriginales = new ArrayList<>();

        this.listener = listener;
        listaProyectosOriginales.addAll(listaProyectos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_PROGRESS) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_footer, parent, false);
            return new FooterVH(v);
        }
        else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proyecto, parent, false);
            return new AdapterProyecto.ProyectoViewHolder(v);
        }
    }

    //Método para cargar los datos en los item del RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DetalleProyectoDto pro = listaProyectos.get(position);
        if(pro == null) return;

        ProyectoViewHolder proyectoViewHolder = (ProyectoViewHolder) holder;
        proyectoViewHolder.nomCliente.setText(pro.getCliente());
        seleccionFuente.cambioDeFuenteEnLosRecyclerView(holder.itemView.getContext(), proyectoViewHolder, null);
        seleccionSize.cambioDeTextSizeEnLosRecyclerView(holder.itemView.getContext(), proyectoViewHolder, null);
    }

    //Método para mostar el item correspondiente a la posición.
    @Override
    public int getItemViewType(int position) {
        DetalleProyectoDto item = listaProyectos.get(position);
        if (item != null) return TYPE_ITEM;
        else return TYPE_PROGRESS;
    }

    //Método para mostar el item correspondiente a la posición.
    @Override
    public int getItemCount() {
        return listaProyectos.size();
    }

    //Método para filtrar en el buscador.
    @SuppressLint({"ObsoleteSdkInt", "NotifyDataSetChanged"})
    public void filter(String strSearch){
        listaProyectos.clear();
        if (strSearch.trim().isEmpty()) {
            listaProyectos.addAll(listaProyectosOriginales);
        } else {
            for (DetalleProyectoDto op : listaProyectosOriginales) {
                if (Objects.requireNonNull(op.getCliente()).toLowerCase().contains(strSearch.toLowerCase())) {
                    listaProyectos.add(op);
                }
            }
        }
        notifyDataSetChanged();
    }

    //Método para añadir todas los proyectos.
    public void addAll(List<DetalleProyectoDto> operacionesProyecto) {
        this.listaProyectosOriginales.addAll(operacionesProyecto);
        int size = this.listaProyectos.size();
        this.listaProyectos.addAll(operacionesProyecto);
        notifyItemRangeInserted(size, operacionesProyecto.size());
    }

    //Método para habilitar progressBar al cargar nueva página en la lista.
    public void habilitarBarraProgreso() {
        this.listaProyectos.add(null);
        notifyItemInserted(this.listaProyectos.size() - 1);
    }

    //Método para deshabilitar progressBar en la lista.
    public void deshabilitarBarraProgreso() {
        if(this.listaProyectos.isEmpty()) return;

        int idx = ultimoIndice();

        if(this.listaProyectos.get(idx) == null)
            this.listaProyectos.remove(idx);

        notifyItemRemoved(idx);
    }

    //Método para gestionar el último indice de la lista.
    private int ultimoIndice() {
        return this.listaProyectos.size() - 1;
    }

    public static class FooterVH extends RecyclerView.ViewHolder {

        public FooterVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ProyectoViewHolder extends RecyclerView.ViewHolder {

        public final TextView nomCliente;

        public ProyectoViewHolder(@NonNull View itemView) {
            super(itemView);

            nomCliente = itemView.findViewById(R.id.nomClienteTv);
            itemView.setOnClickListener(view -> listener.onItemClick(listaProyectos.get(getAdapterPosition())));
            nomCliente.setSelected(true);
        }
    }
}
