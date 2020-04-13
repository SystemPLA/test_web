package ru.systempla.test_web.main;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.systempla.test_web.R;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.web_frame)
    WebView webView;

    WebChromeClient webChromeClient;
    WebViewClient webViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
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
