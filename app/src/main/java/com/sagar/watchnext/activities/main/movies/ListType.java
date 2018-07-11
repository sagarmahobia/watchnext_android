package com.sagar.watchnext.activities.main.movies;

import com.sagar.watchnext.ContentType;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 22:30
 */
public enum ListType {
    InTheaters(ContentType.MOVIE),
    Upcoming(ContentType.MOVIE),
    Popular(ContentType.MOVIE),
    TopRated(ContentType.MOVIE);

    private ContentType contentType;

    ListType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }
}
