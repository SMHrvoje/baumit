package baumit.services;

import baumit.dtos.ZadatakDto;
import baumit.dtos.ZadatakRequestDto;
import baumit.models.Zadatak;
import baumit.repos.ZadatakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZadatakService {
    private final ZadatakRepository zadatakRepository;


    public boolean saveNewZadatak(ZadatakRequestDto zadatakRequestDto){
        Zadatak zadatak = new Zadatak();
        zadatak.setNaziv(zadatakRequestDto.naziv());
        zadatak.setOpis(zadatakRequestDto.opis());
        zadatak.setIdgradilista(zadatakRequestDto.idGradilista());
        zadatak.setIdstanjazadatka(zadatakRequestDto.idStanjaZadatka());
        zadatakRepository.save(zadatak);
        return true;
    };

    public boolean updateZadatak(ZadatakDto zadatakRequestDto){
        Zadatak zadatak = new Zadatak();
        zadatak.setNaziv(zadatakRequestDto.naziv());
        zadatak.setOpis(zadatakRequestDto.opis());
        zadatak.setIdgradilista(zadatakRequestDto.idGradilista());
        zadatak.setIdstanjazadatka(zadatakRequestDto.idStanjaZadatka());
        zadatak.setIdzadatka(zadatakRequestDto.idZadatka());
        zadatakRepository.save(zadatak);
        return true;
    }

    public boolean deleteZadatak(int id){
      Optional<Zadatak> optionalZadatak = zadatakRepository.findById(id);
      if(optionalZadatak.isPresent()){
          Zadatak zadatak = optionalZadatak.get();
          zadatakRepository.delete(zadatak);
          return true;
      }
      return false;
    };
}
