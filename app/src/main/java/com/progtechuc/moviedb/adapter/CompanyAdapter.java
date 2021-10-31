package com.progtechuc.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.model.NowPlaying;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CardViewViewHolder> {

    private Context context;
    private List<Movies.ProductionCompanies> listProductionCompanies;
    private List<Movies.ProductionCompanies> getListProductionCompanies(){
        return listProductionCompanies;
    }
    public void setListProductionCompanies(List<Movies.ProductionCompanies> listProductionCompanies){
        this.listProductionCompanies = listProductionCompanies;
    }
    public CompanyAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_production_companies, parent, false);
        return new CompanyAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {

        final Movies.ProductionCompanies results = getListProductionCompanies().get(position);
        holder.lbl_title.setText(results.getName());
        if (results.getLogo_path() == null){
            Glide.with(context)
                    .load(R.drawable.ic_baseline_person_245)
                    .into(holder.img_poster_company);
        } else {
            Glide.with(context)
                    .load(Const.IMG_URL + results.getLogo_path())
                    .into(holder.img_poster_company);
        }





    }

    @Override
    public int getItemCount() {
        return getListProductionCompanies().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster_company;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster_company = itemView.findViewById(R.id.imageView_company_moviedetails);
            lbl_title = itemView.findViewById(R.id.textView_company_moviedetails);
            cv = itemView.findViewById(R.id.cardView_company_moviedetails);
        }
    }
}
