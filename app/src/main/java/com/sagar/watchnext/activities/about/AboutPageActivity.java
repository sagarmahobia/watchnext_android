package com.sagar.watchnext.activities.about;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplication;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutPageActivity extends AppCompatActivity {

    @BindView(R.id.about_logo_image)
    ImageView logoImage;

    @BindView(R.id.tmdb_image_license_text)
    TextView tmdbLicense;

    @BindView(R.id.tmdb_disclaimer_text)
    TextView tmdbDisclaimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        Picasso picasso = WatchNextApplication.get(this).getComponent().providesPicasso();
        picasso.load(R.drawable.watch_next_feature_graphics).into(logoImage);


        String imageLicenseText = "WatchNext uses data and images by TMDb licensed under ";
        String imageLinkText = "CC BY-NC 4.0";

        SpannableString tmdbLicenseSpan = new SpannableString(imageLicenseText + imageLinkText);
        tmdbLicenseSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://creativecommons.org/licenses/by-nc/4.0/"));
                startActivity(i);
            }
        }, imageLicenseText.length(), imageLicenseText.length() + imageLinkText.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tmdbLicense.setText(tmdbLicenseSpan);
        tmdbLicense.setMovementMethod(LinkMovementMethod.getInstance());

        String disclaimerText = "WatchNext uses the TMDb API but is not endorsed or certified by TMDb: - ";
        String disclaimerLink = "https://www.themoviedb.org/documentation/api/terms-of-use";

        SpannableString tmdbDisclaimerSpan = new SpannableString(disclaimerText + disclaimerLink);

        tmdbDisclaimerSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(disclaimerLink));
                startActivity(i);
            }
        }, disclaimerText.length(), disclaimerText.length() + disclaimerLink.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tmdbDisclaimer.setText(tmdbDisclaimerSpan);
        tmdbDisclaimer.setMovementMethod(LinkMovementMethod.getInstance());

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
