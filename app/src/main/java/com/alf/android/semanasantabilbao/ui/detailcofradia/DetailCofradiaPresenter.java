package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.detailcofradia.GetDetailCofradiaInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto Laría Fernández on 16/11/2016.
 */

public class DetailCofradiaPresenter  implements DetailCofradiaContract.DetailCofradiaPresenter {

    private static final String LOG_TAG = DetailCofradiaPresenter.class.getSimpleName();
    private DetailCofradiaContract.DetailCofradiaView detailCofradiaView;
    private GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor;
    private Subscription subscription;
    private ObservableArrayList<Cofradia> listaCofradias;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public DetailCofradiaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {

        this.getDetailCofradiaInteractor = getDetailCofradiaInteractor;
        listaCofradias = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachDetailCofradiaView(DetailCofradiaContract.DetailCofradiaView detailCofradiaView) {
        this.detailCofradiaView = detailCofradiaView;
    }

    @Override
    public void detachDetailCofradiaView() {
        this.detailCofradiaView = null;
    }

    @Override
    public void unsuscribeDetailCofradiaSuscription() {
//COMPROBAR ESTA MIERDA
        //COMPROBAR ESTA MIERDA
        //COMPROBAR ESTA MIERDA
        //COMPROBAR ESTA MIERDA
        //COMPROBAR ESTA MIERDA
        //COMPROBAR ESTA MIERDA
        //COMPROBAR ESTA MIERDA
    }

    @Override
    public void initPresenter(String idCofradia) {
        loading = true;
        setSpinnerAndLoadindText(loading);
        if (listaCofradias.isEmpty()) {
            subscription = getDetailCofradiaInteractor.getCofradia(idCofradia).subscribe(subscriber);
        }
    }

    private Subscriber subscriber = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {
            Log.d(LOG_TAG, "onNext");

            listaCofradias.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Log.d(LOG_TAG, "RECUPERANDO COFRADIAS");
                listaCofradias.add(dataSnapshot.getValue(Cofradia.class));
            }

            detailCofradiaView.setCofradiaValue(listaCofradias);
            setSpinnerAndLoadindText(false);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            errorMessage.set(e.getMessage());
            detailCofradiaView.showErrorGettingCofradias(errorMessage);
        }
    };

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (detailCofradiaView != null) {
            this.detailCofradiaView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }
}
