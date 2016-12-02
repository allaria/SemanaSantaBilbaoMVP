package com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.GalleryImage;

/**
 * Created by Alberto Laría Fernández on 02/11/2016.
 */

public interface DetailCofradiaViewPagerGaleriaContract {

    interface DetailGaleriaView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printImagenGaleria(ObservableArrayList<GalleryImage> listaGalleryImage);

        void showErrorGettingImagenesGaleria(ObservableField<String> mensajeError);
    }

    interface DetailGaleriaPresenter {

        void attachGaleriaView(DetailCofradiaViewPagerGaleriaContract.DetailGaleriaView view);

        void detachGaleriaView();

        void unsuscribeGaleriaSuscription();

        void initPresenter(String idCofradia);
    }
}
