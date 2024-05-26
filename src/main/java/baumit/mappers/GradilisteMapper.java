package baumit.mappers;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteWithTasksDto;
import baumit.dtos.ZadatakDto;
import baumit.models.Gradiliste;
import baumit.models.Stanjezadatka;
import baumit.models.Zadatak;
import baumit.repos.StanjezadatkaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GradilisteMapper {
    private final StanjezadatkaRepository stanjezadatkaRepository;

    public GradilisteDto gradilisteToGradilisteDto(Gradiliste gradiliste){
        return new GradilisteDto(
                gradiliste.getIdgradilista(),
                gradiliste.getIdkorisnika(),
                gradiliste.getAdresa(),
                gradiliste.getNaziv()
        );
    }

    public GradilisteWithTasksDto gradilisteToGradilisteWithTasksDto(Gradiliste gradiliste){
        return new GradilisteWithTasksDto(
                gradiliste.getIdgradilista(),
                gradiliste.getIdkorisnika(),
                gradiliste.getAdresa(),
                gradiliste.getNaziv(),
                gradiliste.getZadataks().stream().map(GradilisteMapper::zadatakToZadatakDto).collect(Collectors.toList())
        );
    }

    public static ZadatakDto zadatakToZadatakDto(Zadatak zadatak){
        return new ZadatakDto(
                zadatak.getIdzadatka(),
                zadatak.getIdgradilista(),
                zadatak.getNaziv(),
                zadatak.getOpis(),
                zadatak.getIdstanjazadatka()

        );
    }
}
