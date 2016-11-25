package com.alf.android.semanasantabilbao.ui.detailcofradia.tabdetalle;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto on 27/10/2016.
 */

public class DetailCofradiaViewPagerDetalle extends View {

    private static String LOG_TAG = DetailCofradiaViewPagerDetalle.class.getSimpleName();

    @BindView(R.id.cofradia_pasos) TextView mPasos;
    @BindView(R.id.cofradia_text) TextView mTexto;
    @BindView(R.id.paso_intro_text) TextView mTextoPaso;
    @BindView(R.id.cofradia_address) TextView mCofradiaDireccion;
    @BindView(R.id.cofradia_phone) TextView mCofradiaTelefono;
    @BindView(R.id.cofradia_web) TextView mCofradiaWeb;

    @BindString(R.string.detail_cofradia_pasos_title) String pasosTitle;

    public DetailCofradiaViewPagerDetalle(Context context, View view) {
        super(context);

        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformationDetalle(Cofradia cofradia) {

        mPasos.setText("\n" + pasosTitle + " (" + String.valueOf(cofradia.getNumeroPasos()) + "):");
        mTexto.setText(cofradia.getTextoDetalle());
        mTextoPaso.setText(cofradia.getTextoIntroPasos());
        mCofradiaDireccion.setText(cofradia.getDireccion());
        mCofradiaTelefono.setText(cofradia.getTelefono());
        mCofradiaWeb.setText(cofradia.getWeb());
    }
}
