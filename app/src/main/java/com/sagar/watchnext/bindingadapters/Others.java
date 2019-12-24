package com.sagar.watchnext.bindingadapters;

import android.graphics.Bitmap;
import android.os.Build;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class Others {

    @BindingAdapter({"bind:image_url"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get().load(imageUrl).into(view);
    }

    @BindingAdapter({"bind:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"bind:bitmap"})
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"bind:progressTint"})
    public static void setProgressTint(ProgressBar view, int resource) {
        view.getProgressDrawable().setTint(view.getContext().getResources().getColor(resource));
    }

    @BindingAdapter({"bind:tint"})
    public static void setImageViewTint(ImageView view, int resource) {
        view.getDrawable().setTint(view.getContext().getResources().getColor(resource));
    }

    @BindingAdapter({"bind:textcolor"})
    public static void setImageViewTint(TextView view, int resource) {
        view.setTextColor(view.getContext().getResources().getColor(resource));
    }

    @BindingAdapter({"bind:progressPercent"})
    public static void setProgressPercent(ProgressBar view, int percent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.setProgress(percent, true);
        } else {
            view.setProgress(percent);
        }
    }

    @BindingAdapter({"bind:color"})
    public static void setCardBackGroundColor(CardView view, int resource) {
        view.setCardBackgroundColor(view.getContext().getResources().getColor(resource));
    }

    @BindingAdapter({"bind:color"})
    public static void setTextColor(TextView view, int resource) {
        view.setTextColor(view.getContext().getResources().getColor(resource));
    }

    @BindingAdapter({"bind:bgcolor"})
    public static void setRelativeLayoutBGColor(RelativeLayout view, int resource) {
        view.setBackgroundColor(view.getContext().getResources().getColor(resource));
    }

    @BindingAdapter({"bind:bgcolor"})
    public static void setTextViewBGColor(TextView view, int resource) {
        view.setBackgroundColor(view.getContext().getResources().getColor(resource));
    }

}
