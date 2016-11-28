package com.alf.android.semanasantabilbao.business.gallerypasos;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by alaria on 26/09/2016.
 */

public class GetGalleryPasosInteractorImpl implements GetGalleryPasosInteractor {

    private static final String LOG_TAG = GetGalleryPasosInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;
    private String mensajeErrorFirebase;
    private List<Paso> listaGalleryPasos;

    public GetGalleryPasosInteractorImpl(FirebaseAccess firebaseAccess) {

        //this.firebaseAccess = new FirebaseDataAccess();
        this.firebaseAccess = firebaseAccess;
        listaGalleryPasos = new ArrayList();
    }

    @Override
    public Observable<DataSnapshot> getGalleryPasos() {
        return firebaseAccess.getFirebaseDataSnapshot(Constants.ConfigFireBase.FIREBASE_CHILD_PASOS);
    }

}

