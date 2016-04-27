package com.sagiller.learn.func.web.webpage.create;


import com.sagiller.mvp.common.MvpView;

public interface CreateWebpageView extends MvpView {

  public void showForm();

  public void showLoading();

  public void showError(Throwable e);

  public void showAuthenticationRequired();

  public void finishBecauseSuccessful();
}
