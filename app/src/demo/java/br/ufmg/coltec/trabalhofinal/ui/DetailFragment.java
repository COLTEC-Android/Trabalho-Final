package br.ufmg.coltec.trabalhofinal.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.db.FavoritesDAO;
import br.ufmg.coltec.trabalhofinal.models.Movie;
import br.ufmg.coltec.trabalhofinal.util.Constants;

public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "movie";

    private Movie movie;
    private FavoritesDAO favoritesDAO;
    private Boolean search_default, search_land;
    private TextView title, orgTitle, language, release, overview;
    private ImageView image;


    public DetailFragment() {

    }

    public static DetailFragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = getArguments().getParcelable(ARG_PARAM1);
        }
        favoritesDAO = new FavoritesDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        title = view.findViewById(R.id.title_detail);
        orgTitle = view.findViewById(R.id.orig_title_detail);
        language = view.findViewById(R.id.language_detail);
        release = view.findViewById(R.id.release_detail);
        overview = view.findViewById(R.id.overview_detail);
        image = view.findViewById(R.id.img_detail);

        setFields(movie);
        
        return view;
    }
    
    private void setFields(Movie movie){
        if(movie != null) {
            title.setText(movie.getTitle());
            orgTitle.setText(movie.getOriginal_title());
            language.setText(movie.getOriginal_language());
            release.setText(movie.getRelease_date());
            overview.setText(movie.getOverview());
            Picasso.get().load(Constants.IMAGE_SMALL + "/" + movie.getPoster_path()).into(image);
        }
    }
}