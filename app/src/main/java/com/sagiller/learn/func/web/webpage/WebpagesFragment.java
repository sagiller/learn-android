package com.sagiller.learn.func.web.webpage;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.sagiller.learn.App;
import com.sagiller.learn.IntentStarter;
import com.sagiller.learn.R;
import com.sagiller.learn.func.base.view.ListAdapter;
import com.sagiller.learn.func.base.view.RefreshRecyclerFragment;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.utils.ToastUtils;
import com.sagiller.mvp.viewstate.lce.LceViewState;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by sagiller on 16/4/18.
 */
public class WebpagesFragment extends RefreshRecyclerFragment<List<Webpage>,WebpagesView,WebpagesPresenter> implements WebpagesView,WebpagesAdapter.ItemClickedListener{

    @Arg int categoryId;

    @Inject IntentStarter intentStarter;

    private WebpagesComponent webpagesComponent;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_webpages;
    }

    @Override
    public LceViewState createViewState() {
        return new DefaultWebpagesViewState();
    }

    @Override
    public List<Webpage> getData() {
        return null;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public WebpagesPresenter createPresenter() {
        return webpagesComponent.presenter();
    }


    @Override
    protected ListAdapter<List<Webpage>> createAdapter() {
        return new WebpagesAdapter(getActivity(), this);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getWebpages(pullToRefresh, categoryId);

    }

    @Override protected void injectDependencies() {
        webpagesComponent =
                DaggerWebpagesComponent.builder().appComponent(App.getAppComponents()).build();
        webpagesComponent.inject(this);
    }

    @Override
    public void onItemClicked(WebpagesAdapterHolders.WebpageViewHolder vh, Webpage webpage) {
        intentStarter.showWeb(this.getContext(), webpage.getUrl(),null);
    }
}
