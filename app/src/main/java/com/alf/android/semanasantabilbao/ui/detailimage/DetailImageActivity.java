package com.alf.android.semanasantabilbao.ui.detailimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

public class DetailImageActivity extends AppCompatActivity implements DetailImageAdapter.DetailImageClickListener{

    private static final String LOG_TAG = DetailImageActivity.class.getSimpleName();
    private DetailImageAdapter adapter;
    private int position;
    private ArrayList<String> imagePathsList;

    @BindView(R.id.detail_image_progress_bar) ProgressBar spinner;
    @BindView(R.id.detail_image_loading_text) TextView loadingText;
    @BindView(R.id.detail_image_toolbar) Toolbar toolbar;
    @BindView(R.id.detail_image_view_pager) ViewPager viewPager;
    @BindString(R.string.firebase_error_detail_image) String firebaseErrorDetailImage;
    @BindString(R.string.POSITION) String intentPosition;
    @BindString(R.string.IMAGESPATH) String intentImagesPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_image_activity);
        ButterKnife.bind(this);

        imagePathsList = new ArrayList<>();

        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinnerAndLoadingText(false);

        Intent intent = getIntent();
        position = (int) intent.getSerializableExtra(intentPosition);
        imagePathsList = (ArrayList<String>) intent.getSerializableExtra(intentImagesPath);

        adapter = new DetailImageAdapter(DetailImageActivity.this, imagePathsList);
        adapter.setDetailImageClickListener(this);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void setViewPagerItem(int i) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+i, true);
    }

    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
