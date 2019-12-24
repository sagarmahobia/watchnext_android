package com.sagar.watchnext.activities.search.movies.adapters;


import androidx.annotation.NonNull;

import com.sagar.watchnext.activities.search.movies.Contract;
import com.sagar.watchnext.activities.search.movies.MovieSearchFragmentScope;
import com.sagar.watchnext.adapters.search.BaseSearchRecyclerAdapter;
import com.sagar.watchnext.adapters.search.SearchCardViewHolder;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 14-Jul-18. at 00:13
 */

@MovieSearchFragmentScope
public class RecyclerAdapter extends BaseSearchRecyclerAdapter {

    private Contract.Presenter presenter;

    @Inject
    public RecyclerAdapter(Picasso picasso, Contract.Presenter presenter) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCardViewHolder searchCardViewHolder, int position) {
        presenter.onBindCard(searchCardViewHolder, position);
        searchCardViewHolder.itemView.setOnClickListener(view -> presenter.onRecyclerItemClick(position));

    }

    @Override
    public int getItemCount() {
        return presenter.getCardsCount();
    }
}
