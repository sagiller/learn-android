package com.sagiller.learn.func.web.category;

import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.AppModule;
import com.sagiller.learn.dagger.NavigationModule;
import com.sagiller.learn.func.web.webview.WebViewFragment;
import com.sagiller.learn.func.web.webview.WebViewPresenter;
import com.sagiller.learn.model.webpage.WebpageCategory;

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
public interface WebpageCategoryComponent {
    public WebpageCategoryPresenter presenter();

    public void inject(WebpageCategoryFragment fragment);
}


