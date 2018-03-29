package com.example.minhthanh.listview_lab3_androidth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class FilmDetail extends AppCompatActivity {


    ImageView Video, IconPlay ;
    TextView  Date, VideoDiscription, Title;
    String Background, OriginTitle, VideoDiscriptionStr, DateStr, VoteAverage,VideoStr;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        Video = (ImageView) findViewById(R.id.youtube);
        Date  = (TextView)  findViewById(R.id.Date);
        VideoDiscription = (TextView) findViewById(R.id.Discription);
        Title = (TextView) findViewById(R.id.Title);
        ratingBar = (RatingBar)findViewById(R.id.ratingbar);
        IconPlay  = (ImageView) findViewById(R.id.iconplay);


        ContactsAdapter.User user = Parcels.unwrap(getIntent().getParcelableExtra("Infor"));

        Background  = user.Background;
        OriginTitle = user.Original_Title;
        VideoStr = user.VideoDiscription;
        DateStr = user.Date;
        VoteAverage = user.VoteAverage;
        VideoDiscriptionStr = user.VideoDiscription;

        if(VideoStr.equals("true"))
            IconPlay.setVisibility(View.VISIBLE);

        Picasso.with(getApplicationContext()).load(Background).into(Video);
        Date.setText("Release Date: "+DateStr);
        VideoDiscription.setText(VideoDiscriptionStr);
        Title.setText(OriginTitle);
        ratingBar.setRating(Float.parseFloat(VoteAverage));
    }
}
