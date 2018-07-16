package com.sagar.watchnext.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.watchnext.R;
import com.squareup.picasso.Picasso;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 22:46
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private Picasso picasso;

    protected BaseRecyclerAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_recycler_item, parent, false);

        return new CardViewHolder(view, picasso);
    }


}
