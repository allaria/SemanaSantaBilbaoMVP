package com.alf.android.semanasantabilbao.business.contacto;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public class GetContactoInteractorImpl implements GetContactoInteractor {

    private static final String LOG_TAG = GetContactoInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetContactoInteractorImpl(FirebaseAccess firebaseAccess) {
        this.firebaseAccess = firebaseAccess;
    }

    @Override
    public Observable<DataSnapshot> getContactos() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_COFRADIAS);
    }
}
