package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.constants.Constants;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.ViewPagerCofradiaDetailAdpter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto on 24/10/2016.
 */

public class DetailCofradiaActivity extends AppCompatActivity {

    private final String LOG_TAG = DetailCofradiaActivity.class.getSimpleName();
    private Cofradia cofradia;

    @BindView(R.id.detail_cofradia_toolbar) Toolbar toolbar;
    @BindView(R.id.detail_cofradia_tablayout) TabLayout tabLayout;
    @BindView(R.id.detail_cofradia_escudo_toolbar) ImageView mEscudoDetailPhoto;
    @BindView(R.id.detail_cofradia_name_toolbar) TextView mNombreCofradia;
    @BindView(R.id.viewpager_detail) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_cofradia_activity);

        Intent intent = getIntent();
        cofradia = (Cofradia) intent.getSerializableExtra(Constants.REFERENCE.COFRADIA);

        ButterKnife.bind(this);

        //toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String detailImg = cofradia.getImagenEscudo();
        int idDrawable = getResources().getIdentifier(detailImg, "drawable", getApplicationContext().getPackageName());
        Picasso.with(getApplicationContext()).load(idDrawable).into(mEscudoDetailPhoto);
        mNombreCofradia.setText(cofradia.getNombreCofradia());

        final ViewPagerCofradiaDetailAdpter viewPagerDetailAdpter = new ViewPagerCofradiaDetailAdpter(this.getApplication(), this.getApplicationContext(), cofradia);
        viewPager.setAdapter(viewPagerDetailAdpter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.d(LOG_TAG, "XXXsetCurrentItem"+tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //detach vista
                Log.d(LOG_TAG, "XXXliberarVistaPaso"+tab.getPosition());
                viewPagerDetailAdpter.liberarVistaPaso();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

/*    @Override
    public void onClick(int position, ProcesionAdapter mProcesionAdapter) {

        Toast.makeText(getApplicationContext(), "Selected Date:", Toast.LENGTH_LONG).show();

        Procesion selectedProcesion = mProcesionAdapter.getSelectedProcesion(position);
        Intent intent = new Intent(DetailCofradiaActivity.this, DetailProcesionActivity.class);
        intent.putExtra(Constants.REFERENCE.PROCESION, selectedProcesion);
        intent.putExtra(Constants.REFERENCE.COFRADIA, cofradia);
        startActivity(intent);
    }*/
}
