package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaContract;

import java.lang.reflect.Field;
import java.util.ArrayList;

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

        void initPresenter();

        void setLoading(boolean loading);
    }
}
