package com.sagar.watchnext.screens.home;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 21:30
 */
public enum ListType {
    InTheaters(0),
    OnTv(1);

    private int index;

    ListType(int index) {
        this.index = index;
    }

    int getIndex() {
        return this.index;
    }
}
