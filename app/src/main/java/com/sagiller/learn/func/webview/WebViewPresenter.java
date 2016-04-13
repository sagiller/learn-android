package com.sagiller.learn.func.webview;

import com.sagiller.learn.func.base.presenter.BaseRxLcePresenter;
import com.sagiller.learn.model.article.ArticleProvider;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

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
