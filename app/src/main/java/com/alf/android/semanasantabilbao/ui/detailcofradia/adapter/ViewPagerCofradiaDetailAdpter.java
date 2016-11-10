package com.alf.android.semanasantabilbao.ui.detailcofradia.adapter;


import android.app.Application;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaActivity;

import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerCofradia;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerDetalle;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerPasos;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaViewPagerGaleria;


public class ViewPagerCofradiaDetailAdpter extends PagerAdapter {

    private final String LOG_TAG = DetailCofradiaActivity.class.getSimpleName();

    private Application application;
    private Context context;
    private Cofradia cofradia;
    private String[] tabtitlearray = new String[4];
    private DetailCofradiaViewPagerCofradia detailCofradiaViewPagerCofradia;
    private DetailCofradiaViewPagerDetalle detailCofradiaViewPagerDetalle;
    private DetailCofradiaViewPagerPasos detailCofradiaViewPagerPasos;
    private DetailCofradiaViewPagerGaleria detailCofradiaViewPagerGaleria;

    public ViewPagerCofradiaDetailAdpter(Application application, Context context, Cofradia cofradia){
        final String tab1, tab2, tab3, tab4;

        this.context = context;
        this.cofradia = cofradia;
        this.application = application;

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

        //DetailCofradiaModelObject detailCofradiaModelObject = DetailCofradiaModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        //ViewGroup layout = (ViewGroup) inflater.inflate(detailCofradiaModelObject.getLayoutResId(), collection, false);

        switch (position){

            case 0: {
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);
                detailCofradiaViewPagerCofradia = new DetailCofradiaViewPagerCofradia(context, contentView);
                detailCofradiaViewPagerCofradia.showDetailCofradiaInformation (cofradia);
                collection.addView(contentView);
                return contentView;
            }
            case 1: {
                contentView = inflater.inflate(R.layout.detail_cofradia_detalle_content, collection, false);
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
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);
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

/*    @Override
    public int getCount() {
        return DetailCofradiaModelObject.values().length;
    }*/

    @Override
    public int getCount() {
        return tabtitlearray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitlearray[position];
    }


    public void liberarVistaPaso(int position) {
        switch (position){
            case 2: detailCofradiaViewPagerPasos.liberarVistaPaso();
        }
    }
}
