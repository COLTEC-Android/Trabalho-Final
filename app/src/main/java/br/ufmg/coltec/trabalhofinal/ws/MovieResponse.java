package br.ufmg.coltec.trabalhofinal.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.ufmg.coltec.trabalhofinal.models.Movie;

public class MovieResponse {

//    @SerializedName("page")
//    @Expose
//    private int page;
//
//    @SerializedName("total_results")
//    @Expose
//    private int total_results;
//
//    @SerializedName("total_pages")
//    @Expose
//    private int total_pages;

    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    public List<Movie> getMovies(){
        return movies;
    }

}
