package com.terselubung.movieshow.dagger.components;

import android.content.SharedPreferences;

import com.terselubung.movieshow.dagger.modules.AppModule;
import com.terselubung.movieshow.dagger.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Oki on 10/6/2016.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}
