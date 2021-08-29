package br.ufmg.coltec.trabalhofinal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.models.Movie;
import br.ufmg.coltec.trabalhofinal.util.Constants;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> moviesList;
    private String stRelease;

    class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMovie;
        TextView title, origTitle, release;
        RatingBar rbMovie;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            title = itemView.findViewById(R.id.title);
            origTitle = itemView.findViewById(R.id.original_title);
            release = itemView.findViewById(R.id.release);
            rbMovie = itemView.findViewById(R.id.rating_movie);
            stRelease = itemView.getContext().getString(R.string.release_date);

        }
    }
    public MovieAdapter(List<Movie> list){
        this.moviesList = list;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {

        holder.title.setText(moviesList.get(position).getTitle());
        holder.origTitle.setText(moviesList.get(position).getOriginal_title());
        holder.release.setText(stRelease + ": " + moviesList.get(position).getRelease_date());
        Picasso.get().load(Constants.IMAGE + "/" + moviesList.get(position).getPoster_path()).into(holder.imgMovie);
        holder.rbMovie.setRating(Float.parseFloat(moviesList.get(position).getVote_average())/2);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}

