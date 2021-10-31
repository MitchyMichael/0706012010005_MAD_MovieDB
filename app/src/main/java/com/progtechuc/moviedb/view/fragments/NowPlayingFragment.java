package com.progtechuc.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.adapter.NowPlayingAdapter;
import com.progtechuc.moviedb.helper.ItemClickSupport;
import com.progtechuc.moviedb.model.NowPlaying;
import com.progtechuc.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
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

    private RecyclerView rv_now_playing;
    private MovieViewModel view_Model;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rv_now_playing = view.findViewById(R.id.rv_now_playing_fragment);
        view_Model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_Model.getNowPlaying();
        view_Model.getResultNowPlaying().observe(getActivity(), showNowPlaying);

        

        return view;
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);

//            ItemClickSupport.addTo(rv_now_playing).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
//                    return false;
//                }
//            });

            ItemClickSupport.addTo(rv_now_playing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", String.valueOf(nowPlaying.getResults().get(position).getId()));
                    bundle.putString("movie_title", String.valueOf(nowPlaying.getResults().get(position).getTitle()));
                    bundle.putString("movie_description", String.valueOf(nowPlaying.getResults().get(position).getOverview()));
                    bundle.putString("movie_date", String.valueOf(nowPlaying.getResults().get(position).getRelease_date()));
                    bundle.putString("movie_popularity", String.valueOf(nowPlaying.getResults().get(position).getPopularity()));
                    bundle.putString("movie_originalLanguage", String.valueOf(nowPlaying.getResults().get(position).getOriginal_language()));
                    bundle.putString("movie_backdrop", String.valueOf(nowPlaying.getResults().get(position).getBackdrop_path()));
                    bundle.putString("movie_voteAverage", String.valueOf(nowPlaying.getResults().get(position).getVote_average()));
                    bundle.putString("movie_vote", String.valueOf(nowPlaying.getResults().get(position).getVote_count()));



                //                intent.putExtra("movie_id", "" + results.getId());
//                intent.putExtra("movie_title", "" + results.getTitle());
//                intent.putExtra("movie_description", "" + results.getOverview());
//                intent.putExtra("movie_date", "" + results.getRelease_date());
//                intent.putExtra("movie_popularity", "" + results.getPopularity());
//                intent.putExtra("movie_originalLanguage", "" + results.getOriginal_language());


                    Navigation.findNavController(v).navigate(R.id.
                            action_nowPlayingFragment_to_movieDetailsFragment, bundle);



                }
            });
        }
    };
}