package com.terselubung.movieshow.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oki on 10/4/2016.
 */

public class RetrofitClient {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static Retrofit mRetrofitClient;

    public static Retrofit getInstance(){
        if(mRetrofitClient==null)
        {
            mRetrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofitClient;
    }
}
