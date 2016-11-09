package com.alf.android.semanasantabilbao.di;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alf.android.semanasantabilbao.business.cofradias.GetCofradiasInteractorImpl;
import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.FirebaseDataAccess;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaContract;
import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaPresenter;
import com.alf.android.semanasantabilbao.ui.cofradias.adapter.CofradiaAdapter;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerPasosContract;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerPasosPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alaria on 17/10/2016.
 */
@Module
public class ApplicationModule {

    private static final String LOG_TAG = ApplicationModule.class.getSimpleName();

    @Provides
    @Singleton
    public CofradiaContract.CofradiaPresenter provideMainPresenter(GetCofradiasInteractorImpl getCofradiasInteractor) {
        return new CofradiaPresenter(getCofradiasInteractor);
    }

    @Provides
    @Singleton
    public CofradiaAdapter provideCofradiaAdapter() {
        return new CofradiaAdapter();
    }

    @Singleton
    @Provides
    public RecyclerView.RecycledViewPool provideRecycledViewPool() {
        return new RecyclerView.RecycledViewPool();
    }

    @Singleton
    @Provides
    public GetCofradiasInteractorImpl provideGetCofradiasInteractorImpl(FirebaseAccess firebaseAccess) {
        return new GetCofradiasInteractorImpl(firebaseAccess);
    }

    @Singleton
    @Provides
    public FirebaseAccess provideFirebaseDataAccess() {
        return new FirebaseDataAccess();
    }

}

