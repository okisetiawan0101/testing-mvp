package com.terselubung.movieshow.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.terselubung.movieshow.R;
import com.terselubung.movieshow.model.Movie;
import com.terselubung.movieshow.service.ApiInterface;
import com.terselubung.movieshow.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private RecyclerView movieRV;
    private MainContract.Presenter mainPresenter;
    private MoviesAdapter moviesAdapter;
    private int page = 1;
    private boolean isLoading = false;
    private int pastVisiblesItems;
    private int visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(this,new MovieRepositoryImpl(RetrofitClient.getInstance().create(ApiInterface.class)));

        setContentView(R.layout.activity_main);
        initComponent();

        moviesAdapter = new MoviesAdapter(this, new ArrayList<Movie>(), new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                moviesAdapter.addLoading();
                page++;
                mainPresenter.loadMovies(page,true);
            }
        });

        movieRV.setLayoutManager(new LinearLayoutManager(this));

        initListener();

        movieRV.setAdapter(moviesAdapter);

        mainPresenter.loadMovies(page,false);
    }

    private void initComponent() {
        movieRV = (RecyclerView) findViewById(R.id.movieRV);
    }

    private void initListener() {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) movieRV.getLayoutManager();
        movieRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading)
                {
                    if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                    {
                        moviesAdapter.loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void removeLoading() {
        moviesAdapter.removeLoading();
    }

    @Override
    public void addLoading() {
        moviesAdapter.addLoading();
    }

    @Override
    public void appendMovies(List<Movie> movies) {
        moviesAdapter.appendData(movies);
    }

    @Override
    public void showError() {

    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setIsLoading(boolean isLoading){
        this.isLoading = isLoading;
    }
}
