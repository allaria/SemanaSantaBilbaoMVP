package com.alf.android.semanasantabilbao.ui.galleryimages.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.GalleryImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alaria on 25/04/2016.
 */
public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.Holder>{

    private static final String LOG_TAG = GalleryImagesAdapter.class.getSimpleName();
    private GalleryImagesClickListener galleryImagesClickListener;
    private List<GalleryImage> mGalleryImages;
    private Context context;

    @Inject
    public GalleryImagesAdapter() {
        mGalleryImages = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_images_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        GalleryImage currGalleryImage = mGalleryImages.get(position);
        String imgGaleria = currGalleryImage.getThumbnail();
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(imgGaleria)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mGalleryImage);

        //Picasso.with(holder.itemView.getContext()).load("https://raw.githubusercontent.com/allaria/CofradiasBilbao/master/app/src/main/res/imagenes/verMapa.png").into(holder.mPhoto);
        //Picasso.with(holder.itemView.getContext()).load(R.drawable.e_santa_vera_cruz).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mGalleryImages.size();
    }

    public void addGalleryImages(List<GalleryImage> listaGalleryImages) {
        mGalleryImages.clear();
        mGalleryImages.addAll(listaGalleryImages);
        notifyDataSetChanged();
    }

    public GalleryImage getSelectedGalleryImage(int position) {
        return mGalleryImages.get(position);
    }

    public GalleryImagesClickListener getGalleryImagesClickListener() {
        return galleryImagesClickListener;
    }

    public void setGalleryImagesClickListener(GalleryImagesClickListener galleryImagesClickListener) {
        this.galleryImagesClickListener = galleryImagesClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_image_gallery) ImageView mGalleryImage;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getGalleryImagesClickListener().onClick(getLayoutPosition());
        }
    }

    public interface GalleryImagesClickListener {
        void onClick(int position);
    }
}
