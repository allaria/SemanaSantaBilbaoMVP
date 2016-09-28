package com.alf.android.semanasantabilbao.data;

import android.util.Log;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.cofradia.Constants.Constants;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaria on 27/09/2016.
 */

public class FirebaseDataAccess implements FirebaseAccess {

    private static final String LOG_TAG = FirebaseDataAccess.class.getSimpleName();
    private List<Cofradia> mCofradias = new ArrayList<>();

    public List<Cofradia> getListCofradias() {
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
        });

        //int i =0;
        //do{i++;}while(i<100000000);
        Log.d(LOG_TAG, "COMPROBANDO RETURN COFRADIAS: "+mCofradias.size()+" ELEMENTOS");
        return mCofradias;
    }
}
