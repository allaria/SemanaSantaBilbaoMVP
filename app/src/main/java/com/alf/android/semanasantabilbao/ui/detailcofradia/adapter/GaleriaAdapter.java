package com.alf.android.semanasantabilbao.ui.detailcofradia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.ImagenGaleria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alaria on 27/04/2016.
 */
public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.Holder>{

    private static final String TAG = GaleriaAdapter.class.getSimpleName();
    private GaleriaAdapter.ImagenGaleriaClickListener imagenGaleriaClickListener;
    private List<ImagenGaleria> mImagenes;
    private Context context;

    @Inject
    public GaleriaAdapter() {
        mImagenes = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_gallery_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        ImagenGaleria currImagen = mImagenes.get(position);
        String imgGaleria = currImagen.getThumbnail();
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(imgGaleria)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mPhotoGaleria);
    }

    @Override
    public int getItemCount() {
        return mImagenes.size();
    }

    public void addImagenGaleria(List<ImagenGaleria> listaImagenGaleria) {
        mImagenes.clear();
        mImagenes.addAll(listaImagenGaleria);
        notifyDataSetChanged();
    }

    public ImagenGaleria getSelectedImagenGaleria(int position) {
        return mImagenes.get(position);
    }

    public GaleriaAdapter.ImagenGaleriaClickListener getImagenGaleriaClickListener() {
        return imagenGaleriaClickListener;
    }

    public void setImagenGaleriaClickListener(GaleriaAdapter.ImagenGaleriaClickListener imagenGaleriaClickListener) {
        this.imagenGaleriaClickListener = imagenGaleriaClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_image_gallery) ImageView mPhotoGaleria;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            imagenGaleriaClickListener.onClickImagenGaleria(getLayoutPosition());
        }
    }

    public interface ImagenGaleriaClickListener {
        void onClickImagenGaleria(int position);
    }
}
