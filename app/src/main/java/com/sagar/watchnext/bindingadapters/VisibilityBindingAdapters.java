package com.sagar.watchnext.bindingadapters;


import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class VisibilityBindingAdapters {


    @BindingAdapter({"bind:refreshing"})
    public static void setSwipeLayoutRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing) {
        refreshLayout.setRefreshing(refreshing);
    }

         @BindingAdapter("bind:goneVisibility")
    public static void goneVisibility(View view, boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("bind:inVisibility")
    public static void inVisibility(View view, boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

}
