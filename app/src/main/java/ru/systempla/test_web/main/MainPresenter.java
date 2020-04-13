package ru.systempla.test_web.main;

import java.time.chrono.IsoChronology;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.test_web.App;
import ru.systempla.test_web.Constants;
import ru.systempla.test_web.session.ISession;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private boolean flag = false;
    private String url;
    private ISession session = App.getInstance().getSessionManager();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        onAppStart();
    }

    private void onAppStart(){
        getViewState().init();
        startLoad();
//        session.resetUrl();
        if (App.getInstance().getNetworkStatus().isOffline()) {
            getViewState().showMessage(Constants.NO_NETWORK);
            stopLoad();
        } else {
            session.getUrl().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe((url) -> {
                        if (url.isEmpty()||url==null) {
                            loadTest();
                        } else {
                           loadUrl(url);
                        }
                    },t->loadTest());
        };
    }

    public void loadTest(){
//        Timber.e("ПЕРВИЧНЫЙ ЗАПРОС");
        Completable.fromAction(() -> getViewState().loadUrl(Constants.TEST_URL)).subscribe();
    }

    public void loadGame() {
        Completable.fromAction(() -> getViewState().loadUrl(Constants.GAME_URL)).subscribe();
    }

    public void loadTrack() {
//        Timber.e("ПОЛУЧИЛ ПЕРЕНАПРАВЛЕНИЕ");
        Completable.fromAction(() -> getViewState().loadUrl(Constants.TRACK_URL)).subscribe();
    }

    public void loadUrl(String url) {
        Completable.fromAction(() -> getViewState().loadUrl(url)).subscribe();
    }

    public void saveSession(String url){
        session.saveUrl(url);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void saveUrl(String urlToSave) {
        url = urlToSave;
    }

    public void startLoad(){
        getViewState().showLoading();
    }

    public void stopLoad(){
        getViewState().hideLoading();
    }
}
