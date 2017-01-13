package com.alf.android.semanasantabilbao.business.detailprocesion;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 15/11/2016.
 */

public interface GetDetailProcesionInteractor {

    Observable<DataSnapshot> getPasos(String idCofradia);

}
