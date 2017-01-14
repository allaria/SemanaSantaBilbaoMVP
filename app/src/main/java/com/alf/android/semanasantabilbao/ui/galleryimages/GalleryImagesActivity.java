package com.alf.android.semanasantabilbao.ui.galleryimages;

import android.content.Intent;
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
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.alf.android.semanasantabilbao.ui.detailimage.DetailImageActivity;
import com.alf.android.semanasantabilbao.ui.galleryimages.adapter.GalleryImagesAdapter;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 25/11/2016.
 */

public class GalleryImagesActivity extends AppCompatActivity implements GalleryImagesContract.GalleryImagesView,
        GalleryImagesAdapter.GalleryImagesClickListener {

    private static final String LOG_TAG = GalleryImagesActivity.class.getSimpleName();
    private ObservableArrayList<String> listaGalleryImagesPaths;
    //private GalleryImagesAdapter galleryImagesAdapter;
    //private GalleryImagesContract.GalleryImagesPresenter galleryImagesPresenter;

    @Inject GalleryImagesContract.GalleryImagesPresenter galleryImagesPresenter;
    @Inject GalleryImagesAdapter galleryImagesAdapter;

    @BindView(R.id.gallery_images_progress_bar) ProgressBar spinner;
    @BindView(R.id.gallery_images_loading_text) TextView loadingText;
    @BindView(R.id.gallery_images_toolbar) Toolbar toolbar;
    @BindView(R.id.gallery_images_recycler_view) RecyclerView mRecyclerView;

    @BindString(R.string.firebase_error_gallery_images) String firebaseErrorGalleryImages;
    @BindString(R.string.POSITION) String intentPosition;
    @BindString(R.string.IMAGESPATH) String intentImagesPath;
    @BindDrawable(R.drawable.no_image) Drawable idDrawableNoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_images_activity);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinnerAndLoadingText(false);
        initRecyclerViewGalleryImages();

        //galleryImagesPresenter = new GalleryImagesPresenter();
        galleryImagesPresenter.attachGalleryImagesView(this);
        galleryImagesPresenter.initPresenter();
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printGalleryImages(ObservableArrayList<GalleryImage> mGalleryImages) {
        Log.d(LOG_TAG, "Number of Gallery Images in the recyclerView: " + mGalleryImages.size() + " (printGalleryImages)");
        ((GalleryImagesAdapter) mRecyclerView.getAdapter()).addGalleryImages(mGalleryImages);
    }

    @Override
    public void setGalleryImagesPaths(ObservableArrayList<String> listaGalleryImagesPaths) {
        this.listaGalleryImagesPaths = listaGalleryImagesPaths;
    }

    @Override
    public void showErrorGettingGalleryImages(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Toast. Error getting Gallery Images from Firebase. (" + firebaseErrorGalleryImages + ")");
        Toast.makeText(this, firebaseErrorGalleryImages + "(" + mensajeError.get() + ")", Toast.LENGTH_LONG).show();
    }

    private void initRecyclerViewGalleryImages() {
        mRecyclerView.setHasFixedSize(true);
        if (new GlobalFunctions().getScreenOrientation(getResources().getConfiguration().orientation).equals("Landscrape")) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        }

        //galleryImagesAdapter = new GalleryImagesAdapter(this);
        galleryImagesAdapter.setGalleryImagesClickListener(this);
        mRecyclerView.setAdapter(galleryImagesAdapter);
    }

    @Override
    public void onClick(int position) {
        //Toast.makeText(getApplicationContext(), "CLICK - "+LOG_TAG, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), DetailImageActivity.class);
        intent.putExtra(intentPosition, position);
        intent.putExtra(intentImagesPath, listaGalleryImagesPaths);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && galleryImagesPresenter != null) {
            Log.d(LOG_TAG, "Detach View & Unsuscribe Suscription.");
            galleryImagesPresenter.unsuscribeGalleryImagesSuscription();
            galleryImagesPresenter.detachGalleryImagesView();
        }
    }

}
