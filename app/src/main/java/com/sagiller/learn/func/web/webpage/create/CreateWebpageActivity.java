package com.sagiller.learn.func.web.webpage.create;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;


import com.sagiller.learn.App;
import com.sagiller.learn.IntentStarter;
import com.sagiller.learn.R;
import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.dagger.NavigationModule;
import com.sagiller.learn.func.base.view.BaseViewStateActivity;
import com.sagiller.learn.func.web.categorylayout.WebPageCategoryLayout;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.utils.BuildUtils;
import com.sagiller.mvp.viewstate.RestorableViewState;

import javax.inject.Inject;

import butterknife.Bind;


@TargetApi(21)
public class CreateWebpageActivity extends BaseViewStateActivity<CreateWebpageView, CreateWebpagePresenter>
        implements CreateWebpageView {

    public static final String CATEGORY_ID = Constants.INTENT_PARAM_PRE + "CreateWebpageActivity.categoryId";
    public static final String CATEGORY_NAME = Constants.INTENT_PARAM_PRE + "CreateWebpageActivity.categoryName";

    @Bind(R.id.loadingOverlay)
    View loadingOverlay;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.description)
    EditText description;
    @Bind(R.id.url)
    EditText url;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.category)
    WebPageCategoryLayout categoryLayout;

    @Inject
    IntentStarter intentStarter;

    private CreateWebpageComponent createComponent;
    private Webpage webpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_webpage);

        if (BuildUtils.isMinApi21()) {
            getWindow().getEnterTransition()
                    .excludeTarget(R.id.toolbar, true)
                    .excludeTarget(android.R.id.statusBarBackground, true)
                    .excludeTarget(android.R.id.navigationBarBackground, true);
        }

        toolbar.setNavigationIcon(getBackArrowDrawable());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildUtils.isMinApi21()) {
                    finish();
                    //TODO finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        int categoryId = getIntent().getIntExtra(CATEGORY_ID,-1);
        String categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        webpage = new Webpage();
        webpage.setCategoryId(categoryId);
        categoryLayout.setWebpage(webpage,categoryName);
        categoryLayout.setVisibility(View.VISIBLE);
        if (!isRestoringViewState()) {
            //TODO
        }
    }

    @TargetApi(21)
    private Drawable getBackArrowDrawable() {

        if (BuildUtils.isMinApi21()) {
            return getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha, getTheme());
        } else {
            return getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        }
    }

    @Override
    public RestorableViewState createViewState() {
        return new CreateWebpageViewState();
    }

    @Override
    public CreateWebpagePresenter createPresenter() {
        return createComponent.presenter();
    }

    @Override
    public void onNewViewStateInstance() {
        showForm();
    }

    @Override
    public CreateWebpageViewState getViewState() {
        return (CreateWebpageViewState) super.getViewState();
    }

    @Override
    public void showForm() {
        getViewState().setStateShowForm();
        loadingOverlay.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        getViewState().setStateShowLoading();
        loadingOverlay.setVisibility(View.VISIBLE);
        if (!restoringViewState) {
            loadingOverlay.setAlpha(0f);
            loadingOverlay.animate().alpha(1f).setDuration(200).start();
        }
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showAuthenticationRequired() {

    }

    @Override
    public void finishBecauseSuccessful() {

    }


    protected void injectDependencies() {
        createComponent = DaggerCreateWebpageComponent.builder()
                .appComponent(App.getAppComponents())
                .navigationModule(new NavigationModule())
                .build();

        createComponent.inject(this);
    }
}
