package com.alf.android.semanasantabilbao.business.galleryimages;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public class GetGalleryImagesInteractorImpl implements GetGalleryImagesInteractor {

    private static final String LOG_TAG = GetGalleryImagesInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetGalleryImagesInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    @Override
    public Observable<DataSnapshot> getGalleryImages() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_GALERIA);
    }

}

