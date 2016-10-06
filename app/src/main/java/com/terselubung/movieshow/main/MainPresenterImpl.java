package com.terselubung.movieshow.main;

import android.support.annotation.NonNull;

import com.terselubung.movieshow.model.Movie;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Oki on 10/4/2016.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MovieRepository movieRepository;

    @Inject
    public MainPresenterImpl(@NonNull MainView mainView, @NonNull MovieRepository movieRepository) {
        this.mainView = mainView;
        this.movieRepository = movieRepository;
    }

    @Override
    public void loadMovies() {
        loadMovies(1,false);
    }

    @Override
    public void loadMovies(int page,final boolean isHasLoading) {
        movieRepository.loadMovies(page, new MovieRepository.OnFinishedCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                if(isHasLoading)
                {
                    mainView.removeLoading();
                }
                mainView.appendMovies(movies);
                mainView.setIsLoading(false);
            }

            @Override
            public void onFailed() {
                mainView.showError();
            }
        });
    }
}
