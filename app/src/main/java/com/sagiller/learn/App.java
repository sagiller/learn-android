package com.sagiller.learn;

import android.app.Application;

import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.DaggerAppComponent;

/**
 * Created by sagiller on 16/4/7.
 */
public class App extends Application {
    private static AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public static AppComponent getAppComponents() {
        return appComponent;
    }
}
