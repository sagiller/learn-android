/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sagiller.mvp.viewstate.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;

import com.sagiller.mvp.common.MvpPresenter;
import com.sagiller.mvp.common.MvpView;
import com.sagiller.mvp.delegate.ViewGroupMvpDelegate;
import com.sagiller.mvp.layout.MvpFrameLayout;
import com.sagiller.mvp.viewstate.ViewState;
import com.sagiller.mvp.viewstate.delegate.ViewGroupMvpViewStateDelegateImpl;
import com.sagiller.mvp.viewstate.delegate.ViewGroupViewStateDelegateCallback;


/**
 * A {@link MvpFrameLayout} with ViewState support.
 *
 * @author Hannes Dorfmann
 * @since 1.1
 */
public abstract class MvpViewStateFrameLayout<V extends MvpView, P extends MvpPresenter<V>>
    extends MvpFrameLayout<V, P> implements ViewGroupViewStateDelegateCallback<V, P> {

  private boolean restoringViewState = false;
  protected ViewState<V> viewState;

  public MvpViewStateFrameLayout(Context context) {
    super(context);
  }

  public MvpViewStateFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MvpViewStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(21)
  public MvpViewStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected ViewGroupMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ViewGroupMvpViewStateDelegateImpl<V, P>(this);
    }

    return mvpDelegate;
  }

  @Override public ViewState<V> getViewState() {
    return viewState;
  }

  @Override public void setViewState(ViewState<V> viewState) {
    this.viewState =  viewState;
  }

  @Override public void setRestoringViewState(boolean retstoringViewState) {
    this.restoringViewState = retstoringViewState;
  }

  @Override public boolean isRestoringViewState() {
    return restoringViewState;
  }

  @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
    // can be overridden in subclass
  }

}
