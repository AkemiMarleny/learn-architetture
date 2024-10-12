package co.idesoft.architetture.hexagonal.domain.valuables;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CreareCliente(
                @NotBlank @Length(min = 3) String nome,
                @NotBlank @Length(min = 3) String cognomePaterno,
                @NotBlank @Length(min = 3) String cognomeMaterno,
                String descrizione) {

}
