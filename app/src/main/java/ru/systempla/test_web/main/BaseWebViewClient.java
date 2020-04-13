package ru.systempla.test_web.main;

import android.os.Build;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.systempla.test_web.Constants;
import timber.log.Timber;

public class BaseWebViewClient extends WebViewClient {

    MainPresenter presenter;

    public BaseWebViewClient(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        presenter.stopLoad();

        view.evaluateJavascript(Constants.JS_URL, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Timber.e("onReceiveValue: " + s);
            }
        });

        presenter.startAskCycle();
    }
}
