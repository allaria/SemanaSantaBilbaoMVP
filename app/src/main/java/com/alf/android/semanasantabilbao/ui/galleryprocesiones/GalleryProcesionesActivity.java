package com.alf.android.semanasantabilbao.ui.galleryprocesiones;

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
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.ui.galleryprocesiones.adapter.GalleryProcesionesAdapter;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class GalleryProcesionesActivity extends AppCompatActivity implements GalleryProcesionesContract.GalleryProcesionesView,
        GalleryProcesionesAdapter.GalleryProcesionesClickListener {

    private static final String LOG_TAG = GalleryProcesionesActivity.class.getSimpleName();
    //private GalleryProcesionesAdapter galleryProcesionesAdapter;
    //private GalleryProcesionesContract.GalleryProcesionesPresenter galleryProcesionesPresenter;

    @Inject GalleryProcesionesContract.GalleryProcesionesPresenter galleryProcesionesPresenter;
    @Inject GalleryProcesionesAdapter galleryProcesionesAdapter;

    @BindView(R.id.gallery_procesion_progress_bar) ProgressBar spinner;
    @BindView(R.id.gallery_procesion_loading_text) TextView loadingText;
    @BindView(R.id.gallery_procesion_toolbar) Toolbar toolbar;
    @BindView(R.id.gallery_procesion_recycler_view) RecyclerView mRecyclerView;

    @BindString(R.string.firebase_error_gallery_procesiones) String firebaseErrorGalleryProcesiones;
    @BindDrawable(R.drawable.no_image) Drawable idDrawableNoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_procesion_activity);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinnerAndLoadingText(false);
        initRecyclerViewGalleryProcesiones();

        //galleryProcesionesPresenter = new GalleryProcesionesPresenter();
        galleryProcesionesPresenter.attachGalleryProcesionesView(this);
        galleryProcesionesPresenter.initPresenter();
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printGalleryProcesiones(ObservableArrayList<Procesion> mGalleryProcesiones) {
        Log.d(LOG_TAG, "Number of Gallery Procesiones in the recyclerView: " + mGalleryProcesiones.size() + " (printGalleryProcesiones)");
        ((GalleryProcesionesAdapter) mRecyclerView.getAdapter()).addGalleryPasos(mGalleryProcesiones);
    }

    @Override
    public void showErrorGettingGalleryProcesiones(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Toast. Error getting Gallery Procesiones from Firebase. (" + firebaseErrorGalleryProcesiones + ")");
        Toast.makeText(this, firebaseErrorGalleryProcesiones + "(" + mensajeError.get() + ")", Toast.LENGTH_LONG).show();
    }

    private void initRecyclerViewGalleryProcesiones() {
        mRecyclerView.setHasFixedSize(true);
        if (new GlobalFunctions().getScreenOrientation(getResources().getConfiguration().orientation).equals("Landscrape")) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        }

        //galleryProcesionesAdapter = new GalleryProcesionesAdapter(this);
        galleryProcesionesAdapter.setGalleryProcesionesClickListener(this);
        mRecyclerView.setAdapter(galleryProcesionesAdapter);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && galleryProcesionesPresenter != null) {
            Log.d(LOG_TAG, "Detach View & Unsuscribe Suscription.");
            galleryProcesionesPresenter.unsuscribeGalleryProcesionesSuscription();
            galleryProcesionesPresenter.detachGalleryProcesionesView();
        }
    }
}
