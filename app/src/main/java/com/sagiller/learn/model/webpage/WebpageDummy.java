package com.sagiller.learn.model.webpage;

import com.sagiller.learn.model.dummy.BaseDummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagiller on 16/4/8.
 */
public class WebpageDummy extends BaseDummy {

    public static final List<Webpage> getWebpages(int categoryId) {
        List<Webpage> items = new ArrayList<>();
        Webpage webpage = new Webpage();
        webpage.setName("Android");
        webpage.setDesc("Android develop");
        Webpage webpage1 = new Webpage();
        webpage1.setName("iOS");
        webpage1.setDesc("iOS develop");
        items.add(webpage);
        items.add(webpage1);
        return items;
    }
}
