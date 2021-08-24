package br.ufmg.coltec.trabalhofinal.data.entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private String password;
    private List<Exercise> favorites;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        favorites = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Exercise> getFavorites() {
        return favorites;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
