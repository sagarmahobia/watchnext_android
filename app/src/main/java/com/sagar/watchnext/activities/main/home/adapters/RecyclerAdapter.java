package com.sagar.watchnext.activities.main.home.adapters;


import android.support.annotation.NonNull;

import com.sagar.watchnext.activities.main.home.Contract;
import com.sagar.watchnext.activities.main.home.ListType;
import com.sagar.watchnext.adapters.BaseRecyclerAdapter;
import com.sagar.watchnext.adapters.CardViewHolder;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by SAGAR MAHOBIA on 03-Jul-18. at 23:35
 */

public class RecyclerAdapter extends BaseRecyclerAdapter {

    private Contract.Presenter presenter;

    private ListType listType;

    @Inject
    RecyclerAdapter(Contract.Presenter presenter, Picasso picasso) {
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
