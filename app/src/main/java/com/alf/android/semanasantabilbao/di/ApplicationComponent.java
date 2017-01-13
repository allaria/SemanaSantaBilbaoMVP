package com.alf.android.semanasantabilbao.di;

import com.alf.android.semanasantabilbao.ui.cofradias.CofradiaActivity;
import com.alf.android.semanasantabilbao.ui.contactos.ContactoActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia.DetailCofradiaViewPagerCofradia;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria.DetailCofradiaViewPagerGaleria;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.detailprocesion.tabpasos.DetailProcesionViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.detailprocesion.tabruta.DetailProcesionViewPagerRuta;
import com.alf.android.semanasantabilbao.ui.galleryimages.GalleryImagesActivity;
import com.alf.android.semanasantabilbao.ui.gallerypasos.GalleryPasosActivity;
import com.alf.android.semanasantabilbao.ui.galleryprocesiones.GalleryProcesionesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alberto Laría Fernández on 17/10/2016.
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

    void inject(GalleryProcesionesActivity galleryProcesionesActivity);

    void inject(ContactoActivity contratoActivity);

    void inject(DetailProcesionViewPagerPasos viewPagerDetailPasos);

    void inject(DetailProcesionViewPagerRuta viewPagerDetailRuta);
}
