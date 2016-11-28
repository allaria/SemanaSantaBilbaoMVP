package com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.detailcofradia.GetDetailCofradiaInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto on 02/11/2016.
 */

public class DetailCofradiaViewPagerGaleriaPresenter implements DetailCofradiaViewPagerGaleriaContract.DetailGaleriaPresenter {

    private static final String LOG_TAG = DetailCofradiaViewPagerGaleriaPresenter.class.getSimpleName();
    private DetailCofradiaViewPagerGaleriaContract.DetailGaleriaView detailGaleriaView;
    private GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor;
    private Subscription subscription;
    private ObservableArrayList<GalleryImage> listaGalleryImage;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public DetailCofradiaViewPagerGaleriaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {

        //getDetailCofradiaInteractor = new GetDetailCofradiaInteractorImpl();
        this.getDetailCofradiaInteractor = getDetailCofradiaInteractor;
        listaGalleryImage = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachGaleriaView(DetailCofradiaViewPagerGaleriaContract.DetailGaleriaView detailGaleriaView) {
        this.detailGaleriaView = detailGaleriaView;
    }

    @Override
    public void detachGaleriaView() {
        this.detailGaleriaView = null;
    }

    @Override
    public void unsuscribeGaleriaSuscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void initPresenter(String idCofradia) {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaGalleryImage.isEmpty()) {
            Log.d(LOG_TAG, "initPresenter - IF loading"+!loading+" - isEmpty: "+ listaGalleryImage.isEmpty());
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "initPresenter - getImagenesGaleria Firebase");
            subscription = getDetailCofradiaInteractor.getImagenesGaleria(idCofradia).subscribe(subscriber);
        } else {
            if (detailGaleriaView != null) {
                Log.d(LOG_TAG, "initPresenter - printImagenGaleria CACHE");
                Log.d(LOG_TAG, "initPresenter - ELSE loading"+!loading+" - isEmpty: "+ listaGalleryImage.isEmpty());
                detailGaleriaView.printImagenGaleria(listaGalleryImage);
            }
        }
    }

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (detailGaleriaView != null) {
            this.detailGaleriaView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }

    private Subscriber subscriber = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {
            Log.d(LOG_TAG, "onNext");

            listaGalleryImage.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                //Log.d(LOG_TAG, "RECUPERANDO PASOS");
                listaGalleryImage.add(dataSnapshot.getValue(GalleryImage.class));
            }

            Log.d(LOG_TAG, "Subscriber llamada printImagenGaleria");
            detailGaleriaView.printImagenGaleria(listaGalleryImage);

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
            detailGaleriaView.showErrorGettingImagenesGaleria(errorMessage);
        }
    };
}
