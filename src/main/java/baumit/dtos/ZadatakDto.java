package baumit.dtos;

public record ZadatakDto(
        int idZadatka,
        int idGradilista,
        String naziv,
        String opis
) {
}
