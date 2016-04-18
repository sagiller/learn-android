package com.sagiller.learn.func.web.webview;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sagiller.learn.R;
import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.func.base.BaseActivity;
import com.sagiller.learn.utils.BuildUtils;

import butterknife.Bind;

/**
 * Created by sagiller on 16/4/12.
 */
public class WebViewActivity extends BaseActivity implements OnWebViewTitleChangeListener {
    public static final String KEY_URL = Constants.INTENT_PARAM_PRE + "WebViewActivity.url";

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
            String url = getIntent().getStringExtra(KEY_URL);
            Bundle bundle = new Bundle();
            WebViewFragment fragment = new WebViewFragmentBuilder(url).build();
            fragment.setWebViewTitleChangeListener(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    @Override
    public void webViewTitleChange(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {

        WebViewFragment fragment = (WebViewFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null && fragment.canGoBack()) {
            fragment.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
