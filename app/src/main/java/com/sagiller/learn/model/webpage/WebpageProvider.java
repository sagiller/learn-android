package com.sagiller.learn.model.webpage;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpageProvider {

    public Observable<List<Webpage>> getWebpages(final int type) {
        return Observable.create(new Observable.OnSubscribe<List<Webpage>>() {
            @Override
            public void call(Subscriber<? super List<Webpage>> subscriber) {
                subscriber.onNext(WebpageDummy.getWebpages(type));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());

    }
}
