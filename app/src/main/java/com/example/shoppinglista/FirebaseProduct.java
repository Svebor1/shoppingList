package com.example.shoppinglista;

public class FirebaseProduct {
    private String naziv;
    private Integer kolicina;

    @Override
    public String toString() {
        return "Product{" +
                "naziv='" + naziv + '\'' +
                ", kolicina=" + kolicina +
                '}';
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

    public FirebaseProduct(String naziv, Integer kolicina) {
        this.naziv = naziv;
        this.kolicina = kolicina;


    }
}
