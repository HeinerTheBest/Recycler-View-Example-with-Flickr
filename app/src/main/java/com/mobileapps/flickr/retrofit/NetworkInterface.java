package com.mobileapps.flickr.retrofit;

import com.mobileapps.flickr.model.FlickrResponse;
import com.mobileapps.flickr.model.Item;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.mobileapps.flickr.retrofit.RetrofitConstant.PATH;
import static com.mobileapps.flickr.retrofit.RetrofitConstant.QUERY_FORMAT;
import static com.mobileapps.flickr.retrofit.RetrofitConstant.QUERY_NOJSONCALLBACK;
import static com.mobileapps.flickr.retrofit.RetrofitConstant.QUERY_TAG;

public interface NetworkInterface
{
    @GET(PATH)
    Observable<FlickrResponse> getPictures(
            @Query(QUERY_TAG) String tag,
            @Query(QUERY_FORMAT) String format,
            @Query(QUERY_NOJSONCALLBACK) String nojsoncallback);

}
