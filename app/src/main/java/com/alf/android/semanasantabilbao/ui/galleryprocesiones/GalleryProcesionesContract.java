package com.alf.android.semanasantabilbao.ui.galleryprocesiones;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Procesion;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public interface GalleryProcesionesContract {

    interface GalleryProcesionesView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printGalleryProcesiones(ObservableArrayList<Procesion> listaGalleryProcesiones);

        void executeIntentToDetailProcesion (Cofradia cofradia);

        void showErrorGettingGalleryProcesiones(ObservableField<String> mensajeError);

        void showErrorGettingCofradia(ObservableField<String> mensajeError);
    }

    interface GalleryProcesionesPresenter {

        void attachGalleryProcesionesView(GalleryProcesionesContract.GalleryProcesionesView view);

        void detachGalleryProcesionesView();

        void unsuscribeGalleryProcesionesSuscription();

        void unsuscribeGalleryCofradiaSuscription();

        void initPresenter();

        void getCofradiaFromProcesion(String idCofradia);
    }
}
