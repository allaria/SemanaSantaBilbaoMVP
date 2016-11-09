package com.alf.android.semanasantabilbao.ui.cofradias;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.cofradias.GetCofradiasInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.firebase.client.DataSnapshot;

import java.lang.reflect.Field;
import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alaria on 22/09/2016.
 */

public class CofradiaPresenter implements CofradiaContract.CofradiaPresenter {

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    private CofradiaContract.CofradiaView cofradiaView;
    private GetCofradiasInteractorImpl getCofradiasInteractor;
    private Subscription subscription;
    private ObservableArrayList<Cofradia> listaCofradias;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public CofradiaPresenter(GetCofradiasInteractorImpl getCofradiasInteractor) {

        //getCofradiasInteractor = new GetCofradiasInteractorImpl();
        this.getCofradiasInteractor = getCofradiasInteractor;

        listaCofradias = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void attachCofradiaView(CofradiaContract.CofradiaView cofradiaView) {
        this.cofradiaView = cofradiaView;
    }

    @Override
    public void detachCofradiaView() {
        this.cofradiaView = null;
    }

    @Override
    public void unsuscribeCofradiaSuscription() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void initPresenter(boolean connection, Field[] fields) {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaCofradias.isEmpty()) {
            Log.d(LOG_TAG, "initPresenter - IF loading"+!loading+" - isEmpty: "+listaCofradias.isEmpty());
            loading = true;
            setSpinnerAndLoadindText(loading);
            if (connection) {
                Log.d(LOG_TAG, "initPresenter - getCofradias Firebase");
                subscription = getCofradiasInteractor.getCofradias().subscribe(subscriber);
            } else{
                Log.d(LOG_TAG, "initPresenter - getCofradiasSinConexion");
                getCofradiasSinConexion(fields);
            }
        } else {
            if (cofradiaView != null) {
                Log.d(LOG_TAG, "initPresenter - printPasos CACHE");
                Log.d(LOG_TAG, "initPresenter - ELSE loading"+!loading+" - isEmpty: "+listaCofradias.isEmpty());
                cofradiaView.printCofradias(listaCofradias);
            }
        }
    }


    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (cofradiaView != null) {
            this.cofradiaView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }


    private void getCofradiasSinConexion(Field[] fields) {
        String idCofradia, nombreCofradia, homeResourceName, escudoResourceName;
        //ArrayList<Cofradia> listaCofradias = new ArrayList();

        for (int i = 0, max = fields.length; i < max; i++) {
            try {
                if (fields[i].getName().substring(0,4).equals("home")) {
                    homeResourceName = fields[i].getName();
                    escudoResourceName = fields[i].getName().replace("home", "e");

                    nombreCofradia = replaceCharacters(fields[i].getName().substring(7,fields[i].getName().length()));
                    nombreCofradia = capitalizeString(nombreCofradia.replace("_", " "));

                    idCofradia = "00"+String.valueOf(fields[i].getName().charAt(5))+"-COF";
                    idCofradia = checkIdCofradia (idCofradia);

                    //Log.d(LOG_TAG, "getCofradiasSinConexion:" + idCofradia + ":" + nombreCofradia + ":" + homeResourceName + ":" + escudoResourceName);

                    Cofradia cofradia = new Cofradia(idCofradia, nombreCofradia, escudoResourceName, homeResourceName);
                    listaCofradias.add(cofradia);
                }
            } catch (Exception e) {
                Log.d(LOG_TAG, "getCofradiasSinConexion ERROR: " + e.getMessage());
                continue;
            }
        }

        cofradiaView.printCofradiasWithoutConnection(listaCofradias);

        setSpinnerAndLoadindText(false);
    }


    private String capitalizeString (String cadena) {
        //Log.d(LOG_TAG, "CADENA capitalizeString:"+cadena);
        StringBuffer res = new StringBuffer();

        String[] strArr = cadena.split(" ");
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }

        return res.toString();
    }

    private String replaceCharacters(String cadena) {
        //Log.d(LOG_TAG, "CADENA replaceCharacters:"+cadena);
        if (cadena.contains("__a__")) cadena = cadena.replace("__a__", "á");
        if (cadena.contains("__e__")) cadena = cadena.replace("__e__", "é");
        if (cadena.contains("__i__")) cadena = cadena.replace("__i__", "í");
        if (cadena.contains("__o__")) cadena = cadena.replace("__o__", "ó");
        if (cadena.contains("__u__")) cadena = cadena.replace("__u__", "ú");
        if (cadena.contains("__n__")) cadena = cadena.replace("__n__", "ñ");

        return cadena;
    }


    private String checkIdCofradia(String cadena) {
        //Log.d(LOG_TAG, "CADENA checkIdCofradia:"+cadena);
        if (cadena.length() > 7) return cadena.substring(1, cadena.length());

        return cadena;
    }


    private Subscriber subscriber = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {
            Log.d(LOG_TAG, "onNext");

            listaCofradias.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                //Log.d(LOG_TAG, "RECUPERANDO COFRADIAS");
                listaCofradias.add(dataSnapshot.getValue(Cofradia.class));
            }

            cofradiaView.printCofradias(listaCofradias);

            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "onCompleted");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(LOG_TAG, "onError");
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            cofradiaView.showErrorGettingCofradias(errorMessage);

            //Generate the Cofradias list from local
            getCofradiasSinConexion(cofradiaView.getDrawablesList());
        }
    };

}
