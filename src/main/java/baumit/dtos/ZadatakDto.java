package baumit.dtos;

import baumit.validation.ValidTaskDescription;
import jakarta.validation.constraints.Size;

public record ZadatakDto(
        int idZadatka,
        int idGradilista,
        @Size(min = 1, message = "Naziv mora postojati")
        @Size(max = 40, message = "Naziv mora biti maksimalno 40 znakova")
        String naziv,
        @ValidTaskDescription
        @Size(max = 300, message = "Opis mora biti maksimalno 300 znakova")
        @Size(min = 1, message = "Opis mora postojati")
        String opis,
        int idStanjaZadatka
) {
}
