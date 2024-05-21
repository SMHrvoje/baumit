package baumit.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class RadinaPK implements Serializable {
    @Column(name = "idzaposlenika")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idzaposlenika;
    @Column(name = "idgradilista")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        RadinaPK radinaPK = (RadinaPK) o;
        return idzaposlenika == radinaPK.idzaposlenika && idgradilista == radinaPK.idgradilista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idzaposlenika, idgradilista);
    }
}
