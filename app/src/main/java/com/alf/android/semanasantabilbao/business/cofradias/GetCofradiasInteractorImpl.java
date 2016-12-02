package com.alf.android.semanasantabilbao.business.cofradias;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public class GetCofradiasInteractorImpl implements GetCofradiasInteractor {

    private static final String LOG_TAG = GetCofradiasInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetCofradiasInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    @Override
    public Observable<DataSnapshot> getCofradias() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_COFRADIAS);
    }

    @Override
    public Observable<DataSnapshot> getGooglePlayUrl() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_CONFIGURACION);
    }
}

