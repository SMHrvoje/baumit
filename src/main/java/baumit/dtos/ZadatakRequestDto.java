package baumit.dtos;

import baumit.validation.ValidTaskDescription;
import jakarta.validation.constraints.Size;

public record ZadatakRequestDto(
        int idGradilista,
        @Size(min = 1, message = "Naziv mora postojati")
        @Size(max = 40, message = "Naziv mora biti maksimalno 30 znakova")
        String naziv,

        @ValidTaskDescription
        @Size(min = 1, message = "Opis mora postojati")
        @Size(max = 300, message = "Opis mora biti maksimalno 300 znakova")
        String opis,
        int idStanjaZadatka
) {
}
