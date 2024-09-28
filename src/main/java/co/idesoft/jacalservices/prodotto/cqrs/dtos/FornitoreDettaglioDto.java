package co.idesoft.jacalservices.prodotto.cqrs.dtos;

import co.idesoft.jacalservices.prodotto.cqrs.entities.Fornitore;

public record FornitoreDettaglioDto(
        Long id,
        String nome,
        String descrizione) {

    public static FornitoreDettaglioDto from(Fornitore fornitore) {
        return new FornitoreDettaglioDto(fornitore.getId(), fornitore.getNome(), fornitore.getDescrizione());
    }
}
