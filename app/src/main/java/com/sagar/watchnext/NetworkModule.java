package com.sagar.watchnext;

import com.sagar.watchnext.network.repo.TmdbMovieRepo;
import com.sagar.watchnext.network.repo.TmdbPeopleRepo;
import com.sagar.watchnext.network.repo.TmdbTvRepo;

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
    private static final String API_KEY = "";//todo add key

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

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient client) {

        return new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TMDPEndPoint)
                .client(client)
                .build();
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

}
