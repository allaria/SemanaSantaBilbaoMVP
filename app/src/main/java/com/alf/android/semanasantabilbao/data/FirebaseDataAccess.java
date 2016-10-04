package com.alf.android.semanasantabilbao.data;

import com.alf.android.semanasantabilbao.ui.cofradia.Constants.Constants;
import com.firebase.client.Firebase;


/**
 * Created by alaria on 27/09/2016.
 */

public class FirebaseDataAccess implements FirebaseAccess {

    private static final String LOG_TAG = FirebaseDataAccess.class.getSimpleName();


    public Firebase getFirebaseConection () {

        Firebase myFirebaseRef = new Firebase(Constants.ConfigFireBase.FIREBASE_URL + Constants.ConfigFireBase.FIREBASE_CHILD_COFRADIAS);
        return myFirebaseRef;
    }
}

/*    public Observable<List<Cofradia>> getListCofradias() {
        return Observable.just(getmCofradias())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public List<Cofradia> getmCofradias() {

        Firebase myFirebaseRef = new Firebase(Constants.ConfigFireBase.FIREBASE_URL + Constants.ConfigFireBase.FIREBASE_CHILD_COFRADIAS);
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Log.d(LOG_TAG, "RECUPERANDO COFRADIAS");
                    mCofradias.add(dataSnapshot.getValue(Cofradia.class));
                }


            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });*/

        //return mCofradias;

        //do{
        //    Log.d(LOG_TAG, "COMPROBANDO RETURN COFRADIAS: "+mCofradias.size()+" ELEMENTOS");
        //}while(!listo);
        //Log.d(LOG_TAG, "COMPROBANDO RETURN COFRADIAS: "+mCofradias.size()+" ELEMENTOS");

//        return Observable.just(mCofradias)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//}
