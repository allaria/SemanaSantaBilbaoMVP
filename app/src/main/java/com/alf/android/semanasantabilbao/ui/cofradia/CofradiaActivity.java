package com.alf.android.semanasantabilbao.ui.cofradia;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.App;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.ui.cofradia.adapter.CofradiaAdapter;
import com.firebase.client.Firebase;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alaria on 22/09/2016.
 */

public class CofradiaActivity extends AppCompatActivity implements CofradiaContract.CofradiaView,
        CofradiaAdapter.CofradiaClickListener,
        NavigationView.OnNavigationItemSelectedListener{

    private static final String LOG_TAG = CofradiaActivity.class.getSimpleName();
    //private CofradiaAdapter cofradiaAdapter;
    //private CofradiaContract.CofradiaPresenter cofradiaPresenter;
    private boolean connectionAvailable;
    private Field[] fields;

    @Inject
    CofradiaContract.CofradiaPresenter cofradiaPresenter;
    @Inject
    CofradiaAdapter cofradiaAdapter;
    @Inject
    RecyclerView.RecycledViewPool recycledViewPool;
    @Inject
    RecyclerView.LayoutManager layoutManager;
//    @Inject
//    ActionBarDrawerToggle actionBarDrawerToggle;

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
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //ActionBarDrawerToggle toggle = actionBarDrawerToggle;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        //setting the recyclerview
        initRecyclerViewCofradias();

        //check network status. false without connection, true with connection
        connectionAvailable = checkNetworkStatus();
        if (connectionAvailable){
            //if conection no fields necessary
            fields = null;
        }else{
            //getting the list of Drawable elements in the app
            fields = getDrawablesList();
        }

        //initilize the presenter
        initPresenter(connectionAvailable, fields);

        //cofradiaAdapter.setCofradiaClickListener(this);
    }


    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }



    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {
            cofradiaPresenter.detachCofradiaView();
            cofradiaPresenter.unsuscribeCofradiaSuspciption();
        }
    }

    @Override
    public void setSpinner(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printCofradias(ObservableArrayList<Cofradia> mCofradias) {
        Log.d(LOG_TAG, "printCofradias: "+mCofradias.size()+" elementos");
        ((CofradiaAdapter) mRecyclerView.getAdapter()).addCofradias(mCofradias);
    }

    @Override
    public void printCofradiasWithoutConnection(ArrayList<Cofradia> mCofradias) {
        Log.d(LOG_TAG, "printCofradias: "+mCofradias.size()+" elementos");
        ((CofradiaAdapter) mRecyclerView.getAdapter()).addCofradias(mCofradias);
    }

    @Override
    public void showErrorGettingCofradias(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "showErrorGettingCofradias");
        Toast.makeText(this, mensajeError.get(), Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerViewCofradias() {
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setRecycledViewPool(recycledViewPool);
        mRecyclerView.setLayoutManager(layoutManager);

        //cofradiaAdapter = new CofradiaAdapter(this);
        cofradiaAdapter.setCofradiaClickListener(this);
        mRecyclerView.setAdapter(cofradiaAdapter);
    }
    
    private boolean checkNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(getApplication().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected() == true) {
            Toast.makeText(this, "Network Available", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    
    private void initPresenter(boolean connectionAvailable, Field[] fields) {
        //cofradiaPresenter = new CofradiaPresenter();
        cofradiaPresenter.attachCofradiaView(this);
        cofradiaPresenter.initPresenter(connectionAvailable, fields);
    }

    public Field[] getDrawablesList() {
        final R.drawable drawableResources = new R.drawable();
        final Class<R.drawable> c = R.drawable.class;

        return c.getDeclaredFields();
    }

    @Override
    public void onClick(int position) {
        //Cofradia selectedCofradia = mCofradiaAdapter.getSelectedCofradia(position);
        //Intent intent = new Intent(CofradiaActivity.this, DetailCofradiaActivity.class);
        //intent.putExtra(Constants.REFERENCE.COFRADIA, selectedCofradia);
        //startActivity(intent);
        Toast.makeText(getApplicationContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
