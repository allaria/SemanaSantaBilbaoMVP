package com.alf.android.semanasantabilbao.ui.detailprocesion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Ruta;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 03/11/2016.
 */

public class DetailProcesionRutaAdapter extends RecyclerView.Adapter<DetailProcesionRutaAdapter.Holder>{

    private static final String LOG_TAG = DetailProcesionRutaAdapter.class.getSimpleName();
    private DetailProcesionRutaAdapter.RutaClickListener rutaClickListener;
    private List<Ruta> mIndicaciones;
    //private Context context;

    @Inject
    public DetailProcesionRutaAdapter() {
        mIndicaciones = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.indicacion_card, null, false);
        //context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Ruta currRuta = mIndicaciones.get(position);
        holder.mIndicacionCalle.setText(currRuta.getCalle());
    }

    @Override
    public int getItemCount() {
        return mIndicaciones.size();
    }

    public void addIndicaciones(List<Ruta> listaIndicaciones) {
        mIndicaciones.clear();
        mIndicaciones.addAll(listaIndicaciones);
        notifyDataSetChanged();
    }

    public Ruta getSelectedRuta(int position) {
        return mIndicaciones.get(position);
    }

    public DetailProcesionRutaAdapter.RutaClickListener getRutaClickListener() {
        return rutaClickListener;
    }

    public void setRutaClickListener(DetailProcesionRutaAdapter.RutaClickListener rutaClickListener) {
        this.rutaClickListener = rutaClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.indicacion_calle) TextView mIndicacionCalle;
        //@BindView(R.id.card_text_pasos) ImageView mPhotoIndicacion;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            rutaClickListener.onClickRuta(getLayoutPosition());
        }
    }

    public interface RutaClickListener {
        void onClickRuta(int position);
    }
}
