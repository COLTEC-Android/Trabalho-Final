package br.ufmg.coltec.tp.database.services;

import java.util.ArrayList;

import br.ufmg.coltec.tp.database.models.Pokemon;

public class PokemonProxy {

    // Para ter certeza de ter só uma lista de pokemons
    private static PokemonProxy instance;
    private PokemonProxy(){}
    public static PokemonProxy getInstance() {
        if(instance == null){
            instance = new PokemonProxy();
        }
        return instance;
    }

    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> favoritedPokemons;

    public ArrayList<Pokemon> getPokemons() {
        if (pokemons != null){
            return pokemons;
        } else {
            return pokemons = new ArrayList<>();
        }
    }

    public ArrayList<Pokemon> getFavoritedPokemons() {
        if (favoritedPokemons != null){
            return favoritedPokemons;
        } else {
            return favoritedPokemons = new ArrayList<>();
        }
    }

    public void addPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
    }
    public void addFavoritedPokemon(Pokemon pokemon) {
        this.favoritedPokemons.add(pokemon);
    }
    public void removeFavoritedPokemon(Pokemon pokemon) {
        this.favoritedPokemons.remove(pokemon);
    }
}
