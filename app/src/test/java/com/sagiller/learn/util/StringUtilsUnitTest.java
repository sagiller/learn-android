package com.sagiller.learn.util;

import com.sagiller.learn.utils.ValidateUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class StringUtilsUnitTest {
    @Test
    public void isUrl_isCorrect() throws Exception {
        assertEquals(true, ValidateUtils.isUrl("http://www.google.com/aaaaa/1.html"));
        assertEquals(true, ValidateUtils.isUrl("http://google.com/aaaaa/1.html"));
        assertEquals(false, ValidateUtils.isUrl("http://www.googlecom/aaaaa/1.html"));
        assertEquals(false, ValidateUtils.isUrl("www.google.ucom/aaaaa/1.html"));
    }
}