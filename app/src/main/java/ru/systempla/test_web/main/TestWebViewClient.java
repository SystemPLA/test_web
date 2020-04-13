package ru.systempla.test_web.main;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.systempla.test_web.Constants;
import timber.log.Timber;

public class TestWebViewClient extends WebViewClient {

    MainPresenter presenter;

    public TestWebViewClient(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        presenter.setFlag(false);
        if (request.getUrl().toString().equals(Constants.YANDEX_URL)) {
            presenter.loadGame();
            return false;
        } else {
//            Timber.e("ПЕРЕНАПРАВЛЯЮ");
            presenter.loadTrack();
            return false;
        }
    }
}
