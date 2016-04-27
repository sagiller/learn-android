package com.sagiller.learn.func.web.webpage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sagiller.learn.R;
import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.func.base.BaseActivity;
import com.sagiller.learn.func.web.webview.WebViewFragment;
import com.sagiller.learn.utils.BuildUtils;

import butterknife.Bind;

/**
 * Created by sagiller on 16/4/12.
 */
public class WebpagesActivity extends BaseActivity{
    public static final String CATEGORY_ID = Constants.INTENT_PARAM_PRE + "WebpagesActivity.categoryId";
    public static final String CATEGORY_NAME = Constants.INTENT_PARAM_PRE + "WebpagesActivity.categoryName";

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
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
            int categoryId = getIntent().getIntExtra(CATEGORY_ID,-1);
            String categoryName = getIntent().getStringExtra(CATEGORY_NAME);
            toolbar.setTitle(categoryName);
            WebpagesFragment fragment = new WebpagesFragmentBuilder(categoryId, categoryName).build();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
