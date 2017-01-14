package com.alf.android.semanasantabilbao.ui.detailprocesion.tabruta;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.data.entities.Ruta;
import com.alf.android.semanasantabilbao.ui.detailprocesion.adapter.DetailProcesionRutaAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 27/10/2016.
 */

public class DetailProcesionViewPagerRuta extends View implements DetailProcesionRutaAdapter.RutaClickListener{

    private static String LOG_TAG = DetailProcesionViewPagerRuta.class.getSimpleName();
    private String idCofradia;

    @Inject DetailProcesionRutaAdapter detailRutaAdapter;

    @BindView(R.id.ruta_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.ruta_detail_loading_text) TextView loadingText;
    @BindView(R.id.ruta_detail_recycler_view) RecyclerView mRecyclerView;

    @BindString(R.string.indicaciones_error) String indicacionesError;

    public DetailProcesionViewPagerRuta(Context context, View view) {
        super(context);
        ((App) context).getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    public void showDetailProcesionInformationRuta(Procesion procesion) {

        setSpinnerAndLoadingText(true);
        initRecyclerViewRuta();

        printIndicaciones(procesion);
    }

    private void initRecyclerViewRuta() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        detailRutaAdapter.setRutaClickListener(this);
        mRecyclerView.setAdapter(detailRutaAdapter);
    }

    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }


    public void printIndicaciones(Procesion procesion) {

        List<Ruta> rutaList = procesion.getRuta();

        if (rutaList != null) {
            Log.d(LOG_TAG, "Number of Indicaciones in the recyclerView: "+rutaList.size());
            ((DetailProcesionRutaAdapter) mRecyclerView.getAdapter()).addIndicaciones(rutaList);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        } else {
            showErrorGettingIndicaciones(indicacionesError);
        }

        setSpinnerAndLoadingText(false);
    }

    public void showErrorGettingIndicaciones(String mensajeError) {
        Toast.makeText(getContext(), mensajeError, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickRuta(int position) {
        //Toast.makeText(getContext(), "CLICK - "+LOG_TAG, Toast.LENGTH_SHORT).show();
    }
}
