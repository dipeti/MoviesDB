package com.dinya.peter.moviedb;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dinya.peter.moviedb.entity.Movie;
import com.dinya.peter.moviedb.loader.MovieDetailsLoader;
import com.dinya.peter.moviedb.loader.MovieLoader;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Movie> {

    private static final int MOVIE_LOADER_ID  = 1;
    Movie mMovie;

    private ImageView mPoster;
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mTagLine;
    private TextView mOverView;
    private TextView mBudget;
    private TextView mVoteAvg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setUp();
        int id = getIntent().getIntExtra("id",0);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        getLoaderManager().initLoader(MOVIE_LOADER_ID, bundle, this);
    }

    private void setUp() {
        mPoster = findViewById(R.id.iv_details_poster);
        mBudget = findViewById(R.id.tv_details_budget);
        mOverView = findViewById(R.id.tv_details_overview);
        mTitle = findViewById(R.id.tv_details_title);
        mBudget = findViewById(R.id.tv_details_budget);
        mReleaseDate = findViewById(R.id.tv_details_release_date);
        mTagLine = findViewById(R.id.tv_details_tagline);
        mVoteAvg = findViewById(R.id.tv_details_vote_average);
    }

    @Override
    public Loader<Movie> onCreateLoader(int id, Bundle args) {
        switch (id){
            case MOVIE_LOADER_ID:
                return new MovieDetailsLoader(getBaseContext(),args.getInt("id",0));
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);

        }
    }

    @Override
    public void onLoadFinished(Loader<Movie> loader, Movie movie) {
        if (movie != null){
            mMovie = movie;
            refreshContent();
        } else {
            Toast.makeText(getBaseContext(),"something went wrong", Toast.LENGTH_SHORT).show();
        }

    }

    private void refreshContent() {
        mTitle.setText(mMovie.getTitle());
        mReleaseDate.setText(mMovie.getReleaseDate());
        mBudget.setText(getResources().getString(R.string.dollars,mMovie.getBudget()));
        mTagLine.setText(mMovie.getTagline());
        mOverView.setText(mMovie.getOverview());
        mVoteAvg.setText(mMovie.getVoteAverage().substring(0,3));
        Picasso.with(getBaseContext())
                .load(mMovie.getPosterPath())
                .error(R.mipmap.ic_launcher_round)
                .resize(100,100)
                .centerInside()
                .into(mPoster);
    }

    @Override
    public void onLoaderReset(Loader<Movie> loader) {
        // do nothing
        Log.e("OnLoaderReset", "OnLoaderReset");
    }

}
