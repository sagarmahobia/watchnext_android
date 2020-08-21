package com.sagar.watchnext.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.sagar.watchnext.R;
import com.squareup.picasso.Picasso;

/**
 * Created by SAGAR MAHOBIA on 16-Feb-19. at 01:31
 */
public class ImageBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {

        Picasso.get().load(imageUrl)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_image)
                .into(view);
    }

}
