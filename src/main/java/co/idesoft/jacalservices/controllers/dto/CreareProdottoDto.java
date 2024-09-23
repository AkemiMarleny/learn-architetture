package co.idesoft.jacalservices.controllers.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CreareProdottoDto(
        @NotBlank @Length(min = 3) String nome,
        String descrizione) {
}
