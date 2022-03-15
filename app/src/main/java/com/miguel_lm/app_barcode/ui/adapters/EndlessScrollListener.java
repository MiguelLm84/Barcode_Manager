package com.miguel_lm.app_barcode.ui.adapters;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.MIN_POSITION_TO_END;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class EndlessScrollListener  extends RecyclerView.OnScrollListener {

    private final ScrollerClient scrollerClient;
    private boolean loadMore;

    public interface ScrollerClient {
        void loadData();
    }

    //Constructor de la clase.
    public EndlessScrollListener(ScrollerClient scrollerClient) {
        this.scrollerClient = scrollerClient;
        this.loadMore = true;
    }

    //Método para crear scroll con posición mínima e ir cargando más paginas al superar el límite marcado.
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (loadMore) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            if (Objects.requireNonNull(layoutManager).findLastCompletelyVisibleItemPosition()
                    >= layoutManager.getItemCount() - MIN_POSITION_TO_END) {
                loadMore = false;
                new Handler(Looper.getMainLooper()).post(scrollerClient::loadData);
            }
        }
    }

    //Método para cargar más paginas en la lista correspondiente.
    public void loadMore() {
        loadMore = true;
    }
}
