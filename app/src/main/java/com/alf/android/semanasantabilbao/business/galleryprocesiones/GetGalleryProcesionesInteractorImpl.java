package com.alf.android.semanasantabilbao.business.galleryprocesiones;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public class GetGalleryProcesionesInteractorImpl implements GetGalleryProcesionesInteractor {

    private static final String LOG_TAG = GetGalleryProcesionesInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetGalleryProcesionesInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    @Override
    public Observable<DataSnapshot> getGalleryProcesiones() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_PROCESIONES);
    }

}

