package com.sagar.watchnext.screens.movies.adapters;

import android.support.annotation.NonNull;

import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.screens.movies.MoviesFragmentMvpContract;
import com.sagar.watchnext.screens.movies.MoviesFragmentScope;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 04-Jul-18. at 12:59
 */
@MoviesFragmentScope
public class InTheatersMoviesAdapter extends BaseRecyclerAdapter {

    private MoviesFragmentMvpContract.Presenter presenter;

    @Inject
      InTheatersMoviesAdapter(Picasso picasso, MoviesFragmentMvpContract.Presenter presenter) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int position) {
        presenter.onBindInTheatersMovieCard(cardViewHolder, position);

        cardViewHolder.itemView.setOnClickListener(view -> {
            presenter.onInTheatersRecyclerItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getInTheatersCardsCount();

    }
}
