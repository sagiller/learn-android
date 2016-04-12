package com.sagiller.learn.json.gson;

import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.model.article.ArticleBodyElement;
import com.sagiller.learn.model.article.ArticleBodyElementImage;
import com.sagiller.learn.model.article.ArticleBodyElementParagraph;

/**
 * Created by sagiller on 16/4/8.
 */
public class TypeFactoryUtil {

    public static RuntimeTypeAdapterFactory getArticleBodyElementTypeFactory() {
        final RuntimeTypeAdapterFactory<ArticleBodyElement> typeFactory = RuntimeTypeAdapterFactory
                .of(ArticleBodyElement.class, "type")
                .registerSubtype(ArticleBodyElementParagraph.class, Constants.ARTICLE_BODY_ELEMENT_TYPE_PARAGRAPH)
                .registerSubtype(ArticleBodyElementImage.class, Constants.ARTICLE_BODY_ELEMENT_TYPE_IMAGE);
        return typeFactory;
    }

}
