package com.example.minhthanh.listview_lab3_androidth;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    public String msgMovie, msgMovieTopRate;
    public static String serverResponse, serverRespondeTopRate;
    private SwipeRefreshLayout swipeContainer;

    JSONObject jsonNowPlaying = null;
    JSONObject jsonTopRate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        String urlTopRate = "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        final Request request = new Request.Builder().url(url).build();
        final Request requestTopRate = new Request.Builder().url(urlTopRate).build();
        okHttpClient.newCall(requestTopRate).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                    msgMovieTopRate = response.body().string();
            }
        });

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                    msgMovie = response.body().string();
            }
        });

        try {
            TimeUnit.SECONDS.sleep(3);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            jsonNowPlaying = new JSONObject(msgMovie);
            serverResponse = jsonNowPlaying.getString("results");
            jsonTopRate = new JSONObject(msgMovieTopRate);
            serverRespondeTopRate = jsonTopRate.getString("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
