package com.terselubung.movieshow.main;

import android.support.annotation.NonNull;

import com.terselubung.movieshow.model.Movie;
import com.terselubung.movieshow.model.MoviesResponse;

import java.util.List;

/**
 * Created by Oki on 10/4/2016.
 */

public interface MovieRepository {
    interface OnFinishedCallback {
        void onSuccess(List<Movie> movies);
        void onFailed();
    }

    void loadMovies(@NonNull OnFinishedCallback callback);
    void loadMovies(int page,@NonNull OnFinishedCallback callback);
}
