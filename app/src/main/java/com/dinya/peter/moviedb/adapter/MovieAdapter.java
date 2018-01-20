package com.dinya.peter.moviedb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinya.peter.moviedb.R;
import com.dinya.peter.moviedb.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movies;
    Context mContext;
    MovieOnClickListener mMovieOnClickListener;

    public MovieAdapter(Context mContext, MovieOnClickListener movieOnClickListener) {
        this.mContext = mContext;
        mMovieOnClickListener = movieOnClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /* Inflating the view */
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);
        view.setFocusable(true);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        holder.bind(currentMovie);
    }

    @Override
    public int getItemCount() {
        if (movies != null){
            return movies.size();
        }
        return 0;

    }

    public void swap(List<Movie> data) {
        movies = data;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView poster;
        final TextView title;
        final TextView vote;

        MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            poster = itemView.findViewById(R.id.list_item_movie_poster);
            title = itemView.findViewById(R.id.list_item_movie_title);
            vote = itemView.findViewById(R.id.list_item_movie_vote);
        }

        @Override
        public void onClick(View view) {

           mMovieOnClickListener.onClick(movies.get(getAdapterPosition()));
        }

         void bind(Movie movie) {
            title.setText(movie.getTitle());
            vote.setText(movie.getVoteAverage());
             Picasso.with(mContext)
                     .load(movie.getPosterPath())
                     .error(R.mipmap.ic_launcher_round)
                     .resize(100,100)
                     .centerInside()
                     .into(poster);
        }
    }

    public interface MovieOnClickListener{

        void onClick(Movie adapterPosition);
    }
}
