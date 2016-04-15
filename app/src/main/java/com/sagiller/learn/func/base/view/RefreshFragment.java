/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sagiller.learn.func.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.sagiller.learn.R;
import com.sagiller.mvp.common.MvpPresenter;
import com.sagiller.mvp.common.MvpView;
import com.sagiller.mvp.common.lce.MvpLceView;


/**
 * @author Hannes Dorfmann
 */
public abstract class RefreshFragment<M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends BaseLceFragment<SwipeRefreshLayout, M, V, P>
    implements MvpLceView<M>, SwipeRefreshLayout.OnRefreshListener {


  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    contentView.setOnRefreshListener(this);
    int [] colors = getActivity().getResources().getIntArray(R.array.loading_colors);
    contentView.setColorSchemeColors(colors);
  }

  @Override public void onRefresh() {
    loadData(true);
  }

  @Override public void showContent() {
    super.showContent();
    contentView.setRefreshing(false);
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    super.showError(e, pullToRefresh);
    contentView.setRefreshing(false);
  }

  @Override public void showLoading(boolean pullToRefresh) {
    super.showLoading(pullToRefresh);
    if (pullToRefresh && !contentView.isRefreshing()) {
      // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
      contentView.post(new Runnable() {
        @Override public void run() {
          contentView.setRefreshing(true);
        }
      });
    }
  }


}
