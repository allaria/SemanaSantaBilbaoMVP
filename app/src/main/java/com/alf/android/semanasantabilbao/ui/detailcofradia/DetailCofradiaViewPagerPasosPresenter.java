package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.detailcofradia.GetPasosInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto on 02/11/2016.
 */

public class DetailCofradiaViewPagerPasosPresenter implements DetailCofradiaViewPagerPasosContract.DetailCofradiaPresenter {

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    private DetailCofradiaViewPagerPasosContract.DetailCofradiaView detailCofradiaView;
    private GetPasosInteractorImpl getPasosInteractor;
    private Subscription subscription;
    private ObservableArrayList<Paso> listaPasos;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public DetailCofradiaViewPagerPasosPresenter() {

        getPasosInteractor = new GetPasosInteractorImpl();
        listaPasos = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void attachPasoView(DetailCofradiaViewPagerPasosContract.DetailCofradiaView detailCofradiaView) {
        this.detailCofradiaView = detailCofradiaView;
    }

    @Override
    public void detachPasoView() {
        this.detailCofradiaView = null;
    }

    @Override
    public void unsuscribePasosSuscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void initPresenter() {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaPasos.isEmpty()) {
            Log.d(LOG_TAG, "initPresenter - IF loading"+!loading+" - isEmpty: "+listaPasos.isEmpty());
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "initPresenter - getPasos Firebase");
            subscription = getPasosInteractor.getPasos().subscribe(subscriber);
        } else {
            if (detailCofradiaView != null) {
                Log.d(LOG_TAG, "initPresenter - printPasos CACHE");
                Log.d(LOG_TAG, "initPresenter - ELSE loading"+!loading+" - isEmpty: "+listaPasos.isEmpty());
                detailCofradiaView.printPasos(listaPasos);
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

            listaPasos.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                //Log.d(LOG_TAG, "RECUPERANDO PASOS");
                listaPasos.add(dataSnapshot.getValue(Paso.class));
            }

            Log.d(LOG_TAG, "Subscriber llamada printPasos");
            detailCofradiaView.printPasos(listaPasos);

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
            detailCofradiaView.showErrorGettingPasos(errorMessage);
        }
    };
}
