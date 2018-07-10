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

    private int pxWidth;

    @Inject
    PixelDensityUtil(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        pxWidth = (displayMetrics.widthPixels);
    }

    public int getBackDropImageHeight() {
        return (int) ((float) pxWidth * 0.5625);
    }

}
