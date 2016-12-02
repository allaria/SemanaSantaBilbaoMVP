package com.alf.android.semanasantabilbao.business.detailcofradia;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 15/11/2016.
 */

public interface GetDetailCofradiaInteractor {

    Observable<DataSnapshot> getProcesiones(String idCofradia);

    Observable<DataSnapshot> getPasos(String idCofradia);

    Observable<DataSnapshot> getImagenesGaleria(String idCofradia);

    Observable<DataSnapshot> getCofradia(String idCofradia);
}
