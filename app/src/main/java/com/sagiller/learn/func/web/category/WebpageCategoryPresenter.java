package com.sagiller.learn.func.web.category;

import com.sagiller.learn.func.base.presenter.BaseRxLcePresenter;
import com.sagiller.learn.func.web.webview.WebViewView;
import com.sagiller.learn.model.article.ArticleProvider;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;
import com.sagiller.learn.model.webpage.WebpageCategory;
import com.sagiller.learn.model.webpage.WebpageCategoryProvider;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpageCategoryPresenter extends BaseRxLcePresenter<WebpageCategoryView, List<WebpageCategory>> {
    private EventBus eventBus;
    private WebpageCategoryProvider webpageCategoryProvider;
    @Inject
    public WebpageCategoryPresenter(EventBus eventBus, WebpageCategoryProvider webpageCategoryProvider) {
        this.eventBus = eventBus;
        this.webpageCategoryProvider = webpageCategoryProvider;
    }

    public void onEventMainThread(LoginSuccessfulEvent event) {
        if (isViewAttached()) {
            getView().loadData(false);
        }
    }

    @Override public void attachView(WebpageCategoryView view) {
        super.attachView(view);
        eventBus.register(this);
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        eventBus.unregister(this);
    }

    public void getWebpageCategorys(boolean pullToRefresh ,int type) {
        subscribe(webpageCategoryProvider.getcategorys(type), pullToRefresh);
    }
}
