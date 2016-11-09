package com.alf.android.semanasantabilbao.business.detailcofradia;

import com.alf.android.semanasantabilbao.business.cofradias.GetCofradiasInteractor;
import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.FirebaseDataAccess;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by alaria on 26/09/2016.
 */

public class GetPasosInteractorImpl implements GetPasosInteractor {

    private static final String LOG_TAG = GetPasosInteractorImpl.class.getSimpleName();
    private FirebaseAccess firebaseAccess;
    private String mensajeErrorFirebase;
    private List<Paso> listaPasos;

    public GetPasosInteractorImpl() {

        this.firebaseAccess = new FirebaseDataAccess();
        //this.firebaseAccess = firebaseAccess;
        listaPasos = new ArrayList();
    }


    public Observable<DataSnapshot> getPasos() {

    return firebaseAccess.getFirebaseDataSnapshotPasos();
    }
}

