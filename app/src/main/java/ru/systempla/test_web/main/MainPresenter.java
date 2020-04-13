package ru.systempla.test_web.main;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.test_web.Constants;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadGame();
    }

    private void loadGame() {
        getViewState().loadUrl(Constants.GAME_URL);
    }
}
