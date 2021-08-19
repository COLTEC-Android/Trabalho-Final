package br.ufmg.coltec.trabalhofinal.models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    private static List<Exercise> exercisesList = new ArrayList();
    private String name;
    private String description;
    private byte[] image;
    private String type;

    public Exercise(String name, String description, byte[] image, String type) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
    }

    public static List getExercisesList() {
        return exercisesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
