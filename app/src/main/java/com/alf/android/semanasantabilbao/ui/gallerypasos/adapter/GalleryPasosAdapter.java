package com.alf.android.semanasantabilbao.ui.gallerypasos.adapter;

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
 * Created by Alberto Laría Fernández on 25/04/2016.
 */
public class GalleryPasosAdapter extends RecyclerView.Adapter<GalleryPasosAdapter.Holder>{

    private static final String LOG_TAG = GalleryPasosAdapter.class.getSimpleName();
    private GalleryPasosClickListener galleryPasosClickListener;
    private List<Paso> mGalleryPasos;
    private Context context;

    @Inject
    public GalleryPasosAdapter() {
        mGalleryPasos = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_pasos_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Paso currGalleryPaso = mGalleryPasos.get(position);
        String imgGaleria = currGalleryPaso.getImagenPaso();
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(imgGaleria)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mGalleryPasosImage);
        holder.mGalleryPasosText.setText(currGalleryPaso.getNombrePaso());

        //Picasso.with(holder.itemView.getContext()).load("https://raw.githubusercontent.com/allaria/CofradiasBilbao/master/app/src/main/res/imagenes/verMapa.png").into(holder.mPhoto);
        //Picasso.with(holder.itemView.getContext()).load(R.drawable.e_santa_vera_cruz).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mGalleryPasos.size();
    }

    public void addGalleryPasos(List<Paso> listaGalleryPasos) {
        mGalleryPasos.clear();
        mGalleryPasos.addAll(listaGalleryPasos);
        notifyDataSetChanged();
    }

    public Paso getSelectedGalleryPasos(int position) {
        return mGalleryPasos.get(position);
    }

    public GalleryPasosClickListener getGalleryPasosClickListener() {
        return galleryPasosClickListener;
    }

    public void setGalleryPasosClickListener(GalleryPasosClickListener galleryPasosClickListener) {
        this.galleryPasosClickListener = galleryPasosClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_image_pasos) ImageView mGalleryPasosImage;
        @BindView(R.id.card_text_pasos) TextView mGalleryPasosText;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getGalleryPasosClickListener().onClick(getLayoutPosition());
        }
    }

    public interface GalleryPasosClickListener {
        void onClick(int position);
    }
}
