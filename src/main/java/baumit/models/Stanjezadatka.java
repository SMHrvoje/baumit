package baumit.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Stanjezadatka {
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idstanjazadatka")
    private int idstanjazadatka;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getIdstanjazadatka() {
        return idstanjazadatka;
    }

    public void setIdstanjazadatka(int idstanjazadatka) {
        this.idstanjazadatka = idstanjazadatka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stanjezadatka that = (Stanjezadatka) o;
        return idstanjazadatka == that.idstanjazadatka && Objects.equals(naziv, that.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, idstanjazadatka);
    }
}
