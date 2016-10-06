package com.terselubung.movieshow.dagger.modules;

import com.terselubung.movieshow.dagger.scopes.UserScope;
import com.terselubung.movieshow.main.MainPresenter;
import com.terselubung.movieshow.main.MainPresenterImpl;
import com.terselubung.movieshow.main.MainView;
import com.terselubung.movieshow.main.MovieRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oki on 10/6/2016.
 */

@Module
public class PresenterModule {
    @Provides
    @UserScope
    MainPresenter provideMainPresenter(MainView mainView, MovieRepository movieRepository) {
        return new MainPresenterImpl(mainView, movieRepository);
    }
}
