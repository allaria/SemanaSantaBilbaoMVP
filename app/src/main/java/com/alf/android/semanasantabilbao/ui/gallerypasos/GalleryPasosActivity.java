package com.alf.android.semanasantabilbao.ui.gallerypasos;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.alf.android.semanasantabilbao.ui.gallerypasos.adapter.GalleryPasosAdapter;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class GalleryPasosActivity extends AppCompatActivity implements GalleryPasosContract.GalleryPasosView,
        GalleryPasosAdapter.GalleryPasosClickListener {

    private static final String LOG_TAG = GalleryPasosActivity.class.getSimpleName();
    //private GalleryImagesAdapter galleryImagesAdapter;
    //private GalleryImagesContract.GalleryImagesPresenter galleryImagesPresenter;

    @Inject GalleryPasosContract.GalleryPasosPresenter galleryPasosPresenter;
    @Inject GalleryPasosAdapter galleryPasosAdapter;

    @BindView(R.id.gallery_pasos_progress_bar) ProgressBar spinner;
    @BindView(R.id.gallery_pasos_loading_text) TextView loadingText;
    @BindView(R.id.gallery_pasos_toolbar) Toolbar toolbar;
    @BindView(R.id.gallery_pasos_recycler_view) RecyclerView mRecyclerView;

    @BindString(R.string.firebase_error_gallery_pasos) String firebaseErrorGalleryPasos;
    @BindDrawable(R.drawable.no_image) Drawable idDrawableNoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_pasos_activity);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinnerAndLoadingText(false);
        initRecyclerViewGalleryPasos();

        //galleryImagesPresenter = new GalleryImagesPresenter();
        galleryPasosPresenter.attachGalleryPasosView(this);
        galleryPasosPresenter.initPresenter();
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printGalleryPasos(ObservableArrayList<Paso> mGalleryPasos) {
        Log.d(LOG_TAG, "Number of Gallery Pasos in the recyclerView: " + mGalleryPasos.size() + " (printGalleryPasos)");
        ((GalleryPasosAdapter) mRecyclerView.getAdapter()).addGalleryPasos(mGalleryPasos);
    }

    @Override
    public void showErrorGettingGalleryPasos(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Toast. Error getting Gallery Pasos from Firebase. (" + firebaseErrorGalleryPasos + ")");
        Toast.makeText(this, firebaseErrorGalleryPasos + "(" + mensajeError.get() + ")", Toast.LENGTH_LONG).show();
    }

    private void initRecyclerViewGalleryPasos() {
        mRecyclerView.setHasFixedSize(true);
        if (new GlobalFunctions().getScreenOrientation(getResources().getConfiguration().orientation).equals("Landscrape")) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        }

        //galleryImagesAdapter = new GalleryImagesAdapter(this);
        galleryPasosAdapter.setGalleryPasosClickListener(this);
        mRecyclerView.setAdapter(galleryPasosAdapter);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && galleryPasosPresenter != null) {
            Log.d(LOG_TAG, "Detach View & Unsuscribe Suscription.");
            galleryPasosPresenter.unsuscribeGalleryPasosSuscription();
            galleryPasosPresenter.detachGalleryPasosView();
        }
    }
}
