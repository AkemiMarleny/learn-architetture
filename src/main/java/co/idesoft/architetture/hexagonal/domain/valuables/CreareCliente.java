package co.idesoft.architetture.hexagonal.domain.valuables;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreareCliente(
                @NotBlank @Length(min = 3) String nome,
                @NotBlank @Length(min = 3) String cognomePaterno,
                @NotBlank @Length(min = 3) String cognomeMaterno,
                @NotNull LocalDate compleanno,
                String descrizione) {

}
