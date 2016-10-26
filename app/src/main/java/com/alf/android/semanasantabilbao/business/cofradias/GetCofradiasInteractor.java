package com.alf.android.semanasantabilbao.business.cofradias;

import com.firebase.client.DataSnapshot;
import rx.Observable;

/**
 * Created by alaria on 26/09/2016.
 */

public interface GetCofradiasInteractor {

    Observable<DataSnapshot> getCofradias();
}
