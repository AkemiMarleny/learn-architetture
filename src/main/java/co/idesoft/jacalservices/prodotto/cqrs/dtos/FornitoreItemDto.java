package co.idesoft.jacalservices.prodotto.cqrs.dtos;

import co.idesoft.jacalservices.prodotto.cqrs.entities.Fornitore;

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
