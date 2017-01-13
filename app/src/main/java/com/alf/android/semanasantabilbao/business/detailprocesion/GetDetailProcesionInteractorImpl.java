package com.alf.android.semanasantabilbao.business.detailprocesion;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 15/11/2016.
 */

public class GetDetailProcesionInteractorImpl {

    private static final String LOG_TAG = GetDetailProcesionInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetDetailProcesionInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    public Observable<DataSnapshot> getPasos(String idCofradia) {
        return firebaseAccess.getFirebaseDataSnapshot(idCofradia, Constants.ConfigFireBase.FIREBASE_CHILD_PASOS);
    }
}
