package com.sagiller.learn.func.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.Bind;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.sagiller.learn.App;
import com.sagiller.learn.R;
import com.sagiller.learn.func.base.view.RefreshFragment;


/**
 * a fragment to view any webpage.
 * @author sagiller@163.com
 * @since 0.1.0
 */
public class WebViewFragment extends RefreshFragment<String,WebViewView,WebViewPresenter> implements WebViewView{

    @Arg String url;

    private OnWebViewTitleChangeListener webViewTitleChangeListener;

    @Bind(R.id.webview) WebView webview;

    @Bind(R.id.progress) ProgressBar progressbar;

    private String currentUrl;

    private WebViewComponent webViewComponent;

    @Override protected int getLayoutRes() {
        return R.layout.fragment_webview;
    }

    @Override
    public WebViewPresenter createPresenter() {
        return webViewComponent.presenter();
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview.getSettings().setJavaScriptEnabled(true);
        setWebChromeClient();
        setWebViewClient();
        currentUrl = url;
    }



    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    private void setWebChromeClient() {
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)) {
                    updateActivityTitle(title);
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressbar == null) return;
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    if (progressbar.getVisibility() == View.GONE)
                        progressbar.setVisibility(View.VISIBLE);
                    progressbar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
    private void setWebViewClient() {
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                currentUrl = url;
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                currentUrl = view.getUrl();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }
    public void setWebViewTitleChangeListener(OnWebViewTitleChangeListener listener) {
        webViewTitleChangeListener = listener;
    }

    public void updateActivityTitle(String title) {
        if (webViewTitleChangeListener == null) return;
        webViewTitleChangeListener.webViewTitleChange(title);
    }

    public void onPause() {
        super.onPause();
        webview.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webview = null;
        progressbar = null;
    }

    public boolean canGoBack() {
        return webview.canGoBack();
    }

    public void goBack() {
        webview.goBack();
    }


    @Override
    public WebViewViewState<String, WebViewView> createViewState() {
        return new DefaultWebViewViewState();
    }

    @Override
    public String getData() {
        return currentUrl;
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        webview.loadUrl(currentUrl);
        getPresenter().loadCurrentUrl();
    }

    @Override protected void injectDependencies() {
        webViewComponent =
                DaggerWebViewComponent.builder().appComponent(App.getAppComponents()).build();
        webViewComponent.inject(this);
    }
}
