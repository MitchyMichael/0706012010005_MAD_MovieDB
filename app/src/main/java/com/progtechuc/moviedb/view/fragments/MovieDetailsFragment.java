package com.progtechuc.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.view.activities.MovieDetailsActivity;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private TextView lbl_movie_id, textView_title_moviedetails,textView_description_moviedetails,
            textView_date_moviedetails, textView_popularity_moviedetails, textView_originalLanguage_moviedetails;
    private ImageView img_poster_moviedetails;
    private MovieViewModel movieViewModel;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

//        lbl_movie_id = view.findViewById(R.id.lbl_movie_id_movie_details_fragment);
//
//        String movieId = getArguments().getString("movieId");
//        lbl_movie_id.setText(movieId);


        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieId = getArguments().getString("movieId");
        movieViewModel.getMovieById(movieId);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showMovieDetail);

        img_poster_moviedetails = view.findViewById(R.id.img_poster_moviedetails);
        textView_title_moviedetails = view.findViewById(R.id.textView_title_moviedetails);
        textView_description_moviedetails = view.findViewById(R.id.textView_description_moviedetails);
        textView_date_moviedetails = view.findViewById(R.id.textView_date_moviedetails);
        textView_popularity_moviedetails = view.findViewById(R.id.textView_popularity_moviedetails);
        textView_originalLanguage_moviedetails = view.findViewById(R.id.textView_originalLanguage_moviedetails);

        textView_title_moviedetails.setText(getArguments().getString("movie_title"));
        textView_description_moviedetails.setText(getArguments().getString("movie_description"));
        textView_date_moviedetails.setText(getArguments().getString("movie_date"));
        textView_popularity_moviedetails.setText(getArguments().getString("movie_popularity"));
        textView_originalLanguage_moviedetails.setText(getArguments().getString("movie_originalLanguage"));

        return view;
    }

    private Observer<Movies> showMovieDetail = new Observer<Movies>() {

        @Override
        public void onChanged(Movies movies) {
            String poster_path = movies.getPoster_path().toString();
            if (!(poster_path == null)) {
                String full_path = Const.IMG_URL + poster_path;
                Glide.with(MovieDetailsFragment.this)
                        .load(full_path)
                        .into(img_poster_moviedetails);
            } else {

            }
        }
    };
}