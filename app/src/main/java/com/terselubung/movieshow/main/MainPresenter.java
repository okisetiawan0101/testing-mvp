package com.terselubung.movieshow.main;

/**
 * Created by Oki on 10/6/2016.
 */

public interface MainPresenter {
    void loadMovies(int page,boolean isHasLoading);
    void loadMovies();
}
