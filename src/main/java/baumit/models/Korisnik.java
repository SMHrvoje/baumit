package baumit.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Korisnik {
    @Basic
    @Column(name = "korisnickoime")
    private String korisnickoime;
    @Basic
    @Column(name = "lozinka")
    private String lozinka;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idkorisnika")
    private int idkorisnika;
    @Basic
    @Column(name = "iduloge")
    private int iduloge;

    @OneToMany(mappedBy = "idkorisnika")
    private Set<Gradiliste> gradilistes = new LinkedHashSet<>();

    public Set<Gradiliste> getGradilistes() {
        return gradilistes;
    }

    public void setGradilistes(Set<Gradiliste> gradilistes) {
        this.gradilistes = gradilistes;
    }

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getIdkorisnika() {
        return idkorisnika;
    }

    public void setIdkorisnika(int idkorisnika) {
        this.idkorisnika = idkorisnika;
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
        Korisnik korisnik = (Korisnik) o;
        return idkorisnika == korisnik.idkorisnika && iduloge == korisnik.iduloge && Objects.equals(korisnickoime, korisnik.korisnickoime) && Objects.equals(lozinka, korisnik.lozinka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(korisnickoime, lozinka, idkorisnika, iduloge);
    }
}
