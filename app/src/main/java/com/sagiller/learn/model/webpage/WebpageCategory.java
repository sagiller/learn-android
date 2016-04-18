package com.sagiller.learn.model.webpage;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;
import com.sagiller.learn.model.webpage.WebpageCategoryParcelablePlease;

import java.util.Date;

/**
 * Created by sagiller on 16/4/18.
 */
@ParcelablePlease public class WebpageCategory implements Parcelable {
    int id;
    String name;
    String desc;
    int order;
    int type;
    Date createdAt;
    Date updatedAt;

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        WebpageCategoryParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<WebpageCategory> CREATOR = new Creator<WebpageCategory>() {
        public WebpageCategory createFromParcel(Parcel source) {
            WebpageCategory target = new WebpageCategory();
            WebpageCategoryParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public WebpageCategory[] newArray(int size) {
            return new WebpageCategory[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
