package co.idesoft.architetture.hexagonal.domain.valuables;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record AggiornareCliente(
        @NotNull Long clienteId,
        @NotBlank @Length(min = 3) String nome,
        @NotBlank @Length(min = 3) String cognomePaterno,
        @NotBlank @Length(min = 3) String cognomeMaterno,
        @NotNull LocalDate compleanno,
        String descrizione
) {
}
