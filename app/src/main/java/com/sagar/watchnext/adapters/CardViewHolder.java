package com.sagar.watchnext.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagar.watchnext.R;
import com.squareup.picasso.Picasso;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 23:12
 */
public class CardViewHolder extends RecyclerView.ViewHolder implements Card {

    private View view;
    private Picasso picasso;

    CardViewHolder(@NonNull View itemView, Picasso picasso) {
        super(itemView);
        this.view = itemView;
        this.picasso = picasso;
    }

    @Override
    public void setTitle(String title) {
        ((TextView) view.findViewById(R.id.title)).setText(title);
    }

    @Override
    public void setImage(String url) {
        url = "https://image.tmdb.org/t/p/w500/" + url;
        picasso.load(url).into((ImageView) view.findViewById(R.id.imageBackdrop));
    }
}
