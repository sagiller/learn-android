package com.sagiller.learn.func.web.categorylayout;

import com.sagiller.learn.func.base.presenter.BaseRxLcePresenter;
import com.sagiller.learn.func.web.category.WebpageCategoryView;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.model.webpage.WebpageCategory;
import com.sagiller.learn.model.webpage.WebpageCategoryProvider;
import com.sagiller.mvp.common.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpageCategoryLayoutPresenter extends BaseRxLcePresenter<WebpageCategoryLayoutView, List<WebpageCategory>> implements MvpPresenter<WebpageCategoryLayoutView>{
    private EventBus eventBus;
    private WebpageCategoryProvider webpageCategoryProvider;
    @Inject
    public WebpageCategoryLayoutPresenter(EventBus eventBus, WebpageCategoryProvider webpageCategoryProvider) {
        this.eventBus = eventBus;
        this.webpageCategoryProvider = webpageCategoryProvider;
    }

    public void onEventMainThread(LoginSuccessfulEvent event) {
        if (isViewAttached()) {
            getView().loadData(false);
        }
    }

    @Override public void attachView(WebpageCategoryLayoutView view) {
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

    public void setCategory(Webpage webpage, int categoryId, String categoryName) {
        if (isViewAttached()) {
            getView().changeCategory(webpage, categoryId,categoryName);
        }
    }
}
