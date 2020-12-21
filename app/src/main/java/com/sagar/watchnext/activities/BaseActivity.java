package com.sagar.watchnext.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.InterstitialAd;
import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplicationComponent;
import com.sagar.watchnext.nativeadview.NativeTemplateStyle;
import com.sagar.watchnext.nativeadview.TemplateView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class BaseActivity extends AppCompatActivity {

    @Inject
    WatchNextApplicationComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }


   protected void load(TemplateView midNativeAd) {
        AdLoader.Builder builder = new AdLoader.Builder(this, getString(R.string.detail_screen_native_ad));


        builder.forUnifiedNativeAd(unifiedNativeAd -> {
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().withMainBackgroundColor(new ColorDrawable(Color.TRANSPARENT)).build();

            midNativeAd.setVisibility(View.VISIBLE);
            TemplateView template = midNativeAd;
            template.setStyles(styles);
            template.setNativeAd(unifiedNativeAd);

        });


        AdLoader adLoader = builder
                .build();

        adLoader.loadAd(component.adRequest());
    }

    protected void openYoutube(String url) {

        InterstitialAd interstitialAd = component.interstitialAd();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                openYoutube(url);
                interstitialAd.loadAd(component.adRequest());
            }
        });
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }else {
            openYoutubeUrl(url);
        }


    }

    protected void openYoutubeUrl(String url) {

        Intent youTubeIntent = new Intent(Intent.ACTION_VIEW);
        youTubeIntent.setData(Uri.parse(url));
        startActivity(youTubeIntent);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (android.R.id.home == id) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}
