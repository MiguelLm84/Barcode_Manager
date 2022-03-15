package com.miguel_lm.app_barcode.ui.fragments;

import static com.miguel_lm.app_barcode.model.common.constatnts.StaticVariablesApp.WEB_DOWNLOAD;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.miguel_lm.app_barcode.R;


public class FragmentWebDescarga extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_DOWNLOAD)));
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        requireActivity().getSupportFragmentManager().popBackStack();

        return inflater.inflate(R.layout.fragment_web_descarga, container, false);
    }
}