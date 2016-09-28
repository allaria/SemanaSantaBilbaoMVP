package com.alf.android.semanasantabilbao.ui.cofradia;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;

import java.util.List;

/**
 * Created by alaria on 22/09/2016.
 */

public interface CofradiaContract {

    interface CofradiaView {

        void setSpinner(boolean loadingSpinner);

        void printCofradias(List<Cofradia> listaCofradias);

    }

    interface CofradiaPresenter {

        void attachCofradiaView(CofradiaContract.CofradiaView view);

        void detachCofradiaView();

        void initPresenter();
    }
}
