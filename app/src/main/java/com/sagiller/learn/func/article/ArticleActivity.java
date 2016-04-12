package com.sagiller.learn.func.article;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sagiller.learn.R;
import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.func.base.BaseActivity;
import com.sagiller.learn.model.article.Article;
import com.sagiller.learn.utils.BuildUtils;

import butterknife.Bind;

/**
 * Created by sagiller on 16/4/11.
 */
public class ArticleActivity extends BaseActivity {
    public static final String KEY_ARTICLE = Constants.INTENT_PARAM_PRE + "ArticleActivity.article";

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        // Activity Transitions
        if (BuildUtils.isMinApi21()) {
            postponeEnterTransition();
        }
        toolbar.setNavigationIcon(BuildUtils.getBackArrowDrawable(this));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (BuildUtils.isMinApi21()) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });

        if (savedInstanceState == null) {
            Article article = getIntent().getParcelableExtra(KEY_ARTICLE);
            ArticleFragment fragment = new ArticleFragmentBuilder(article.getId(),article.getTitle()).build();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
