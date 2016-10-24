package com.alf.android.semanasantabilbao.data;

import android.util.Log;

import com.alf.android.semanasantabilbao.ui.cofradia.Constants.Constants;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by alaria on 27/09/2016.
 */

public class FirebaseDataAccess implements FirebaseAccess {

    private static final String LOG_TAG = FirebaseDataAccess.class.getSimpleName();
    private Firebase myFirebaseRef;


    public Observable<DataSnapshot> getFirebaseDataSnapshot () {

        myFirebaseRef = new Firebase(Constants.ConfigFireBase.FIREBASE_URL + Constants.ConfigFireBase.FIREBASE_CHILD_COFRADIAS);

        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber subscriber) {
                //final ValueEventListener listener = myFirebaseRef.addValueEventListener(new ValueEventListener() {
                myFirebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(LOG_TAG, "onDataChange");
                        subscriber.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(FirebaseError error) {
                        Log.d(LOG_TAG, "onCancelled ERROR: " + error.getMessage());

                        Throwable fireBaseError = new Throwable(error.getMessage());
                        subscriber.onError(fireBaseError);
                    }

                });
            }
        });




        //return myFirebaseRef;
    }
}
