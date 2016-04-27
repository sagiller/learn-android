package com.sagiller.learn.func.web.webpage.create;

import android.os.Bundle;

import com.sagiller.learn.constant.Constants;
import com.sagiller.mvp.viewstate.RestorableViewState;



public class CreateWebpageViewState implements RestorableViewState<CreateWebpageView> {

  private final String KEY_STATE =  Constants.INTENT_PARAM_PRE + "CreateWebpageViewState.current_state";

  private final int STATE_SHOWING_FORM = 0;
  private final int STATE_SHOWING_LOADING = 1;
  private final int STATE_SHOWING_AUTH_REQUIRED = -1;

  private int currentState = 0;

  @Override public void saveInstanceState(Bundle out) {
    out.putInt(KEY_STATE, currentState);
  }

  @Override public RestorableViewState<CreateWebpageView> restoreInstanceState(Bundle in) {
    currentState = in.getInt(KEY_STATE);
    return this;
  }

  @Override public void apply(CreateWebpageView view, boolean retained) {
    if (currentState == STATE_SHOWING_FORM) {
      view.showForm();
    } else if (currentState == STATE_SHOWING_AUTH_REQUIRED) {
      view.showAuthenticationRequired();
    } else {
      view.showLoading();
    }
  }

  public void setStateShowForm() {
    currentState = STATE_SHOWING_FORM;
  }

  public void setStateShowLoading() {
    currentState = STATE_SHOWING_LOADING;
  }

  public void setStateAuthenticationRequired() {
    currentState = STATE_SHOWING_AUTH_REQUIRED;
  }
}
