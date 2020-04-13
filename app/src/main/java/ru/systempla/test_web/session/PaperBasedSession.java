package ru.systempla.test_web.session;

import io.paperdb.Paper;
import io.reactivex.Completable;
import io.reactivex.Single;

public class PaperBasedSession implements ISession {

    @Override
    public void saveUrl(String session) {
        Completable.fromAction(()-> Paper.book("sessions").write("session",session)).subscribe();
    }

    @Override
    public Single<String> getUrl() {
        return Single.fromCallable(()->Paper.book("sessions").read("session"));
    }

    @Override
    public void resetUrl() {
        Completable.fromAction(()->Paper.book("sessions").write("session","")).subscribe();
    }
}
