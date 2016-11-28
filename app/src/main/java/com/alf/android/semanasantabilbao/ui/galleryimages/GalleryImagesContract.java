package com.alf.android.semanasantabilbao.ui.galleryimages;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaContract;
import com.alf.android.semanasantabilbao.ui.galleryimages.adapter.GalleryImagesAdapter;

/**
 * Created by Alberto on 25/11/2016.
 */

public interface GalleryImagesContract {

    interface GalleryImagesView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printGalleryImages(ObservableArrayList<GalleryImage> listaGalleryImages);

        void showErrorGettingGalleryImages(ObservableField<String> mensajeError);
    }

    interface GalleryImagesPresenter {

        void attachGalleryImagesView(GalleryImagesContract.GalleryImagesView view);

        void detachGalleryImagesView();

        void unsuscribeGalleryImagesSuscription();

        void initPresenter();
    }
}
