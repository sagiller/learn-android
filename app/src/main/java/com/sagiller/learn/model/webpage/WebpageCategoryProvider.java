package com.sagiller.learn.model.webpage;

import com.sagiller.learn.model.article.Article;
import com.sagiller.learn.model.article.ArticleDummy;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpageCategoryProvider {

    public Observable<List<WebpageCategory>> getcategorys(final int type) {
        return Observable.create(new Observable.OnSubscribe<List<WebpageCategory>>() {
            @Override
            public void call(Subscriber<? super List<WebpageCategory>> subscriber) {
                subscriber.onNext(WebpageCategoryDummy.getCategorys(type));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());

    }
}
