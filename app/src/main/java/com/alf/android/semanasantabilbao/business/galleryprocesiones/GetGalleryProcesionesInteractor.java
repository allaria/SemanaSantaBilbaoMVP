package com.alf.android.semanasantabilbao.business.galleryprocesiones;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public interface GetGalleryProcesionesInteractor {

    Observable<DataSnapshot> getGalleryProcesiones();

    Observable<DataSnapshot> getCofradia(String idCofradia);
}
