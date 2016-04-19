package com.sagiller.learn.func.web.webpage;

import com.sagiller.learn.func.base.presenter.BaseRxLcePresenter;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.model.webpage.WebpageProvider;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpagesPresenter extends BaseRxLcePresenter<WebpagesView, List<Webpage>> {
    private EventBus eventBus;
    private WebpageProvider webpageProvider;
    @Inject
    public WebpagesPresenter(EventBus eventBus, WebpageProvider webpageProvider) {
        this.eventBus = eventBus;
        this.webpageProvider = webpageProvider;
    }

    public void onEventMainThread(LoginSuccessfulEvent event) {
        if (isViewAttached()) {
            getView().loadData(false);
        }
    }

    @Override public void attachView(WebpagesView view) {
        super.attachView(view);
        eventBus.register(this);
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        eventBus.unregister(this);
    }

    public void getWebpages(int categoryId) {
        subscribe(webpageProvider.getWebpages(categoryId), false);
    }
}
