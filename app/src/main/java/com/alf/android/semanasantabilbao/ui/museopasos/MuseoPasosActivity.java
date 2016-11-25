package com.alf.android.semanasantabilbao.ui.museopasos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alf.android.semanasantabilbao.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alaria on 16/06/2016.
 */
public class MuseoPasosActivity extends AppCompatActivity {

    private final String LOG_TAG = MuseoPasosActivity.class.getSimpleName();

    @BindView(R.id.museo_pasos_toolbar) Toolbar toolbar;
    @BindView(R.id.museo_pasos_webView) WebView mWebView;
    @BindString(R.string.url_museo_pasos) String urlMuseoPasos;
    @BindString(R.string.loading_museo_pasos_text) String loadingMuseoPasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.museo_pasos_activity);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.logo);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.getSettings().setDisplayZoomControls(false);

            Log.d(LOG_TAG, "Loading Web: " + urlMuseoPasos);
            mWebView.loadUrl(urlMuseoPasos);

            //mWebView.setWebViewClient(new MyWebViewClient());

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mWebView.saveState(outState);
    }

    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

/*    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Toast.makeText(getApplicationContext(), loadingMuseoPasos, LENGTH_SHORT).show();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Toast.makeText(getApplicationContext(), loadingMuseoPasos, LENGTH_SHORT).show();
        }
    }*/
}
