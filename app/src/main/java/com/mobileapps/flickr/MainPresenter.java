package com.mobileapps.flickr;

import android.util.Log;

import com.mobileapps.flickr.model.FlickrResponse;
import com.mobileapps.flickr.model.Item;
import com.mobileapps.flickr.retrofit.NetworkClient;
import com.mobileapps.flickr.retrofit.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface
{

    MainViewInterface mvi;
    private String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }

    @Override
    public void getPictures()
    {
        Log.d("Heiner","Im going to get observale");
        getObservable().subscribeWith(getObserver());
        Log.d("Heiner","I Got observable");
    }

    //Create Observable TV
    public Observable<FlickrResponse> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getPictures("kitten","json","1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //Create Observer Me watching TV
    public DisposableObserver<FlickrResponse> getObserver(){
        return new DisposableObserver<FlickrResponse>() {

            @Override
            public void onNext(@NonNull FlickrResponse pictureResponse) {
                Log.d(TAG,"OnNext"+pictureResponse.getItems().size());
                mvi.displayMovies(pictureResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }


}
