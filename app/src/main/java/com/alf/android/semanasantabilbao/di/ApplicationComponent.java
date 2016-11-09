package com.alf.android.semanasantabilbao.di;


import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerPasos;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by alaria on 17/10/2016.
 */
@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

    void inject(CofradiaActivity activity);
}
