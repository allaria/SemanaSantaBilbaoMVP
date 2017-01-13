package com.alf.android.semanasantabilbao.ui.detailcofradia.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia.DetailCofradiaViewPagerCofradia;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabdetalle.DetailCofradiaViewPagerDetalle;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabgaleria.DetailCofradiaViewPagerGaleria;
import com.alf.android.semanasantabilbao.ui.detailcofradia.tabpasos.DetailCofradiaViewPagerPasos;

/**
 * Created by Alberto Laría Fernández on 27/04/2016.
 */

public class ViewPagerCofradiaDetailAdapter extends PagerAdapter implements DetailCofradiaViewPagerCofradia.DetailCofradiaViewPagerCofradiaClickListener {

    private final String LOG_TAG = ViewPagerCofradiaDetailAdapter.class.getSimpleName();

    private Context context;
    private Cofradia cofradia;
    private String[] tabtitlearray = new String[4];
    private DetailCofradiaViewPagerCofradia detailCofradiaViewPagerCofradia;
    private DetailCofradiaViewPagerDetalle detailCofradiaViewPagerDetalle;
    private DetailCofradiaViewPagerPasos detailCofradiaViewPagerPasos;
    private DetailCofradiaViewPagerGaleria detailCofradiaViewPagerGaleria;

    private ViewPagerCofradiaDetailAdapter.ViewPagerCofradiaDetailAdapterClickListener viewPagerCofradiaDetailAdapterClickListener;

    public ViewPagerCofradiaDetailAdapter(Context context, Cofradia cofradia){
        final String tab1, tab2, tab3, tab4;

        this.context = context;
        this.cofradia = cofradia;

        tab1 = context.getString(R.string.tab_detail_cofradia_one);
        tab2 = context.getString(R.string.tab_detail_cofradia_two);
        tab3 = context.getString(R.string.tab_detail_cofradia_three);
        tab4 = context.getString(R.string.tab_detail_cofradia_four);

        tabtitlearray[0]=tab1;
        tabtitlearray[1]=tab2;
        tabtitlearray[2]=tab3;
        tabtitlearray[3]=tab4;
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View contentView;

        LayoutInflater inflater = LayoutInflater.from(context);
        switch (position){
            case 0: {
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);
                detailCofradiaViewPagerCofradia = new DetailCofradiaViewPagerCofradia(context, contentView);
                detailCofradiaViewPagerCofradia.showDetailCofradiaInformation (cofradia);
                collection.addView(contentView);

                detailCofradiaViewPagerCofradia.setDetailCofradiaViewPagerCofradiaClickListener(this);

                return contentView;
            }
            case 1: {
                contentView = inflater.inflate(R.layout.detail_cofradia_detail_content, collection, false);
                detailCofradiaViewPagerDetalle = new DetailCofradiaViewPagerDetalle(context, contentView);
                detailCofradiaViewPagerDetalle.showDetailCofradiaInformationDetalle(cofradia);
                collection.addView(contentView);
                return contentView;
            }
            case 2: {
                contentView = inflater.inflate(R.layout.detail_cofradia_paso_content, collection, false);
                detailCofradiaViewPagerPasos = new DetailCofradiaViewPagerPasos(context, contentView);
                detailCofradiaViewPagerPasos.showDetailCofradiaInformationPasos(cofradia);
                collection.addView(contentView);
                return contentView;
            }
            case 3: {
                contentView = inflater.inflate(R.layout.detail_cofradia_gallery_content, collection, false);
                detailCofradiaViewPagerGaleria = new DetailCofradiaViewPagerGaleria(context, contentView);
                detailCofradiaViewPagerGaleria.showDetailCofradiaInformationGaleria(cofradia);
                collection.addView(contentView);
                return contentView;
            }
        }
        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return tabtitlearray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitlearray[position];
    }


    public void detachViewUnsuscribeSuscription(int position) {
        switch (position){
            case 0: {
                Log.d(LOG_TAG, "Detach Pasos View & Unsuscribe Procesiones Suscription. "+position);
                detailCofradiaViewPagerCofradia.detachViewUnsuscribeSuscriptionProcesiones();
                break;
            }
            case 1: {
                //No View or Suscription must be detach or unsuscribe from.
            }
            case 2: {
                Log.d(LOG_TAG, "Detach Pasos View & Unsuscribe Pasos Suscription. "+position);
                detailCofradiaViewPagerPasos.detachViewUnsuscribeSuscriptionPaso();
                break;
            }
            case 3: {
                Log.d(LOG_TAG, "Detach Galeria View & Unsuscribe Galeria Suscription. "+position);
                detailCofradiaViewPagerGaleria.detachViewUnsuscribeSuscriptionImagenesGaleria();
                break;
            }
        }
    }

    @Override
    public void onClickDetailCofradiaViewPagerCofradia(int position, Procesion procesion) {
        //Toast.makeText(context, "CLICK - ViewPagerCofradiaDetailAdapter", Toast.LENGTH_SHORT).show();
        viewPagerCofradiaDetailAdapterClickListener.onClickDetailCofradiaActivity(position, procesion);
    }

    public interface ViewPagerCofradiaDetailAdapterClickListener {
        void onClickDetailCofradiaActivity(int position, Procesion procesion);
    }

    public ViewPagerCofradiaDetailAdapter.ViewPagerCofradiaDetailAdapterClickListener getViewPagerCofradiaDetailAdapterClickListener() {
        return viewPagerCofradiaDetailAdapterClickListener;
    }

    public void setViewPagerCofradiaDetailAdapterClickListener(ViewPagerCofradiaDetailAdapter.ViewPagerCofradiaDetailAdapterClickListener viewPagerCofradiaDetailAdapterClickListener) {
        this.viewPagerCofradiaDetailAdapterClickListener = viewPagerCofradiaDetailAdapterClickListener;
    }
}
