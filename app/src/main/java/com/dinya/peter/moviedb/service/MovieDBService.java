package com.dinya.peter.moviedb.service;


import com.dinya.peter.moviedb.entity.Movie;
import com.dinya.peter.moviedb.entity.MovieList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBService {
    @GET("/3/search/movie")
    Call<MovieList> listMovies(@Query("query") String title);

    @GET("/3/movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") int id);
}
