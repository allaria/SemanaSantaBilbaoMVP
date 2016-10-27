package com.alf.android.semanasantabilbao.ui.detailcofradia.modelobject;

import com.alf.android.semanasantabilbao.R;

/**
 * Created by anupamchugh on 26/12/15.
 */
public enum DetailCofradiaModelObject {

    Cofradía(R.string.tab_detail_cofradia_one, R.layout.detail_cofradia_content),
    Detalle(R.string.tab_detail_cofradia_two, R.layout.view_blue),
    Pasos(R.string.tab_detail_cofradia_three, R.layout.view_green),
    Galería(R.string.tab_detail_cofradia_four, R.layout.view_yellow);

    private int mTitleResId;
    private int mLayoutResId;

    DetailCofradiaModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
