package com.alf.android.semanasantabilbao.ui.contactos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.alf.android.semanasantabilbao.business.contacto.GetContactoInteractorImpl;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.firebase.client.DataSnapshot;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class ContactoPresenter implements ContactoContract.ContactoPresenter {

    private static final String LOG_TAG = ContactoPresenter.class.getSimpleName();
    private ContactoContract.ContactoView contratoView;
    private GetContactoInteractorImpl getContactoInteractor;
    private Subscription subscriptionContacto;
    private ObservableArrayList<Cofradia> listaContactos;
    private ObservableField<String> errorMessage;
    private boolean loading;

    public ContactoPresenter(GetContactoInteractorImpl getContactoInteractor) {

        this.getContactoInteractor = getContactoInteractor;
        listaContactos = new ObservableArrayList();
        errorMessage = new ObservableField();

        loading = false;
    }

    @Override
    public void attachContactosView(ContactoContract.ContactoView contratoView) {
        this.contratoView = contratoView;
    }

    @Override
    public void detachContactosView() {
        this.contratoView = null;
    }

    @Override
    public void unsuscribeContactosSuscription() {
        if (!subscriptionContacto.isUnsubscribed()) {
            subscriptionContacto.unsubscribe();
        }
    }

    @Override
    public void initPresenter() {
        if (loading) {
            setSpinnerAndLoadindText(loading);
        }

        if (!loading && listaContactos.isEmpty()) {
            //Log.d(LOG_TAG, "Contactos list is empty");
            loading = true;
            setSpinnerAndLoadindText(loading);
            Log.d(LOG_TAG, "Contactos list is empty. Getting Contactos from Firebase");
            subscriptionContacto = getContactoInteractor.getContactos().subscribe(subscriberContacto);
        } else {
            if (contratoView != null) {
                Log.d(LOG_TAG, "Contactos list not empty. Calling printContactos.");
                loading = false;
                setSpinnerAndLoadindText(loading);
                contratoView.printContactos(listaContactos);
            }
        }
    }

    private void setSpinnerAndLoadindText(boolean loadingSpinner) {
        if (contratoView != null) {
            this.contratoView.setSpinnerAndLoadingText(loadingSpinner);
        }
    }

    private Subscriber subscriberContacto = new Subscriber<DataSnapshot>() {
        @Override
        public void onNext(DataSnapshot snapshot) {

            Log.d(LOG_TAG, "Suscriber Contactos onNext. Adding Contactos ("+ snapshot.getChildrenCount() +") to listaContactos");
            listaContactos.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                listaContactos.add(dataSnapshot.getValue(Cofradia.class));
            }

            Log.d(LOG_TAG, "Contactos list generated. Calling printContactos.");
            contratoView.printContactos(listaContactos);
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onCompleted() {
            Log.d(LOG_TAG, "Suscriber Contactos onCompleted.");
            loading = false;
            setSpinnerAndLoadindText(loading);
        }

        @Override
        public void onError(Throwable e) {
            setSpinnerAndLoadindText(false);
            errorMessage.set(e.getMessage());
            Log.d(LOG_TAG, "Suscriber Contactos onError. Error while getting Contactos from Firebase. ("+ errorMessage.get() +")");
            contratoView.showErrorGettingContactos(errorMessage);
        }
    };
}
