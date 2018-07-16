package com.sagar.watchnext.utils;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 23:33
 */
public class GenreUtil {

    public static String getMovieGenreById(int id) {
        if (id == 28) {
            return "Action";
        } else if (id == 12) {
            return "Adventure";
        } else if (id == 16) {
            return "Animation";
        } else if (id == 35) {
            return "Comedy";
        } else if (id == 80) {
            return "Crime";
        } else if (id == 99) {
            return "Documentary";
        } else if (id == 18) {
            return "Drama";
        } else if (id == 10751) {
            return "Family";
        } else if (id == 14) {
            return "Fantasy";
        } else if (id == 36) {
            return "History";
        } else if (id == 27) {
            return "Horror";
        } else if (id == 10402) {
            return "Music";
        } else if (id == 9648) {
            return "Mystery";
        } else if (id == 10749) {
            return "Romance";
        } else if (id == 878) {
            return "Science Fiction";
        } else if (id == 10770) {
            return "TV Movie";
        } else if (id == 53) {
            return "Thriller";
        } else if (id == 10752) {
            return "War";
        } else if (id == 37) {
            return "Western";
        } else {
            return "N/A";
        }
    }

    public static String getTvGenreById(int id) {

        if (id == 10759) {
            return "Action & Adventure";
        } else if (id == 16) {
            return "Animation";
        } else if (id == 35) {
            return "Comedy";
        } else if (id == 80) {
            return "Crime";
        } else if (id == 99) {
            return "Documentary";
        } else if (id == 18) {
            return "Drama";
        } else if (id == 10751) {
            return "Family";
        } else if (id == 10762) {
            return "Kids";
        } else if (id == 9648) {
            return "Mystery";
        } else if (id == 10763) {
            return "News";
        } else if (id == 10764) {
            return "Reality";
        } else if (id == 10765) {
            return "Sci-Fi & Fantasy";
        } else if (id == 10766) {
            return "Soap";
        } else if (id == 10767) {
            return "Talk";
        } else if (id == 10768) {
            return "War & Politics";
        } else if (id == 37) {
            return "Western";
        }
        return "N/A";
    }
}
