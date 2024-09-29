package co.idesoft.architetture.cqrs.dtos;

import co.idesoft.architetture.cqrs.entities.Fornitore;

public record FornitoreItemDto(
    Long id,
    String nome,
    String descrizione
) {
    public static FornitoreItemDto fromEntity(Fornitore fornitore){
        return new FornitoreItemDto(
            fornitore.getId(),
            fornitore.getNome(),
            fornitore.getDescrizione()
            );
    }
    
}
