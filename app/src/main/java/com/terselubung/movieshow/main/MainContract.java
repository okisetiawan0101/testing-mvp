package com.terselubung.movieshow.main;

import com.terselubung.movieshow.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oki on 10/4/2016.
 */

public interface MainContract {
    interface View {
        void appendMovies(List<Movie> movies);
        void addLoading();
        void removeLoading();
        void showError();
        void setIsLoading(boolean isLoading);
    }

    interface Presenter {
        void loadMovies(int page,boolean isHasLoading);
        void loadMovies();
    }
}
