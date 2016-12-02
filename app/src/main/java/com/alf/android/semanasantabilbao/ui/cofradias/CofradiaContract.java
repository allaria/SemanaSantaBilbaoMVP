package com.alf.android.semanasantabilbao.ui.cofradias;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Alberto Laría Fernández on 22/09/2016.
 */

public interface CofradiaContract {

    interface CofradiaView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printCofradias(ObservableArrayList<Cofradia> listaCofradias);

        void printCofradiasWhenError(ArrayList<Cofradia> listaCofradias);

        Field[] getDrawablesList();

        void showErrorGettingCofradias(ObservableField<String> mensajeError);

        void setUrl (String urlGooglePlayUrl);
    }

    interface CofradiaPresenter {

        void attachCofradiaView(CofradiaContract.CofradiaView view);

        void detachCofradiaView();

        void unsuscribeCofradiaSuscription();

        void initPresenter(boolean conexion, Field[] fields);
    }
}
