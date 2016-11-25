package com.alf.android.semanasantabilbao.business.detailcofradia;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.entities.ImagenGaleria;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.data.utils.Constants;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by alaria on 15/11/2016.
 */

public class GetDetailCofradiaInteractorImpl {

    private static final String LOG_TAG = GetDetailCofradiaInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;
    private String mensajeErrorFirebase;
    private List<Procesion> listaProcesiones;
    private List<Paso> listaPasos;
    private List<ImagenGaleria> listaImagenesGaleria;

    public GetDetailCofradiaInteractorImpl(FirebaseAccess firebaseAccess) {

        //this.firebaseAccess = new FirebaseDataAccess();
        this.firebaseAccess = firebaseAccess;
        listaProcesiones = new ArrayList();
        listaPasos = new ArrayList();
        listaImagenesGaleria = new ArrayList();

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
