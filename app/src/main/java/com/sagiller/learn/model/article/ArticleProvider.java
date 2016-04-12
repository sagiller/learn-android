package com.sagiller.learn.model.article;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sagiller on 16/4/11.
 */
public class ArticleProvider {

    public Observable<Article> getArticle(final int id) {
        return Observable.create(new Observable.OnSubscribe<Article>() {
            @Override
            public void call(Subscriber<? super Article> subscriber) {
                subscriber.onNext(ArticleDummy.getArticleById(id));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());

    }
}
