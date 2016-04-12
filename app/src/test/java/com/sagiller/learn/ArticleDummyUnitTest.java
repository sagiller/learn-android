package com.sagiller.learn;

import com.sagiller.learn.model.article.ArticleDummy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ArticleDummyUnitTest {
    @Test
    public void getArticleById_isCorrect() throws Exception {
        assertEquals(1, ArticleDummy.getArticleById(1).getElements().get(0).getId());
    }
}