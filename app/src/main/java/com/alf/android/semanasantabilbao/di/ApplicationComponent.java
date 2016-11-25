package com.alf.android.semanasantabilbao.di;


import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia.DetailCofradiaViewPagerCofradia;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria.DetailCofradiaViewPagerGaleria;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasos;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by alaria on 17/10/2016.
 */
@Singleton
@Component(modules = { CommonApplicationModule.class, UiApplicationModule.class, BusinessApplicationModule.class, DataApplicationModule.class })
public interface ApplicationComponent {

    void inject(CofradiaActivity activity);

    void inject(DetailCofradiaActivity detailCofradiaActivity);

    void inject(DetailCofradiaViewPagerCofradia viewPagerCofradia);

    void inject(DetailCofradiaViewPagerPasos viewPagerPasos);

    void inject(DetailCofradiaViewPagerGaleria viewPagerGaleria);
}
