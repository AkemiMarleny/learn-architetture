package co.idesoft.architetture.mvcservices.controllers.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CreareDipendenteDto(
        @NotBlank @Length(min = 3) String nome,
        String descrizione) {

}