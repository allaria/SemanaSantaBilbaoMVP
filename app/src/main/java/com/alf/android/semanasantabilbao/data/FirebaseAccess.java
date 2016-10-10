package com.alf.android.semanasantabilbao.data;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by alaria on 27/09/2016.
 */

public interface FirebaseAccess {

    Observable<DataSnapshot> getFirebaseDataSnapshot();
}
