package com.alf.android.semanasantabilbao.ui.galleryimages;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.galleryimages.GetGalleryImagesInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class GalleryImagesPresenter implements GalleryImagesContract.GalleryImagesPresenter {

    private static final String LOG_TAG = GalleryImagesPresenter.class.getSimpleName();
    private GalleryImagesContract.GalleryImagesView galleryImagesView;
    private GetGalleryImagesInteractorImpl getGalleryImagesInteractor;
    private Subscription subscriptionGalleryImages;
    private ObservableArrayList<GalleryImage> listaGalleryImages;
    private ArrayList<String> listaImagesPaths;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public GalleryImagesPresenter(GetGalleryImagesInteractorImpl getGalleryImagesInteractor) {

        this.getGalleryImagesInteractor = getGalleryImagesInteractor;
        listaGalleryImages = new ObservableArrayList();
        listaImagesPaths = new ArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachGalleryImagesView(GalleryImagesContract.GalleryImagesView galleryImagesView) {
        this.galleryImagesView = galleryImagesView;
    }

    @Override
    public void detachGalleryImagesView() {
        this.galleryImagesView = null;
    }

    @Override
    public void unsuscribeGalleryImagesSuscription() {
        if (!subscriptionGalleryImages.isUnsubscribed()) {
            subscriptionGalleryImages.unsubscribe();
        }
    }

    @Override
    public void initPresenter() {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaGalleryImages.isEmpty()) {
            //Log.d(LOG_TAG, "Imagenes list is empty");
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "Gallery Images list is empty. Getting Gallery Images from Firebase");
            subscriptionGalleryImages = getGalleryImagesInteractor.getGalleryImages().subscribe(subscriberGalleryImages);
        } else {
            if (galleryImagesView != null) {
                Log.d(LOG_TAG, "Gallery Images list not empty. Calling printGalleryImages.");
                loading = false;
                setSpinnerAndLoadindText(loading);
                galleryImagesView.printGalleryImages(listaGalleryImages);
            }
        }
    }

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (galleryImagesView != null) {
            this.galleryImagesView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }

    private Subscriber subscriberGalleryImages = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {

            Log.d(LOG_TAG, "Suscriber Gallery Images onNext. Adding Gallery Images ("+ snapshot.getChildrenCount() +") to listaGalleryImages");
            listaGalleryImages.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                listaGalleryImages.add(dataSnapshot.getValue(GalleryImage.class));

                //List of the image paths for the DetailImage
                String path = (String) dataSnapshot.child("image").getValue();
                listaImagesPaths.add(path);
            }

            Log.d(LOG_TAG, "Gallery Images list generated. Calling printGalleryImages.");
            galleryImagesView.printGalleryImages(listaGalleryImages);
            galleryImagesView.setGalleryImagesPaths(listaImagesPaths);
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber Gallery Images onCompleted.");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            Log.d(LOG_TAG, "Suscriber Gallery Images onError. Error while getting Gallery Images from Firebase. ("+ errorMessage.get() +")");
            galleryImagesView.showErrorGettingGalleryImages(errorMessage);
        }
    };
}
