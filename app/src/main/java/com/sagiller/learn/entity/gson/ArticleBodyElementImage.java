package com.sagiller.learn.entity.gson;

import com.google.gson.annotations.Expose;

/**
 * Created by sagiller on 16/4/8.
 */
public class ArticleBodyElementImage extends ArticleBodyElement{
    @Expose
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
