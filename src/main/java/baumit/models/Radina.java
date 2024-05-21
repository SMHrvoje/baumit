package baumit.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(RadinaPK.class)
public class Radina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idzaposlenika")
    private int idzaposlenika;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idgradilista")
    private int idgradilista;

    public int getIdzaposlenika() {
        return idzaposlenika;
    }

    public void setIdzaposlenika(int idzaposlenika) {
        this.idzaposlenika = idzaposlenika;
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
        Radina radina = (Radina) o;
        return idzaposlenika == radina.idzaposlenika && idgradilista == radina.idgradilista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idzaposlenika, idgradilista);
    }
}
