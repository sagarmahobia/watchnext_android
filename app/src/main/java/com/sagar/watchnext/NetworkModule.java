package com.sagar.watchnext;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sagar.watchnext.network.repo.TmdbMovieRepo;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
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
            }
        };
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return okClient;
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

    @Provides
    @ApplicationScope
    public TmdbMovieRepo provideTmdpRepo(Retrofit retrofit) {
        return retrofit.create(TmdbMovieRepo.class);
    }

}
