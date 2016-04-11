package com.sagiller.learn.entity.gson;

/**
 * Created by sagiller on 16/4/8.
 */
public class ArticleBodyElement {
    int id;
    private transient String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
