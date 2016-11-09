package com.alf.android.semanasantabilbao.ui.detailcofradia;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto on 27/10/2016.
 */

public class DetailCofradiaViewPagerCofradia extends View {

    private static String LOG_TAG = DetailCofradiaViewPagerCofradia.class.getSimpleName();

    @BindView(R.id.cofradia_fundacion) TextView mFundacion;
    @BindView(R.id.cofradia_sede) TextView mSede;
    @BindView(R.id.cofradia_abad) TextView mHernamoAbad;
    @BindView(R.id.cofradia_vestimenta) TextView mVestimenta;
    @BindView(R.id.cofradia_procesiones) TextView mNumeroProcesiones;

    public DetailCofradiaViewPagerCofradia(Context context, View view) {
        super(context);
        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformation(Cofradia cofradia) {

        mFundacion.setText(String.valueOf(cofradia.getFundacion()));
        mSede.setText(cofradia.getSede());
        mHernamoAbad.setText(cofradia.getHermanoAbad());
        mVestimenta.setText(cofradia.getVestimenta());
        mNumeroProcesiones.setText(String.valueOf(cofradia.getNumeroProcesiones()));
    }
}
