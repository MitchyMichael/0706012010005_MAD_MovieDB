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
import com.progtechuc.moviedb.adapter.UpComingAdapter;
import com.progtechuc.moviedb.helper.ItemClickSupport;
import com.progtechuc.moviedb.model.NowPlaying;
import com.progtechuc.moviedb.model.UpComing;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpComingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpComingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpComingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpComingFragment newInstance(String param1, String param2) {
        UpComingFragment fragment = new UpComingFragment();
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

    private RecyclerView rv_up_coming;
    private MovieViewModel view_Model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        rv_up_coming = view.findViewById(R.id.rv_up_coming_fragment);
        view_Model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_Model.getUpComing();
        view_Model.getResultUpComing().observe(getActivity(), showUpComing);



        return view;
    }

    private Observer<UpComing> showUpComing = new Observer<UpComing>() {
        @Override
        public void onChanged(UpComing upComing) {
            rv_up_coming.setLayoutManager(new LinearLayoutManager(getActivity()));
            UpComingAdapter adapter = new UpComingAdapter(getActivity());
            adapter.setListUpComing(upComing.getResults());
            rv_up_coming.setAdapter(adapter);


            ItemClickSupport.addTo(rv_up_coming).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", String.valueOf(upComing.getResults().get(position).getId()));
                    bundle.putString("movie_title", String.valueOf(upComing.getResults().get(position).getTitle()));
                    bundle.putString("movie_description", String.valueOf(upComing.getResults().get(position).getOverview()));
                    bundle.putString("movie_date", String.valueOf(upComing.getResults().get(position).getRelease_date()));
                    bundle.putString("movie_popularity", String.valueOf(upComing.getResults().get(position).getPopularity()));
                    bundle.putString("movie_originalLanguage", String.valueOf(upComing.getResults().get(position).getOriginal_language()));
                    bundle.putString("movie_backdrop", String.valueOf(upComing.getResults().get(position).getBackdrop_path()));
                    bundle.putString("movie_voteAverage", String.valueOf(upComing.getResults().get(position).getVote_average()));
                    bundle.putString("movie_vote", String.valueOf(upComing.getResults().get(position).getVote_count()));



                    //                intent.putExtra("movie_id", "" + results.getId());
//                intent.putExtra("movie_title", "" + results.getTitle());
//                intent.putExtra("movie_description", "" + results.getOverview());
//                intent.putExtra("movie_date", "" + results.getRelease_date());
//                intent.putExtra("movie_popularity", "" + results.getPopularity());
//                intent.putExtra("movie_originalLanguage", "" + results.getOriginal_language());


                    Navigation.findNavController(v).navigate(R.id.
                            action_upComingFragment_to_movieDetailsFragment, bundle);
                }
            });
        }
    };


}