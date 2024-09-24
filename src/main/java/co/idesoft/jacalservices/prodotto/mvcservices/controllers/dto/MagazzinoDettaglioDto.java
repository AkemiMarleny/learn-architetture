package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Magazzino;

public record MagazzinoDettaglioDto(
        Long magazzinoId,
        String nome,
        String descrizione) {

    public static MagazzinoDettaglioDto fromEntity(Magazzino magazzino) {
        return new MagazzinoDettaglioDto(
                magazzino.getMagazzinoId(),
                magazzino.getNome(),
                magazzino.getDescrizione());
    }
}
