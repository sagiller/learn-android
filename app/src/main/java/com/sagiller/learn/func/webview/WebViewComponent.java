package com.sagiller.learn.func.webview;

import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.AppModule;
import com.sagiller.learn.dagger.NavigationModule;
import com.sagiller.learn.func.article.ArticleFragment;
import com.sagiller.learn.func.article.ArticlePresenter;

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
public interface WebViewComponent {
    public WebViewPresenter presenter();

    public void inject(WebViewFragment fragment);
}


