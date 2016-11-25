package com.alf.android.semanasantabilbao.di;

import android.support.v7.widget.RecyclerView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alaria on 25/11/2016.
 */
@Module
public class CommonApplicationModule {

    @Singleton
    @Provides
    public RecyclerView.RecycledViewPool provideRecycledViewPool() {
        return new RecyclerView.RecycledViewPool();
    }
}
