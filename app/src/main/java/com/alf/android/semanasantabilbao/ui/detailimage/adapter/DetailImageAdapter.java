package com.alf.android.semanasantabilbao.ui.detailimage.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.ui.detailimage.utils.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Alberto Laria Fernández on 30/11/2016.
 */

public class DetailImageAdapter extends PagerAdapter {

    private static final String LOG_TAG = DetailImageAdapter.class.getSimpleName();
    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;

    public DetailImageAdapter(Activity activity,
                              ArrayList<String> imagePaths) {
        this._activity = activity;
        this._imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return this._imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView imgDisplay;
        ImageButton btnClose;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.detail_image_content, container,
                false);

        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (ImageButton) viewLayout.findViewById(R.id.btnClose);

        String imgGaleria = _imagePaths.get(position);
        int idDrawableNoImage = container.getContext().getResources().getIdentifier("no_image", "drawable", container.getContext().getPackageName());
        Picasso.with(container.getContext())
                .load(imgGaleria)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(imgDisplay);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
