package com.terselubung.movieshow.dagger.components;

import android.app.Activity;

import com.terselubung.movieshow.dagger.modules.ApiModule;
import com.terselubung.movieshow.dagger.scopes.UserScope;
import com.terselubung.movieshow.main.MovieRepositoryImpl;

import dagger.Component;

/**
 * Created by Oki on 10/6/2016.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = ApiModule.class)
public interface ApiComponent {
    void inject(MovieRepositoryImpl movieRepositoryImpl);
}
