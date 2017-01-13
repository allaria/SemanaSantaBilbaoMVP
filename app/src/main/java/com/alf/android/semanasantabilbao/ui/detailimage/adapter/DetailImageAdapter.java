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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alberto Laria Fernández on 30/11/2016.
 */

public class DetailImageAdapter extends PagerAdapter {

    private static final String LOG_TAG = DetailImageAdapter.class.getSimpleName();
    private DetailImageAdapter.DetailImageClickListener detailImageClickListener;
    private Activity activity;
    private ArrayList<String> imagePaths;
    private LayoutInflater inflater;

    @BindView(R.id.btnClose) ImageButton btnClose;
    @BindView(R.id.imgDisplay) TouchImageView imgDisplay;
    @BindView(R.id.detail_image_backward_arrow) ImageButton imgBackwardArrow;
    @BindView(R.id.detail_image_forward_arrow) ImageButton imgForwardArrow;

    public DetailImageAdapter(Activity activity, ArrayList<String> imagePaths) {
        this.activity = activity;
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return this.imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.detail_image_content, container, false);

        ButterKnife.bind(this, viewLayout);

        String imgGaleria = imagePaths.get(position);
        int idDrawableNoImage = container.getContext().getResources().getIdentifier("no_image", "drawable", container.getContext().getPackageName());
        Picasso.with(container.getContext())
                .load(imgGaleria)
                .placeholder(idDrawableNoImage)
                .error(idDrawableNoImage)
                .into(imgDisplay);

        ((ViewPager) container).addView(viewLayout);

        if (position == 0) {
            imgBackwardArrow.setVisibility(View.INVISIBLE);
        }

        if (position == imagePaths.size()-1) {
            imgForwardArrow.setVisibility(View.INVISIBLE);
        }

        return viewLayout;
    }

    public DetailImageAdapter.DetailImageClickListener getDetailImageClickListener() {
        return detailImageClickListener;
    }

    public void setDetailImageClickListener(DetailImageAdapter.DetailImageClickListener detailImageClickListener) {
        this.detailImageClickListener = detailImageClickListener;
    }

    @OnClick(R.id.btnClose)
    public void onClick(View v) {
        activity.finish();
    }

    @OnClick(R.id.detail_image_backward_arrow)
    public void onClickDetailImageBackward(View v) {
        getDetailImageClickListener().setViewPagerItem(-1);
    }

    @OnClick(R.id.detail_image_forward_arrow)
    public void onClickDetailImageForward(View v) {
        getDetailImageClickListener().setViewPagerItem(1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

    public interface DetailImageClickListener {
        void setViewPagerItem(int i);
    }
}
