package com.sagar.watchnext.activities.tvdetail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplication;
import com.sagar.watchnext.network.models.tv.details.Details;
import com.sagar.watchnext.network.models.tv.details.Genre;
import com.sagar.watchnext.network.models.tv.details.Network;
import com.sagar.watchnext.network.models.tv.details.ProductionCompany;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvDetailActivity extends AppCompatActivity implements Contract.View {


    @Inject
    Contract.Presenter presenter;

    @Inject
    Picasso picasso;

    @Inject
    PixelDensityUtil pixelDensityUtil;

    //butter knife
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.content_scroll_view)
    ScrollView scrollView;

    @BindView(R.id.error_message_text_and_image)
    Group errorMessage;

    @BindView(R.id.backdrop_image)
    ImageView backDropImage;

    @BindView(R.id.poster_image)
    ImageView posterImage;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.year)
    TextView year;

    @BindView(R.id.runtime)
    TextView runtime;

    @BindView(R.id.genres)
    TextView genres;

    @BindView(R.id.overview_text)
    TextView overviewText;

    @BindView(R.id.original_name_text)
    TextView originalNameText;

    @BindView(R.id.original_language_text)
    TextView originalLanguageText;

    @BindView(R.id.show_type_text)
    TextView showTypeText;

    @BindView(R.id.show_status_text)
    TextView showStatusText;

    @BindView(R.id.first_air_date_text)
    TextView firstAirDateText;

    @BindView(R.id.last_air_date_text)
    TextView lastAirDateText;

    @BindView(R.id.networks_text)
    TextView networkText;

    @BindView(R.id.production_companies_text)
    TextView productionCompaniesText;

    @BindView(R.id.homepage_text)
    TextView homepageText;
    private int tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        DaggerTvDetailActivityComponent.builder().
                watchNextApplicationComponent(WatchNextApplication.get(this).getComponent()).
                tvDetailActivityModule(new TvDetailActivityModule(this)).
                build().
                inject(this);

        ButterKnife.bind(this);

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        pixelDensityUtil.getBackDropImageHeight()
                );
        backDropImage.setLayoutParams(lp);

        Intent intent = getIntent();
        tv_id = intent.getIntExtra("tv_id", -1);

        if (tv_id == -1) {
            //todo handle error in getting tv_id
            this.finish();
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.load());

        getLifecycle().addObserver(presenter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (android.R.id.home == id) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getTvId() {
        return tv_id;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onSucceedLoadingTvDetail(Details tvDetails) {
        swipeRefreshLayout.setRefreshing(false);
        errorMessage.setVisibility(View.GONE);
        getSupportActionBar().setTitle(tvDetails.getName());
        scrollView.setVisibility(View.VISIBLE);

        picasso.load(ImageUrlUtil.getBackdropImageUrl(tvDetails.getBackdropPath()))
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_image)
                .into(backDropImage);
        picasso.load(ImageUrlUtil.getPosterImageUrl(tvDetails.getPosterPath()))
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_image)
                .into(posterImage);

        title.setText(tvDetails.getName());
        String firstAirDate = tvDetails.getFirstAirDate();
        if (firstAirDate != null && firstAirDate.length() >= 4) {
            year.setText(firstAirDate.substring(0, 4));
        }

        int t = tvDetails.getEpisodeRunTimeList().get(0);
        int hours = t / 60; //since both are ints, you get an int
        int minutes = t % 60;

        if (hours == 0 && minutes == 0) {
            runtime.setText("");
        } else if (hours == 0) {
            runtime.setText(String.format("%02dm", minutes));
        } else if (minutes == 0) {
            runtime.setText(String.format("%02dh", hours));
        } else {
            runtime.setText(String.format("%02dh %02dm", hours, minutes));
        }
        StringBuilder genreString = new StringBuilder();
        List<Genre> genres = tvDetails.getGenres();
        int index = 1;
        int size = genres.size();
        for (Genre genre : tvDetails.getGenres()) {
            if (index++ < size) {
                genreString.append(genre.getName()).append(", ");
            } else {
                genreString.append(genre.getName());
            }
        }

        if (size > 0) {
            this.genres.setText(genreString.toString());
        }

        String overview = tvDetails.getOverview();
        if (overview != null) {
            overviewText.setText(overview);
        }

        String originalName = tvDetails.getOriginalName();
        if (originalName != null) {
            originalNameText.setText(originalName);
        }

        String originalLanguage = tvDetails.getOriginalLanguage();
        if (originalLanguage != null) {
            Locale loc = new Locale(originalLanguage);
            originalLanguage = loc.getDisplayLanguage(loc);
            originalLanguageText.setText(originalLanguage);
        }

        String showType = tvDetails.getType();
        if (showType != null) {
            showTypeText.setText(showType);
        }

        String showStatus = tvDetails.getStatus();
        if (showStatus != null) {
            showStatusText.setText(showStatus);
        }

        if (firstAirDate != null) {
            firstAirDateText.setText(firstAirDate);
        }

        String lastAirDate = tvDetails.getLastAirDate();
        if (lastAirDate != null) {
            lastAirDateText.setText(lastAirDate);
        }

        StringBuilder networkStringBuilder = new StringBuilder();
        index = 1;
        size = tvDetails.getNetworks().size();
        for (Network network : tvDetails.getNetworks()) {
            if (index++ < size) {
                networkStringBuilder.append(network.getName()).append(", ");
            } else {
                networkStringBuilder.append(network.getName());
            }
        }
        if (size > 0) {
            networkText.setText(networkStringBuilder.toString());
        }

        StringBuilder productionCompanyString = new StringBuilder();
        index = 1;
        size = tvDetails.getProductionCompanies().size();
        for (ProductionCompany productionCompany : tvDetails.getProductionCompanies()) {
            if (index++ < size) {
                productionCompanyString.append(productionCompany.getName()).append(", ");
            } else {
                productionCompanyString.append(productionCompany.getName());
            }
        }
        if (size > 0) {
            productionCompaniesText.setText(productionCompanyString);
        }

        String homepage = tvDetails.getHomepage();
        if (homepage != null && homepage.length() > 0) {
            SpannableString spannableString = new SpannableString(homepage);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(homepage));
                    startActivity(i);
                }
            };
            spannableString.setSpan(clickableSpan, 0, homepage.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, homepage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            homepageText.setText(spannableString);
            homepageText.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    @Override
    public void onErrorLoadingMovieDetail() {
        scrollView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
