package com.sagiller.learn.func.web.category;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.sagiller.learn.App;
import com.sagiller.learn.IntentStarter;
import com.sagiller.learn.R;
import com.sagiller.learn.func.base.view.ListAdapter;
import com.sagiller.learn.func.base.view.RefreshRecyclerFragment;
import com.sagiller.learn.model.webpage.WebpageCategory;
import com.sagiller.learn.utils.ToastUtils;
import com.sagiller.mvp.viewstate.lce.LceViewState;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpageCategoryFragment extends RefreshRecyclerFragment<List<WebpageCategory>,WebpageCategoryView,WebpageCategoryPresenter> implements WebpageCategoryView,WebpageCategoryAdapter.ItemClickedListener{

    @Arg int type; //0:normal ; 1:is module

    @Inject IntentStarter intentStarter;

    private WebpageCategoryComponent webpageCategoryComponent;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_webpage_category;
    }

    @Override
    public LceViewState createViewState() {
        return new DefaultWebpageCategoryViewState();
    }

    @Override
    public List<WebpageCategory> getData() {
        return null;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public WebpageCategoryPresenter createPresenter() {
        return webpageCategoryComponent.presenter();
    }


    @Override
    protected ListAdapter<List<WebpageCategory>> createAdapter() {
        return new WebpageCategoryAdapter(getActivity(), this);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getWebpageCategorys(type);

    }

    @Override protected void injectDependencies() {
        webpageCategoryComponent =
                DaggerWebpageCategoryComponent.builder().appComponent(App.getAppComponents()).build();
        webpageCategoryComponent.inject(this);
    }

    @Override
    public void onItemClicked(WebpageCategoryAdapterHolders.CategoryViewHolder vh, WebpageCategory category) {
        ToastUtils.show(this.getContext(),category.getName());
        intentStarter.showWebpages(this.getActivity(),category.getId());
    }
}
