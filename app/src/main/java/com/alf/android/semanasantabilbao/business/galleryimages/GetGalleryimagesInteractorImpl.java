package com.alf.android.semanasantabilbao.business.galleryimages;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by alaria on 26/09/2016.
 */

public class GetGalleryImagesInteractorImpl implements GetGalleryImagesInteractor {

    private static final String LOG_TAG = GetGalleryImagesInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;
    private String mensajeErrorFirebase;
    private List<GalleryImage> listaGalleryImages;

    public GetGalleryImagesInteractorImpl(FirebaseAccess firebaseAccess) {

        //this.firebaseAccess = new FirebaseDataAccess();
        this.firebaseAccess = firebaseAccess;
        listaGalleryImages = new ArrayList();
    }

    @Override
    public Observable<DataSnapshot> getGalleryImages() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_GALERIA);
    }

}

