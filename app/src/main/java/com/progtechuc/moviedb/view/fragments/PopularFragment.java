package com.progtechuc.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.adapter.NowPlayingAdapter;
import com.progtechuc.moviedb.adapter.PopularAdapter;
import com.progtechuc.moviedb.helper.ItemClickSupport;
import com.progtechuc.moviedb.model.NowPlaying;
import com.progtechuc.moviedb.model.Popular;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PopularFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopularFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularFragment newInstance(String param1, String param2) {
        PopularFragment fragment = new PopularFragment();
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

    private RecyclerView rv_popular_fragment;
    private MovieViewModel view_Model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        rv_popular_fragment = view.findViewById(R.id.rv_popular_fragment);
        view_Model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_Model.getPopular();
        view_Model.getResultPopular().observe(getActivity(), showPopular);

        return view;
    }

    private Observer<Popular> showPopular = new Observer<Popular>() {
        @Override
        public void onChanged(Popular popular) {
            rv_popular_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));
            PopularAdapter adapter = new PopularAdapter(getActivity());
            adapter.setListPopular(popular.getResults());
            rv_popular_fragment.setAdapter(adapter);

//            ItemClickSupport.addTo(rv_now_playing).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
//                    return false;
//                }
//            });

            ItemClickSupport.addTo(rv_popular_fragment).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", String.valueOf(popular.getResults().get(position).getId()));
                    bundle.putString("movie_title", String.valueOf(popular.getResults().get(position).getTitle()));
                    bundle.putString("movie_description", String.valueOf(popular.getResults().get(position).getOverview()));
                    bundle.putString("movie_date", String.valueOf(popular.getResults().get(position).getRelease_date()));
                    bundle.putString("movie_popularity", String.valueOf(popular.getResults().get(position).getPopularity()));
                    bundle.putString("movie_originalLanguage", String.valueOf(popular.getResults().get(position).getOriginal_language()));
                    bundle.putString("movie_backdrop", String.valueOf(popular.getResults().get(position).getBackdrop_path()));
                    bundle.putString("movie_voteAverage", String.valueOf(popular.getResults().get(position).getVote_average()));
                    bundle.putString("movie_vote", String.valueOf(popular.getResults().get(position).getVote_count()));



                    //                intent.putExtra("movie_id", "" + results.getId());
//                intent.putExtra("movie_title", "" + results.getTitle());
//                intent.putExtra("movie_description", "" + results.getOverview());
//                intent.putExtra("movie_date", "" + results.getRelease_date());
//                intent.putExtra("movie_popularity", "" + results.getPopularity());
//                intent.putExtra("movie_originalLanguage", "" + results.getOriginal_language());


                    Navigation.findNavController(v).navigate(R.id.
                            action_popularFragment_to_movieDetailsFragment, bundle);



                }
            });
        }
    };
}