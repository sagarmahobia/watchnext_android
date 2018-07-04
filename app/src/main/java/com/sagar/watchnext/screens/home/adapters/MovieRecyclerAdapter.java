package com.sagar.watchnext.screens.home.adapters;

import android.support.annotation.NonNull;

import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.screens.home.HomeFragmentMvpContract;
import com.sagar.watchnext.screens.home.HomeFragmentScope;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 23:35
 */

@HomeFragmentScope
public class MovieRecyclerAdapter extends BaseRecyclerAdapter {
    protected HomeFragmentMvpContract.Presenter presenter;

    @Inject
    MovieRecyclerAdapter(HomeFragmentMvpContract.Presenter presenter, Picasso picasso) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int position) {
        presenter.onBindMovieCard(cardViewHolder, position);

        cardViewHolder.itemView.setOnClickListener(view -> {
            presenter.onMovieRecyclerItemClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return presenter.getMovieCardsCount();
    }
}
