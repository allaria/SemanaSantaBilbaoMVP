package com.alf.android.semanasantabilbao.di;


import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia.DetailCofradiaViewPagerCofradia;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria.DetailCofradiaViewPagerGaleria;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.galleryimages.GalleryImagesActivity;
import com.alf.android.semanasantabilbao.ui.gallerypasos.GalleryPasosActivity;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by alaria on 17/10/2016.
 */
@Singleton
@Component(modules = { UiApplicationModule.class, BusinessApplicationModule.class, DataApplicationModule.class })
public interface ApplicationComponent {

    void inject(CofradiaActivity activity);

    void inject(DetailCofradiaActivity detailCofradiaActivity);

    void inject(DetailCofradiaViewPagerCofradia viewPagerCofradia);

    void inject(DetailCofradiaViewPagerPasos viewPagerPasos);

    void inject(DetailCofradiaViewPagerGaleria viewPagerGaleria);

    void inject(GalleryImagesActivity galleryImagesActivity);

    void inject(GalleryPasosActivity galleryPasosActivity);
}
