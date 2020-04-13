package ru.systempla.test_web.session;

import io.reactivex.Single;

public interface ISession {

    void saveUrl(String url);
    Single<String> getUrl();
    void resetUrl();
}
