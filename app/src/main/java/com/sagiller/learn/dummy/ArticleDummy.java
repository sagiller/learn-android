package com.sagiller.learn.dummy;

import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.json.gson.JsonUtil;
import com.sagiller.learn.entity.gson.Article;
import com.sagiller.learn.entity.gson.ArticleBodyElement;
import com.sagiller.learn.entity.gson.ArticleBodyElementImage;
import com.sagiller.learn.entity.gson.ArticleBodyElementParagraph;

import java.util.ArrayList;

/**
 * Created by sagiller on 16/4/8.
 */
public class ArticleDummy extends BaseDummy{

    public static final Article getArticleById(int id) {
        Article origionArticle = new Article();
        origionArticle.setId(1);
        origionArticle.setDesc("我是文章描述");
        origionArticle.setTitle("Activity全解析");
        ArrayList<ArticleBodyElement> elements = new ArrayList<>();
        ArticleBodyElementParagraph paragraph = new ArticleBodyElementParagraph();
        paragraph.setId(1);
        paragraph.setType(Constants.ARTICLE_BODY_ELEMENT_TYPE_PARAGRAPH);
        paragraph.setContent("我是正文，我是正文");
        ArticleBodyElementImage image = new ArticleBodyElementImage();
        image.setId(2);
        image.setType(Constants.ARTICLE_BODY_ELEMENT_TYPE_IMAGE);
        image.setUrl("https://futurestud.io/images/futurestudio-logo.png");
        elements.add(paragraph);
        elements.add(image);
        origionArticle.setElements(elements);
        String dummy = JsonUtil.getJsonFromJavaObject(origionArticle);
        Article article = JsonUtil.getObjectFromJson(dummy, Article.class);
        return article;
    }
}
