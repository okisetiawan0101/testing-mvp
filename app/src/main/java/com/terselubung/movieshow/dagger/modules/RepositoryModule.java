package com.terselubung.movieshow.dagger.modules;

import com.terselubung.movieshow.dagger.scopes.UserScope;
import com.terselubung.movieshow.main.MovieRepository;
import com.terselubung.movieshow.main.MovieRepositoryImpl;
import com.terselubung.movieshow.service.ApiInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oki on 10/6/2016.
 */

@Module
public class RepositoryModule {
    @Provides
    @UserScope
    MovieRepository provideMovieRepository(ApiInterface apiInterface) {
        return new MovieRepositoryImpl(apiInterface);
    }
}
