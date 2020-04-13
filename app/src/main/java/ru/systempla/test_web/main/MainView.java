package ru.systempla.test_web.main;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void init();
    void loadUrl(String url);
    void onAsking();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showMessage(String message);
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showLoading();
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void hideLoading();

}
