package baumit.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Gradiliste {
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "adresa")
    private String adresa;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idgradilista")
    private int idgradilista;
    @Basic
    @Column(name = "idkorisnika")
    private int idkorisnika;

    @ManyToMany
    @JoinTable(name = "koristisena",
            joinColumns = @JoinColumn(name = "idgradilista"),
            inverseJoinColumns = @JoinColumn(name = "serijskibroj"))
    private Set<Stroj> strojs = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "radina",
            joinColumns = @JoinColumn(name = "idgradilista"),
            inverseJoinColumns = @JoinColumn(name = "idzaposlenika"))
    private Set<Zaposlenik> zaposleniks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idgradilista")
    private Set<Zadatak> zadataks = new LinkedHashSet<>();

    public Set<Zadatak> getZadataks() {
        return zadataks;
    }

    public void setZadataks(Set<Zadatak> zadataks) {
        this.zadataks = zadataks;
    }

    public Set<Zaposlenik> getZaposleniks() {
        return zaposleniks;
    }

    public void setZaposleniks(Set<Zaposlenik> zaposleniks) {
        this.zaposleniks = zaposleniks;
    }

    public Set<Stroj> getStrojs() {
        return strojs;
    }

    public void setStrojs(Set<Stroj> strojs) {
        this.strojs = strojs;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getIdgradilista() {
        return idgradilista;
    }

    public void setIdgradilista(int idgradilista) {
        this.idgradilista = idgradilista;
    }

    public int getIdkorisnika() {
        return idkorisnika;
    }

    public void setIdkorisnika(int idkorisnika) {
        this.idkorisnika = idkorisnika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gradiliste that = (Gradiliste) o;
        return idgradilista == that.idgradilista && idkorisnika == that.idkorisnika && Objects.equals(naziv, that.naziv) && Objects.equals(adresa, that.adresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, adresa, idgradilista, idkorisnika);
    }
}
