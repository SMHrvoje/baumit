package baumit.repos;

import baumit.models.Stanjezadatka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StanjezadatkaRepository extends JpaRepository<Stanjezadatka,Integer> {

}
