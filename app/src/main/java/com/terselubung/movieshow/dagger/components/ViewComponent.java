package com.terselubung.movieshow.dagger.components;

import com.terselubung.movieshow.dagger.modules.ApiModule;
import com.terselubung.movieshow.dagger.modules.AppModule;
import com.terselubung.movieshow.dagger.modules.NetModule;
import com.terselubung.movieshow.dagger.modules.PresenterModule;
import com.terselubung.movieshow.dagger.modules.RepositoryModule;
import com.terselubung.movieshow.dagger.modules.ViewModule;
import com.terselubung.movieshow.dagger.scopes.UserScope;
import com.terselubung.movieshow.main.MainActivity;
import com.terselubung.movieshow.main.MainView;
import com.terselubung.movieshow.main.MovieRepositoryImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Oki on 10/6/2016.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {ApiModule.class, ViewModule.class, PresenterModule.class, RepositoryModule.class})
public interface ViewComponent {
    MainActivity inject(MainActivity mainActivity);
}
