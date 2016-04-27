package com.sagiller.learn.func.web.webpage.create;


import com.sagiller.learn.dagger.AppComponent;
import com.sagiller.learn.dagger.AppModule;
import com.sagiller.learn.dagger.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton @Component(
    modules = { AppModule.class, NavigationModule.class },
    dependencies = AppComponent.class) public interface CreateWebpageComponent {

  public CreateWebpagePresenter presenter();

  public void inject(CreateWebpageActivity activity);
}
