package com.sagar.watchnext.activities.main.tv.adapters;

import android.support.annotation.NonNull;

import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.sagar.watchnext.activities.main.tv.ListType;
import com.sagar.watchnext.activities.main.tv.TvFragmentMvpContract;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 05-Jul-18. at 22:29
 */
public class RecyclerAdapter extends BaseRecyclerAdapter {

    private TvFragmentMvpContract.Presenter presenter;
    private ListType listType;

    @Inject
    RecyclerAdapter(Picasso picasso, TvFragmentMvpContract.Presenter presenter) {
        super(picasso);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int position) {
        presenter.onBindCard(listType, cardViewHolder, position);

        cardViewHolder.itemView.setOnClickListener(view -> {
            presenter.onRecyclerItemClick(listType, position);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getCardsCount(listType);

    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }
}
