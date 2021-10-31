package com.progtechuc.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.adapter.CompanyAdapter;
import com.progtechuc.moviedb.adapter.NowPlayingAdapter;
import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.helper.ItemClickSupport;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.view.activities.MovieDetailsActivity;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

import java.util.List;

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
            textView_date_moviedetails, textView_popularity_moviedetails,
            textView_originalLanguage_moviedetails, textView_voteAvg_moviedetails,
            textView_tagline_moviedetails, textView_vote_moviedetails, textView_genre_moviedetails;
    private ImageView img_poster_moviedetails, img_backdrop_moviedetails, imageView_company_moviedetails;
    private MovieViewModel movieViewModel;
    private RecyclerView rv_movieCompany_moviedetails;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        rv_movieCompany_moviedetails = view.findViewById(R.id.rv_movieCompany_moviedetails);
        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieIdCompany = getArguments().getString("movieId");
        movieViewModel.getMovieById(movieIdCompany);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showCompany);



//        lbl_movie_id = view.findViewById(R.id.lbl_movie_id_movie_details_fragment);
//
//        String movieId = getArguments().getString("movieId");
//        lbl_movie_id.setText(movieId);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
//
//        rv_movieCompany_moviedetails = view.findViewById(R.id.rv_movieCompany_moviedetails);
//        rv_movieCompany_moviedetails.setLayoutManager(layoutManager);


        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieId = getArguments().getString("movieId");
        movieViewModel.getMovieById(movieId);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showMovieDetail);

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieIdBD = getArguments().getString("movieId");
        movieViewModel.getMovieById(movieIdBD);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showMovieDetailBackdrop);

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieIdTagline = getArguments().getString("movieId");
        movieViewModel.getMovieById(movieIdTagline);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showMovieTagline);

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieIdGenre = getArguments().getString("movieId");
        movieViewModel.getMovieById(movieIdGenre);
        movieViewModel.getResultGetMovieById().observe(getActivity(), showMovieGenre);






        img_poster_moviedetails = view.findViewById(R.id.img_poster_moviedetails);
        img_backdrop_moviedetails = view.findViewById(R.id.img_backdrop_moviedetails);

        textView_title_moviedetails = view.findViewById(R.id.textView_title_moviedetails);
        textView_description_moviedetails = view.findViewById(R.id.textView_description_moviedetails);
        textView_date_moviedetails = view.findViewById(R.id.textView_date_moviedetails);
        textView_popularity_moviedetails = view.findViewById(R.id.textView_popularity_moviedetails);
        textView_originalLanguage_moviedetails = view.findViewById(R.id.textView_originalLanguage_moviedetails);
        textView_voteAvg_moviedetails = view.findViewById(R.id.textView_voteAvg_moviedetails);
        textView_tagline_moviedetails = view.findViewById(R.id.textView_tagline_moviedetails);
        textView_vote_moviedetails = view.findViewById(R.id.textView_vote_moviedetails);
        textView_genre_moviedetails = view.findViewById(R.id.textView_genre_moviedetails);
        imageView_company_moviedetails = view.findViewById(R.id.imageView_company_moviedetails);


        textView_title_moviedetails.setText(getArguments().getString("movie_title"));
        textView_description_moviedetails.setText(getArguments().getString("movie_description"));
        textView_date_moviedetails.setText("Date Released: " + getArguments().getString("movie_date"));
        textView_popularity_moviedetails.setText("Popularity: " + getArguments().getString("movie_popularity"));
        textView_originalLanguage_moviedetails.setText("Original Language: " + getArguments().getString("movie_originalLanguage"));
        textView_voteAvg_moviedetails.setText(getArguments().getString("movie_voteAverage"));
        textView_vote_moviedetails.setText("Vote Count: " + getArguments().getString("movie_vote"));



        return view;
    }


    private Observer<Movies> showCompany = new Observer<Movies>() {



        @Override
        public void onChanged(Movies movies) {

            List<Movies.ProductionCompanies> productionCompaniesList = movies.getProduction_companies();

            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

            CompanyAdapter adapter = new CompanyAdapter(getActivity());
            adapter.setListProductionCompanies(movies.getProduction_companies());
            rv_movieCompany_moviedetails.setAdapter(adapter);
            rv_movieCompany_moviedetails.setLayoutManager(layoutManager);

            ItemClickSupport.addTo(rv_movieCompany_moviedetails).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    String name = productionCompaniesList.get(position).getName();
                    Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Observer<Movies> showMovieGenre = new Observer<Movies>() {

        @Override
        public void onChanged(Movies movies) {

            List<Movies.Genres> genresList = movies.getGenres();

            for (int i = 0; i < genresList.size(); i++){
                Movies.Genres genres = genresList.get(i);


                if (i < genresList.size() - 1){
                    textView_genre_moviedetails.append(genres.getName() + ", ");
                } else {
                    textView_genre_moviedetails.append(genres.getName());
                }
            }
        }
    };

    private Observer<Movies> showMovieTagline = new Observer<Movies>() {

        @Override
        public void onChanged(Movies movies) {
            String tagline = movies.getTagline();
            if (!(tagline == null)) {
                textView_tagline_moviedetails.setText(tagline);
            }
        }
    };

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

    private Observer<Movies> showMovieDetailBackdrop = new Observer<Movies>() {

        @Override
        public void onChanged(Movies movies) {
            String backdrop_path = movies.getBackdrop_path();
            if (!(backdrop_path == null)) {
                String full_path = Const.IMG_URL + backdrop_path;
                Glide.with(MovieDetailsFragment.this)
                        .load(full_path)
                        .into(img_backdrop_moviedetails);
            } else {

            }
        }
    };
}