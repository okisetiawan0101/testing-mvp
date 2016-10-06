package com.terselubung.movieshow.dagger.modules;

import android.app.Activity;
import android.app.Fragment;

import com.terselubung.movieshow.dagger.scopes.UserScope;
import com.terselubung.movieshow.main.MainActivity;
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
public class ViewModule {
    private Fragment fragment;
    private Activity activity;

    public ViewModule(Activity activity){
        this.activity = activity;
    }

    public ViewModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @UserScope
    MainView provideMainView() {
        return (MainActivity)activity;
    }
}
