package com.miguel_lm.app_barcode.ui.adapters;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.CARGA;
import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.ITEM;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.miguel_lm.app_barcode.R;
import com.miguel_lm.app_barcode.model.management.fonts_manager.SeleccionFuente;
import com.miguel_lm.app_barcode.model.management.size_fonts.SeleccionSize;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AdapterSelector<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<T> items;
    private final OnItemSelectedListener<T> selectedListener;
    private final Function<T, String> getItemTitle;
    private boolean cargando;

    SeleccionFuente seleccionFuente = new SeleccionFuente();
    SeleccionSize seleccionSize = new SeleccionSize();

    public interface OnItemSelectedListener<T> {
        void onItemSelected(T item);
    }

    //Constructor de la clase.
    public AdapterSelector(OnItemSelectedListener<T> selectedListener, Function<T, String> getItemTitle) {
        this.items = new ArrayList<>();
        this.selectedListener = selectedListener;
        this.getItemTitle = getItemTitle;
    }

    //Método para crear e inflar lista.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == CARGA)
            return new LoadingProyectosVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_footer, parent, false));

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_text_tipo_op, parent, false);
        return new AdapterSelector.ItemVH(v);
    }

    //Método para mostar el item correspondiente a la posición.
    @Override
    public int getItemViewType(int position) {
        T item = this.items.get(position);
        if(item == null) return CARGA;
        else return ITEM;
    }

    //Método para cargar los datos en los item del RecyclerView.
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        T item = items.get(position);
        if(item != null) {
            ItemVH itemVH = (ItemVH) holder;
            itemVH.title.setText(getItemTitle.apply(item));
            seleccionFuente.gestionarUsuariosDialogFragment(holder.itemView.getContext(), itemVH);
            seleccionSize.gestionarTextSizeUsuariosDialogFragment(holder.itemView.getContext(), itemVH);
        }
    }

    //Método que contiene la lista total de items a mostrar.
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Método para añadir todos los elementos de la lista de itrems.
    @SuppressLint("NotifyDataSetChanged")
    public void addAll(List<T> items) {
        if(cargando)
            this.items.remove(this.items.size() - 1);

        this.cargando = false;
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    //Método para habilitar ProgressBar al cargar nueva página (Paginación).
    public void habilitarBarraProgreso() {
        this.cargando = true;
        this.items.add(null);
        notifyItemInserted(this.items.size() - 1);
    }

    //Método para cargar items de proyectos.
    public static class LoadingProyectosVH extends RecyclerView.ViewHolder {
        public LoadingProyectosVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    //Método para mapear y seleccionar los Proyectos de la vista del RecyclerView.
    public class ItemVH extends RecyclerView.ViewHolder {
        final TextView title;

        @SuppressLint("ResourceAsColor")
        public ItemVH(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textViewSpinnerItem);
            itemView.setOnClickListener(view -> selectedListener.onItemSelected(items.get(getAdapterPosition())));
        }
    }
}
