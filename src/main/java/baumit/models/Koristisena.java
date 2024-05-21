package baumit.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(KoristisenaPK.class)
public class Koristisena {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "serijskibroj")
    private int serijskibroj;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idgradilista")
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
        Koristisena that = (Koristisena) o;
        return serijskibroj == that.serijskibroj && idgradilista == that.idgradilista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serijskibroj, idgradilista);
    }
}
