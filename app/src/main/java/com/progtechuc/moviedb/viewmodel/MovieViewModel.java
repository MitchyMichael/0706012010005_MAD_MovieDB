package com.progtechuc.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.model.NowPlaying;
import com.progtechuc.moviedb.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel (@NonNull Application application){
        super (application);
        repository = MovieRepository.getInstance();
    }

    //== Begin of viewmodel get movie by id
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieid){
        resultGetMovieById = repository.getMovieData(movieid);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }
    //== End of viewmodel get movie by id


    //== Begin of viewmodel get now playing
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }
    //== End of viewmodel get now playing

}
