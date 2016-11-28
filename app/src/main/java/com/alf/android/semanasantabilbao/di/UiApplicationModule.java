package com.alf.android.semanasantabilbao.di;

import com.alf.android.semanasantabilbao.business.cofradias.GetCofradiasInteractorImpl;
import com.alf.android.semanasantabilbao.business.detailcofradia.GetDetailCofradiaInteractorImpl;
import com.alf.android.semanasantabilbao.business.galleryimages.GetGalleryImagesInteractorImpl;
import com.alf.android.semanasantabilbao.business.gallerypasos.GetGalleryPasosInteractorImpl;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaContract;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaPresenter;
import com.alf.android.semanasantabilbao.ui.cofradias.adapter.CofradiaAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaContract;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaPresenter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.GaleriaAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.PasoAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.ProcesionAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia.DetailCofradiaViewPagerCofradiaContract;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia.DetailCofradiaViewPagerCofradiaPresenter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria.DetailCofradiaViewPagerGaleriaContract;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria.DetailCofradiaViewPagerGaleriaPresenter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasosContract;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasosPresenter;
import com.alf.android.semanasantabilbao.ui.galleryimages.GalleryImagesContract;
import com.alf.android.semanasantabilbao.ui.galleryimages.GalleryImagesPresenter;
import com.alf.android.semanasantabilbao.ui.galleryimages.adapter.GalleryImagesAdapter;
import com.alf.android.semanasantabilbao.ui.gallerypasos.GalleryPasosContract;
import com.alf.android.semanasantabilbao.ui.gallerypasos.GalleryPasosPresenter;
import com.alf.android.semanasantabilbao.ui.gallerypasos.adapter.GalleryPasosAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alaria on 17/10/2016.
 */
@Module
public class UiApplicationModule {

    private static final String LOG_TAG = UiApplicationModule.class.getSimpleName();

    //CofradiaActivity Dependencies
    @Provides
    @Singleton
    public CofradiaContract.CofradiaPresenter provideCofradiaPresenter(GetCofradiasInteractorImpl getCofradiasInteractor) {
        return new CofradiaPresenter(getCofradiasInteractor);
    }

    @Provides
    @Singleton
    public CofradiaAdapter provideCofradiaAdapter() {
        return new CofradiaAdapter();
    }


    //DetailCofradiaActivity Dependencies
    @Provides
    @Singleton
    public DetailCofradiaContract.DetailCofradiaPresenter provideDetailCofradiaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {
        return new DetailCofradiaPresenter(getDetailCofradiaInteractor);
    }

    //DetailCofradiaViewPagerCofradia Dependencies
    @Provides
    @Singleton
    public DetailCofradiaViewPagerCofradiaContract.DetailCofradiaPresenter provideViewPagerDetailCofradiaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {
        return new DetailCofradiaViewPagerCofradiaPresenter(getDetailCofradiaInteractor);
    }

    @Provides
    @Singleton
    public ProcesionAdapter provideProcesionAdapter() {
        return new ProcesionAdapter();
    }


    //DetailCofradiaViewPagerGaleria Dependencies
    @Provides
    @Singleton
    public DetailCofradiaViewPagerGaleriaContract.DetailGaleriaPresenter provideViewPagerDetailGaleriaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {
        return new DetailCofradiaViewPagerGaleriaPresenter(getDetailCofradiaInteractor);
    }

    @Provides
    @Singleton
    public GaleriaAdapter provideGaleriaAdapter() {
        return new GaleriaAdapter();
    }


    //DetailCofradiaViewPagerPasos Dependencies
    @Provides
    @Singleton
    public DetailCofradiaViewPagerPasosContract.DetailPasosPresenter provideViewPagerDetailPasosPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {
        return new DetailCofradiaViewPagerPasosPresenter(getDetailCofradiaInteractor);
    }

    @Provides
    @Singleton
    public PasoAdapter providePasoAdapter() {
        return new PasoAdapter();
    }


    //GalleryImages Dependencies
    @Provides
    @Singleton
    public GalleryImagesContract.GalleryImagesPresenter provideGalleryIamgesPresenter(GetGalleryImagesInteractorImpl getGalleryImagesInteractor) {
        return new GalleryImagesPresenter(getGalleryImagesInteractor);
    }

    @Provides
    @Singleton
    public GalleryImagesAdapter provideGalleryImagesAdapter() {
        return new GalleryImagesAdapter();
    }


    //GalleryPasos Dependencies
    @Provides
    @Singleton
    public GalleryPasosContract.GalleryPasosPresenter provideGalleryPasosPresenter(GetGalleryPasosInteractorImpl getGalleryPasosInteractor) {
        return new GalleryPasosPresenter(getGalleryPasosInteractor);
    }

    @Provides
    @Singleton
    public GalleryPasosAdapter provideGalleryPasosAdapter() {
        return new GalleryPasosAdapter();
    }
}

