package com.sagar.watchnext.utils;

import com.sagar.watchnext.ApplicationScope;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 07-Jul-18. at 22:23
 */
public class ImageUrlUtil {

    public static String getBackdropImageUrl(String backdropPath) {
        return "https://image.tmdb.org/t/p/w1280" + backdropPath;
    }

    public static String getPosterImageUrl(String posterPath) {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

}
