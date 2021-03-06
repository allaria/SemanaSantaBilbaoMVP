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

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.GaleriaAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 27/10/2016.
 */

public class DetailCofradiaViewPagerGaleria extends View implements DetailCofradiaViewPagerGaleriaContract.DetailGaleriaView,
        GaleriaAdapter.ImagenGaleriaClickListener{

    private static String LOG_TAG = DetailCofradiaViewPagerGaleria.class.getSimpleName();
    private String idCofradia;
    private ObservableArrayList<String> listaGalleryImagesPaths;
    private DetailCofradiaViewPagerGaleria.DetailCofradiaViewPagerGaleriaClickListener detailCofradiaViewPagerGaleriaClickListener;

    @Inject DetailCofradiaViewPagerGaleriaContract.DetailGaleriaPresenter galeriaPresenter;
    @Inject GaleriaAdapter galeriaAdapter;

    @BindView(R.id.gallery_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.gallery_loading_text) TextView loadingText;
    @BindView(R.id.gallery_recycler_view) RecyclerView mRecyclerView;

    public DetailCofradiaViewPagerGaleria(Context context, View view) {
        super(context);
        ButterKnife.bind(this, view);
        ((App) context).getApplicationComponent().inject(this);
    }

    public void showDetailCofradiaInformationGaleria(Cofradia cofradia) {

        idCofradia = cofradia.getId_cofradia();

        setSpinnerAndLoadingText(false);
        initRecyclerViewPasos();

        galeriaPresenter.attachGaleriaView(this);
        galeriaPresenter.initPresenter(idCofradia);
    }

    private void initRecyclerViewPasos() {
        mRecyclerView.setHasFixedSize(true);
        if (new GlobalFunctions().getScreenOrientation(getResources().getConfiguration().orientation).equals("Landscrape")) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        }

        galeriaAdapter.setImagenGaleriaClickListener(this);
        mRecyclerView.setAdapter(galeriaAdapter);
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printImagenGaleria(ObservableArrayList<GalleryImage> mImagenesGaleria) {
        Log.d(LOG_TAG, "Number of Gallery Images in the recyclerView: "+mImagenesGaleria.size());
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
    public void setGalleryImagesPaths(ObservableArrayList<String> listaGalleryImagesPaths) {
        this.listaGalleryImagesPaths = listaGalleryImagesPaths;
    }

    @Override
    public void onClickImagenGaleria(int position) {
        //Toast.makeText(getContext(), "CLICK - "+LOG_TAG, Toast.LENGTH_SHORT).show();
        detailCofradiaViewPagerGaleriaClickListener.onClickDetailCofradiaViewPagerGaleria(position, listaGalleryImagesPaths);
    }

    public interface DetailCofradiaViewPagerGaleriaClickListener {
        void onClickDetailCofradiaViewPagerGaleria(int position, ObservableArrayList<String> listaGalleryImagesPaths);
    }

    public DetailCofradiaViewPagerGaleria.DetailCofradiaViewPagerGaleriaClickListener getDetailCofradiaViewPagerGaleriaClickListener() {
        return detailCofradiaViewPagerGaleriaClickListener;
    }

    public void setDetailCofradiaViewPagerGaleriaClickListener(DetailCofradiaViewPagerGaleria.DetailCofradiaViewPagerGaleriaClickListener detailCofradiaViewPagerGaleriaClickListener) {
        this.detailCofradiaViewPagerGaleriaClickListener = detailCofradiaViewPagerGaleriaClickListener;
    }
}
