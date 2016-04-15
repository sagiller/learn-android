package com.sagiller.learn.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;



/**
 * Utilities for displaying toast notifications
 */
public class ToastUtils {
    public static void show(final Context context, final String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


}
