package baumit.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class KoristisenaPK implements Serializable {
    @Column(name = "serijskibroj")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serijskibroj;
    @Column(name = "idgradilista")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idgradilista;

    public int getSerijskibroj() {
        return serijskibroj;
    }

    public void setSerijskibroj(int serijskibroj) {
        this.serijskibroj = serijskibroj;
    }

    public int getIdgradilista() {
        return idgradilista;
    }

    public void setIdgradilista(int idgradilista) {
        this.idgradilista = idgradilista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KoristisenaPK that = (KoristisenaPK) o;
        return serijskibroj == that.serijskibroj && idgradilista == that.idgradilista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serijskibroj, idgradilista);
    }
}
