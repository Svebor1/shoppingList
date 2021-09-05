package com.example.shoppinglista;

public class Product {
    private String naziv;
    private String firebaseId;
    private Integer kolicina;

    @Override
    public String toString() {
        return "Product{" +
                "naziv='" + naziv + '\'' +
                ", kolicina=" + kolicina +
                '}';
    }
    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Product(String naziv, Integer kolicina, String firebaseId) {
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.firebaseId = firebaseId;

    }
}
