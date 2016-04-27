package com.sagiller.learn;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.DaggerAppComponent;
import com.sagiller.learn.model.http.okhttp.OkNetworkManager;

/**
 * Created by sagiller on 16/4/7.
 */
public class App extends Application {
    private static AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        OkNetworkManager.initialize();
        Fresco.initialize(this);
        appComponent = DaggerAppComponent.create();
    }

    public static AppComponent getAppComponents() {
        return appComponent;
    }
}
