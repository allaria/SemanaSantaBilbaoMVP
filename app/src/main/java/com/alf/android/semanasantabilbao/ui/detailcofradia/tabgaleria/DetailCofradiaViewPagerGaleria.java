package com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria;

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

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.ImagenGaleria;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.GaleriaAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto on 27/10/2016.
 */

public class DetailCofradiaViewPagerGaleria extends View implements DetailCofradiaViewPagerGaleriaContract.DetailGaleriaView,
        GaleriaAdapter.ImagenGaleriaClickListener{

    private static String LOG_TAG = DetailCofradiaViewPagerGaleria.class.getSimpleName();
    private GaleriaAdapter galeriaAdapter;
    private DetailCofradiaViewPagerGaleriaContract.DetailGaleriaPresenter galeriaPresenter;
    private String idCofradia;

    @BindView(R.id.gallery_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.gallery_loading_text) TextView loadingText;
    @BindView(R.id.gallery_recycler_view) RecyclerView mRecyclerView;

    public DetailCofradiaViewPagerGaleria(Context context, View view) {
        super(context);
        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformationGaleria(Cofradia cofradia) {

        idCofradia = cofradia.getId_cofradia();

        setSpinnerAndLoadingText(false);
        initRecyclerViewPasos();

        galeriaPresenter = new DetailCofradiaViewPagerGaleriaPresenter();
        galeriaPresenter.attachGaleriaView(this);
        galeriaPresenter.initPresenter(idCofradia);
    }

    private void initRecyclerViewPasos() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        //mRecyclerView.setRecycledViewPool(recycledViewPool);

        galeriaAdapter = new GaleriaAdapter(this);
        galeriaAdapter.setImagenGaleriaClickListener(this);
        mRecyclerView.setAdapter(galeriaAdapter);
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printImagenGaleria(ObservableArrayList<ImagenGaleria> mImagenesGaleria) {
        Log.d(LOG_TAG, "Number of Pasos in the recyclerView:"+mImagenesGaleria.size());
        ((GaleriaAdapter) mRecyclerView.getAdapter()).addImagenGaleria(mImagenesGaleria);
    }

    @Override
    public void showErrorGettingImagenesGaleria(ObservableField<String> mensajeError) {

    }

    public void detachViewUnsuscribeSuscriptionImagenesGaleria(){
        galeriaPresenter.detachGaleriaView();
        galeriaPresenter.unsuscribeGaleriaSuscription();
    }


    @Override
    public void onClickImagenGaleria(int position) {
        Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }
}
