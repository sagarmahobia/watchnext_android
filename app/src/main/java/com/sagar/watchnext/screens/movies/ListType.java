package com.sagar.watchnext.screens.movies;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 22:30
 */
public enum ListType {
    InTheaters(0),
    Upcoming(1),
    Popular(2),
    TopRated(3);

    private int index;

    ListType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
