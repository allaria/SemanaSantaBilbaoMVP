package com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Procesion;

/**
 * Created by Alberto Laría Fernández on 02/11/2016.
 */

public interface DetailCofradiaViewPagerCofradiaContract {

    interface DetailCofradiaView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printProcesiones(ObservableArrayList<Procesion> listaProcesiones);

        void showErrorGettingProcesiones(ObservableField<String> mensajeError);
    }

    interface DetailCofradiaPresenter {

        void attachProcesionesView(DetailCofradiaViewPagerCofradiaContract.DetailCofradiaView view);

        void detachProcesionesView();

        void unsuscribeProcesionesSuscription();

        void initPresenter(String idCofradia);
    }
}
