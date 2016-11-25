package com.alf.android.semanasantabilbao.di;

import com.alf.android.semanasantabilbao.data.FirebaseAccess;
import com.alf.android.semanasantabilbao.data.FirebaseDataAccess;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alaria on 17/10/2016.
 */
@Module
public class DataApplicationModule {

    private static final String LOG_TAG = DataApplicationModule.class.getSimpleName();

    @Singleton
    @Provides
    public FirebaseAccess provideFirebaseDataAccess() {
        return new FirebaseDataAccess();
    }
}

