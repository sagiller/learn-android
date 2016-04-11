package com.sagiller.learn.func.base;

import android.app.Fragment;

import com.android.volley.toolbox.Volley;

/**
 * Created by sagiller on 16/4/7.
 */
public class BaseFragment extends Fragment {
    String mRequestTag;
    public String getRequestTag()
    {
        return getClass().getName();
    }

    public String getUniqueRequestTag()
    {
        return getClass().getName() + System.currentTimeMillis();
    }
}
