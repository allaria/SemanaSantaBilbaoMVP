package com.alf.android.semanasantabilbao.ui.gallerypasos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Paso;

import java.util.ArrayList;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public interface GalleryPasosContract {

    interface GalleryPasosView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printGalleryPasos(ObservableArrayList<Paso> listaGalleryPasos);

        void setPasosPaths(ArrayList<String> listaPasosPaths);

        void showErrorGettingGalleryPasos(ObservableField<String> mensajeError);
    }

    interface GalleryPasosPresenter {

        void attachGalleryPasosView(GalleryPasosContract.GalleryPasosView view);

        void detachGalleryPasosView();

        void unsuscribeGalleryPasosSuscription();

        void initPresenter();
    }
}
