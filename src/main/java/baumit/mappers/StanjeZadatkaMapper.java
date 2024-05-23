package baumit.mappers;

import baumit.dtos.StanjeZadatkaDto;
import baumit.models.Stanjezadatka;
import org.springframework.stereotype.Component;

@Component

public class StanjeZadatkaMapper {


    public StanjeZadatkaDto stanjeZadatkaToStanjeZadatkaDto(Stanjezadatka stanjezadatka){
        return new StanjeZadatkaDto(
                stanjezadatka.getIdstanjazadatka(),
                stanjezadatka.getNaziv()
        );
    }
}
