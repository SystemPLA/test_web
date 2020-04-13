package ru.systempla.test_web.main;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import timber.log.Timber;

public class TrackWebViewClient extends WebViewClient {

    MainPresenter presenter;

    public TrackWebViewClient(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (!presenter.isFlag()) {
            presenter.setFlag(true);
//            Timber.e("В НУЖНОМ МЕСТЕ");
            presenter.saveUrl(request.getUrl().toString());
            presenter.loadUrl(request.getUrl().toString());
            return false;
        }
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        presenter.stopLoad();
    }
}
