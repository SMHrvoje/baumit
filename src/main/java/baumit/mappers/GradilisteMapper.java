package baumit.mappers;

import baumit.dtos.GradilisteWithTasksDto;
import baumit.models.Gradiliste;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradilisteMapper {

    public GradilisteWithTasksDto gradilisteToGradilisteWithTasksDto(Gradiliste gradiliste){
        return new GradilisteWithTasksDto(
                gradiliste.getIdgradilista(),
                gradiliste.getIdkorisnika(),
                gradiliste.getAdresa(),
                gradiliste.getNaziv()
        );
    }
}
