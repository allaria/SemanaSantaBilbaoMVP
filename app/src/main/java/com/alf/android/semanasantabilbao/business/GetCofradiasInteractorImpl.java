package com.alf.android.semanasantabilbao.business;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.FirebaseDataAccess;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.cofradia.CofradiaActivity;
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

    public GetCofradiasInteractorImpl() {

        this.firebaseAccess = new FirebaseDataAccess();
        listaCofradias = new ArrayList();
    }


    public Observable<DataSnapshot> getCofradias() {

    return firebaseAccess.getFirebaseDataSnapshot();
//        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
//            @Override
//            public void call(final Subscriber subscriber) {
//                final ValueEventListener listener = firebaseAccess.getFirebaseConection().addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Log.d(LOG_TAG, "onDataChange");
//                        subscriber.onNext(dataSnapshot);
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError error) {
//                        Log.d(LOG_TAG, "onCancelled");
//                        subscriber.onError(error.toException().getCause());
//                    }
//
//                });
//            }
//        });
    }
}

