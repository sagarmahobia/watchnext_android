package com.sagar.watchnext.adapters.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SAGAR MAHOBIA on 12-Jul-18. at 22:44
 */
public class SearchCardViewHolder extends RecyclerView.ViewHolder implements SearchCard {

    private Picasso picasso;

    @BindView(R.id.poster_image)
    ImageView posterImage;

    @BindView(R.id.show_title_text)
    TextView titleTextView;

    @BindView(R.id.show_year_text)
    TextView yearText;

    @BindView(R.id.show_genres_text)
    TextView showGenreText;


    public SearchCardViewHolder(@NonNull View itemView, Picasso picasso) {
        super(itemView);
        this.picasso = picasso;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setImage(String path) {
        picasso.load(ImageUrlUtil.getPosterImageUrl(path))
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_image)
                .into(posterImage);

    }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setYear(String year) {
        yearText.setText(year);
    }

    @Override
    public void setGenre(String genre) {
        showGenreText.setText(genre);
    }
}
