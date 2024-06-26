package baumit.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Uloga {
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduloge")
    private int iduloge;

    @OneToMany(mappedBy = "iduloge")
    private Set<Korisnik> korisniks = new LinkedHashSet<>();

    public Set<Korisnik> getKorisniks() {
        return korisniks;
    }

    public void setKorisniks(Set<Korisnik> korisniks) {
        this.korisniks = korisniks;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getIduloge() {
        return iduloge;
    }

    public void setIduloge(int iduloge) {
        this.iduloge = iduloge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uloga uloga = (Uloga) o;
        return iduloge == uloga.iduloge && Objects.equals(naziv, uloga.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, iduloge);
    }
}
