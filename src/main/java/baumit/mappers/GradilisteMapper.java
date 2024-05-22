package baumit.mappers;

import baumit.dtos.GradilisteDto;
import baumit.dtos.GradilisteWithTasksDto;
import baumit.dtos.ZadatakDto;
import baumit.models.Gradiliste;
import baumit.models.Zadatak;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradilisteMapper {

    public static GradilisteDto gradilisteToGradilisteDto(Gradiliste gradiliste){
        return new GradilisteDto(
                gradiliste.getIdgradilista(),
                gradiliste.getIdkorisnika(),
                gradiliste.getAdresa(),
                gradiliste.getNaziv()
        );
    }

    public static GradilisteWithTasksDto gradilisteToGradilisteWithTasksDto(Gradiliste gradiliste){
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
                zadatak.getOpis()
        );
    }
}
