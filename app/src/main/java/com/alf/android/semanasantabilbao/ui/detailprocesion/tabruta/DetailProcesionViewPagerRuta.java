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

    public DetailProcesionViewPagerRuta(Context context, View view) {
        super(context);
        ((App) context).getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    public void showDetailProcesionInformationRuta(Procesion procesion) {

        //idCofradia = cofradia.getId_cofradia();

        setSpinnerAndLoadingText(false);
        initRecyclerViewRuta();

        printIndicaciones(procesion);
    }

    private void initRecyclerViewRuta() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        detailRutaAdapter.setRutaClickListener(this);
        mRecyclerView.setAdapter(detailRutaAdapter);
    }

    //@Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

/*    @Override
    public void printIndicaciones(ObservableArrayList<Ruta> mIndicaciones) {
        Log.d(LOG_TAG, "Number of Indicaciones in the recyclerView: "+mIndicaciones.size());
        //((DetailProcesionPasoAdapter) mRecyclerView.getAdapter()).addIndicacion(mPasos);
    }*/

    public void printIndicaciones(Procesion procesion) {

        List<Ruta> rutaList = procesion.getRuta();

        Log.d(LOG_TAG, "Number of Indicaciones in the recyclerView: "+rutaList.size());
        //for (int i=0; i< rutaList.size(); i++){

            ((DetailProcesionRutaAdapter) mRecyclerView.getAdapter()).addIndicaciones(rutaList);
            mRecyclerView.getAdapter().notifyDataSetChanged();

        //}
    }

    //@Override
    public void showErrorGettingIndicaciones(ObservableField<String> mensajeError) {

    }

/*    public void detachViewUnsuscribeSuscriptionRuta(){
        rutaPresenter.detachRutaView();
        rutaPresenter.unsuscribeRutaSuscription();
    }*/


    @Override
    public void onClickRuta(int position) {
        Toast.makeText(getContext(), "CLICK - RUTA", Toast.LENGTH_SHORT).show();
    }
}
