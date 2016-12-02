package com.alf.android.semanasantabilbao.business.detailcofradia;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 15/11/2016.
 */

public class GetDetailCofradiaInteractorImpl {

    private static final String LOG_TAG = GetDetailCofradiaInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetDetailCofradiaInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    public Observable<DataSnapshot> getProcesiones(String idCofradia) {
        return firebaseAccess.getFirebaseDataSnapshot(idCofradia, Constants.ConfigFireBase.FIREBASE_CHILD_PROCESIONES);
    }

    public Observable<DataSnapshot> getPasos(String idCofradia) {
        return firebaseAccess.getFirebaseDataSnapshot(idCofradia, Constants.ConfigFireBase.FIREBASE_CHILD_PASOS);
    }

    public Observable<DataSnapshot> getImagenesGaleria(String idCofradia) {
        return firebaseAccess.getFirebaseDataSnapshot(idCofradia, Constants.ConfigFireBase.FIREBASE_CHILD_GALERIA);
    }

    public Observable<DataSnapshot> getCofradia(String idCofradia) {
        return firebaseAccess.getFirebaseDataSnapshot(idCofradia, Constants.ConfigFireBase.FIREBASE_CHILD_COFRADIAS);
    }
}
