package com.alf.android.semanasantabilbao.ui.detailcofradia.tabcofradia;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alf.android.semanasantabilbao.R;
import com.alf.android.semanasantabilbao.data.entities.Cofradia;
import com.alf.android.semanasantabilbao.data.entities.Procesion;
import com.alf.android.semanasantabilbao.ui.detailcofradia.adapter.ProcesionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alberto on 27/10/2016.
 */

public class DetailCofradiaViewPagerCofradia extends View implements DetailCofradiaViewPagerCofradiaContract.DetailCofradiaView,
        ProcesionAdapter.ProcesionClickListener{

    private static String LOG_TAG = DetailCofradiaViewPagerCofradia.class.getSimpleName();
    private ProcesionAdapter procesionAdapter;
    private DetailCofradiaViewPagerCofradiaContract.DetailCofradiaPresenter procesionPresenter;
    private Context context;
    private String idCofradia;

    @BindView(R.id.cofradia_fundacion) TextView mFundacion;
    @BindView(R.id.cofradia_sede) TextView mSede;
    @BindView(R.id.cofradia_abad) TextView mHernamoAbad;
    @BindView(R.id.cofradia_vestimenta) TextView mVestimenta;
    @BindView(R.id.cofradia_procesiones) TextView mNumeroProcesiones;

    @BindView(R.id.cofradia_detail_procesiones_progress_bar) ProgressBar spinner;
    @BindView(R.id.procesiones_loading_text) TextView loadingText;
    @BindView(R.id.detail_cofradia_recycler_view) RecyclerView mRecyclerView;

    public DetailCofradiaViewPagerCofradia(Context context, View view) {
        super(context);
        this.context = context;
        ButterKnife.bind(this, view);
    }

    public void showDetailCofradiaInformation(Cofradia cofradia) {

        idCofradia = cofradia.getId_cofradia();

        mFundacion.setText(String.valueOf(cofradia.getFundacion()));
        mSede.setText(cofradia.getSede());
        mHernamoAbad.setText(cofradia.getHermanoAbad());
        mVestimenta.setText(cofradia.getVestimenta());
        mNumeroProcesiones.setText(String.valueOf(cofradia.getNumeroProcesiones()));

        setSpinnerAndLoadingText(false);
        initRecyclerViewProcesiones();

        procesionPresenter = new DetailCofradiaViewPagerCofradiaPresenter();
        procesionPresenter.attachProcesionesView(this);
        procesionPresenter.initPresenter(idCofradia);
    }

    private void initRecyclerViewProcesiones() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        //mRecyclerView.setRecycledViewPool(recycledViewPool);

        procesionAdapter = new ProcesionAdapter(this);
        procesionAdapter.setProcesionClickListener(this);
        mRecyclerView.setAdapter(procesionAdapter);
    }

    @Override
    public void setSpinnerAndLoadingText(boolean loadingSpinner) {
        spinner.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
        loadingText.setVisibility(loadingSpinner? View.VISIBLE : View.GONE);
    }

    @Override
    public void printProcesiones(ObservableArrayList<Procesion> mProcesiones) {
        Log.d(LOG_TAG, "Number of Procesiones in the recyclerView:"+mProcesiones.size());
        ((ProcesionAdapter) mRecyclerView.getAdapter()).addProcesiones(mProcesiones);
    }

    @Override
    public void showErrorGettingProcesiones(ObservableField<String> mensajeError) {
        Log.d(LOG_TAG, "Error getting procesiones from Firebase.");
        Toast.makeText(context, mensajeError.get(), Toast.LENGTH_SHORT).show();
    }

    public void detachViewUnsuscribeSuscriptionProcesiones(){
        procesionPresenter.detachProcesionesView();
        procesionPresenter.unsuscribeProcesionesSuscription();
    }


    @Override
    public void onClickProcesion(int position) {
        Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }
}
