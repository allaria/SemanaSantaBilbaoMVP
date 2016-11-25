package com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.detailcofradia.GetDetailCofradiaInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto on 02/11/2016.
 */

public class DetailCofradiaViewPagerCofradiaPresenter implements DetailCofradiaViewPagerCofradiaContract.DetailCofradiaPresenter {

    private static final String LOG_TAG = DetailCofradiaViewPagerCofradiaPresenter.class.getSimpleName();
    private DetailCofradiaViewPagerCofradiaContract.DetailCofradiaView detailCofradiaView;
    private GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor;
    private Subscription subscription;
    private ObservableArrayList<Procesion> listaProcesiones;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public DetailCofradiaViewPagerCofradiaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {

        //getDetailCofradiaInteractor = new GetDetailCofradiaInteractorImpl();
        this.getDetailCofradiaInteractor = getDetailCofradiaInteractor;
        listaProcesiones = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachProcesionesView(DetailCofradiaViewPagerCofradiaContract.DetailCofradiaView detailCofradiaView) {
        this.detailCofradiaView = detailCofradiaView;
    }

    @Override
    public void detachProcesionesView() {
        this.detailCofradiaView = null;
    }

    @Override
    public void unsuscribeProcesionesSuscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void initPresenter(String idCofradia) {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaProcesiones.isEmpty()) {
            Log.d(LOG_TAG, "initPresenter - IF loading"+!loading+" - isEmpty: "+listaProcesiones.isEmpty());
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "initPresenter - getImagenesGaleria Firebase");
            subscription = getDetailCofradiaInteractor.getProcesiones(idCofradia).subscribe(subscriber);
        } else {
            if (detailCofradiaView != null) {
                Log.d(LOG_TAG, "initPresenter - printPasos CACHE");
                Log.d(LOG_TAG, "initPresenter - ELSE loading"+!loading+" - isEmpty: "+listaProcesiones.isEmpty());
                detailCofradiaView.printProcesiones(listaProcesiones);
            }
        }
    }

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (detailCofradiaView != null) {
            this.detailCofradiaView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }

    private Subscriber subscriber = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {
            Log.d(LOG_TAG, "onNext");

            listaProcesiones.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                //Log.d(LOG_TAG, "RECUPERANDO PASOS");
                listaProcesiones.add(dataSnapshot.getValue(Procesion.class));
            }

            Log.d(LOG_TAG, "Subscriber llamada printPasos");
            detailCofradiaView.printProcesiones(listaProcesiones);

            Log.d(LOG_TAG, "Subscriber llamada setSpinnerAndLoadindText");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "onCompleted");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(LOG_TAG, "onErrorXX");
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            detailCofradiaView.showErrorGettingProcesiones(errorMessage);
        }
    };
}
