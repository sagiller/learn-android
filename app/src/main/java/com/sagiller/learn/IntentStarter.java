package com.sagiller.learn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sagiller.learn.func.article.ArticleActivity;
import com.sagiller.learn.func.login.LoginActivity;
import com.sagiller.learn.func.webview.WebViewActivity;
import com.sagiller.learn.model.article.Article;


/**
 * A simple helper class that helps to create and launch Intents. It checks if we our device is a
 * phone or a tablet app.
 *
 * @author sagiller@163.com
 */
// TODO make it injectable with dagger
public class IntentStarter {

  private boolean isTablet(Context context) {
    return context.getResources().getBoolean(R.bool.tablet);
  }

  public void showAuthentication(Context context) {
    context.startActivity(new Intent(context, LoginActivity.class));
  }

  public void showArticle(Context context, Article article, Bundle activityTransitionBundle) {
    Intent i = null;
    if (isTablet(context)) {
    } else {
      i = new Intent(context, ArticleActivity.class);
      i.putExtra(ArticleActivity.KEY_ARTICLE, article);
    }

    context.startActivity(i);
  }

  public void showWeb(Context context, String url, Bundle activityTransitionBundle) {
    Intent i = null;
    if (isTablet(context)) {
    } else {
      i = new Intent(context, WebViewActivity.class);
      i.putExtra(WebViewActivity.KEY_URL, url);
    }

    context.startActivity(i);
  }

}
