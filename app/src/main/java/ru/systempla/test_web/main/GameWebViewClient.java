package ru.systempla.test_web.main;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameWebViewClient extends WebViewClient {

    MainPresenter presenter;

    public GameWebViewClient(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        presenter.stopLoad();
    }
}
