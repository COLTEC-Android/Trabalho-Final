package br.ufmg.coltec.tp.database.services;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final Retrofit retrofit;

    private RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    private static RetrofitConfig instance;

    public static RetrofitConfig getInstance() {
        if(instance == null){
            instance = new RetrofitConfig();
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public PokemonService getUserService(){
        return this.retrofit.create(PokemonService.class);
    }
}
