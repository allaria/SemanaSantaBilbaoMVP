package com.alf.android.semanasantabilbao.ui.cofradia;

import android.databinding.ObservableArrayList;
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
    public void initPresenter() {
        setSpinner(true);

        getCofradiasInteractor.getCofradias().subscribe(subscriber);

        setSpinner(false);
    }

    private void setSpinner(boolean loadingSpinner) {
        if (cofradiaView != null) {
            this.cofradiaView.setSpinner(loadingSpinner);
        }
    }


    private Subscriber subscriber = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {

            listaCofradias.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Log.d(LOG_TAG, "RECUPERANDO COFRADIAS");
                listaCofradias.add(dataSnapshot.getValue(Cofradia.class));
            }

            cofradiaView.printCofradias(listaCofradias);
        }

        public void onCompleted() {
//            if (!subscription.isUnsubscribed()) {
//                subscription.unsubscribe();
//            }
        }

        @Override
        public void onError(Throwable e) {

        }
    };

}
