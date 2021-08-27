package br.ufmg.coltec.data.entities;

public class Favorite {

    String idUser;
    String idExercise;

    public Favorite(String idUser, String idExercise) {
        this.idUser = idUser;
        this.idExercise = idExercise;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(String idExercise) {
        this.idExercise = idExercise;
    }
}
