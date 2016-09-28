package com.alf.android.semanasantabilbao.ui.cofradia;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.cofradia.adapter.CofradiaAdapter;
import com.firebase.client.Firebase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alaria on 22/09/2016.
 */

public class CofradiaActivity extends AppCompatActivity implements CofradiaContract.CofradiaView,
        CofradiaAdapter.CofradiaClickListener,
        NavigationView.OnNavigationItemSelectedListener{

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    private CofradiaAdapter mCofradiaAdapter;
    private CofradiaContract.CofradiaPresenter cofradiaPresenter;

    @BindView(R.id.cofradia_progress_bar) ProgressBar spinner;
    @BindView(R.id.cofradia_toolbar) Toolbar toolbar;
    @BindView(R.id.cofradia_drawer_layout) DrawerLayout drawer;
    @BindView(R.id.cofradia_navigation_view) NavigationView navigationView;
    @BindView(R.id.cofradia_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.cofradia_top_imagen) ImageView mTopImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cofradia_activity);
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        initRecyclerViewCofradias();
        initPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            cofradiaPresenter.detachCofradiaView();
        }
    }

    @Override
    public void setSpinner(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printCofradias(List<Cofradia> mCofradias) {
        Log.d(LOG_TAG, "PINTANDO COFRADIAS (printCofradias): "+mCofradias.size()+" ELEMENTOS");
        ((CofradiaAdapter) mRecyclerView.getAdapter()).addCofradias(mCofradias);
    }

    private void initRecyclerViewCofradias() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        mCofradiaAdapter = new CofradiaAdapter(this);
        mRecyclerView.setAdapter(mCofradiaAdapter);
    }

    private void initPresenter() {
        cofradiaPresenter = new CofradiaPresenter();
        cofradiaPresenter.attachCofradiaView(this);
        cofradiaPresenter.initPresenter();
    }



    @Override
    public void onClick(int position) {
        //Cofradia selectedCofradia = mCofradiaAdapter.getSelectedCofradia(position);
        //Intent intent = new Intent(CofradiaActivity.this, DetailCofradiaActivity.class);
        //intent.putExtra(Constants.REFERENCE.COFRADIA, selectedCofradia);
        //startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
