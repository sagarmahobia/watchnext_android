package com.sagar.watchnext.adapters.people;

import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.databinding.PeopleListItemBinding;

public class PeopleViewHolder extends RecyclerView.ViewHolder {

    private PeopleListItemBinding binding;

    PeopleViewHolder(PeopleListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public PeopleListItemBinding getBinding() {
        return binding;
    }

    public void bindTo(PeopleModel peopleModel) {
        binding.setModel(peopleModel);
    }
}
