package com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.detailcofradia.GetDetailCofradiaInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto Laría Fernández on 02/11/2016.
 */

public class DetailCofradiaViewPagerPasosPresenter implements DetailCofradiaViewPagerPasosContract.DetailPasosPresenter {

    private static final String LOG_TAG = DetailCofradiaViewPagerPasosPresenter.class.getSimpleName();
    private DetailCofradiaViewPagerPasosContract.DetailGaleriaView detailCofradiaView;
    private GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor;
    private Subscription subscription;
    private ObservableArrayList<Paso> listaPasos;
    private ObservableArrayList<String> listaImagesPaths;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public DetailCofradiaViewPagerPasosPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {

        //getDetailCofradiaInteractor = new GetDetailCofradiaInteractorImpl();
        this.getDetailCofradiaInteractor = getDetailCofradiaInteractor;
        listaPasos = new ObservableArrayList();
        listaImagesPaths = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachPasoView(DetailCofradiaViewPagerPasosContract.DetailGaleriaView detailCofradiaView) {
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
    public void initPresenter(String idCofradia) {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaPasos.isEmpty()) {
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "Pasos list is empty. Getting Pasos from Firebase");
            subscription = getDetailCofradiaInteractor.getPasos(idCofradia).subscribe(subscriber);
        } else {
            if (detailCofradiaView != null) {
                Log.d(LOG_TAG, "Pasos list not empty. Calling printCofradias and setPasosPaths.");
                detailCofradiaView.setPasosPaths(listaImagesPaths);
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
            Log.d(LOG_TAG, "Suscriber Pasos onNext. Adding Pasos ("+ snapshot.getChildrenCount() +") to listaPasos");
            listaPasos.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                listaPasos.add(dataSnapshot.getValue(Paso.class));

                //List of the image paths for the DetailImage
                String path = (String) dataSnapshot.child("imagenPaso").getValue();
                listaImagesPaths.add(path);
            }

            Log.d(LOG_TAG, "Images path list generated. Calling setPasosPaths.");
            detailCofradiaView.setPasosPaths(listaImagesPaths);

            Log.d(LOG_TAG, "Pasos list generated. Calling printPasos.");
            detailCofradiaView.printPasos(listaPasos);

            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber Pasos onCompleted.");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(LOG_TAG, "Suscriber Pasos onError. Error while getting Pasos from Firebase. ("+ errorMessage.get() +")");
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            detailCofradiaView.showErrorGettingPasos(errorMessage);
        }
    };
}
