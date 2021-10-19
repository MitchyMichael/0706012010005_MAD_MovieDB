package com.progtechuc.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button btn_hit_main;
    private TextView txt_view;
    private TextInputLayout til_hint;
    private ImageView img_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_view = findViewById(R.id.txt_show_main);
        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        img_poster = findViewById(R.id.img);
        til_hint = findViewById(R.id.til_hint);
        btn_hit_main = findViewById(R.id.btn_hit_main);
        btn_hit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = til_hint.getEditText().getText().toString().trim();
                if(movieId.isEmpty()){
                    til_hint.setError("Please fill movie id field");
                }else{
                    til_hint.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }

            }
        });
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies == null){
                txt_view.setText("Movie ID is not available in MovieDB");
            }else{
                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
                String full_path = "https://image.tmdb.org/t/p/w500/" + img_path;
                Glide.with(MainActivity.this).load(full_path).into(img_poster);
                txt_view.setText(title);
            }

        }
    };
}