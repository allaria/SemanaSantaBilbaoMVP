package com.alf.android.semanasantabilbao.ui.galleryprocesiones.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto Laría Fernández on 25/04/2016.
 */
public class GalleryProcesionesAdapter extends RecyclerView.Adapter<GalleryProcesionesAdapter.Holder>{

    private static final String LOG_TAG = GalleryProcesionesAdapter.class.getSimpleName();
    private GalleryProcesionesClickListener galleryProcesionesClickListener;
    private List<Procesion> mGalleryProcesiones;
    private Context context;

    @Inject
    public GalleryProcesionesAdapter() {
        mGalleryProcesiones = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.procesion_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Procesion currGalleryProcesion = mGalleryProcesiones.get(position);
        String imgProcesion = currGalleryProcesion.getImagenProcesion();
        int idDrawable = holder.itemView.getContext().getResources().getIdentifier(imgProcesion, "drawable", holder.itemView.getContext().getPackageName());
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(idDrawable)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mGalleryProcesionesImage);
        holder.mGalleryProcesionesText.setText(currGalleryProcesion.getNombreProcesion());

        //Picasso.with(holder.itemView.getContext()).load("https://raw.githubusercontent.com/allaria/CofradiasBilbao/master/app/src/main/res/imagenes/verMapa.png").into(holder.mPhoto);
        //Picasso.with(holder.itemView.getContext()).load(R.drawable.e_santa_vera_cruz).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mGalleryProcesiones.size();
    }

    public void addGalleryPasos(List<Procesion> listaGalleryProcesiones) {
        mGalleryProcesiones.clear();
        mGalleryProcesiones.addAll(listaGalleryProcesiones);
        notifyDataSetChanged();
    }

    public Procesion getSelectedGalleryProcesion(int position) {
        return mGalleryProcesiones.get(position);
    }

    public GalleryProcesionesClickListener getGalleryProcesionesClickListener() {
        return galleryProcesionesClickListener;
    }

    public void setGalleryProcesionesClickListener(GalleryProcesionesClickListener galleryProcesionesClickListener) {
        this.galleryProcesionesClickListener = galleryProcesionesClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_procesion_image) ImageView mGalleryProcesionesImage;
        @BindView(R.id.card_procesion_name) TextView mGalleryProcesionesText;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getGalleryProcesionesClickListener().onClick(getLayoutPosition());
        }
    }

    public interface GalleryProcesionesClickListener {
        void onClick(int position);
    }
}
