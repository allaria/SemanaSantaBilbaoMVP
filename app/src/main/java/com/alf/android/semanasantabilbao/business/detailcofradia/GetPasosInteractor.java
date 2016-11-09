package com.alf.android.semanasantabilbao.business.detailcofradia;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by alaria on 26/09/2016.
 */

public interface GetPasosInteractor {

    Observable<DataSnapshot> getPasos();
}
