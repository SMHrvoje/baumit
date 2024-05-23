package baumit.services;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteRequestDto;
import baumit.dtos.GradilisteWithTasksDto;
import baumit.mappers.GradilisteMapper;
import baumit.models.Gradiliste;
import baumit.models.Zadatak;
import baumit.repos.GradilisteRepository;
import baumit.repos.ZadatakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GradilisteService {
    private final GradilisteRepository gradilisteRepository;
    private final ZadatakRepository zadatakRepository;
    private final GradilisteMapper gradilisteMapper;

    public List<GradilisteDto> allConstructionsDtos(){
         List<Gradiliste> gradilista = gradilisteRepository.findAll();
         gradilista.forEach(gradiliste -> {
             Optional<List<Zadatak>> zadaciGradilista = zadatakRepository.getZadatakByIdgradilistaEquals(gradiliste.getIdgradilista());
             if (zadaciGradilista.isPresent()){
                 List<Zadatak> zadaci = zadaciGradilista.get();
                 zadaci.forEach(zadatak -> System.out.println(zadatak.getNaziv()));
             }
         });
         return gradilista.stream().map(GradilisteMapper::gradilisteToGradilisteDto).collect(Collectors.toList());

    }
    public GradilisteWithTasksDto constructionWithTasksDtos(int idGradiliste){
       Optional<Gradiliste> gradilisteOptional = gradilisteRepository.findById(idGradiliste);
       if (gradilisteOptional.isPresent()){
           Gradiliste gradiliste = gradilisteOptional.get();
           return gradilisteMapper.gradilisteToGradilisteWithTasksDto(gradiliste);
       }

        return new GradilisteWithTasksDto(1,1,"few","few",new ArrayList<>());
    }

    public Gradiliste saveGradiliste(GradilisteRequestDto gradilisteDto) {
        Gradiliste gradiliste = new Gradiliste();
        gradiliste.setNaziv(gradilisteDto.naziv());
        gradiliste.setAdresa(gradilisteDto.adresa());
        gradiliste.setIdkorisnika(gradilisteDto.idKorisnika());
        return gradilisteRepository.save(gradiliste);
    }

    public void updateGradiliste(GradilisteDto gradilisteDto) {
        Gradiliste gradiliste = gradilisteRepository.findById(gradilisteDto.idGradilista())
                .orElseThrow(() -> new IllegalArgumentException("Invalid construction Id:" + gradilisteDto.idGradilista()));
        gradiliste.setNaziv(gradilisteDto.naziv());
        gradiliste.setAdresa(gradilisteDto.adresa());
        gradiliste.setIdkorisnika(gradilisteDto.idKorisnika());
        gradilisteRepository.save(gradiliste);
    }
}
