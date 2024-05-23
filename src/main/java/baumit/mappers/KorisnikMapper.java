package baumit.mappers;

import baumit.dtos.KorisnikDto;
import baumit.models.Korisnik;
import org.springframework.stereotype.Component;

@Component

public class KorisnikMapper {


    public KorisnikDto korisniciDto(Korisnik korisnik){
        return new KorisnikDto(
                korisnik.getKorisnickoime(),
                korisnik.getIdkorisnika(),
                korisnik.getIduloge()
        );
    }
}
