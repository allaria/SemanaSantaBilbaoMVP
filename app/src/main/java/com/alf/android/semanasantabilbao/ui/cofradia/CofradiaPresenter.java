package com.alf.android.semanasantabilbao.ui.cofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.GetCofradiasInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alaria on 22/09/2016.
 */

public class CofradiaPresenter implements CofradiaContract.CofradiaPresenter {

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    private CofradiaContract.CofradiaView cofradiaView;
    private GetCofradiasInteractorImpl getCofradiasInteractor;
    private Subscription subscription;
    private ObservableArrayList<Cofradia> listaCofradias;
    private ObservableField<String> mensajeError;

    public CofradiaPresenter() {

        getCofradiasInteractor = new GetCofradiasInteractorImpl();
        listaCofradias = new ObservableArrayList();
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
    public void unsuscribeCofradiaSuspciption() {
        this.subscription.unsubscribe();
    }

    @Override
    public void initPresenter() {
        Log.d(LOG_TAG, "setSpinner true");
        setSpinner(true);

        Log.d(LOG_TAG, "Recuperar Cofradias");
        getCofradiasInteractor.getCofradias().subscribe(subscriber);
    }

    private void setSpinner(boolean loadingSpinner) {
        if (cofradiaView != null) {
            this.cofradiaView.setSpinner(loadingSpinner);
        }
    }


    private Subscriber subscriber = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {
            Log.d(LOG_TAG, "onNext");

            listaCofradias.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                //Log.d(LOG_TAG, "RECUPERANDO COFRADIAS");
                listaCofradias.add(dataSnapshot.getValue(Cofradia.class));
            }

            cofradiaView.printCofradias(listaCofradias);
            Log.d(LOG_TAG, "setSpinner false");
            setSpinner(false);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(LOG_TAG, "onError");
            //setSpinner(false);
            mensajeError.set(e.getMessage().toString());
            cofradiaView.mostrarErrorRecuperarCofradias(mensajeError);
        }
    };

}
