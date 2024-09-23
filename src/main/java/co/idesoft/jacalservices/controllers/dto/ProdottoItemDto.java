package co.idesoft.jacalservices.controllers.dto;

import co.idesoft.jacalservices.entities.Prodotto;

public record ProdottoItemDto(
        Long prodottoId,
        String nome,
        String descrizione) {

    public static ProdottoItemDto fromEntity(Prodotto prodotto) {
        return new ProdottoItemDto(
                prodotto.getProdottoId(),
                prodotto.getNome(),
                prodotto.getDescrizione());
    }
}
