package com.alf.android.semanasantabilbao.ui.contactos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.contactos.adapter.ContactoAdapter;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class ContactoActivity extends AppCompatActivity implements ContactoContract.ContactoView,
        ContactoAdapter.ContactoClickListener {

    private static final String LOG_TAG = ContactoActivity.class.getSimpleName();

    @Inject ContactoContract.ContactoPresenter contactoPresenter;
    @Inject ContactoAdapter contactoAdapter;

    @BindView(R.id.contacto_progress_bar) ProgressBar spinner;
    @BindView(R.id.contacto_loading_text) TextView loadingText;
    @BindView(R.id.contacto_toolbar) Toolbar toolbar;
    @BindView(R.id.contacto_recycler_view) RecyclerView mRecyclerView;

    @BindString(R.string.firebase_error_contactos) String firebaseErrorContacto;
    @BindDrawable(R.drawable.no_image) Drawable idDrawableNoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto_activity);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinnerAndLoadingText(false);
        initRecyclerViewContactos();

        contactoPresenter.attachContactosView(this);
        contactoPresenter.initPresenter();
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printContactos(ObservableArrayList<Cofradia> mContactos) {
        Log.d(LOG_TAG, "Number of Contactos in the recyclerView: " + mContactos.size() + " (printGalleryContactos)");
        ((ContactoAdapter) mRecyclerView.getAdapter()).addContactos(mContactos);
    }

    @Override
    public void showErrorGettingContactos(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Toast. Error getting Contactos from Firebase. (" + firebaseErrorContacto + ")");
        Toast.makeText(this, firebaseErrorContacto + "(" + mensajeError.get() + ")", Toast.LENGTH_LONG).show();
    }

    private void initRecyclerViewContactos() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        contactoAdapter.setContactoClickListener(this);
        mRecyclerView.setAdapter(contactoAdapter);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && contactoPresenter != null) {
            Log.d(LOG_TAG, "Detach View & Unsuscribe Suscription.");
            contactoPresenter.unsuscribeContactosSuscription();
            contactoPresenter.detachContactosView();
        }
    }
}
