package com.alf.android.semanasantabilbao.ui.detailprocesion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.ui.detailprocesion.adapter.ViewPagerProcesionDetailAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alaria on 10/01/2017.
 */

public class DetailProcesionActivity extends AppCompatActivity {

    private final String LOG_TAG = DetailProcesionActivity.class.getSimpleName();
    private Cofradia cofradia;
    private Procesion procesion;
    private ViewPagerProcesionDetailAdapter viewPagerProcesionDetailAdpter;

    @BindView(R.id.detail_procesion_toolbar) Toolbar toolbar;
    @BindView(R.id.detail_procesion_tablayout) TabLayout tabLayout;
    @BindView(R.id.detail_procesion_logo_toolbar) ImageView mCofradiaLogo;
    @BindView(R.id.detail_procesion_name_toolbar) TextView mCofradiaName;
    @BindView(R.id.viewpager_detail_procesion) ViewPager viewPager;
    @BindString(R.string.COFRADIA) String intentCofradia;
    @BindString(R.string.PROCESION) String intentProcesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_procesion_activity);
        //((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        cofradia = (Cofradia) intent.getSerializableExtra(intentCofradia);
        procesion = (Procesion) intent.getSerializableExtra(intentProcesion);

        //toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String detailImg = cofradia.getImagenEscudo();
        int idDrawable = getResources().getIdentifier(detailImg, "drawable", getApplicationContext().getPackageName());
        Picasso.with(getApplicationContext())
                .load(idDrawable)
                .into(mCofradiaLogo);
        mCofradiaName.setText(cofradia.getNombreCofradia());

        loadDetailProcesion();

    }

    private void loadDetailProcesion() {
        viewPagerProcesionDetailAdpter = new ViewPagerProcesionDetailAdapter(this.getApplicationContext(), procesion, cofradia);
        viewPager.setAdapter(viewPagerProcesionDetailAdpter);
        //viewPagerProcesionDetailAdpter.setViewPagerCofradiaDetailAdapterClickListener(this);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(LOG_TAG, "Detach View. Tab Unselected:" + tab.getPosition());
                viewPagerProcesionDetailAdpter.detachViewUnsuscribeSuscription(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

/*    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }*/

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
