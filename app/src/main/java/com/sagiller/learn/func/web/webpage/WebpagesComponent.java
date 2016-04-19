package com.sagiller.learn.func.web.webpage;

import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.AppModule;
import com.sagiller.learn.dagger.NavigationModule;
import com.sagiller.learn.func.web.category.WebpageCategoryFragment;
import com.sagiller.learn.func.web.category.WebpageCategoryPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sagiller on 16/4/11.
 */
@Singleton
@Component(
        modules = { AppModule.class, NavigationModule.class },
        dependencies = AppComponent.class
)
public interface WebpagesComponent {
    public WebpagesPresenter presenter();

    public void inject(WebpagesFragment fragment);
}


