package com.alf.android.semanasantabilbao.business.contacto;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public interface GetContactoInteractor {

    Observable<DataSnapshot> getContactos();
}
