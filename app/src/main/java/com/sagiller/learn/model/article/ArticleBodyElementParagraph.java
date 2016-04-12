package com.sagiller.learn.model.article;

import com.google.gson.annotations.Expose;

/**
 * Created by sagiller on 16/4/8.
 */
public class ArticleBodyElementParagraph extends ArticleBodyElement{
    @Expose
    String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
