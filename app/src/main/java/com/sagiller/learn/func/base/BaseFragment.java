package com.sagiller.learn.func.base;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import icepick.Icepick;

/**
 * Base Fragment for this app that uses Butterknife, Icepick and dependency injection
 *
 * @author Hannes Dorfmann
 */
public abstract class BaseFragment extends Fragment {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    @LayoutRes protected abstract int getLayoutRes();

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * Inject the dependencies
     */
    protected void injectDependencies() {

    }

}
