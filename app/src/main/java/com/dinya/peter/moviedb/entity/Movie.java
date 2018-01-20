package com.dinya.peter.moviedb.entity;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Movie {
    private int id;

    @SerializedName("vote_average")
    private String voteAverage;
    private String title;

    @SerializedName("poster_path")
    private String posterPath;


    public int getId() {
        return id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return "http://image.tmdb.org/t/p/w500" + posterPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
