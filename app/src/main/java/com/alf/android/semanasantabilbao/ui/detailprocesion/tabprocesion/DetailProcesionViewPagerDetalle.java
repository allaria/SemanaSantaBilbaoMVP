package com.alf.android.semanasantabilbao.ui.detailprocesion.tabprocesion;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 27/10/2016.
 */

public class DetailProcesionViewPagerDetalle extends View {

    private static String LOG_TAG = DetailProcesionViewPagerDetalle.class.getSimpleName();

    @BindView(R.id.procesion_imagen) ImageView mImagenDetalle;
    @BindView(R.id.procesion_nombre) TextView mNombre;
    @BindView(R.id.procesion_dia) TextView mDia;
    @BindView(R.id.procesion_fecha) TextView mFecha;
    @BindView(R.id.procesion_hora) TextView mHora;
    @BindView(R.id.procesion_lugar_inicio) TextView mLugarInicio;
    @BindView(R.id.procesion_pasos) TextView mPasos;
    @BindString(R.string.NO_IMAGE) String noDetailProcesionImage;

    public DetailProcesionViewPagerDetalle(Context context, View view) {
        super(context);

        ButterKnife.bind(this, view);
    }

    public void showDetailProcesionInformationDetalle(Procesion procesion) {

        String procesionImg = procesion.getImagenProcesion();
        int idDrawable = getResources().getIdentifier(procesionImg, "drawable", getContext().getPackageName());
        int idDrawableNoImage = this.getResources().getIdentifier(noDetailProcesionImage, "drawable", getContext().getPackageName());
        Picasso.with(getContext())
                .load(idDrawable)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(mImagenDetalle);

        mNombre.setText(procesion.getNombreProcesion());
        mDia.setText(procesion.getDia());
        mFecha.setText(procesion.getFecha());
        mHora.setText(procesion.getHorario());
        mLugarInicio.setText(procesion.getSalida());
        mPasos.setText(procesion.getPasos());
    }
}
