package baumit.dtos;

import baumit.validation.ValidConstructionAddress;
import jakarta.validation.constraints.Size;

public record GradilisteRequestDto(
        int idKorisnika,
        @ValidConstructionAddress
        String adresa,
        @Size(min = 1, message = "Naziv mora postojati")
        @Size(max = 40, message = "Naziv mora biti maksimalno 30 znakova")
        String naziv
) {
}
