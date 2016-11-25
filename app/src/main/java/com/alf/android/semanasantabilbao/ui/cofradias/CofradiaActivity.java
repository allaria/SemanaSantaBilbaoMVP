package com.alf.android.semanasantabilbao.ui.cofradias;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.alf.android.semanasantabilbao.ui.cofradias.adapter.CofradiaAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaActivity;
import com.alf.android.semanasantabilbao.ui.museopasos.MuseoPasosActivity;
import com.alf.android.semanasantabilbao.ui.utils.GlobalFunctions;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alf.android.semanasantabilbao.R.string.texto_compartir_app;

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
    private Boolean withoutConnectionCofradiasList;
    private String Url;

    @Inject CofradiaContract.CofradiaPresenter cofradiaPresenter;
    @Inject CofradiaAdapter cofradiaAdapter;
    @Inject RecyclerView.RecycledViewPool recycledViewPool;

    @BindView(R.id.cofradia_progress_bar) ProgressBar spinner;
    @BindView(R.id.cofradia_loading_text) TextView loadingText;
    @BindView(R.id.cofradia_toolbar) Toolbar toolbar;
    @BindView(R.id.cofradia_drawer_layout) DrawerLayout drawer;
    @BindView(R.id.cofradia_navigation_view) NavigationView navigationView;
    @BindView(R.id.cofradia_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.cofradia_top_image) ImageView mTopImage;
    //@BindString(R.string.NO_IMAGE) String no_image;
    @BindString(R.string.firebase_error) String firebaseError;
    @BindString(R.string.COFRADIA) String cofradiaIntent;
    @BindString(R.string.COFRADIAERROR) String cofradiaIntentError;
    @BindString(R.string.app_name) String subject;
    @BindString(R.string.texto_share_app) String body;
    @BindString(texto_compartir_app) String shareText;
    @BindString(R.string.share_app_error) String shareAppError;
    @BindDrawable (R.drawable.no_image) Drawable idDrawableNoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cofradia_activity);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);

        withoutConnectionCofradiasList = false;

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        //int idDrawableNoImage = this.getResources().getIdentifier(getResources().getString(R.string.NO_IMAGE), "drawable", this.getPackageName());
        //int idDrawableNoImage = this.getResources().getIdentifier(no_image, "drawable", this.getPackageName());
        Picasso.with(this)
                .load(R.drawable.intro_photo)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(mTopImage);

        setSpinnerAndLoadingText(false);
        initRecyclerViewCofradias();

        //check network status. false without connection, true with connection
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(getApplication().CONNECTIVITY_SERVICE);
        connectionAvailable = new GlobalFunctions().checkNetworkStatus(connectivityManager);
        if (connectionAvailable){
            Log.d(LOG_TAG, "Internet connection available.");
            fields = null;
        }else{
            Log.d(LOG_TAG, "No internet connection available. Getting Drawables list.");
            fields = getDrawablesList();
        }

        //initilize the presenter
        //initPresenter(connectionAvailable, fields);

        cofradiaPresenter.attachCofradiaView(this);
        cofradiaPresenter.initPresenter(connectionAvailable, fields);

        //cofradiaPresenter.getGooglePlayUrl(connectionAvailable);
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printCofradias(ObservableArrayList<Cofradia> mCofradias) {
        Log.d(LOG_TAG, "Number of Cofradias in the recyclerView: " + mCofradias.size() + " (printCofradias)");
        ((CofradiaAdapter) mRecyclerView.getAdapter()).addCofradias(mCofradias);
    }

    @Override
    public void printCofradiasWhenError(ArrayList<Cofradia> mCofradias) {
        Log.d(LOG_TAG, "Number of Cofradias in the recyclerView: " + mCofradias.size() + " (printCofradiasWhenError)");
        ((CofradiaAdapter) mRecyclerView.getAdapter()).addCofradias(mCofradias);
    }

    @Override
    public void showErrorGettingCofradias(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Toast. Error getting Cofradias from Firebase. (" + firebaseError + ")");
        Toast.makeText(this, firebaseError + "(" + mensajeError.get() + ")", Toast.LENGTH_LONG).show();
    }

    private void initRecyclerViewCofradias() {
        mRecyclerView.setHasFixedSize(true);
        if (new GlobalFunctions().getScreenOrientation(getResources().getConfiguration().orientation).equals("Landscrape")) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        }else{
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }
        //mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        //cofradiaAdapter = new CofradiaAdapter(this);
        cofradiaAdapter.setCofradiaClickListener(this);
        mRecyclerView.setAdapter(cofradiaAdapter);
    }

/*    private void initPresenter(boolean connectionAvailable, Field[] fields) {
        //cofradiaPresenter = new CofradiaPresenter();
        cofradiaPresenter.attachCofradiaView(this);
        cofradiaPresenter.initPresenter(connectionAvailable, fields);
    }*/

    public Field[] getDrawablesList() {
        final R.drawable drawableResources = new R.drawable();
        final Class<R.drawable> c = R.drawable.class;

        withoutConnectionCofradiasList = true;

        return c.getDeclaredFields();
    }

    @Override
    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public void onClick(int position) {
        Cofradia selectedCofradia = cofradiaAdapter.getSelectedCofradia(position);
        Intent intent = new Intent(CofradiaActivity.this, DetailCofradiaActivity.class);
        //intent.putExtra(getResources().getString(R.string.COFRADIA), selectedCofradia);
        intent.putExtra(cofradiaIntent, selectedCofradia);
        intent.putExtra(cofradiaIntentError, withoutConnectionCofradiasList);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && cofradiaPresenter != null) {
            cofradiaPresenter.unsuscribeCofradiaSuscription();
            cofradiaPresenter.detachCofradiaView();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_museo_pasos) {
            Intent intentMenu = new Intent(CofradiaActivity.this, MuseoPasosActivity.class);
            intentMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentMenu);
        } else if (id == R.id.nav_share_app) {
            if (Url != null) {
                Intent intentMenu = new Intent(Intent.ACTION_SEND);
                intentMenu.setType("text/plain");
                intentMenu.putExtra(Intent.EXTRA_SUBJECT, subject);
                intentMenu.putExtra(Intent.EXTRA_TEXT, body + Url + "\n");
                startActivity(Intent.createChooser(intentMenu, shareText));
            } else {
                Log.d(LOG_TAG, "No App Url available");
                Toast.makeText(getApplicationContext(), shareAppError, Toast.LENGTH_LONG).show();
            }
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
