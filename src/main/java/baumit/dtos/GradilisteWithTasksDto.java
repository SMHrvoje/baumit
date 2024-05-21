package baumit.dtos;

import baumit.models.Zadatak;

import java.util.List;

public record GradilisteWithTasksDto(
        int idGradilista,
        int idKorisnika,
        String adresa,
        String naziv
) {
}
