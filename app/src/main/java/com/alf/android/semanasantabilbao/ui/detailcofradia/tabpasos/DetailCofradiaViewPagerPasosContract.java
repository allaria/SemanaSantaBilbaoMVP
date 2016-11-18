package com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Paso;

/**
 * Created by Alberto on 02/11/2016.
 */

public interface DetailCofradiaViewPagerPasosContract {

    interface DetailCofradiaView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printPasos(ObservableArrayList<Paso> listaPasos);

        void showErrorGettingPasos(ObservableField<String> mensajeError);
    }

    interface DetailCofradiaPresenter {

        void attachPasoView(DetailCofradiaViewPagerPasosContract.DetailCofradiaView view);

        void detachPasoView();

        void unsuscribePasosSuscription();

        void initPresenter(String idCofradia);
    }
}
