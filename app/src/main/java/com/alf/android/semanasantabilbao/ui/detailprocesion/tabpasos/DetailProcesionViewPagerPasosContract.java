package com.alf.android.semanasantabilbao.ui.detailprocesion.tabpasos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Paso;

/**
 * Created by Alberto Laría Fernández on 02/11/2016.
 */

public interface DetailProcesionViewPagerPasosContract {

    interface DetailPasosView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printPasos(ObservableArrayList<Paso> listaPasos);

        void showErrorGettingPasos(ObservableField<String> mensajeError);
    }

    interface DetailPasosPresenter {

        void attachPasoView(DetailPasosView view);

        void detachPasoView();

        void unsuscribePasosSuscription();

        void initPresenter(String idCofradia);
    }
}
