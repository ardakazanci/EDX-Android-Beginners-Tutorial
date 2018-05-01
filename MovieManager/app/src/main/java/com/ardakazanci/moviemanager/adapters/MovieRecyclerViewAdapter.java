package com.ardakazanci.moviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardakazanci.moviemanager.R;
import com.ardakazanci.moviemanager.activities.MovieDetailActivity;
import com.ardakazanci.moviemanager.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    List<Movie> movies;
    Context context;


    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {

        this.movies = movies;
        this.context = context;


    }

    private Context getContext() {
        return context;
    }

    // ViewHolder oluşturma ve şişirme işlemleri
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(view);

    }

    // İlgili sırada ki movie nesnesinin değerlerinin atanması olayı.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        Picasso.with(getContext())
                .load(movie.getPosterPath())
                .into(holder.ivMovieImage);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.ivMovieImage)
        ImageView ivMovieImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvOverview)
        TextView tvOverview;
        @BindView(R.id.cardView)
        CardView cardView;

        ViewHolder(View view) { // 1 görünümün içeriği
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this); // dinleyici burada tanımlanıyor.
        }

        @Override
        public void onClick(View v) {
            Movie movie = movies.get(getAdapterPosition()); // İlgili pozisyonda ki Movie nesnesini getir.
            Intent i = new Intent(getContext(), MovieDetailActivity.class);
            i.putExtra("MOVIE",movie);
            getContext().startActivity(i);
        }
    }
}
