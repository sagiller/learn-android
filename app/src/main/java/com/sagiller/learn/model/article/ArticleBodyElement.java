package com.sagiller.learn.model.article;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelableNoThanks;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

/**
 * Created by sagiller on 16/4/8.
 */
@ParcelablePlease
public class ArticleBodyElement implements Parcelable{
    int id;
    transient String type;

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        ArticleBodyElementParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Parcelable.Creator<ArticleBodyElement> CREATOR = new Parcelable.Creator<ArticleBodyElement>() {
        public ArticleBodyElement createFromParcel(Parcel source) {
            ArticleBodyElement target = new ArticleBodyElement();
            ArticleBodyElementParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public ArticleBodyElement[] newArray(int size) {
            return new ArticleBodyElement[size];
        }
    };

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
