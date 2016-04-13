package com.sagiller.learn.func.webview;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.sagiller.mvp.common.lce.MvpLceView;
import com.sagiller.mvp.viewstate.RestorableViewState;
import com.sagiller.mvp.viewstate.lce.LceViewState;

/**
 * Created by sagiller on 16/4/13.
 */
public interface WebViewViewState<D, V extends MvpLceView<D>> extends LceViewState<D,V> {

    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;

    int state = STATE_SHOW_LOADING;
}

