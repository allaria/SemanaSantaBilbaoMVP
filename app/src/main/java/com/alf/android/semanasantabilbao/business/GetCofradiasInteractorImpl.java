package com.alf.android.semanasantabilbao.business;

import android.util.Log;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.FirebaseDataAccess;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.cofradia.CofradiaActivity;

import java.util.List;

/**
 * Created by alaria on 26/09/2016.
 */

public class GetCofradiasInteractorImpl implements GetCofradiasInteractor {

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    private FirebaseAccess firebaseAccess;

    public GetCofradiasInteractorImpl() {
        this.firebaseAccess = new FirebaseDataAccess();
    }

    public List<Cofradia> getCofradias() {
        List<Cofradia> lista = firebaseAccess.getListCofradias();

        Log.d(LOG_TAG, "COMPROBANDO COFRADIAS (getCofradias): "+lista.size()+" ELEMENTOS");

//        int i =0;
//        do{i++;}while(i<100000000);
        return lista;
        //return firebaseAccess.getListCofradias();
    }
}
