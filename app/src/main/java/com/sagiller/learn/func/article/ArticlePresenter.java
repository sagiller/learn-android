package com.sagiller.learn.func.article;

import com.sagiller.learn.func.base.presenter.BaseRxLcePresenter;
import com.sagiller.learn.model.article.Article;
import com.sagiller.learn.model.article.ArticleProvider;
import com.sagiller.learn.model.event.LoginSuccessfulEvent;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by sagiller on 16/4/11.
 */
public class ArticlePresenter extends BaseRxLcePresenter<ArticleView, Article> {
    private EventBus eventBus;
    private ArticleProvider articleProvider;

    @Inject
    public ArticlePresenter(EventBus eventBus, ArticleProvider articleProvider) {
        this.eventBus = eventBus;
        this.articleProvider = articleProvider;
    }

    public void onEventMainThread(LoginSuccessfulEvent event) {
        if (isViewAttached()) {
            getView().loadData(false);
        }
    }

    @Override public void attachView(ArticleView view) {
        super.attachView(view);
        eventBus.register(this);
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        eventBus.unregister(this);
    }

    public void loadArticle(int id) {
        subscribe(articleProvider.getArticle(id), false);
    }
}
