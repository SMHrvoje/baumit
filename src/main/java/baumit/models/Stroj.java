package baumit.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Stroj {
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "serijskibroj")
    private int serijskibroj;
    @Basic
    @Column(name = "masa")
    private BigDecimal masa;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getSerijskibroj() {
        return serijskibroj;
    }

    public void setSerijskibroj(int serijskibroj) {
        this.serijskibroj = serijskibroj;
    }

    public BigDecimal getMasa() {
        return masa;
    }

    public void setMasa(BigDecimal masa) {
        this.masa = masa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stroj stroj = (Stroj) o;
        return serijskibroj == stroj.serijskibroj && Objects.equals(naziv, stroj.naziv) && Objects.equals(masa, stroj.masa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, serijskibroj, masa);
    }
}
