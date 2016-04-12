package com.sagiller.learn.model.article;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.ArrayList;

/**
 * Created by sagiller on 16/4/8.
 */
@ParcelablePlease public class Article implements Parcelable {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    String desc;
    @Expose
    ArrayList<ArticleBodyElement> elements;

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        ArticleParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            Article target = new Article();
            ArticleParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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
