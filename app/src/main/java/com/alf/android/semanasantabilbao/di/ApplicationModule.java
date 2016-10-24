package com.alf.android.semanasantabilbao.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.ui.cofradia.CofradiaActivity;
import com.alf.android.semanasantabilbao.ui.cofradia.CofradiaContract;
import com.alf.android.semanasantabilbao.ui.cofradia.CofradiaPresenter;
import com.alf.android.semanasantabilbao.ui.cofradia.adapter.CofradiaAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by epelde on 22/9/16.
 */
@Module
public class ApplicationModule {

    private static final String LOG_TAG = ApplicationModule.class.getSimpleName();

    private Application application;

    public ApplicationModule(Application app) {
        this.application = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public CofradiaContract.CofradiaPresenter provideMainPresenter() {
        return new CofradiaPresenter();
    }

    @Provides
    @Singleton
    public CofradiaAdapter provideCofradiaAdapter() {
        return new CofradiaAdapter();
    }

    @Singleton
    @Provides
    public RecyclerView.LayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Singleton
    @Provides
    public RecyclerView.RecycledViewPool provideRecycledViewPool() {
        return new RecyclerView.RecycledViewPool();
    }

/*    @Singleton
    @Provides
    public ActionBarDrawerToggle provideActionBarDrawerToggle(Context context, DrawerLayout drawer, Toolbar toolbar) {
        return new ActionBarDrawerToggle((Activity) context, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }*/
}

