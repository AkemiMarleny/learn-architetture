package co.idesoft.jacalservices.prodotto.mvc.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvc.entities.Prodotto;

public record ProdottoDettaglioDto(
        Long prodottoId,
        String nome,
        String descrizione) {

    public static ProdottoDettaglioDto fromEntity(Prodotto prodotto) {
        return new ProdottoDettaglioDto(
                prodotto.getProdottoId(),
                prodotto.getNome(),
                prodotto.getDescrizione());
    }

}
