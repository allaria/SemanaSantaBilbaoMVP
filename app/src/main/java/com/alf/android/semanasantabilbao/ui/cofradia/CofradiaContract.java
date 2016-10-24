package com.alf.android.semanasantabilbao.ui.cofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;

/**
 * Created by alaria on 22/09/2016.
 */

public interface CofradiaContract {

    interface CofradiaView {

        void setSpinner(boolean loadingSpinner);

        void printCofradias(ObservableArrayList<Cofradia> listaCofradias);

        void printCofradiasWithoutConnection(ArrayList<Cofradia> listaCofradias);

        Field[] getDrawablesList();

        void showErrorGettingCofradias(ObservableField<String> mensajeError);

    }

    interface CofradiaPresenter {

        void attachCofradiaView(CofradiaContract.CofradiaView view);

        void detachCofradiaView();

        void unsuscribeCofradiaSuspciption();

        void initPresenter(boolean conexion, Field[] fields);

    }
}
