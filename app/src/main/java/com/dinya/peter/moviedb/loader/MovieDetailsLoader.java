package com.dinya.peter.moviedb.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.dinya.peter.moviedb.entity.Movie;
import com.dinya.peter.moviedb.utils.NetworkUtils;

import java.util.List;


public class MovieDetailsLoader extends AsyncTaskLoader<Movie> {
    private int mId;
    private Movie mResult;

    public MovieDetailsLoader(Context context, int id) {
        super(context);
        this.mId = id;
    }

    @Override
    public Movie loadInBackground() {
        return NetworkUtils.executeMovieSearch(mId);
    }

    @Override
    public void deliverResult(Movie data) {
        mResult = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
