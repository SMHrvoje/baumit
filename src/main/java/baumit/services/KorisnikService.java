package baumit.services;

import baumit.dtos.GradilisteDto;
import baumit.dtos.KorisnikDto;
import baumit.dtos.StanjeZadatkaDto;
import baumit.mappers.KorisnikMapper;
import baumit.mappers.StanjeZadatkaMapper;
import baumit.models.Korisnik;
import baumit.models.Stanjezadatka;
import baumit.repos.KorisnikRepository;
import baumit.repos.StanjezadatkaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KorisnikService {


    private final KorisnikRepository korisnikRepository;
    private final KorisnikMapper korisnikMapper;


    public List<KorisnikDto> voditelji(){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        return korisnici.stream().map(korisnikMapper::korisniciDto).filter(korisnikDto -> korisnikDto.iduloge() == 1)
                .collect(Collectors.toList());
    };


    public String getKorisnikById(int korisnikId) {
        Optional<Korisnik> korisnikOptional = korisnikRepository.findById(korisnikId);
        return korisnikOptional.map(Korisnik::getKorisnickoime).orElse("Unknown");
    }
}
