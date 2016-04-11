package com.sagiller.learn.entity.gson;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by sagiller on 16/4/8.
 */
public class Article {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    String desc;
    @Expose
    ArrayList<ArticleBodyElement> elements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<ArticleBodyElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<ArticleBodyElement> elements) {
        this.elements = elements;
    }
}
