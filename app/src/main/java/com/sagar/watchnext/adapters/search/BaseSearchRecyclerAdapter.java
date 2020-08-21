package com.sagar.watchnext.adapters.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.R;
import com.squareup.picasso.Picasso;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 22:46
 */
public abstract class BaseSearchRecyclerAdapter extends RecyclerView.Adapter<SearchCardViewHolder> {

    private Picasso picasso;

    protected BaseSearchRecyclerAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public SearchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_vertical_recycler_item, parent, false);
        return new SearchCardViewHolder(view, picasso);
    }
}
