package com.alf.android.semanasantabilbao.ui.cofradia;

import com.alf.android.semanasantabilbao.business.GetCofradiasInteractorImpl;

/**
 * Created by alaria on 22/09/2016.
 */

public class CofradiaPresenter implements CofradiaContract.CofradiaPresenter {

    private CofradiaContract.CofradiaView cofradiaView;
    private GetCofradiasInteractorImpl getCofradiasInteractor;


    public CofradiaPresenter() {
        getCofradiasInteractor = new GetCofradiasInteractorImpl();
    }

    @Override
    public void attachCofradiaView(CofradiaContract.CofradiaView cofradiaView) {
        this.cofradiaView = cofradiaView;
    }

    @Override
    public void detachCofradiaView() {
        this.cofradiaView = null;
    }

    @Override
    public void initPresenter() {
        setSpinner(true);

        cofradiaView.printCofradias(getCofradiasInteractor.getCofradias());

        //setSpinner(false);
    }

    private void setSpinner(boolean loadingSpinner) {
        if (cofradiaView != null) {
            this.cofradiaView.setSpinner(loadingSpinner);
        }
    }
}
