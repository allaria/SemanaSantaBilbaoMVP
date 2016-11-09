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

public class DetailCofradiaViewPagerDetalle extends View {

    private static String LOG_TAG = DetailCofradiaViewPagerDetalle.class.getSimpleName();

    @BindView(R.id.cofradia_pasos) TextView mPasos;
    @BindView(R.id.cofradia_texto) TextView mTexto;
    @BindView(R.id.texto_intro_paso) TextView mTextoPaso;
    @BindView(R.id.cofradia_direccion) TextView mCofradiaDireccion;
    @BindView(R.id.cofradia_telefono) TextView mCofradiaTelefono;
    @BindView(R.id.cofradia_web) TextView mCofradiaWeb;

    public DetailCofradiaViewPagerDetalle(Context context, View view) {
        super(context);

        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformationDetalle(Cofradia cofradia) {

        mPasos.setText("\nPasos (" + String.valueOf(cofradia.getNumeroPasos()) + "):");
        mTexto.setText(cofradia.getTextoDetalle());
        mTextoPaso.setText(cofradia.getTextoIntroPasos());
        mCofradiaDireccion.setText(cofradia.getDireccion());
        mCofradiaTelefono.setText(cofradia.getTelefono());
        mCofradiaWeb.setText(cofradia.getWeb());
    }
}
