package ru.systempla.test_web;

import android.app.Application;

import io.paperdb.Paper;
import ru.systempla.test_web.session.ISession;
import ru.systempla.test_web.session.PaperBasedSession;
import timber.log.Timber;

public class App extends Application {

    private static App instance;
    private static INetworkStatus networkStatus;
    private static ISession sessions;

    @Override
    public void onCreate() {
        Timber.plant(new Timber.DebugTree());
        this.networkStatus = new NetworkStatus();
        this.instance = this;
        this.sessions = new PaperBasedSession();
        Paper.init(this);
        super.onCreate();
    }

    public static App getInstance(){
        return instance;
    }
    public static INetworkStatus getNetworkStatus() {return networkStatus;}
    public static ISession getSessionManager(){return sessions;}
}
