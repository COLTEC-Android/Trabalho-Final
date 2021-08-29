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
import android.widget.SearchView;
import android.widget.Toast;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.adapters.MovieAdapter;
import br.ufmg.coltec.trabalhofinal.models.Movie;
import br.ufmg.coltec.trabalhofinal.util.Constants;
import br.ufmg.coltec.trabalhofinal.util.RecyclerItemClickListener;
import br.ufmg.coltec.trabalhofinal.ws.ApiConfig;
import br.ufmg.coltec.trabalhofinal.ws.MovieResponse;
import br.ufmg.coltec.trabalhofinal.ws.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {


    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private FragmentTransaction transaction;
    private SearchView search;
    private List<Movie> moviesList;
    private Movie movie;
    private String locale;
    boolean tablet;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        search = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recycler_view);
        tablet = getActivity().findViewById(R.id.search_sw600d_land) != null;
        locale = getString(R.string.locale);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getResults(newText);
                return false;
            }
        });

        return view;
    }

   private void getResults(String query) {
        MovieService service = ApiConfig.getMovieService();
        Call<MovieResponse> movieCall = service.getMovie(Constants.API_KEY, locale,  query);

        movieCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    moviesList = response.body().getMovies();
                    initRecycle ();
                }

            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Erro durante a requisição",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initRecycle () {
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

               if(tablet){
                   transaction.replace(R.id.detail_frag, detailFrag);
                   transaction.commit();
               }
               else {
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