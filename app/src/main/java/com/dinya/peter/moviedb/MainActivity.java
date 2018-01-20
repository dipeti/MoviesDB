package com.dinya.peter.moviedb;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dinya.peter.moviedb.adapter.MovieAdapter;
import com.dinya.peter.moviedb.entity.Movie;
import com.dinya.peter.moviedb.loader.MovieLoader;
import com.dinya.peter.moviedb.utils.PropertiesReader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>,MovieAdapter.MovieOnClickListener {
    public static String API_KEY;
    private static final int MOVIE_LOADER_ID =  1;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private EditText mQueryText;
    private Button mSearchButton;
    private TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();


        getLoaderManager().initLoader(MOVIE_LOADER_ID,new Bundle(),this);
    }

    private void setUp() {
        API_KEY = PropertiesReader.getProperty("apikey", getBaseContext());
        mMovieAdapter = new MovieAdapter(getApplicationContext(), this);

        mEmptyView = findViewById(R.id.tv_empty_view);
        mRecyclerView = findViewById(R.id.rv_movies);
        mRecyclerView.setAdapter(mMovieAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
         mRecyclerView.setLayoutManager(linearLayoutManager);
         mQueryText = findViewById(R.id.et_query);
         mSearchButton = findViewById(R.id.bt_search);
         mSearchButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String query = String.valueOf(mQueryText.getText());
                 Bundle bundle = new Bundle();
                 bundle.putString("query", query);
                 getLoaderManager().restartLoader(MOVIE_LOADER_ID,bundle,MainActivity.this).forceLoad();
             }
         });
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        switch (id){
            case MOVIE_LOADER_ID:
                return new MovieLoader(getBaseContext(),args.getString("query","Pulp Fiction"));
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);

        }
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
       if (data.size() == 0){
           showEmptyView();
       } else {
           mMovieAdapter.swap(data);
           mMovieAdapter.notifyDataSetChanged();
           showMovieData();
       }

        Log.v("OnLoaderFinished","Success");
    }



    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        Log.e("OnLoaderReset", "OnLoaderReset");
    }

    private void showEmptyView() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    private void showMovieData(){
        mEmptyView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private Toast mToast;
    @Override
    public void onClick(Movie movie) {
        if (mToast != null ){
            mToast.cancel();
        }
        mToast = Toast.makeText(getBaseContext(),movie.toString() +  " has been clicked.",Toast.LENGTH_SHORT);
        mToast.show();

    }
}
