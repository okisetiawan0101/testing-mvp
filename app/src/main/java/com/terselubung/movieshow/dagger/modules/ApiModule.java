package com.terselubung.movieshow.dagger.modules;

import com.terselubung.movieshow.dagger.scopes.UserScope;
import com.terselubung.movieshow.service.ApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @UserScope
    public ApiInterface providesApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
