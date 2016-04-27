package com.sagiller.learn.func.web.category;

import com.sagiller.mvp.common.lce.MvpLceView;
import com.sagiller.mvp.viewstate.lce.LceViewState;

/**
 * Created by sagiller on 16/4/13.
 */
public interface WebpageCategoryViewState<D, V extends MvpLceView<D>> extends LceViewState<D,V> {

    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;

    int state = STATE_SHOW_LOADING;
    public void setStateShowingCategory();
}

