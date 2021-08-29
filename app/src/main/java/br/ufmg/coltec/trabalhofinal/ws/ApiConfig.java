package br.ufmg.coltec.trabalhofinal.ws;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    private static Retrofit instance = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static MovieService service = instance.create(MovieService.class);

    public static MovieService getMovieService(){
        return service;
    }
}
