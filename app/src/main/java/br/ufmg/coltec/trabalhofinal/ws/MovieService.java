package br.ufmg.coltec.trabalhofinal.ws;

import java.util.List;

import br.ufmg.coltec.trabalhofinal.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET ("movie")
    public Call<MovieResponse> getMovie(
            @Query("api_key")String api_key,
            @Query("language") String language,
            @Query("query") String query);

}
