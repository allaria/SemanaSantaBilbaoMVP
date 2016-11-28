package com.alf.android.semanasantabilbao.di;

import com.alf.android.semanasantabilbao.business.cofradias.GetCofradiasInteractorImpl;
import com.alf.android.semanasantabilbao.business.detailcofradia.GetDetailCofradiaInteractorImpl;
import com.alf.android.semanasantabilbao.business.galleryimages.GetGalleryImagesInteractorImpl;
import com.alf.android.semanasantabilbao.business.gallerypasos.GetGalleryPasosInteractorImpl;
import com.alf.android.semanasantabilbao.data.FirebaseAccess;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alaria on 17/10/2016.
 */
@Module
public class BusinessApplicationModule {

    private static final String LOG_TAG = BusinessApplicationModule.class.getSimpleName();

    @Singleton
    @Provides
    public GetCofradiasInteractorImpl provideGetCofradiasInteractorImpl(FirebaseAccess firebaseAccess) {
        return new GetCofradiasInteractorImpl(firebaseAccess);
    }

    @Singleton
    @Provides
    public GetDetailCofradiaInteractorImpl provideGetDetailCofradiaInteractorImpl(FirebaseAccess firebaseAccess) {
        return new GetDetailCofradiaInteractorImpl(firebaseAccess);
    }

    @Singleton
    @Provides
    public GetGalleryImagesInteractorImpl provideGetGalleryImagesInteractorImpl(FirebaseAccess firebaseAccess) {
        return new GetGalleryImagesInteractorImpl(firebaseAccess);
    }

    @Singleton
    @Provides
    public GetGalleryPasosInteractorImpl provideGetGalleryPasosInteractorImpl(FirebaseAccess firebaseAccess) {
        return new GetGalleryPasosInteractorImpl(firebaseAccess);
    }
}

