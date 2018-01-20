package com.dinya.peter.moviedb.loader;

import android.content.Context;
import android.content.AsyncTaskLoader;

import com.dinya.peter.moviedb.entity.Movie;
import com.dinya.peter.moviedb.utils.NetworkUtils;

import java.util.List;


public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    private List<Movie> mResult;
    private String mTitle;

    public MovieLoader(Context context, String title) {
        super(context);
        this.mTitle = title;
    }

    @Override
    public List<Movie> loadInBackground() {
        return NetworkUtils.executeMoviesSearch(mTitle);
    }

    @Override
    public void deliverResult(List<Movie> data) {
        mResult = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
