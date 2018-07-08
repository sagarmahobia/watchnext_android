package com.sagar.watchnext.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.sagar.watchnext.ApplicationScope;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 07-Jul-18. at 12:33
 */
@ApplicationScope
public class PixelDensityUtil {

    private Context context;
    private int pxWidth;
    private int pxHeight;
    private float density;


    @Inject
    PixelDensityUtil(Context context) {
        this.context = context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        pxWidth = (displayMetrics.widthPixels);
        pxHeight = (displayMetrics.heightPixels);
    }

    public int getBackDropImageHeight() {
        return (int) ((float) pxWidth * 0.5625);
    }

}
