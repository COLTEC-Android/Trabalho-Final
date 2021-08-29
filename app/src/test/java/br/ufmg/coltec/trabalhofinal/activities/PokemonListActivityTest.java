package br.ufmg.coltec.trabalhofinal.activities;

import android.content.SharedPreferences;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.tp.database.models.Pokemon;
import br.ufmg.coltec.tp.database.models.basics.PokemonBasic;
import br.ufmg.coltec.tp.database.models.endpoints.EndpointPokemon;
import br.ufmg.coltec.tp.database.services.PokemonService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PokemonListActivityTest {
    PokemonService service = mock(PokemonService.class);
    SharedPreferences sharedPreferences = mock(SharedPreferences.class);
    PokemonProxyTest pokemonProxyTest = PokemonProxyTest.getInstance();

    ArrayList<Pokemon> pokemonsTest = new ArrayList<>();
    int offsetTest;


    public static void main() {
        PokemonListActivityTest test = new PokemonListActivityTest();
        test.pokemonsTest = test.pokemonProxyTest.getPokemons();
        if(test.pokemonsTest.size() == 0){
            test.offsetTest = 0;
            test.getData(test.offsetTest);
        }
        System.out.println(test.pokemonsTest.size() != 0);
    }

    public void getFavorite(Pokemon pokemon) {
        int value = sharedPreferences.getInt(String.valueOf(pokemon.getId()), -1);
        pokemon.setFavorited(value != -1);
    }

    public void getData(int offset) {
        Call<EndpointPokemon> endpoint = service.getPokemonEndpoint(20, offset);

        endpoint.enqueue(new Callback<EndpointPokemon>() {
            @Override
            public void onResponse(Call<EndpointPokemon> call, Response<EndpointPokemon> response) {
                if (response.isSuccessful()) {
                    EndpointPokemon endpointPokemon = response.body();
                    ArrayList<PokemonBasic> pokemonBasicList = endpointPokemon.getPokemonList();
                    List<Observable<?>> requests = new ArrayList<>();
                    for (PokemonBasic p : pokemonBasicList) {
                        requests.add(service.getPokemonByUrl(p.getUrl()));
                    }
                    Observable.zip(requests, (Function<Object[], Object>) objects -> {
                        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
                        for (Object o : objects) {
                            getFavorite((Pokemon) o);
                            pokemonArrayList.add((Pokemon) o);
                        }
                        return pokemonArrayList;
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(o -> pokemonProxyTest.addPokemons((ArrayList<Pokemon>) o));
                }
            }

            @Override
            public void onFailure(Call<EndpointPokemon> call, Throwable t) {
            }
        });
    }

    public static class PokemonProxyTest {
        ArrayList<Pokemon> pokemons;
        private static PokemonProxyTest instance;
        private PokemonProxyTest() {}
        public static PokemonProxyTest getInstance() {
            if (instance == null) {
                instance = new PokemonProxyTest();
            }
            return instance;
        }

        public ArrayList<Pokemon> getPokemons() {
            if (pokemons != null) {
                return pokemons;
            } else {
                return pokemons = new ArrayList<>();
            }
        }

        public void addPokemons(ArrayList<Pokemon> pokemons) {
            this.pokemons.addAll(pokemons);
        }
    }
}

