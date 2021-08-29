package br.ufmg.coltec.tp.database.models;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufmg.coltec.tp.database.models.basics.AbilityBasic;
import br.ufmg.coltec.tp.database.models.basics.TypeBasic;

public class Pokemon implements Serializable {

    private boolean isFavorited;
    private int id;
    private String name;
    private int height;
    private int weight;
    private ArrayList<AbilityBasic> abilities;
    private ArrayList<Stat> stats;
    private ArrayList<TypeBasic> types;
    private Sprites sprites;
    // TODO: forms
    // TODO: moves
    // TODO: evolutions

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean bool) {
        isFavorited = bool;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<AbilityBasic> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<AbilityBasic> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }

    public ArrayList<TypeBasic> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TypeBasic> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }


}


