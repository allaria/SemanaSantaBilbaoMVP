package com.alf.android.semanasantabilbao.di;


import com.alf.android.semanasantabilbao.ui.cofradia.CofradiaActivity;
import com.alf.android.semanasantabilbao.ui.cofradia.adapter.CofradiaAdapter;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by epelde on 22/9/16.
 */
@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

    void inject(CofradiaActivity target);

}
