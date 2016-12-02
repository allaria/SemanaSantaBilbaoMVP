package com.alf.android.semanasantabilbao.data;

import android.util.Log;

import com.alf.android.semanasantabilbao.data.utils.Constants;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Alberto Laría Fernández on 27/09/2016.
 */

public class FirebaseDataAccess implements FirebaseAccess {

    private static final String LOG_TAG = FirebaseDataAccess.class.getSimpleName();
    private Firebase myFirebaseRef;

    public Observable<DataSnapshot> getFirebaseDataSnapshot (String cadenaConexion) {

        myFirebaseRef = new Firebase(Constants.ConfigFireBase.FIREBASE_URL + cadenaConexion);

        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber subscriber) {
                //final ValueEventListener listener = myFirebaseRef.addValueEventListener(new ValueEventListener() {
                myFirebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(LOG_TAG, "Firebase onDataChange. Sending datasnapshot to suscriber.");
                        subscriber.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(FirebaseError error) {
                        Log.d(LOG_TAG, "Firebase onCancelled. Error getting datasnapshot (" + error.getMessage() + ")");
                        Throwable fireBaseError = new Throwable(error.getMessage());
                        subscriber.onError(fireBaseError);
                    }

                });
            }
        });
    }

    public Observable<DataSnapshot> getFirebaseDataSnapshot(String idCofradia, String cadenaConexion) {

        myFirebaseRef = new Firebase(Constants.ConfigFireBase.FIREBASE_URL + cadenaConexion);
        final Query queryRef = myFirebaseRef.orderByChild("id_cofradia").equalTo(idCofradia);

        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber subscriber) {
                //final ValueEventListener listener = myFirebaseRef.addValueEventListener(new ValueEventListener() {
                queryRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(LOG_TAG, "Firebase onDataChange. Sending datasnapshot to suscriber.");
                        subscriber.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(FirebaseError error) {
                        Log.d(LOG_TAG, "Firebase onCancelled. Error getting datasnapshot (" + error.getMessage() + ")");
                        Throwable fireBaseError = new Throwable(error.getMessage());
                        subscriber.onError(fireBaseError);
                    }

                });
            }
        });
    }
}
