package com.mobileapps.flickr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobileapps.flickr.MainPresenter;
import com.mobileapps.flickr.MainViewInterface;
import com.mobileapps.flickr.R;
import com.mobileapps.flickr.adapter.ImagesAdapter;
import com.mobileapps.flickr.model.FlickrResponse;



public class MainActivity extends AppCompatActivity implements MainViewInterface
{

//    @BindView(R.id.rvPicture)
    RecyclerView rvPictures;

    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ButterKnife.bind(this);
        rvPictures = findViewById(R.id.rvPicture);

        Log.d("Heiner","Im in the main actvity");

        setupMVP();
        setupViews();
        getMovieList();
    }

    private void setupMVP()
    {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews()
    {
        rvPictures.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void getMovieList()
    {
        mainPresenter.getPictures();

    }

    @Override
    public void showToast(String s)
    {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(FlickrResponse pictures)
    {
        if(pictures!=null) {
            Log.d(TAG,pictures.getItems().get(1).getTitle());
            adapter = new ImagesAdapter(pictures.getItems(), MainActivity.this);
            rvPictures.setAdapter(adapter);
        }else{
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayError(String s) {

    }
}
