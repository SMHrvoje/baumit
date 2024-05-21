package baumit.repos;

import baumit.models.Zadatak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZadatakRepository extends JpaRepository<Zadatak,Integer> {
}
