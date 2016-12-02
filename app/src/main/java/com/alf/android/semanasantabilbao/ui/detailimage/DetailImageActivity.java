package com.alf.android.semanasantabilbao.ui.detailimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.ui.detailimage.adapter.DetailImageAdapter;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laria Fernández on 30/11/2016.
 */

public class DetailImageActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailImageActivity.class.getSimpleName();
    private DetailImageAdapter adapter;
    private int position;
    private ArrayList<String> imagePathsList = new ArrayList<>();;

    @BindView(R.id.detail_image_progress_bar) ProgressBar spinner;
    @BindView(R.id.detail_image_loading_text) TextView loadingText;
    @BindView(R.id.detail_image_toolbar) Toolbar toolbar;
    @BindView(R.id.detail_image_view_pager) ViewPager viewPager;
    @BindString(R.string.firebase_error_detail_image) String firebaseErrorDetailImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_image_activity);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinnerAndLoadingText(false);

        //utils = new Utils(getApplicationContext());
        //imagePathsList.add("https://firebasestorage.googleapis.com/v0/b/mr-cofrade.appspot.com/o/Galeria%2Fp_svc_entierro_01.png?alt=media&token=31a642a9-7ec5-46a3-a475-a924d511cbab");
        //imagePathsList.add("https://firebasestorage.googleapis.com/v0/b/mr-cofrade.appspot.com/o/Galeria%2Fp_svc_entierro_02.png?alt=media&token=069cdc01-6bf5-4da7-abaf-3f188e9bcdab");
        //imagePathsList.add("https://firebasestorage.googleapis.com/v0/b/mr-cofrade.appspot.com/o/Galeria%2Fp_svc_entierro_03.png?alt=media&token=2ef4df37-e31e-42bc-8f92-7e087e68c56b");

        Intent intent = getIntent();

        position = (int) intent.getSerializableExtra("position");
        imagePathsList = (ArrayList<String>) intent.getSerializableExtra("imagesPath");
        //position = intent.getIntExtra("position", 0);
        //imagePathsList = intent.getIntExtra("imagesPath", "0");

        adapter = new DetailImageAdapter(DetailImageActivity.this, imagePathsList);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);

    }

    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }
}
