package com.alf.android.semanasantabilbao.ui.detailcofradia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Paso;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 03/11/2016.
 */

public class PasoAdapter extends RecyclerView.Adapter<PasoAdapter.Holder>{

    private static final String LOG_TAG = PasoAdapter.class.getSimpleName();
    private PasoAdapter.PasoClickListener pasoClickListener;
    private List<Paso> mPasos;
    private Context context;

    @Inject
    public PasoAdapter() {
        mPasos = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_pasos_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Paso currPaso = mPasos.get(position);
        String imgPaso = currPaso.getImagenPaso();
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(imgPaso)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mPhotoPaso);
        holder.mPasoName.setText(currPaso.getNombrePaso());

        //int idDrawable = holder.itemView.getContext().getResources().getIdentifier(imgGaleria, "drawable", holder.itemView.getContext().getPackageName());
        //Picasso.with(holder.itemView.getContext()).load(idDrawable).into(holder.mPhotoGaleria);
    }

    @Override
    public int getItemCount() {
        return mPasos.size();
    }

    public void addPaso(List<Paso> listaPasos) {
        mPasos.clear();
        mPasos.addAll(listaPasos);
        notifyDataSetChanged();
    }

    public Paso getSelectedPaso(int position) {
        return mPasos.get(position);
    }

    public PasoAdapter.PasoClickListener getPasoClickListener() {
        return pasoClickListener;
    }

    public void setPasoClickListener(PasoAdapter.PasoClickListener pasoClickListener) {
        this.pasoClickListener = pasoClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_image_pasos) ImageView mPhotoPaso;
        @BindView(R.id.card_text_pasos) TextView mPasoName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            pasoClickListener.onClickPaso(getLayoutPosition());
        }
    }

    public interface PasoClickListener {
        void onClickPaso(int position);
    }
}
