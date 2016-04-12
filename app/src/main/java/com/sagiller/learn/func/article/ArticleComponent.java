package com.sagiller.learn.func.article;

import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.AppModule;
import com.sagiller.learn.dagger.NavigationModule;

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
public interface ArticleComponent {
    public ArticlePresenter presenter();

    public void inject(ArticleFragment fragment);
}


