package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaContract;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerPasosContract;
import com.alf.android.semanasantabilbao.ui.cofradias.adapter.CofradiaAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.PasoAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto on 27/10/2016.
 */

public class DetailCofradiaViewPagerPasos extends View implements DetailCofradiaViewPagerPasosContract.DetailCofradiaView,
        PasoAdapter.PasoClickListener{

    private static String LOG_TAG = DetailCofradiaViewPagerPasos.class.getSimpleName();
    private PasoAdapter pasoAdapter;
    private DetailCofradiaViewPagerPasosContract.DetailCofradiaPresenter pasoPresenter;

    @BindView(R.id.paso_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.paso_loading_text) TextView loadingText;
    @BindView(R.id.paso_recycler_view) RecyclerView mRecyclerView;

    public DetailCofradiaViewPagerPasos(Context context) {
        super(context);
    }

    public DetailCofradiaViewPagerPasos(Context context, View view) {
        super(context);

        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformationPasos(Cofradia cofradia) {

        setSpinnerAndLoadingText(false);
        initRecyclerViewPasos();

        pasoPresenter = new DetailCofradiaViewPagerPasosPresenter();

        pasoPresenter.attachPasoView(this);
        pasoPresenter.setLoading(false);
        pasoPresenter.initPresenter();
    }

    private void initRecyclerViewPasos() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        //mRecyclerView.setRecycledViewPool(recycledViewPool);

        pasoAdapter = new PasoAdapter(this);
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
        Log.d(LOG_TAG, "printPasos: "+mPasos.size()+" elementos");
        ((PasoAdapter) mRecyclerView.getAdapter()).addPaso(mPasos);
    }

    @Override
    public void showErrorGettingPasos(ObservableField<String> mensajeError) {

    }

    public void liberarVistaPaso(){
        //pasoPresenter.detachPasoView();
    }


    @Override
    public void onClickPaso(int position) {
        Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }
}
