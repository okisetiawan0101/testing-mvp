package com.terselubung.movieshow.main;

import android.support.annotation.NonNull;

import com.terselubung.movieshow.model.MoviesResponse;
import com.terselubung.movieshow.service.ApiInterface;
import com.terselubung.movieshow.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oki on 10/4/2016.
 */

public class MovieRepositoryImpl implements MovieRepository {
    private ApiInterface service;

    public MovieRepositoryImpl(ApiInterface service){
        this.service = service;
    }

    @Override
    public void loadMovies(@NonNull OnFinishedCallback callback) {
        loadMovies(1,callback);
    }

    @Override
    public void loadMovies(int page, @NonNull final OnFinishedCallback callback) {
        service.getTopRatedMovies("df61b1950bc5e9794a13056af5b79db5",page).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                callback.onSuccess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }
}
