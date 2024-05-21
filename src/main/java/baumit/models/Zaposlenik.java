package baumit.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Zaposlenik {
    @Basic
    @Column(name = "ime")
    private String ime;
    @Basic
    @Column(name = "prezime")
    private String prezime;
    @Basic
    @Column(name = "oib")
    private String oib;
    @Basic
    @Column(name = "datumrodenja")
    private Date datumrodenja;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idzaposlenika")
    private int idzaposlenika;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public Date getDatumrodenja() {
        return datumrodenja;
    }

    public void setDatumrodenja(Date datumrodenja) {
        this.datumrodenja = datumrodenja;
    }

    public int getIdzaposlenika() {
        return idzaposlenika;
    }

    public void setIdzaposlenika(int idzaposlenika) {
        this.idzaposlenika = idzaposlenika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zaposlenik that = (Zaposlenik) o;
        return idzaposlenika == that.idzaposlenika && Objects.equals(ime, that.ime) && Objects.equals(prezime, that.prezime) && Objects.equals(oib, that.oib) && Objects.equals(datumrodenja, that.datumrodenja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, oib, datumrodenja, idzaposlenika);
    }
}
