package com.sagar.watchnext.activities.main.tv;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 23:25
 */
public enum ListType {
    AiringToday(0),
    OnTheAir(1),
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
