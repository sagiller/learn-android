package com.sagiller.learn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.sagiller.learn.func.article.ArticleActivity;
import com.sagiller.learn.func.login.LoginActivity;
import com.sagiller.learn.func.web.webpage.WebpagesActivity;
import com.sagiller.learn.func.web.webpage.create.CreateWebpageActivity;
import com.sagiller.learn.func.web.webview.WebViewActivity;
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

    public void showWebpages(Context context, int categoryId, String categoryName) {
        Intent intent = new Intent(context, WebpagesActivity.class);
        intent.putExtra(WebpagesActivity.CATEGORY_ID,categoryId);
        intent.putExtra(WebpagesActivity.CATEGORY_NAME,categoryName);
        context.startActivity(intent);
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

    public void shareTextViaIntent(Context context, String extraText) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, extraText);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "Share to.."));
    }

    public void shareImageViaIntent(Context context, Uri uri) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(sendIntent, "Share images to.."));
    }


    public void showCreateWebpage(Context context, int categoryId,String categoryName, Bundle activityTransitionBundle) {
        Intent i = new Intent(context, CreateWebpageActivity.class);
        i.putExtra(CreateWebpageActivity.CATEGORY_ID, categoryId);
        i.putExtra(CreateWebpageActivity.CATEGORY_NAME, categoryName);
        context.startActivity(i, activityTransitionBundle);
    }
}
