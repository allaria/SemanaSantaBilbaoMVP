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
 * Created by Alberto Laría Fernández on 02/11/2016.
 */

public class DetailCofradiaViewPagerGaleriaPresenter implements DetailCofradiaViewPagerGaleriaContract.DetailGaleriaPresenter {

    private static final String LOG_TAG = DetailCofradiaViewPagerGaleriaPresenter.class.getSimpleName();
    private DetailCofradiaViewPagerGaleriaContract.DetailGaleriaView detailGaleriaView;
    private GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor;
    private Subscription subscription;
    private ObservableArrayList<GalleryImage> listaGalleryImage;
    private ObservableArrayList<String> listaGalleryImagesPaths;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public DetailCofradiaViewPagerGaleriaPresenter(GetDetailCofradiaInteractorImpl getDetailCofradiaInteractor) {

        //getDetailCofradiaInteractor = new GetDetailCofradiaInteractorImpl();
        this.getDetailCofradiaInteractor = getDetailCofradiaInteractor;
        listaGalleryImage = new ObservableArrayList();
        listaGalleryImagesPaths = new ObservableArrayList();
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
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "Gallery images list is empty. Getting gallery images from Firebase");
            subscription = getDetailCofradiaInteractor.getImagenesGaleria(idCofradia).subscribe(subscriber);
        } else {
            if (detailGaleriaView != null) {
                Log.d(LOG_TAG, "Gallery images list not empty. Calling printImagenGaleria and setGalleryImagesPaths.");
                detailGaleriaView.printImagenGaleria(listaGalleryImage);
                detailGaleriaView.setGalleryImagesPaths(listaGalleryImagesPaths);
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
            Log.d(LOG_TAG, "Suscriber gallery images onNext. Adding gallery image ("+ snapshot.getChildrenCount() +") to listaGalleryImage");
            listaGalleryImage.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                listaGalleryImage.add(dataSnapshot.getValue(GalleryImage.class));

                //List of the image paths for the DetailImage
                String path = (String) dataSnapshot.child("image").getValue();
                listaGalleryImagesPaths.add(path);
            }

            Log.d(LOG_TAG, "Images path list generated. Calling setGalleryImagesPaths.");
            detailGaleriaView.setGalleryImagesPaths(listaGalleryImagesPaths);

            Log.d(LOG_TAG, "Gallery images list generated. Calling printImagenGaleria.");
            detailGaleriaView.printImagenGaleria(listaGalleryImage);

            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber gallery images onCompleted.");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(LOG_TAG, "Suscriber gallery images onError. Error while getting gallery images from Firebase. ("+ errorMessage.get() +")");
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            detailGaleriaView.showErrorGettingImagenesGaleria(errorMessage);
        }
    };
}
