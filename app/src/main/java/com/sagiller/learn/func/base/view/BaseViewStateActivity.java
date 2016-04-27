/*
 *  Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sagiller.learn.func.base.view;

import android.os.Bundle;


import com.sagiller.mvp.common.MvpPresenter;
import com.sagiller.mvp.common.MvpView;
import com.sagiller.mvp.viewstate.MvpViewStateActivity;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Base class which adds Butterknife, icepick and depenedncy injection to a MvpViewStateActivity
 *
 * @author Hannes Dorfmann
 */
public abstract class BaseViewStateActivity<V extends MvpView, P extends MvpPresenter<V>>
    extends MvpViewStateActivity<V, P> {

  @Override protected void onCreate(Bundle savedInstanceState) {
    injectDependencies();
    super.onCreate(savedInstanceState);
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();
    ButterKnife.bind(this);
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }

  protected void injectDependencies() {

  }
}
