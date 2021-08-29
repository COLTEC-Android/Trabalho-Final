package br.ufmg.coltec.trabalhofinal.ui;

import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.adapters.MovieAdapter;
import br.ufmg.coltec.trabalhofinal.db.FavoritesDAO;
import br.ufmg.coltec.trabalhofinal.models.Movie;
import br.ufmg.coltec.trabalhofinal.util.RecyclerItemClickListener;


public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private FragmentTransaction transaction;
    private List<Movie> moviesList;
    private FavoritesDAO favorites;
    private Movie movie;
    boolean tablet;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        favorites = new FavoritesDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        tablet = getActivity().findViewById(R.id.favorites_sw600d_land) != null;
        moviesList = favorites.getAll();
        initRecycle();
        return view;
    }

    private void initRecycle() {
        if (moviesList == null || moviesList.isEmpty()){
            Toast.makeText(getActivity(), R.string.no_favorite, Toast.LENGTH_LONG).show();
        }
        else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setHasFixedSize(true);
            movieAdapter = new MovieAdapter(moviesList);
            recyclerView.setAdapter(movieAdapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onItemClick(View view, int position) {
                    movie = moviesList.get(position);
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    DetailFragment detailFrag = DetailFragment.newInstance(movie);

                    if (tablet) {
                        transaction.replace(R.id.detail_frag, detailFrag);
                        transaction.commit();
                    } else {
                        transaction.replace(R.id.main_frag, detailFrag);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }

                @Override
                public void onLongItemClick(View view, int position) {

                }

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            }));
        }
    }
}