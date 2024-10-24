package co.idesoft.architetture.mvc.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AggiornareProdottoDto(
        @NotBlank @Length(min = 3) String nome,
        @NotNull Long unitaMisuraId,
        String descrizione) {

}
