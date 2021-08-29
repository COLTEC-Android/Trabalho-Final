package br.ufmg.coltec.tp.database.services;

import br.ufmg.coltec.tp.database.models.endpoints.EndpointPokemon;
import br.ufmg.coltec.tp.database.models.Pokemon;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokemonService {

    @GET("pokemon")
    Call<EndpointPokemon> getPokemonEndpoint(@Query("limit") int limit, @Query("offset") int offset);

    @GET
    Observable<Pokemon> getPokemonByUrl(@Url String url);

    @GET("pokemon/{pokemon}")
    Call<Pokemon> getPokemonByName(@Path("pokemon") String pokemonName);

    @GET("pokemon/{pokemon}")
    Call<Pokemon> getPokemonById(@Path("pokemon") int pokemonId);

}
