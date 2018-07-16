package com.sagar.watchnext.activities.main.tv;

import com.sagar.watchnext.ContentType;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 23:25
 */
public enum ListType {
    AiringToday(ContentType.TV_SHOW),
    OnTheAir(ContentType.TV_SHOW),
    Popular(ContentType.TV_SHOW),
    TopRated(ContentType.TV_SHOW);

    private ContentType contentType;

    ListType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }
}
