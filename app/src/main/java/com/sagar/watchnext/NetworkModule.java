package com.sagar.watchnext;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sagar.watchnext.network.repo.TMDBRepository;
import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbPeopleRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SAGAR MAHOBIA on 01-Jul-18. at 12:24
 */
@Module
public class NetworkModule {
    private static final String TMDPEndPoint = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "2b3183f319e7b4fa4d6bbaa66289d5c1";

    @Provides
    @ApplicationScope
    Interceptor provideInterceptor() {
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    @ApplicationScope
    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Interceptor interceptor, HttpLoggingInterceptor httpLoggingInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient client) {

        return new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(TMDPEndPoint)
                .client(client)
                .build();
    }


    @Provides
    @ApplicationScope
    Picasso providePicasso(Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(context, Integer.MAX_VALUE))
                .build();

        if (BuildConfig.DEBUG) {
            picasso.setIndicatorsEnabled(true);
            picasso.setLoggingEnabled(true);
        }
        return picasso;
    }

    @ApplicationScope
    @Provides
    TmdbMovieRepo provideTmdpMovieRepo(Retrofit retrofit) {

        return retrofit.create(TmdbMovieRepo.class);

    }

    @Provides
    @ApplicationScope
    TmdbPeopleRepo provideTmdbPeopleRepo(Retrofit retrofit) {

        return retrofit.create(TmdbPeopleRepo.class);
    }

    @Provides
    @ApplicationScope
    TmdbTvRepo provideTmdbTvRepo(Retrofit retrofit) {

        return retrofit.create(TmdbTvRepo.class);
    }

    @Provides
    @ApplicationScope
    TMDBRepository tmdbRepository(Retrofit retrofit) {

        return retrofit.create(TMDBRepository.class);
    }

}
