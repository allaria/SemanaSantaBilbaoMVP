package com.alf.android.semanasantabilbao.ui.detailprocesion.tabpasos;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.detailprocesion.adapter.DetailProcesionPasoAdapter;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 27/10/2016.
 */

public class DetailProcesionViewPagerPasos extends View implements DetailProcesionViewPagerPasosContract.DetailPasosView,
        DetailProcesionPasoAdapter.PasoClickListener{

    private static String LOG_TAG = DetailProcesionViewPagerPasos.class.getSimpleName();
    private String idCofradia;
    private ObservableArrayList<String> listaPasosPaths;
    private DetailProcesionViewPagerPasos.DetailProcesionViewPagerPasosClickListener detailProcesionViewPagerPasosClickListener;

    @Inject DetailProcesionViewPagerPasosContract.DetailPasosPresenter pasoPresenter;
    @Inject DetailProcesionPasoAdapter detailPasoAdapter;

    @BindView(R.id.paso_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.paso_detail_loading_text) TextView loadingText;
    @BindView(R.id.paso_detail_recycler_view) RecyclerView mRecyclerView;

    public DetailProcesionViewPagerPasos(Context context, View view) {
        super(context);
        ((App) context).getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    public void showDetailProcesionInformationPasos(Procesion procesion, Cofradia cofradia) {

        idCofradia = cofradia.getId_cofradia();

        setSpinnerAndLoadingText(false);
        initRecyclerViewPasos();

        pasoPresenter.attachPasoView(this);
        pasoPresenter.initPresenter(idCofradia);
    }

    private void initRecyclerViewPasos() {
        mRecyclerView.setHasFixedSize(true);
        if (new GlobalFunctions().getScreenOrientation(getResources().getConfiguration().orientation).equals("Landscrape")) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        }

        detailPasoAdapter.setPasoClickListener(this);
        mRecyclerView.setAdapter(detailPasoAdapter);
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printPasos(ObservableArrayList<Paso> mPasos) {
        Log.d(LOG_TAG, "Number of Pasos in the recyclerView: "+mPasos.size());
        ((DetailProcesionPasoAdapter) mRecyclerView.getAdapter()).addPaso(mPasos);
    }

    @Override
    public void showErrorGettingPasos(ObservableField<String> mensajeError) {

    }

    public void detachViewUnsuscribeSuscriptionPaso(){
        pasoPresenter.detachPasoView();
        pasoPresenter.unsuscribePasosSuscription();
    }

    @Override
    public void setPasosPaths(ObservableArrayList<String> listaPasosPaths) {
        this.listaPasosPaths = listaPasosPaths;
    }

    @Override
    public void onClickPaso(int position) {
        //Toast.makeText(getContext(), "CLICK - "+LOG_TAG, Toast.LENGTH_SHORT).show();
        detailProcesionViewPagerPasosClickListener.onClickDetailProcesionViewPagerPasos(position, listaPasosPaths);
    }

    public interface DetailProcesionViewPagerPasosClickListener {
        void onClickDetailProcesionViewPagerPasos(int position, ObservableArrayList<String> listaPasosPaths);
    }

    public DetailProcesionViewPagerPasos.DetailProcesionViewPagerPasosClickListener getDetailProcesionViewPagerPasosClickListener() {
        return detailProcesionViewPagerPasosClickListener;
    }

    public void setDetailProcesionViewPagerPasosClickListener(DetailProcesionViewPagerPasos.DetailProcesionViewPagerPasosClickListener detailProcesionViewPagerPasosClickListener) {
        this.detailProcesionViewPagerPasosClickListener = detailProcesionViewPagerPasosClickListener;
    }
}
