package com.alf.android.semanasantabilbao.ui.galleryprocesiones;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.galleryprocesiones.GetGalleryProcesionesInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class GalleryProcesionesPresenter implements GalleryProcesionesContract.GalleryProcesionesPresenter {

    private static final String LOG_TAG = GalleryProcesionesPresenter.class.getSimpleName();
    private GalleryProcesionesContract.GalleryProcesionesView galleryProcesionesView;
    private GetGalleryProcesionesInteractorImpl getGalleryProcesionesInteractor;
    private Subscription subscriptionGalleryProcesiones;
    private Subscription subscriptionGalleryCofradia;
    private ObservableArrayList<Procesion> listaGalleryProcesiones;
    private ObservableField<String> errorMessage;
    private boolean loading;
    private Cofradia cofradia;

    public GalleryProcesionesPresenter(GetGalleryProcesionesInteractorImpl getGalleryProcesionesInteractor) {

        this.getGalleryProcesionesInteractor = getGalleryProcesionesInteractor;
        listaGalleryProcesiones = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachGalleryProcesionesView(GalleryProcesionesContract.GalleryProcesionesView galleryProcesionesView) {
        this.galleryProcesionesView = galleryProcesionesView;
    }

    @Override
    public void detachGalleryProcesionesView() {
        this.galleryProcesionesView = null;
    }

    @Override
    public void unsuscribeGalleryProcesionesSuscription() {
        if (!subscriptionGalleryProcesiones.isUnsubscribed()) {
            subscriptionGalleryProcesiones.unsubscribe();
        }
    }

    @Override
    public void unsuscribeGalleryCofradiaSuscription() {
        if (!(subscriptionGalleryCofradia.isUnsubscribed() && subscriberGalleryCofradia == null) ) {
            subscriptionGalleryCofradia.unsubscribe();
        }
    }

    @Override
    public void initPresenter() {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaGalleryProcesiones.isEmpty()) {
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "Gallery Procesiones list is empty. Getting Gallery Procesiones from Firebase");
            subscriptionGalleryProcesiones = getGalleryProcesionesInteractor.getGalleryProcesiones().subscribe(subscriberGalleryProcesiones);
        } else {
            if (galleryProcesionesView != null) {
                Log.d(LOG_TAG, "Gallery Procesiones list not empty. Calling printGalleryProcesiones.");
                loading = false;
                setSpinnerAndLoadindText(loading);
                galleryProcesionesView.printGalleryProcesiones(listaGalleryProcesiones);
            }
        }
    }

    @Override
    public void getCofradiaFromProcesion(String idCofradia) {
        if (cofradia == null) {
            Log.d(LOG_TAG, "Cofradia is empty. Getting Cofradia from Firebase");
            subscriptionGalleryCofradia = getGalleryProcesionesInteractor.getCofradia(idCofradia).subscribe(subscriberGalleryCofradia);
        } else {
            galleryProcesionesView.executeIntentToDetailProcesion(cofradia);
        }
    }

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (galleryProcesionesView != null) {
            this.galleryProcesionesView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }

    private Subscriber subscriberGalleryProcesiones = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {

            Log.d(LOG_TAG, "Suscriber Gallery Procesiones onNext. Adding Gallery Procesiones ("+ snapshot.getChildrenCount() +") to listaGalleryProcesiones");
            listaGalleryProcesiones.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                listaGalleryProcesiones.add(dataSnapshot.getValue(Procesion.class));
            }

            Log.d(LOG_TAG, "Gallery Procesiones list generated. Calling printGalleryProcesiones.");
            galleryProcesionesView.printGalleryProcesiones(listaGalleryProcesiones);
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber Gallery Procesiones onCompleted.");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            Log.d(LOG_TAG, "Suscriber Gallery Procesiones onError. Error while getting Gallery Procesiones from Firebase. ("+ errorMessage.get() +")");
            galleryProcesionesView.showErrorGettingGalleryProcesiones(errorMessage);
        }
    };

    private Subscriber subscriberGalleryCofradia = new Subscriber<DataSnapshot>() {

        @Override
        public void onNext(DataSnapshot snapshot) {

            Log.d(LOG_TAG, "Suscriber Gallery Cofradia");
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                cofradia = dataSnapshot.getValue(Cofradia.class);
            }

            galleryProcesionesView.executeIntentToDetailProcesion(cofradia);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber Gallery Cofradia onCompleted.");
        }

        @Override
        public void onError(Throwable e) {
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            Log.d(LOG_TAG, "Suscriber Gallery Cofradia onError. Error while getting Gallerry Cofradia from Firebase. ("+ errorMessage.get() +")");
            galleryProcesionesView.showErrorGettingCofradia(errorMessage);
        }
    };
}
