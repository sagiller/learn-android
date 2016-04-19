package com.sagiller.learn.func.web.webpage;

import com.sagiller.mvp.common.lce.MvpLceView;
import com.sagiller.mvp.viewstate.lce.LceViewState;

/**
 * Created by sagiller on 16/4/13.
 */
public interface WebpagesViewState<D, V extends MvpLceView<D>> extends LceViewState<D,V> {

    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;

    int state = STATE_SHOW_LOADING;
}

