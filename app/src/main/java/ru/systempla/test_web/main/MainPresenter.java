package ru.systempla.test_web.main;

import io.reactivex.Completable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.test_web.Constants;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private boolean flag = false;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadTest();
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
