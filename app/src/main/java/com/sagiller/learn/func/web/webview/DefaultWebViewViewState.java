package com.sagiller.learn.func.web.webview;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.sagiller.mvp.common.lce.MvpLceView;
import com.sagiller.mvp.viewstate.RestorableViewState;
import com.sagiller.mvp.viewstate.lce.ParcelableLceViewState;

/**
 * Created by sagiller on 16/4/13.
 */
public class DefaultWebViewViewState<D,V extends MvpLceView<D>> implements WebViewViewState<D,V>,ParcelableLceViewState<D, V> {

    @Override
    public void apply(V view, boolean retained) {

    }

    @Override
    public void setStateShowContent(D loadedData) {

    }

    @Override
    public void setStateShowError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setStateShowLoading(boolean pullToRefresh) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public void saveInstanceState(@NonNull Bundle out) {

    }

    @Override
    public RestorableViewState<V> restoreInstanceState(Bundle in) {
        return null;
    }
}
