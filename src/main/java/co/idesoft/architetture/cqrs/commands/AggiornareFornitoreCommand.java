package co.idesoft.architetture.cqrs.commands;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record AggiornareFornitoreCommand(
    @NotBlank @Length(min=3) String nome,
    String descrizione
) {
    
}
