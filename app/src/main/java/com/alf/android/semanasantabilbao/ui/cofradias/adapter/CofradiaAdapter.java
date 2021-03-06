package com.alf.android.semanasantabilbao.ui.cofradias.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 25/04/2016.
 */

public class CofradiaAdapter extends RecyclerView.Adapter<CofradiaAdapter.Holder>{

    private static final String LOG_TAG = CofradiaAdapter.class.getSimpleName();
    private CofradiaClickListener cofradiaClickListener;
    private List<Cofradia> mCofradias;
    private Context context;

    @Inject
    public CofradiaAdapter() {
        mCofradias = new ArrayList<>();
        //cofradiaClickListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cofradia_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Cofradia currCofradia = mCofradias.get(position);
        String escudoPhoto = currCofradia.getImagenEscudo();
        String homePhoto = currCofradia.getImagenDetalle();
        int idDrawableEscudo = holder.itemView.getContext().getResources().getIdentifier(escudoPhoto, "drawable", holder.itemView.getContext().getPackageName());
        int idDrawableHome = holder.itemView.getContext().getResources().getIdentifier(homePhoto, "drawable", holder.itemView.getContext().getPackageName());
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(idDrawableEscudo)
                .into(holder.mCofradiaEscudo);
        Picasso.with(holder.itemView.getContext())
                .load(idDrawableHome)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mCofradiaImagen);
        holder.mCofradiaName.setText(currCofradia.getNombreCofradia());

        //Picasso.with(holder.itemView.getContext()).load("https://raw.githubusercontent.com/allaria/CofradiasBilbao/master/app/src/main/res/imagenes/verMapa.png").into(holder.mPhoto);
        //Picasso.with(holder.itemView.getContext()).load(R.drawable.e_santa_vera_cruz).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mCofradias.size();
    }

    public void addCofradias(List<Cofradia> listaCofradia) {
        mCofradias.clear();
        mCofradias.addAll(listaCofradia);
        notifyDataSetChanged();
    }

    public Cofradia getSelectedCofradia(int position) {
        return mCofradias.get(position);
    }

    public CofradiaClickListener getCofradiaClickListener() {
        return cofradiaClickListener;
    }

    public void setCofradiaClickListener(CofradiaClickListener cofradiaClickListener) {
        this.cofradiaClickListener = cofradiaClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_cofradia_image) ImageView mCofradiaImagen;
        @BindView(R.id.card_cofradia_logo) ImageView mCofradiaEscudo;
        @BindView(R.id.card_cofradia_name) TextView mCofradiaName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getCofradiaClickListener().onClick(getLayoutPosition());
        }
    }

    public interface CofradiaClickListener {
        void onClick(int position);
    }
}
