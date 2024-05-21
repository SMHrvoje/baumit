package baumit.repos;

import baumit.models.Zaposlenik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZaposlenikRepository extends JpaRepository<Zaposlenik,Integer> {
}
