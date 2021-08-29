package br.ufmg.coltec.trabalhofinal.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.db.FavoritesDAO;
import br.ufmg.coltec.trabalhofinal.models.Movie;
import br.ufmg.coltec.trabalhofinal.util.Constants;

public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "movie";

    private Movie movie;
    private FavoritesDAO favoritesDAO;
    private Button addFav, shareMovie;
    private Boolean search_default, search_land, favorites_default, favorites_land;

    private TextView title, orgTitle, language, release, overview;
    private ImageView image;


    public DetailFragment() {
        // Required empty public constructor
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
        search_default = getActivity().findViewById(R.id.search_default) != null;
        search_land = getActivity().findViewById(R.id.search_sw600d_land) != null;
        favorites_default = getActivity().findViewById(R.id.favorites_default) != null;
        favorites_land = getActivity().findViewById(R.id.favorites_sw600d_land) != null;
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
        addFav = view.findViewById(R.id.btn_add);
        shareMovie = view.findViewById(R.id.btn_share);

        setLayout();
        setFields(movie);

        addFav.setOnClickListener(v -> {
            if(movie != null){
                favoritesDAO.insert(movie);
                Toast.makeText(getContext(),R.string.msg_add,Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(),R.string.msg_err,Toast.LENGTH_SHORT).show();
            }
        });

        shareMovie.setOnClickListener(v -> {
            if(movie != null){
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setPackage("com.whatsapp");
                shareIntent.setType("text/*");
                shareIntent.putExtra(shareIntent.EXTRA_TEXT, "https://www.themoviedb.org/movie/" + movie.getId());
                startActivity(Intent.createChooser(shareIntent,"Share with"));
            }
            else {
                Toast.makeText(getContext(),R.string.msg_err,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setLayout(){
        if(search_default || search_land){
            addFav.setVisibility(View.VISIBLE);
            shareMovie.setVisibility(View.VISIBLE);
        }
        else if (favorites_default || favorites_land){
            shareMovie.setVisibility(View.VISIBLE);
        }
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