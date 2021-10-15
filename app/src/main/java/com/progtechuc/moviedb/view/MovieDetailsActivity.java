package com.progtechuc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;



public class MovieDetailsActivity extends AppCompatActivity {

    private Context context;
    private TextView lbl_text, textView_title_moviedetails, textView_description_moviedetails,
            textView_date_moviedetails, textView_popularity_moviedetails, textView_originalLanguage_moviedetails;
    private String movie_id = "";
    private ImageView img_poster_moviedetails;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");

        textView_title_moviedetails = findViewById(R.id.textView_title_moviedetails);
        textView_description_moviedetails = findViewById(R.id.textView_description_moviedetails);
        img_poster_moviedetails = findViewById(R.id.img_poster_moviedetails);
        textView_date_moviedetails = findViewById(R.id.textView_date_moviedetails);
        textView_popularity_moviedetails = findViewById(R.id.textView_popularity_moviedetails);
        textView_originalLanguage_moviedetails = findViewById(R.id.textView_originalLanguage_moviedetails);

        movieViewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        movieViewModel.getMovieById(movie_id);
        movieViewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showMovieDetail);

        textView_title_moviedetails.setText(intent.getStringExtra("movie_title"));
        textView_description_moviedetails.setText(intent.getStringExtra("movie_description"));
        textView_date_moviedetails.setText("Release Date: "+ intent.getStringExtra("movie_date"));
        textView_popularity_moviedetails.setText("Popularity: " + intent.getStringExtra("movie_popularity"));
        textView_originalLanguage_moviedetails.setText("Original Language: " + intent.getStringExtra("movie_originalLanguage"));

        //lbl_text = findViewById(R.id.lbl_movie_details);
        //lbl_text.setText(movie_id);
        //cek cek

    }

    private Observer<Movies> showMovieDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String poster_path = movies.getPoster_path().toString();
            if (!(poster_path == null)) {
                String full_path = Const.IMG_URL + poster_path;
                Glide.with(MovieDetailsActivity.this)
                        .load(full_path)
                        .into(img_poster_moviedetails);
            }
        }
    };


        @Override
    public void onBackPressed() {
        finish();
    }
}