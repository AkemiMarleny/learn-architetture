package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record AggiornareDipendenteDto(
        @NotBlank @Length(min = 3) String nome,
        String descrizione) {

}
