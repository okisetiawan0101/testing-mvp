package com.terselubung.movieshow.main;

import com.terselubung.movieshow.model.Movie;

import java.util.List;

/**
 * Created by Oki on 10/6/2016.
 */

public interface MainView {
    void appendMovies(List<Movie> movies);
    void addLoading();
    void removeLoading();
    void showError();
    void setIsLoading(boolean isLoading);
}
