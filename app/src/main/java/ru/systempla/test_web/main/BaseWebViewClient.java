package ru.systempla.test_web.main;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.systempla.test_web.Constants;

public class BaseWebViewClient extends WebViewClient {

    MainPresenter presenter;

    public BaseWebViewClient(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        presenter.stopLoad();
    }
}
