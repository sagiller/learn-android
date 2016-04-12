package com.sagiller.learn.model.article;


import rx.Observable;

/**
 * Created by sagiller on 16/4/11.
 */
public interface ArticleManager {
    Observable<Article> getArticleById(int id);
}
