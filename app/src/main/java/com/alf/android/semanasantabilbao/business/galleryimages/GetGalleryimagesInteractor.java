package com.alf.android.semanasantabilbao.business.galleryimages;

import com.firebase.client.DataSnapshot;

import rx.Observable;

/**
 * Created by alaria on 26/09/2016.
 */

public interface GetGalleryImagesInteractor {

    Observable<DataSnapshot> getGalleryImages();
}
