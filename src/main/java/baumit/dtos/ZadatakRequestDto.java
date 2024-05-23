package baumit.dtos;

public record ZadatakRequestDto(
        int idGradilista,
        String naziv,
        String opis,
        int idStanjaZadatka
) {
}
