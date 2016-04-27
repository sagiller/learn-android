package com.sagiller.learn.func.web.categorylayout;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sagiller.learn.App;
import com.sagiller.learn.R;
import com.sagiller.learn.func.web.category.DefaultWebpageCategoryViewState;
import com.sagiller.learn.func.web.category.WebpageCategoryViewState;
import com.sagiller.learn.model.webpage.Webpage;
import com.sagiller.learn.model.webpage.WebpageCategory;
import com.sagiller.learn.utils.DimensUtils;
import com.sagiller.mvp.viewstate.ViewState;
import com.sagiller.mvp.viewstate.layout.MvpViewStateLinearLayout;

import butterknife.ButterKnife;

import java.util.List;

import butterknife.Bind;
import icepick.Icepick;
import icepick.Icicle;

public class WebPageCategoryLayout extends MvpViewStateLinearLayout<WebpageCategoryLayoutView, WebpageCategoryLayoutPresenter>
        implements WebpageCategoryLayoutView {

    @Bind(R.id.categoryTextView)
    TextView categoryView;
    @Bind(R.id.categoryLoadingView)
    View loadingView;


    ListPopupWindow popUpWindow;
    WebpageCategoryLayoutAdapter adapter;
    Webpage webpage;

    public WebPageCategoryLayout(Context context) {
        super(context);
        init();
    }

    public WebPageCategoryLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WebPageCategoryLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WebPageCategoryLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    private void init() {

        View.inflate(getContext(), R.layout.view_webpage_category_layout, this);

        LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        this.setLayoutTransition(transition);

        adapter = new WebpageCategoryLayoutAdapter(getContext());
        popUpWindow = new ListPopupWindow(getContext());
        popUpWindow.setAnchorView(this);
        popUpWindow.setAdapter(adapter);
        popUpWindow.setWidth(DimensUtils.dpToPx(getContext(), 140));
        popUpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                showWebpageCategory();
            }
        });
        popUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WebpageCategory category = (WebpageCategory) adapter.getItem(position);
                if (!(category.getId()==webpage.getCategoryId())) {
                    presenter.setCategory(webpage, category.getId(), category.getName());
                    popUpWindow.dismiss();
                }
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(false);
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        popUpWindow.setOnDismissListener(null);

        if (popUpWindow.isShowing()) {
            popUpWindow.dismiss();
        }
    }

    @Override
    public void setWebpage(Webpage webpage, String categoryName) {
        this.webpage = webpage;
        categoryView.setText(categoryName);
    }

    @Override
    public void changeCategory(Webpage webpage, int categoryId, String categoryName) {
        if (this.webpage.getId() == webpage.getId()) {
            this.webpage.setCategoryId(categoryId);
            categoryView.setText(categoryName);
        }
    }

    ;

    @Override
    public WebpageCategoryLayoutPresenter createPresenter() {
        return DaggerWebpageCategoryLayoutComponent.builder()
                .appComponent(App.getAppComponents())
                .build()
                .presenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        loadingView.setVisibility(View.VISIBLE);
        this.setClickable(false);
        getViewState().setStateShowLoading(false);
    }

    @Override
    public void showContent() {
        loadingView.setVisibility(View.GONE);
        this.setClickable(true);
        post(new Runnable() {
            @Override
            public void run() { // Need for rotation changes
                popUpWindow.show();
            }
        });
        getViewState().setStateShowContent(adapter.getItems());
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        if (!isRestoringViewState()) {
            //Toast.makeText(getContext(), R.string.error_loading_labels, Toast.LENGTH_SHORT).show();
        }
        showWebpageCategory();
    }

    @Override
    public void setData(List<WebpageCategory> data) {
        adapter.setItems(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getWebpageCategorys(pullToRefresh, 1);
    }

    @Override
    public void showWebpageCategory() {
        if (popUpWindow.isShowing()) {
            popUpWindow.dismiss();
        }

        loadingView.setVisibility(View.GONE);
        this.setClickable(true);
        getViewState().setStateShowingCategory();
    }

    @Override
    public void showChangeWebpageCategoryFailed(Webpage webpage, Throwable t) {

    }

    @Override
    public ViewState createViewState() {
        return new DefaultWebpageCategoryViewState();
    }

    @Override
    public WebpageCategoryViewState getViewState() {
        return (WebpageCategoryViewState) super.getViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showWebpageCategory();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return Icepick.saveInstanceState(this, super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    }

}
