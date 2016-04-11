package com.sagiller.learn.dagger;


import com.sagiller.learn.IntentStarter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Hannes Dorfmann
 */
@Module public class NavigationModule {

  @Singleton @Provides public IntentStarter providesIntentStarter() {
    return new IntentStarter();
  }
}
