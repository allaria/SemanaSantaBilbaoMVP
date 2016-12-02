package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.ViewPagerCofradiaDetailAdapter;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 24/10/2016.
 */

public class DetailCofradiaActivity extends AppCompatActivity implements DetailCofradiaContract.DetailCofradiaView{

    private final String LOG_TAG = DetailCofradiaActivity.class.getSimpleName();
    private Cofradia cofradia;
    private Boolean cofradiaError;
    private boolean connectionAvailable;
    //private DetailCofradiaContract.DetailPasosPresenter detailCofradiaPresenter;

    @Inject DetailCofradiaContract.DetailCofradiaPresenter detailCofradiaPresenter;

    @BindView(R.id.detail_cofradia_toolbar) Toolbar toolbar;
    @BindView(R.id.detail_cofradia_tablayout) TabLayout tabLayout;
    @BindView(R.id.detail_cofradia_logo_toolbar) ImageView mCofradiaLogo;
    @BindView(R.id.detail_cofradia_name_toolbar) TextView mCofradiaName;
    @BindView(R.id.viewpager_detail) ViewPager viewPager;
    @BindView(R.id.detail_coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.cofradia_detail_progress_bar) ProgressBar spinner;
    @BindView(R.id.cofradia_loading_text) TextView loadingText;
    @BindString(R.string.COFRADIA) String intentCofradia;
    @BindString(R.string.no_connection) String noConnection;
    @BindString(R.string.back_action) String backAction;
    @BindString(R.string.COFRADIAERROR) String intentCofradiaError;
    @BindString(R.string.firebase_error_cofradias) String firebaseErrorCofradia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_cofradia_activity);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //cofradia = (Cofradia) intent.getSerializableExtra(getResources().getString(R.string.COFRADIA));
        cofradia = (Cofradia) intent.getSerializableExtra(intentCofradia);
        cofradiaError = (Boolean) intent.getSerializableExtra(intentCofradiaError);
        Log.d(LOG_TAG, "Object Cofradia not completely created: "+cofradiaError);
        ButterKnife.bind(this);

        //toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String detailImg = cofradia.getImagenEscudo();
        int idDrawable = getResources().getIdentifier(detailImg, "drawable", getApplicationContext().getPackageName());
        Picasso.with(getApplicationContext())
                .load(idDrawable)
                .into(mCofradiaLogo);
        mCofradiaName.setText(cofradia.getNombreCofradia());

        if(cofradiaError) {
            Log.d(LOG_TAG, "Cofradias list generated form Drawables. Cofradia is incomplete");
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplication().CONNECTIVITY_SERVICE);
            connectionAvailable = new GlobalFunctions().checkNetworkStatus(connectivityManager);
            if (connectionAvailable) {
                Log.d(LOG_TAG, "Internet connection. Getting Cofradia from Firebase");
                detailCofradiaPresenter.attachDetailCofradiaView(this);
                detailCofradiaPresenter.initPresenter(cofradia.getId_cofradia());
            } else {
                Log.d(LOG_TAG, "Snackbar. No Internet connection. Back to CofradiaActivity");
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, noConnection, Snackbar.LENGTH_LONG)
                        .setAction(backAction, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                            }
                        });
                snackbar.show();
            }
        } else {
            Log.d(LOG_TAG, "Cofradias list generated form Firebase. Cofradia is complete");
            loadDetailCofradia();
        }
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
    public void setCofradiaValue (ObservableArrayList<Cofradia> listaCofradias){
        this.cofradia = listaCofradias.get(0);
        loadDetailCofradia();
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorGettingCofradias(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Toast. Error getting Cofradias from Firebase. (" + firebaseErrorCofradia + ")");
        Toast.makeText(this, firebaseErrorCofradia + "(" + mensajeError.get() + ")", Toast.LENGTH_LONG).show();
    }

    private void loadDetailCofradia() {
        final ViewPagerCofradiaDetailAdapter viewPagerDetailAdpter = new ViewPagerCofradiaDetailAdapter(this.getApplicationContext(), cofradia);
        viewPager.setAdapter(viewPagerDetailAdpter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(LOG_TAG, "Detach View & Unsuscribe Suscription. Tab Unselected:" + tab.getPosition());
                viewPagerDetailAdpter.detachViewUnsuscribeSuscription(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && detailCofradiaPresenter != null) {
            detailCofradiaPresenter.unsuscribeDetailCofradiaSuscription();
            detailCofradiaPresenter.detachDetailCofradiaView();
        }
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
