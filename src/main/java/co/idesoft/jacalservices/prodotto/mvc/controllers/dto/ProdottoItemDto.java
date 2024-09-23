package co.idesoft.jacalservices.prodotto.mvc.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvc.entities.Prodotto;

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
