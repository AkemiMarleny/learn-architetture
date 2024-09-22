package co.idesoft.jacalservices.controllers.dto;

import co.idesoft.jacalservices.entities.Prodotto;

public record ProdottoDto(
        Long prodottoId,
        String nome,
        String descrizione) {

    public static ProdottoDto fromEntity(Prodotto prodotto) {
        return new ProdottoDto(
                prodotto.getProdottoId(),
                prodotto.getNome(),
                prodotto.getDescrizione());
    }
}
