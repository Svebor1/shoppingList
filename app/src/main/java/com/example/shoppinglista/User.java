package com.example.shoppinglista;

public class User {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public User(String ime, String id) {
        this.ime = ime;
        this.id = id;
    }
    private String ime;
    private String id;

}
