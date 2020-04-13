package ru.systempla.test_web.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.systempla.test_web.Constants;
import ru.systempla.test_web.R;
import timber.log.Timber;

import static io.reactivex.Maybe.fromAction;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.web_frame)
    WebView webView;

    @BindView(R.id.rl_loading)
    RelativeLayout loadingRelativeLayout;

    WebChromeClient webChromeClient;
    WebViewClient webViewClient;
    TestWebViewClient testWebViewClient;
    TrackWebViewClient trackWebViewClient;
    BaseWebViewClient baseWebViewClient;
    GameWebViewClient gameWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testWebViewClient = new TestWebViewClient(presenter);
        trackWebViewClient = new TrackWebViewClient(presenter);
        baseWebViewClient = new BaseWebViewClient(presenter);
        gameWebViewClient = new GameWebViewClient(presenter);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        presenter.stopAskCycle();
        super.onDestroy();
    }

    @Override
    public void loadUrl(String url) {
        if (url.equals(Constants.TEST_URL)) {
            webView.setWebViewClient(testWebViewClient);
            presenter.saveSession(url);
            webView.loadUrl(url);
            return;
        } else if (url.equals(Constants.TRACK_URL)) {
            webView.setWebViewClient(trackWebViewClient);
            presenter.saveSession(url);
            webView.loadUrl(url);
            return;
        } else if (url.equals(Constants.GAME_URL)) {
            webView.setWebViewClient(gameWebViewClient);
            presenter.saveSession(url);
            webView.loadUrl(url);
            return;
        } else {
            webView.setWebViewClient(baseWebViewClient);
            presenter.saveSession(url);
            webView.loadUrl(url);
        }
    }

    @Override
    public void init(){
        webViewClient = new WebViewClient();
        webChromeClient = new WebChromeClient();

        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingRelativeLayout.setVisibility(View.GONE);
            }
        }, 4500);
    }

    @Override
    public void onAsking() {
        webView.evaluateJavascript("javascript:ask()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                presenter.logData(s);
            }
        });
    }

}
