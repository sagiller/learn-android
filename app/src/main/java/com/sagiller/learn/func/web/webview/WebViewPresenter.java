package com.sagiller.learn.func.web.webview;

import com.sagiller.learn.func.base.presenter.BaseRxLcePresenter;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;

import javax.inject.Inject;

/**
 * Created by sagiller on 16/4/11.
 */
public class WebViewPresenter extends BaseRxLcePresenter<WebViewView, String> {

    @Inject
    public WebViewPresenter() {
    }

    public void onEventMainThread(LoginSuccessfulEvent event) {
        if (isViewAttached()) {
            getView().loadData(false);
        }
    }

    @Override public void attachView(WebViewView view) {
        super.attachView(view);
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    public void loadCurrentUrl() {
        this.onCompleted();
    }
}
