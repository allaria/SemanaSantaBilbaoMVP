package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;

/**
 * Created by alaria on 16/11/2016.
 */

public interface DetailCofradiaContract {

    interface DetailCofradiaView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void showErrorGettingCofradias(ObservableField<String> mensajeError);

        void setCofradiaValue(ObservableArrayList<Cofradia> listaCofradias);
    }

    interface DetailCofradiaPresenter {

        void attachDetailCofradiaView(DetailCofradiaContract.DetailCofradiaView view);

        void detachDetailCofradiaView();

        void unsuscribeDetailCofradiaSuscription();

        void initPresenter(String idCofradia);
    }
}
