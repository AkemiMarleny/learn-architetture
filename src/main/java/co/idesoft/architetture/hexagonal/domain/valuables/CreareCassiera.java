package co.idesoft.architetture.hexagonal.domain.valuables;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CreareCassiera(
        @NotBlank @Length(min = 3) String nome,
        String descrizione) {

}
