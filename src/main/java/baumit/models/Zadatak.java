package baumit.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Zadatak {
    @Basic
    @Column(name = "opis")
    private String opis;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idzadatka")
    private int idzadatka;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "idgradilista")
    private int idgradilista;
    @Basic
    @Column(name = "idstanjazadatka")
    private int idstanjazadatka;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIdzadatka() {
        return idzadatka;
    }

    public void setIdzadatka(int idzadatka) {
        this.idzadatka = idzadatka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getIdgradilista() {
        return idgradilista;
    }

    public void setIdgradilista(int idgradilista) {
        this.idgradilista = idgradilista;
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
        Zadatak zadatak = (Zadatak) o;
        return idzadatka == zadatak.idzadatka && idgradilista == zadatak.idgradilista && idstanjazadatka == zadatak.idstanjazadatka && Objects.equals(opis, zadatak.opis) && Objects.equals(naziv, zadatak.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opis, idzadatka, naziv, idgradilista, idstanjazadatka);
    }
}
