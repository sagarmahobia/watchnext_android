package com.sagar.watchnext.activities.list.showadapter;

import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.databinding.ShowListItemBinding;


public class ShowViewHolder extends RecyclerView.ViewHolder {

    private ShowListItemBinding binding;

    ShowViewHolder(ShowListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ShowListItemBinding getBinding() {
        return binding;
    }

    public void bindTo(ShowModel showModel) {
        binding.setModel(showModel);
    }
}
