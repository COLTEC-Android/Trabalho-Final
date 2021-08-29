package br.ufmg.coltec.tp.database.models.endpoints;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufmg.coltec.tp.database.models.basics.PokemonBasic;

public class EndpointPokemon implements Serializable {

    //The total number of resources available from this API.
    @SerializedName("count")
    private int itemCount;

    //The URL for the next page in the list.
    @SerializedName("next")
    private String nextPageUrl;

    //The URL for the previous page in the list. CAN be null.
    @SerializedName("previous")
    private String previousPageUrl;

    //A list of named API resources.
    @SerializedName("results")
    private ArrayList<PokemonBasic> pokemonBasic;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    public ArrayList<PokemonBasic> getPokemonList() {
        return pokemonBasic;
    }

    public void setPokemonList(ArrayList<PokemonBasic> pokemonBasic) {
        this.pokemonBasic = pokemonBasic;
    }
}
