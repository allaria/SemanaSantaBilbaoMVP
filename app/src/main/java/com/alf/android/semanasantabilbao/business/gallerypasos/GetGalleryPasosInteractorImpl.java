package com.alf.android.semanasantabilbao.business.gallerypasos;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public class GetGalleryPasosInteractorImpl implements GetGalleryPasosInteractor {

    private static final String LOG_TAG = GetGalleryPasosInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetGalleryPasosInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    @Override
    public Observable<DataSnapshot> getGalleryPasos() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_PASOS);
    }

}

