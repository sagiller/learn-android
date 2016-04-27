package com.sagiller.learn.func.web.webpage.create;

import android.content.Context;


import com.sagiller.learn.IntentStarter;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.mvp.common.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


public class CreateWebpagePresenter extends MvpBasePresenter<CreateWebpageView> {

  private EventBus eventBus;
  private IntentStarter intentStarter;

  @Inject public CreateWebpagePresenter(EventBus eventBus, IntentStarter intentStarter) {
    this.eventBus = eventBus;
    this.intentStarter = intentStarter;
  }

  public void createWebpage(Context context, Webpage webpage) {
    getView().showLoading();
    //intentStarter.sendMailViaService(context, mail);
  }

  public void onEventMainThread(LoginSuccessfulEvent event) {
    if (isViewAttached()) {
      getView().showForm();
    }
  }

  @Override public void attachView(CreateWebpageView view) {
    super.attachView(view);
    eventBus.register(this);
  }

  @Override public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);
    eventBus.unregister(this);
  }
}
