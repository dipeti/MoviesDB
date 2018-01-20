package com.dinya.peter.moviedb.utils;


import com.dinya.peter.moviedb.MainActivity;
import com.dinya.peter.moviedb.entity.Movie;
import com.dinya.peter.moviedb.entity.MovieList;
import com.dinya.peter.moviedb.service.MovieDBService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", MainActivity.API_KEY)
                    .build();
            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }).build();


    private final static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static List<Movie> executeMoviesSearch(String title){
        MovieDBService service = retrofit.create(MovieDBService.class);
        Call<MovieList> moviesCall = service.listMovies(title);
        try {
            retrofit2.Response<MovieList> response = moviesCall.execute();
            if (response.isSuccessful()){
                return response.body().getResults();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }




    private NetworkUtils(){}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
