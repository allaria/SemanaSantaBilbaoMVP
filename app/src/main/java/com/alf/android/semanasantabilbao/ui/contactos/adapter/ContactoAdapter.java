package com.alf.android.semanasantabilbao.ui.contactos.adapter;

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

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.Holder>{

    private static final String LOG_TAG = ContactoAdapter.class.getSimpleName();
    private ContactoClickListener contactoClickListener;
    private List<Cofradia> mContactos;

    @Inject
    public ContactoAdapter() {
        mContactos = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto_card, null, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        int idDrawable;

        Cofradia currContacto = mContactos.get(position);
        String imgEscudo = currContacto.getImagenEscudo();
        int idDrawableEscudo = holder.itemView.getContext().getResources().getIdentifier(imgEscudo, "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext())
                .load(idDrawableEscudo)
                .into(holder.mPhotoEscudo);
        holder.mContactoName.setText(currContacto.getNombreCofradia());
        idDrawable = holder.itemView.getContext().getResources().getIdentifier("ic_contacto_direccion", "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext()).load(idDrawable).into(holder.mPhotoDireccion);
        holder.mContactoDireccion.setText(currContacto.getDireccion());
        idDrawable = holder.itemView.getContext().getResources().getIdentifier("ic_contacto_telefono", "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext()).load(idDrawable).into(holder.mPhotoTelefono);
        holder.mContactoTelefono.setText(currContacto.getTelefono());
        idDrawable = holder.itemView.getContext().getResources().getIdentifier("ic_contacto_email", "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext()).load(idDrawable).into(holder.mPhotoEmail);
        holder.mContactoEmail.setText(currContacto.getEmail());
        idDrawable = holder.itemView.getContext().getResources().getIdentifier("ic_contacto_web", "drawable", holder.itemView.getContext().getPackageName());
        Picasso.with(holder.itemView.getContext()).load(idDrawable).into(holder.mPhotoWeb);
        holder.mContactoWeb.setText(currContacto.getWeb());
    }

    @Override
    public int getItemCount() {
        return mContactos.size();
    }

    public void addContactos(List<Cofradia> listaContactos) {
        mContactos.clear();
        mContactos.addAll(listaContactos);
        notifyDataSetChanged();
    }

    public Cofradia getSelectedContacto(int position) {
        return mContactos.get(position);
    }

    public ContactoClickListener getContactoClickListener() {
        return contactoClickListener;
    }

    public void setContactoClickListener(ContactoClickListener contactoClickListener) {
        this.contactoClickListener = contactoClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.contacto_cofradia_escudo) ImageView mPhotoEscudo;
        @BindView(R.id.icono_direccion) ImageView mPhotoDireccion;
        @BindView(R.id.icono_telefono) ImageView mPhotoTelefono;
        @BindView(R.id.icono_email) ImageView mPhotoEmail;
        @BindView(R.id.icono_web) ImageView mPhotoWeb;
        @BindView(R.id.contacto_cofradia_name) TextView mContactoName;
        @BindView(R.id.contacto_cofradia_direccion) TextView mContactoDireccion;
        @BindView(R.id.contacto_cofradia_telefono) TextView mContactoTelefono;
        @BindView(R.id.contacto_cofradia_email) TextView mContactoEmail;
        @BindView(R.id.contacto_cofradia_web) TextView mContactoWeb;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getContactoClickListener().onClick(getLayoutPosition());
        }
    }

    public interface ContactoClickListener {
        void onClick(int position);
    }
}
