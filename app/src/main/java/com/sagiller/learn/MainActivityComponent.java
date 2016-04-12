package com.sagiller.learn;


import com.sagiller.learn.dagger.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Hannes Dorfmann
 */
@Singleton
@Component(
    modules = NavigationModule.class) public interface MainActivityComponent {

  public void inject(MainActivity activity);
}
