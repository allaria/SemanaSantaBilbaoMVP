package com.alf.android.semanasantabilbao.ui.detailprocesion.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.ui.detailprocesion.tabpasos.DetailProcesionViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.detailprocesion.tabprocesion.DetailProcesionViewPagerDetalle;
import com.alf.android.semanasantabilbao.ui.detailprocesion.tabruta.DetailProcesionViewPagerRuta;

/**
 * Created by Alberto Laría Fernández on 27/04/2016.
 */

public class ViewPagerProcesionDetailAdapter extends PagerAdapter {

    private final String LOG_TAG = ViewPagerProcesionDetailAdapter.class.getSimpleName();

    private Context context;
    private Procesion procesion;
    private Cofradia cofradia;
    private String[] tabtitlearray = new String[4];
    private DetailProcesionViewPagerDetalle detailProcesionViewPagerDetalle;
    private DetailProcesionViewPagerPasos detailProcesionViewPagerPasos;
    private DetailProcesionViewPagerRuta detailProcesionViewPagerRuta;

    //private ViewPagerProcesionDetailAdapter.ViewPagerCofradiaDetailAdapterClickListener viewPagerCofradiaDetailAdapterClickListener;

    public ViewPagerProcesionDetailAdapter(Context context, Procesion procesion, Cofradia cofradia){
        final String tab1, tab2, tab3, tab4;

        this.context = context;
        this.procesion = procesion;
        this.cofradia = cofradia;

        tab1 = context.getString(R.string.tab_detail_procesion_one);
        tab2 = context.getString(R.string.tab_detail_procesion_two);
        tab3 = context.getString(R.string.tab_detail_procesion_three);
        tab4 = context.getString(R.string.tab_detail_procesion_four);

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
                contentView = inflater.inflate(R.layout.detail_procesion_detail_content, collection, false);
                detailProcesionViewPagerDetalle = new DetailProcesionViewPagerDetalle(context, contentView);
                detailProcesionViewPagerDetalle.showDetailProcesionInformationDetalle (procesion);
                collection.addView(contentView);

                //detailCofradiaViewPagerCofradia.setDetailCofradiaViewPagerCofradiaClickListener(this);

                return contentView;
            }
            case 1: {
                contentView = inflater.inflate(R.layout.detail_procesion_paso_content, collection, false);
                detailProcesionViewPagerPasos = new DetailProcesionViewPagerPasos(context, contentView);
                detailProcesionViewPagerPasos.showDetailProcesionInformationPasos(procesion, cofradia);
                collection.addView(contentView);
                return contentView;
            }
            case 2: {
                contentView = inflater.inflate(R.layout.detail_procesion_ruta_content, collection, false);
                detailProcesionViewPagerRuta = new DetailProcesionViewPagerRuta(context, contentView);
                detailProcesionViewPagerRuta.showDetailProcesionInformationRuta(procesion);
                collection.addView(contentView);
                return contentView;
            }
            case 3: {
//                contentView = inflater.inflate(R.layout.detail_cofradia_paso_content, collection, false);
//                detailCofradiaViewPagerPasos = new DetailCofradiaViewPagerPasos(context, contentView);
//                detailCofradiaViewPagerPasos.showDetailCofradiaInformationPasos(cofradia);
//                collection.addView(contentView);
//                return contentView;
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
                //No View or Suscription must be detach or unsuscribe from.
            }
            case 1: {
                Log.d(LOG_TAG, "Detach Ruta View. "+position);
                detailProcesionViewPagerPasos.detachViewUnsuscribeSuscriptionPaso();
                break;
            }
            case 2: {
                //No View or Suscription must be detach or unsuscribe from.
            }
            case 3: {
                //No View or Suscription must be detach or unsuscribe from.
            }
        }
    }

/*    @Override
    public void onClickDetailCofradiaViewPagerCofradia(int position, Procesion procesion) {
        //Toast.makeText(context, "CLICK - ViewPagerCofradiaDetailAdapter", Toast.LENGTH_SHORT).show();
        viewPagerCofradiaDetailAdapterClickListener.onClickDetailCofradiaActivity(position, procesion);
    }

    public interface ViewPagerCofradiaDetailAdapterClickListener {
        void onClickDetailCofradiaActivity(int position, Procesion procesion);
    }

    public ViewPagerProcesionDetailAdapter.ViewPagerCofradiaDetailAdapterClickListener getViewPagerCofradiaDetailAdapterClickListener() {
        return viewPagerCofradiaDetailAdapterClickListener;
    }

    public void setViewPagerCofradiaDetailAdapterClickListener(ViewPagerProcesionDetailAdapter.ViewPagerCofradiaDetailAdapterClickListener viewPagerCofradiaDetailAdapterClickListener) {
        this.viewPagerCofradiaDetailAdapterClickListener = viewPagerCofradiaDetailAdapterClickListener;
    }*/
}
