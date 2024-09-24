package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Magazzino;

public record MagazzinoItemDto(
        Long magazzinoId,
        String nome,
        String descrizione) {

    public static MagazzinoItemDto fromEntity(Magazzino magazzino) {
        return new MagazzinoItemDto(
                magazzino.getMagazzinoId(),
                magazzino.getNome(),
                magazzino.getDescrizione());
    }
}
