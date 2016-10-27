package com.alf.android.semanasantabilbao.ui.detailcofradia.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.ui.detailcofradia.DetailCofradiaActivity;
import com.alf.android.semanasantabilbao.ui.detailcofradia.modelobject.DetailCofradiaModelObject;


public class ViewPagerCofradiaDetailAdpter extends PagerAdapter {

    private final String LOG_TAG = DetailCofradiaActivity.class.getSimpleName();

    private Context context;
    private Cofradia cofradia;
    private View contentView;
    private String tab1, tab2, tab3, tab4;
    private String[] tabtitlearray = new String[4];
    private TextView mNombreCofradia, mFundacion, mSede, mPasos, mTexto, mHernamoAbad, mTunica, mNumeroProcesiones, mProcesion;

    public ViewPagerCofradiaDetailAdpter(Context context, Cofradia cofradia){
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

        Log.d(LOG_TAG, ""+tabtitlearray[position]);

        DetailCofradiaModelObject detailCofradiaModelObject = DetailCofradiaModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        //ViewGroup layout = (ViewGroup) inflater.inflate(detailCofradiaModelObject.getLayoutResId(), collection, false);

        switch (position){

            case 0: {
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);

                mFundacion = (TextView) contentView.findViewById(R.id.cofradia_fundacion);
                mFundacion.setText(String.valueOf(cofradia.getFundacion()));

                collection.addView(contentView);
                return contentView;
            }
            case 1: {
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);

                collection.addView(contentView);
                return contentView;
            }
            case 2: {
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);

                collection.addView(contentView);
                return contentView;
            }
            case 3: {
                contentView = inflater.inflate(R.layout.detail_cofradia_content, collection, false);

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
        return DetailCofradiaModelObject.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabtitlearray[position];
    }
}
