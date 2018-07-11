package com.sagar.watchnext.activities.main.home;


import com.sagar.watchnext.ContentType;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 21:30
 */
public enum ListType {
    InTheaters(ContentType.MOVIE),
    OnTv(ContentType.TV_SHOW);

    private ContentType contentType;

    ListType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }
}
