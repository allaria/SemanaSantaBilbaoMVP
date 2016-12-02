package com.alf.android.semanasantabilbao.business.galleryimages;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by Alberto Laría Fernández on 26/09/2016.
 */

public interface GetGalleryImagesInteractor {

    Observable<DataSnapshot> getGalleryImages();
}
