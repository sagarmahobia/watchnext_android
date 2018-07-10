package com.sagar.watchnext.activities.moviedetail;

import com.sagar.watchnext.R;
import com.sagar.watchnext.WatchNextApplication;
import com.sagar.watchnext.network.models.movies.moviedetail.Genre;
import com.sagar.watchnext.network.models.movies.moviedetail.MovieDetail;
import com.sagar.watchnext.network.models.movies.moviedetail.ProductionCompany;
import com.sagar.watchnext.utils.ImageUrlUtil;
import com.sagar.watchnext.utils.PixelDensityUtil;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailActivityMvpContract.View {


    //dagger
    @Inject
    MovieDetailActivityMvpContract.Presenter presenter;

    @Inject
    Picasso picasso;

    @Inject
    PixelDensityUtil pixelDensityUtil;


    //butter knife
    @BindView(R.id.content_scroll_view)
    ScrollView scrollView;

    @BindView(R.id.backdrop_image)
    ImageView backDropImage;

    @BindView(R.id.poster_image)
    ImageView posterImage;

    @BindView(R.id.title)
    TextView movieTitle;

    @BindView(R.id.year)
    TextView movieYear;

    @BindView(R.id.runtime)
    TextView movieRuntime;

    @BindView(R.id.genres)
    TextView movieGenres;

    @BindView(R.id.overview_text)
    TextView overviewText;

    @BindView(R.id.original_title_text)
    TextView originalTitleText;

    @BindView(R.id.original_language_text)
    TextView originalLanguageText;

    @BindView(R.id.status_text)
    TextView statusText;

    @BindView(R.id.release_date_text)
    TextView releaseDateText;

    @BindView(R.id.budget_text)
    TextView budgetText;

    @BindView(R.id.revenue_text)
    TextView revenueText;

    @BindView(R.id.production_companies_text)
    TextView productionCompaniesText;

    @BindView(R.id.homepage_text)
    TextView homepageText;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        DaggerMovieDetailActivityComponent.
                builder().
                watchNextApplicationComponent(WatchNextApplication.get(this).getComponent()).
                movieDetailActivityModule(new MovieDetailActivityModule(this)).
                build().
                injectMovieDetailActivity(this);

        ButterKnife.bind(this);

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        pixelDensityUtil.getBackDropImageHeight()
                );
        backDropImage.setLayoutParams(lp);

        Intent intent = getIntent();
        int movie_id = intent.getIntExtra("movie_id", -1);

        if (movie_id == -1) {
            //todo handle error in getting movie_id
            this.finish();
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onCreate(movie_id));

        presenter.onCreate(movie_id);
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
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onSucceedLoadingMovieDetail(MovieDetail movieDetail) {
        swipeRefreshLayout.setRefreshing(false);
        getSupportActionBar().setTitle(movieDetail.getTitle());
        scrollView.setVisibility(View.VISIBLE);

        picasso.load(ImageUrlUtil.getBackdropImageUrl(movieDetail.getBackdropPath())).into(backDropImage);
        picasso.load(ImageUrlUtil.getPosterImageUrl(movieDetail.getPosterPath())).into(posterImage);

        movieTitle.setText(movieDetail.getTitle());
        movieYear.setText(movieDetail.getReleaseDate().substring(0, 4));

        int t = movieDetail.getRuntime();
        int hours = t / 60; //since both are ints, you get an int
        int minutes = t % 60;

        if (hours == 0) {
            movieRuntime.setText(String.format("%02dm", minutes));
        } else if (minutes == 0) {
            movieRuntime.setText(String.format("%02dh", hours));

        } else {
            movieRuntime.setText(String.format("%02dh %02dm", hours, minutes));
        }

        StringBuilder genreString = new StringBuilder();
        List<Genre> genres = movieDetail.getGenres();
        int index = 1;
        int size = genres.size();
        for (Genre genre : movieDetail.getGenres()) {
            if (index++ < size) {
                genreString.append(genre.getName()).append(", ");
            } else {
                genreString.append(genre.getName());
            }
        }

        if (size > 0) {
            movieGenres.setText(genreString.toString());
        }

        String overview = movieDetail.getOverview();
        if (overview != null) {
            overviewText.setText(overview);
        }

        String originalTitle = movieDetail.getOriginalTitle();
        if (originalTitle != null) {
            originalTitleText.setText(originalTitle);
        }

        String originalLanguage = movieDetail.getOriginalLanguage();
        if (originalLanguage != null) {
            Locale loc = new Locale(originalLanguage);
            originalLanguage = loc.getDisplayLanguage(loc);
            originalLanguageText.setText(originalLanguage);
        }

        String status = movieDetail.getStatus();
        if (status != null) {
            statusText.setText(status);
        }

        String releaseDate = movieDetail.getReleaseDate();
        if (releaseDate != null) {
            releaseDateText.setText(releaseDate);
        }

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMaximumFractionDigits(0);

        int budget = movieDetail.getBudget();
        if (budget != 0) {
            budgetText.setText(format.format(budget));
        }

        int revenue = movieDetail.getRevenue();
        if (revenue != 0) {
            revenueText.setText(format.format(revenue));
        }

        StringBuilder productionCompanyString = new StringBuilder();
        index = 1;
        size = movieDetail.getProductionCompanies().size();
        for (ProductionCompany productionCompany : movieDetail.getProductionCompanies()) {
            if (index++ < size) {
                productionCompanyString.append(productionCompany.getName()).append(", ");
            } else {
                productionCompanyString.append(productionCompany.getName());
            }
        }
        if (size > 0) {
            productionCompaniesText.setText(productionCompanyString);
        }

        String homepage = movieDetail.getHomepage();
        if (homepage != null && homepage.length() > 0) {
            SpannableString spannableString = new SpannableString(homepage);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Log.d("SPAN", "clicked");
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
        //todo modify error handling
        showToast("Couldn't load movie detail.");
        this.finish();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}




























