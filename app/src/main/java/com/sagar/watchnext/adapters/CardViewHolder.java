package com.sagar.watchnext.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.utils.ImageUrlUtil;
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
    public void setImage(String path) {
        picasso.load(ImageUrlUtil.getPosterImageUrl(path)).
                error(R.drawable.ic_broken_image).
                placeholder(R.drawable.ic_image).
                into((ImageView) view.findViewById(R.id.imagePoster));
    }
}
