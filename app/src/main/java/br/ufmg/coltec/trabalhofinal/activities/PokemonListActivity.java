package br.ufmg.coltec.trabalhofinal.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.tp.database.models.endpoints.EndpointPokemon;
import br.ufmg.coltec.tp.database.models.Pokemon;
import br.ufmg.coltec.tp.database.models.basics.PokemonBasic;
import br.ufmg.coltec.tp.database.services.PokemonProxy;
import br.ufmg.coltec.tp.database.services.PokemonService;
import br.ufmg.coltec.tp.database.services.RetrofitConfig;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.adapters.PokemonDetailsAdapter;
import br.ufmg.coltec.trabalhofinal.adapters.PokemonListAdapter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Pokemon List >>>";
    /// Quantidade de pokemons existentes
    private static final int TOTAL_OF_POKEMON = 898;
    // offset da busca na api
    private int offset;
    // Informa se está carregando dados ou não, para impedir algumas ações
    private boolean isLoading;
    // Cuida do tema
    boolean isNightMode;
    // Cuida do layout
    boolean isGridView;

    // Lista de pokemons
    PokemonProxy pokemonProxy = PokemonProxy.getInstance();
    ArrayList<Pokemon> pokemons;
    ArrayList<Pokemon> favoritedPokemons;

    // Componentes da tela
    private PokemonListAdapter listAdapter;
    private RecyclerView recyclerView;
    private RecyclerView detaislRecyclerView;
    private LinearLayoutManager layoutManager;
    private PokemonDetailsAdapter detailsAdapter;

    // Carrega as preferências do usuário
    private SharedPreferences sharedPreferences;

    // Recebe a ordem de abrir os detalhes de um pokemon.
    // A ordem vem através de um broadcast com o id do pokemon,
    // daí só chamar a activity e passar o pokemon que tem esse id.
    BroadcastReceiver showDetails = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("OpenDetails")) {
                int position = intent.getIntExtra("position", -1);
                if (position >= 0 && listAdapter.getItemCount() > position) {
                    Pokemon pokemon = getPokemon(position);
                    if(getResources().getBoolean(R.bool.isTablet)){
                        detailsAdapter.setPokemon(pokemon);
                        detailsAdapter.notifyDataSetChanged();
                    } else {
                        startActivity(new Intent(PokemonListActivity.this, PokemonDetailsActivity.class)
                                .putExtra("pokemon", pokemon));
                    }
                }
            }
        }
    };

    // Conexão com a api. Foi usado o RXAndroid junto do Retrofit para encadear as solicitações.
    // Em resumo, primeira solicitação é feita com o Retrofit e pede as urls dos pokemons,
    // Daí, para cada url, é feita uma nova solicitação usando a extensão RXAndroid,
    // que no fundo é um foreach assíncrono.
    public void getData(int offset) {
        RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();
        PokemonService service = retrofitConfig.getUserService();
        // Sempre vai buscar de 20 em 20.
        Call<EndpointPokemon> endpointCall = service.getPokemonEndpoint(20, offset);

        endpointCall.enqueue(new Callback<EndpointPokemon>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(Call<EndpointPokemon> call, Response<EndpointPokemon> response) {
                isLoading = true;
                if (response.isSuccessful()) {
                    // Primeira solicitação é uma lista de PokemonBasic, que contém o nome e a url
                    EndpointPokemon endpointPokemon = response.body();
                    ArrayList<PokemonBasic> pokemonBasicList = endpointPokemon.getPokemonList();

                    // Montagem da lista de urls para solicitar a api
                    List<Observable<?>> requests = new ArrayList<>();
                    for (PokemonBasic p : pokemonBasicList) {
                        requests.add(service.getPokemonByUrl(p.getUrl()));
                    }

                    // Solicitação dos pokemons com base nas urls
                    Observable.zip(requests, (Function<Object[], Object>) objects -> {
                        // Cada resposta é colocada na lista.
                        // Não precisa de ordenar, já que cada solicitação é feita individualmente
                        // Também verifica se o pokemon foi marcado como favorito
                        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
                        for (Object o : objects) {
                            getFavorite(getApplicationContext(), (Pokemon) o);
                            pokemonArrayList.add((Pokemon) o);
                        }
                        return pokemonArrayList;
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    o -> {
                                        // Ao final, passa acrescenta os pokemons na lista do adapter
                                        pokemonProxy.addPokemons((ArrayList<Pokemon>) o);
                                        listAdapter.notifyDataSetChanged();
                                        isLoading = false;
                                    },
                                    e -> Log.d(LOG_TAG, ">>>onRXAndroid: " + e.getMessage())
                            );
                } else {
                    isLoading = false;
                    Log.d(LOG_TAG, ">>>onResponse -> Endpoint: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<EndpointPokemon> call, Throwable t) {
                isLoading = false;
                Log.d(LOG_TAG, ">>>onFailure -> Endpoint: " + t.getMessage());
            }
        });
    }

    private void getFavorite(Context context, Pokemon pokemon) {
        sharedPreferences = context.getSharedPreferences("Favorites", 0);
        int value = sharedPreferences.getInt(String.valueOf(pokemon.getId()), -1);
        if(value == -1){
            pokemon.setFavorited(false);
        }else{
            pokemon.setFavorited(true);
            favoritedPokemons.add(pokemon);
        }
    }

    public Pokemon getPokemon(int position) {
        return pokemons.get(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        // Fica em modo paisagem se for tablet
        if(getResources().getBoolean(R.bool.isTablet)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        // Solicitação dos dados
        pokemons = pokemonProxy.getPokemons();
        favoritedPokemons = pokemonProxy.getFavoritedPokemons();
        if(pokemons.size() == 0){
            isLoading = true;
            offset = 0;
            getData(offset);
        }

        // Inicialização da actionBar
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Registra o broadcast criado para abrir a tela de mais informações
        LocalBroadcastManager.getInstance(this).registerReceiver(showDetails, new IntentFilter("OpenDetails"));

        // Carrega o tema escolhido pelo usuário, salvo com o SharedPreferences
        sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
        isNightMode = sharedPreferences.getBoolean("NightMode", false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Inicialização do RecyclerView
        recyclerView = findViewById(R.id.pokemon_list);
        recyclerView.setHasFixedSize(true);
        isGridView = sharedPreferences.getBoolean("GridView", false);
        layoutManager = isGridView ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new PokemonListAdapter(this, pokemons, isGridView);
        recyclerView.setAdapter(listAdapter);

        if(getResources().getBoolean(R.bool.isTablet)){
            detaislRecyclerView = findViewById(R.id.pokemon_details);
            detaislRecyclerView.setHasFixedSize(true);
            detaislRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            detailsAdapter = new PokemonDetailsAdapter(this);
            detaislRecyclerView.setAdapter(detailsAdapter);
        }

        // Cada vez que o usuário chegar no fim da lista, pede mais 20 pokemóns pra api
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Não deixa mostrar o que tem depois do último pokemon :)
                if (offset <= TOTAL_OF_POKEMON) {
                    if (dy > 0) {
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int pastVisibleItens = layoutManager.findFirstVisibleItemPosition();

                        if (!isLoading) {
                            if ((visibleItemCount + pastVisibleItens) >= totalItemCount) {
                                isLoading = true;
                                offset += 20;
                                getData(offset);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        // Definições das opções do menu da actionBar
        // A action theme_change altera o tema entre night mode e light mode, e salva a escolha no SharedPreferences
        switch (itemID) {
            //TODO: Implementar busca
            case R.id.action_search:
                return true;

            case R.id.action_sort_all:
                listAdapter.setPokemonList(pokemons);
                return true;

            case R.id.action_sort_favorite:
                listAdapter.setPokemonList(favoritedPokemons);
                return true;

            case R.id.action_layout_change:
                // Coloca o layout de acordo com a preferência do usuário
                // Lista ou Grid de 2 por linha
                sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
                supportInvalidateOptionsMenu();
                isGridView = !isGridView;
                if(isGridView){
                    layoutManager = new GridLayoutManager(this, 2);
                    recyclerView.setLayoutManager(layoutManager);
                    sharedPreferences.edit().putBoolean("GridView", true).apply();
                }else{
                    layoutManager = new LinearLayoutManager(this);
                    recyclerView.setLayoutManager(layoutManager);
                    sharedPreferences.edit().putBoolean("GridView", false).apply();
                }
                listAdapter.toggleItemViewType();
                recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();
                return true;
            case R.id.action_theme_change:
                // Define e salva a troca do tema
                sharedPreferences = getApplicationContext().getSharedPreferences("Settings", 0);
                if (isNightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferences.edit().putBoolean("NightMode", false).apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferences.edit().putBoolean("NightMode", true).apply();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pokemon_list, menu);
        // Muda o ícone do botão do tema com base no tema escolhido
        MenuItem themeItem = menu.findItem(R.id.action_theme_change);
        themeItem.setIcon(isNightMode ? R.drawable.outline_wb_sunny_24 : R.drawable.outline_dark_mode_24);
        // O mesmo mas pro layout
        MenuItem layoutItem = menu.findItem(R.id.action_layout_change);
        layoutItem.setIcon(isGridView ? R.drawable.outline_view_list_24 : R.drawable.outline_grid_view_24);

        // TODO: Alterar para realmente buscar
        // Busca pokemons pela string digitada
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(PokemonListActivity.this, s, Toast.LENGTH_SHORT).show();
                return false;
            }

            // TODO: Buscar usando onTextChange, se viável
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
