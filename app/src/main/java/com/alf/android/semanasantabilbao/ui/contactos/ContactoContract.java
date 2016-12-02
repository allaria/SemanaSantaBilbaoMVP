package com.alf.android.semanasantabilbao.ui.contactos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public interface ContactoContract {

    interface ContactoView {

        void setSpinnerAndLoadingText(boolean loadingSpinner);

        void printContactos(ObservableArrayList<Cofradia> listaContactos);

        void showErrorGettingContactos(ObservableField<String> mensajeError);
    }

    interface ContactoPresenter {

        void attachContactosView(ContactoView view);

        void detachContactosView();

        void unsuscribeContactosSuscription();

        void initPresenter();
    }
}
