package com.alf.android.semanasantabilbao;

import android.app.Application;

import com.alf.android.semanasantabilbao.di.ApplicationComponent;
import com.alf.android.semanasantabilbao.di.ApplicationModule;
import com.alf.android.semanasantabilbao.di.DaggerApplicationComponent;


/**
 * Created by epelde on 22/9/16.
 */
public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
