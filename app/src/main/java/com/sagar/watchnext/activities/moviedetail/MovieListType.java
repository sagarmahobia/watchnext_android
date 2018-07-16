package com.sagar.watchnext.activities.moviedetail;

/**
 * Created by SAGAR MAHOBIA on 07-Jul-18. at 01:43
 */
public enum MovieListType {
    Similar(0),
    Recommended(1);
    private int index;

    MovieListType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
