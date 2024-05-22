package baumit.repos;

import baumit.models.Zadatak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZadatakRepository extends JpaRepository<Zadatak,Integer> {

    public Optional<List<Zadatak>> getZadatakByIdgradilistaEquals(int idGradilista);
}
