package com.sagiller.learn.func.article;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.sagiller.learn.App;
import com.sagiller.learn.IntentStarter;
import com.sagiller.learn.R;
import com.sagiller.learn.func.base.view.BaseLceFragment;
import com.sagiller.learn.model.article.Article;
import com.sagiller.mvp.common.MvpPresenter;
import com.sagiller.mvp.viewstate.lce.LceViewState;
import com.sagiller.mvp.viewstate.lce.data.ParcelableDataLceViewState;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by sagiller on 16/4/11.
 */
public class ArticleFragment extends BaseLceFragment<TextView, Article, ArticleView, ArticlePresenter>
        implements ArticleView, View.OnClickListener {
    @Arg int articleId;
    @Arg String title;

    @Inject IntentStarter intentStarter;

    @Bind(R.id.search) FloatingActionButton searchView;
    @Bind(R.id.scrollView) ObservableScrollView scrollView;
    @Bind(R.id.title) TextView titleView;
    private Article article;
    private ArticleComponent articleComponent;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_article;
    }

    @Override
    public LceViewState<Article, ArticleView> createViewState() {
        return new ParcelableDataLceViewState<>();
    }

    @TargetApi(21) @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView.attachToScrollView(scrollView);

        titleView.setText(title);

        // Shared element animation
        if (Build.VERSION.SDK_INT >= 21 && !getResources().getBoolean(R.bool.tablet)) {

            initTransitions();

            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    getActivity().startPostponedEnterTransition();
                    return true;
                }
            });
        }
    }

    @TargetApi(21) private void initTransitions() {

//        Window window = getActivity().getWindow();
//        window.setEnterTransition(
//                new ExplodeFadeEnterTransition(senderNameView, senderMailView, separatorLine));
//        window.setExitTransition(new ExcludedExplodeTransition());
//        window.setReenterTransition(new ExcludedExplodeTransition());
//        window.setReturnTransition(new ExcludedExplodeTransition());
//
//        TransitionSet textSizeSet = new TransitionSet();
//        textSizeSet.addTransition(
//                TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.move));
//        TextSizeTransition textSizeTransition = new TextSizeTransition();
//        textSizeTransition.addTarget(R.id.subject);
//        textSizeTransition.addTarget(getString(R.string.shared_mail_subject));
//
//        textSizeSet.addTransition(textSizeTransition);
//        textSizeSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
//
//        window.setSharedElementEnterTransition(textSizeSet);
//        getActivity().setEnterSharedElementCallback(
//                new TextSizeEnterSharedElementCallback(getActivity()));
    }

    @Override
    public Article getData() {
        return article;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public ArticlePresenter createPresenter() {
        return articleComponent.presenter();
    }

    @Override
    public void setData(Article data) {
        this.article = data;
        titleView.setText(data.getTitle());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadArticle(1);
    }

    @Override
    public void onClick(View v) {

    }

    @Override protected void injectDependencies() {
        articleComponent =
                DaggerArticleComponent.builder().appComponent(App.getAppComponents()).build();
        articleComponent.inject(this);
    }
}
