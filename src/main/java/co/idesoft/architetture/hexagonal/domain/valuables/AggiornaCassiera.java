package co.idesoft.architetture.hexagonal.domain.valuables;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AggiornaCassiera(
        @NotNull Long id,
        @NotBlank @Length(min = 3) String nome,
        String descrizione) {

}
