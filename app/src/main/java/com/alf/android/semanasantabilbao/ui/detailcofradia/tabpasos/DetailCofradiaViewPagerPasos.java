package com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos;

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
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.PasoAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 27/10/2016.
 */

public class DetailCofradiaViewPagerPasos extends View implements DetailCofradiaViewPagerPasosContract.DetailGaleriaView,
        PasoAdapter.PasoClickListener{

    private static String LOG_TAG = DetailCofradiaViewPagerPasos.class.getSimpleName();
    private String idCofradia;

    @Inject DetailCofradiaViewPagerPasosContract.DetailPasosPresenter pasoPresenter;
    @Inject PasoAdapter pasoAdapter;

    @BindView(R.id.paso_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.paso_loading_text) TextView loadingText;
    @BindView(R.id.paso_recycler_view) RecyclerView mRecyclerView;

    public DetailCofradiaViewPagerPasos(Context context, View view) {
        super(context);
        ((App) context).getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformationPasos(Cofradia cofradia) {

        idCofradia = cofradia.getId_cofradia();

        setSpinnerAndLoadingText(false);
        initRecyclerViewPasos();

        pasoPresenter.attachPasoView(this);
        pasoPresenter.initPresenter(idCofradia);
    }

    private void initRecyclerViewPasos() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        pasoAdapter.setPasoClickListener(this);
        mRecyclerView.setAdapter(pasoAdapter);
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printPasos(ObservableArrayList<Paso> mPasos) {
        Log.d(LOG_TAG, "Number of Pasos in the recyclerView: "+mPasos.size());
        ((PasoAdapter) mRecyclerView.getAdapter()).addPaso(mPasos);
    }

    @Override
    public void showErrorGettingPasos(ObservableField<String> mensajeError) {

    }

    public void detachViewUnsuscribeSuscriptionPaso(){
        pasoPresenter.detachPasoView();
        pasoPresenter.unsuscribePasosSuscription();
    }


    @Override
    public void onClickPaso(int position) {
        Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }
}
