package com.sagiller.learn.model.webpage;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;
import com.sagiller.learn.model.image.Image;

import java.util.Date;

/**
 * Created by sagiller on 16/4/18.
 */
@ParcelablePlease public class Webpage implements Parcelable {
    int id;
    String name;
    String desc;
    int order;
    String url;
    int status;
    int categoryId;
    int type;
    Image icon;
    Date createdAt;
    Date updatedAt;

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        WebpageParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Webpage> CREATOR = new Creator<Webpage>() {
        public Webpage createFromParcel(Parcel source) {
            Webpage target = new Webpage();
            WebpageParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Webpage[] newArray(int size) {
            return new Webpage[size];
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
