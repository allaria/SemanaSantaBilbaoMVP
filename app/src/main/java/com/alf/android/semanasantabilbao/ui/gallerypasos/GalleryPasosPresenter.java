package com.alf.android.semanasantabilbao.ui.gallerypasos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.gallerypasos.GetGalleryPasosInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class GalleryPasosPresenter implements GalleryPasosContract.GalleryPasosPresenter {

    private static final String LOG_TAG = GalleryPasosPresenter.class.getSimpleName();
    private GalleryPasosContract.GalleryPasosView galleryPasosView;
    private GetGalleryPasosInteractorImpl getGalleryPasosInteractor;
    private Subscription subscriptionGalleryPasos;
    private ObservableArrayList<Paso> listaGalleryPasos;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public GalleryPasosPresenter(GetGalleryPasosInteractorImpl getGalleryPasosInteractor) {

        this.getGalleryPasosInteractor = getGalleryPasosInteractor;
        listaGalleryPasos = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachGalleryPasosView(GalleryPasosContract.GalleryPasosView galleryPasosView) {
        this.galleryPasosView = galleryPasosView;
    }

    @Override
    public void detachGalleryPasosView() {
        this.galleryPasosView = null;
    }

    @Override
    public void unsuscribeGalleryPasosSuscription() {
        if (!subscriptionGalleryPasos.isUnsubscribed()) {
            subscriptionGalleryPasos.unsubscribe();
        }
    }

    @Override
    public void initPresenter() {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaGalleryPasos.isEmpty()) {
            //Log.d(LOG_TAG, "Pasos list is empty");
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "Gallery Pasos list is empty. Getting Gallery Pasos from Firebase");
            subscriptionGalleryPasos = getGalleryPasosInteractor.getGalleryPasos().subscribe(subscriberGalleryPasos);
        } else {
            if (galleryPasosView != null) {
                Log.d(LOG_TAG, "Gallery Pasos list not empty. Calling printGalleryPasos.");
                loading = false;
                setSpinnerAndLoadindText(loading);
                galleryPasosView.printGalleryPasos(listaGalleryPasos);
            }
        }
    }

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (galleryPasosView != null) {
            this.galleryPasosView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }

    private Subscriber subscriberGalleryPasos = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {

            Log.d(LOG_TAG, "Suscriber Gallery Pasos onNext. Adding Gallery Pasos ("+ snapshot.getChildrenCount() +") to listaGalleryPasos");
            listaGalleryPasos.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                listaGalleryPasos.add(dataSnapshot.getValue(Paso.class));
            }

            Log.d(LOG_TAG, "Gallery Pasos list generated. Calling printGalleryPasos.");
            galleryPasosView.printGalleryPasos(listaGalleryPasos);
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber Gallery Pasos onCompleted.");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            Log.d(LOG_TAG, "Suscriber Gallery Pasos onError. Error while getting Gallery Pasos from Firebase. ("+ errorMessage.get() +")");
            galleryPasosView.showErrorGettingGalleryPasos(errorMessage);
        }
    };
}
