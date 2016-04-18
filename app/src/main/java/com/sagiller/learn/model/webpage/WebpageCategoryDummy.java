package com.sagiller.learn.model.webpage;

import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.json.gson.JsonUtil;
import com.sagiller.learn.model.article.Article;
import com.sagiller.learn.model.article.ArticleBodyElement;
import com.sagiller.learn.model.article.ArticleBodyElementImage;
import com.sagiller.learn.model.article.ArticleBodyElementParagraph;
import com.sagiller.learn.model.dummy.BaseDummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagiller on 16/4/8.
 */
public class WebpageCategoryDummy extends BaseDummy {

    public static final List<WebpageCategory> getCategorys(int type) {
        List<WebpageCategory> categories = new ArrayList<>();
        WebpageCategory webpageCategory = new WebpageCategory();
        webpageCategory.setName("Android");
        webpageCategory.setDesc("Android develop");
        WebpageCategory webpageCategory1 = new WebpageCategory();
        webpageCategory1.setName("iOS");
        webpageCategory1.setDesc("iOS develop");
        categories.add(webpageCategory);
        categories.add(webpageCategory1);
        return categories;
    }
}
