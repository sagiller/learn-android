package com.sagiller.learn.func.web.categorylayout;


import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.model.webpage.WebpageCategory;
import com.sagiller.mvp.common.lce.MvpLceView;

import java.util.List;


public interface WebpageCategoryLayoutView extends MvpLceView<List<WebpageCategory>> {

  public void showWebpageCategory();

  public void setWebpage(Webpage webpage, String categoryName);

  public void changeCategory(Webpage webpage, int categoryId, String categoryName);

  public void showChangeWebpageCategoryFailed(Webpage webpage, Throwable t);

}
