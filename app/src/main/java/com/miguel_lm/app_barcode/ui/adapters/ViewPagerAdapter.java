package com.miguel_lm.app_barcode.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.miguel_lm.app_barcode.ui.fragments.FragmentContenedor;
import com.miguel_lm.app_barcode.ui.fragments.FragmentScanner;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //Método para crear el fragment correspondiente.
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new FragmentScanner();
        }

        return new FragmentContenedor();
    }

    //Método que contiene el número de fragments contenidos en el viewPager y que va a gestionar.
    @Override
    public int getItemCount() {
        return 2;
    }
}
