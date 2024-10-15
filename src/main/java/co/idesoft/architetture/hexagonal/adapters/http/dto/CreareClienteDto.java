package co.idesoft.architetture.hexagonal.adapters.http.dto;

import java.time.LocalDate;

public record CreareClienteDto(
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        LocalDate compleanno,
        String descrizione) {

}
