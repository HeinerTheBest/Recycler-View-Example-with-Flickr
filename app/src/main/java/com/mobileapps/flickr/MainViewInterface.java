package com.mobileapps.flickr;

import com.mobileapps.flickr.model.FlickrResponse;
import com.mobileapps.flickr.model.Item;

public interface MainViewInterface
{
    void showToast(String s);
    void displayMovies(FlickrResponse pictures);
    void displayError(String s);
}
