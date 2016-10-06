package com.terselubung.movieshow;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.terselubung.movieshow.dagger.components.ApiComponent;
import com.terselubung.movieshow.dagger.components.ViewComponent;
import com.terselubung.movieshow.dagger.components.DaggerApiComponent;
import com.terselubung.movieshow.dagger.components.DaggerViewComponent;
import com.terselubung.movieshow.dagger.components.DaggerNetComponent;
import com.terselubung.movieshow.dagger.components.NetComponent;
import com.terselubung.movieshow.dagger.modules.ApiModule;
import com.terselubung.movieshow.dagger.modules.AppModule;
import com.terselubung.movieshow.dagger.modules.NetModule;
import com.terselubung.movieshow.dagger.modules.PresenterModule;
import com.terselubung.movieshow.dagger.modules.RepositoryModule;
import com.terselubung.movieshow.dagger.modules.ViewModule;

/**
 * Created by Oki on 10/6/2016.
 */

public class MovieShowApp extends Application {
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static NetComponent netComponent;
    private static ApiComponent apiComponent;
    private ViewComponent viewComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getNetComponent();
        getApiComponent();
        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
    }

    public NetComponent getNetComponent() {
        if(netComponent == null) {
            netComponent = DaggerNetComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule(BASE_URL))
                    .build();
        }

        return netComponent;
    }

    public ApiComponent getApiComponent() {
        if(apiComponent == null) {
            apiComponent = DaggerApiComponent.builder()
                    .netComponent(netComponent)
                    .apiModule(new ApiModule())
                    .build();
        }

        return apiComponent;
    }

    public ViewComponent getViewComponent(Activity activity) {
        viewComponent = DaggerViewComponent.builder()
                .netComponent(netComponent)
                .apiModule(new ApiModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule(activity))
                .repositoryModule(new RepositoryModule())
                .build();

        return viewComponent;
    }

    public ViewComponent getViewComponent(Fragment fragment) {
        viewComponent = DaggerViewComponent.builder()
                .netComponent(netComponent)
                .apiModule(new ApiModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule(fragment))
                .repositoryModule(new RepositoryModule())
                .build();

        return viewComponent;
    }
}
