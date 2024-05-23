package baumit.mappers;

import baumit.dtos.ZadatakDto;
import baumit.models.Zadatak;

public class ZadatakMapper {

    public ZadatakDto zadatakToZadatakDto(Zadatak zadatak){
        return new ZadatakDto(
                zadatak.getIdzadatka(),
                zadatak.getIdgradilista(),
                zadatak.getNaziv(),
                zadatak.getOpis(),
                zadatak.getIdstanjazadatka()
        );
    }
}
