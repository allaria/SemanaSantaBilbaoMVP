package com.alf.android.semanasantabilbao.business.cofradias;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;

import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by alaria on 26/09/2016.
 */

public class GetCofradiasInteractorImpl implements GetCofradiasInteractor {

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    private FirebaseAccess firebaseAccess;
    private String mensajeErrorFirebase;
    private List<Cofradia> listaCofradias;

    public GetCofradiasInteractorImpl(FirebaseAccess firebaseAccess) {

        //this.firebaseAccess = new FirebaseDataAccess();
        this.firebaseAccess = firebaseAccess;
        listaCofradias = new ArrayList();
    }


    public Observable<DataSnapshot> getCofradias() {

    return firebaseAccess.getFirebaseDataSnapshot();
    }
}

