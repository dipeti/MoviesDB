package com.dinya.peter.moviedb.entity;

import java.util.List;

/**
 * Created by dipet on 2018. 01. 20..
 */

public class MovieList {

    private int totalPages;

    public List<Movie> getResults() {
        return results;
    }

    private List<Movie> results;

    public int getTotalPages() {
        return totalPages;
    }
}
