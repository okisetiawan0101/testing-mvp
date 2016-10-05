package com.terselubung.movieshow.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.terselubung.movieshow.R;
import com.terselubung.movieshow.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oki on 10/4/2016.
 */

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> moviesList;
    private Context mContext;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private MainActivity.OnLoadMoreListener mOnLoadMoreListener;


    public MoviesAdapter(Context context, List<Movie> moviesList, MainActivity.OnLoadMoreListener mOnLoadMoreListener) {
        this.moviesList = moviesList;
        this.mContext = context;
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void replaceData(List<Movie> data) {
        setList(data);
        this.notifyDataSetChanged();
    }

    private void setList(List<Movie> data) {
        moviesList = data;
    }

    public void appendData(List<Movie> data) {
        appendList(data);
        this.notifyDataSetChanged();
    }

    private void appendList(List<Movie> data) {
        if(moviesList==null){moviesList= new ArrayList<>();}
        moviesList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie, parent, false);
            return new MoviesViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return moviesList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MoviesViewHolder) {
            MoviesViewHolder movieHolder = (MoviesViewHolder) holder;
            Movie movie = moviesList.get(position);
            movieHolder.titleTV.setText(movie.getTitle());
            movieHolder.yearTV.setText(movie.getReleaseDate());
            movieHolder.descriptionTV.setText(movie.getOverview());
            movieHolder.rattingTV.setText(String.valueOf(movie.getVoteAverage()));
            movieHolder.descriptionTV.setText(movie.getOverview());
            Glide.with(mContext)
            .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+movie.getPosterPath())
            .into(movieHolder.thumbnailIV);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void loadMore(){
        mOnLoadMoreListener.onLoadMore();
    }

    public void addLoading(){
        moviesList.add(null);
        notifyItemInserted(moviesList.size() - 1);
    }

    public void removeLoading(){
        moviesList.remove(moviesList.size() - 1);
        notifyItemRemoved(moviesList.size());
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTV, yearTV, descriptionTV, rattingTV;
        public final ImageView thumbnailIV;

        public MoviesViewHolder(View view) {
            super(view);
            thumbnailIV = (ImageView) view.findViewById(R.id.thumbnailIV);
            titleTV = (TextView) view.findViewById(R.id.titleTV);
            yearTV = (TextView) view.findViewById(R.id.yearTV);
            descriptionTV = (TextView) view.findViewById(R.id.descriptionTV);
            rattingTV = (TextView) view.findViewById(R.id.rattingTV);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public final ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loadProggressBar);
        }
    }
}
