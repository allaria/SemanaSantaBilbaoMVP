package com.alf.android.semanasantabilbao.ui.detailcofradia.adapter;

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
 * Created by alaria on 27/04/2016.
 */
public class ProcesionAdapter extends RecyclerView.Adapter<ProcesionAdapter.Holder>{

    private static final String TAG = ProcesionAdapter.class.getSimpleName();
    private ProcesionAdapter.ProcesionClickListener procesionClickListener;
    private List<Procesion> mProcesiones;
    private Context context;

    @Inject
    public ProcesionAdapter() {
        mProcesiones = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.procesion_card, null, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Procesion currProcesion = mProcesiones.get(position);
        String imgProcesion = currProcesion.getImagenProcesion();
        int idDrawable = holder.itemView.getContext().getResources().getIdentifier(imgProcesion, "drawable", holder.itemView.getContext().getPackageName());
        int idDrawableNoImage = holder.itemView.getContext().getResources().getIdentifier(context.getResources().getString(R.string.NO_IMAGE), "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(idDrawable)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(holder.mPhotoProcesion);
        holder.mProcesion.setText(currProcesion.getNombreProcesion());
    }

    @Override
    public int getItemCount() {
        return mProcesiones.size();
    }

    public void addProcesiones(List<Procesion> listaProcesiones) {
        mProcesiones.clear();
        mProcesiones.addAll(listaProcesiones);
        notifyDataSetChanged();
    }

    public Procesion getSelectedProcesion(int position) {
        return mProcesiones.get(position);
    }

    public ProcesionAdapter.ProcesionClickListener getProcesionClickListener() {
        return procesionClickListener;
    }

    public void setProcesionClickListener(ProcesionAdapter.ProcesionClickListener procesionClickListener) {
        this.procesionClickListener = procesionClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_procesion_image) ImageView mPhotoProcesion;
        @BindView(R.id.card_procesion_name) TextView mProcesion;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            procesionClickListener.onClickProcesion(getLayoutPosition());
        }
    }

    public interface ProcesionClickListener {
        void onClickProcesion(int position);
    }
}
