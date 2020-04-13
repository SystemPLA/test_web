package ru.systempla.test_web.main;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.systempla.test_web.Constants;
import ru.systempla.test_web.R;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.web_frame)
    WebView webView;

    WebChromeClient webChromeClient;
    WebViewClient webViewClient;
    TestWebViewClient testWebViewClient;
    TrackWebViewClient trackWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testWebViewClient = new TestWebViewClient(presenter);
        trackWebViewClient = new TrackWebViewClient(presenter);
        ButterKnife.bind(this);
    }

    @Override
    public void loadUrl(String url) {
//        Timber.e("В АКТИВИТИ");
        if (url.equals(Constants.TEST_URL)) {
//            Timber.e("ЗАГРУЗКА ТЕСТ");
            webView.setWebViewClient(testWebViewClient);
            webView.loadUrl(url);
            return;
        } else if (url.equals(Constants.TRACK_URL)) {
            webView.setWebViewClient(trackWebViewClient);
            webView.loadUrl(url);
            return;
        } else if (url.equals(Constants.GAME_URL)) {
            webView.setWebViewClient(webViewClient);
            webView.loadUrl(url);
            return;
        } else webView.loadUrl(url);
    }

    @Override
    public void init(){
        webViewClient = new WebViewClient();
        webChromeClient = new WebChromeClient();

        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        webView.getSettings().setJavaScriptEnabled(true);
    }
}
