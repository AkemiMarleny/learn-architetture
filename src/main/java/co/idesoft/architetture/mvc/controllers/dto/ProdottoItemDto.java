package co.idesoft.architetture.mvc.controllers.dto;

import co.idesoft.architetture.mvc.entities.Prodotto;

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
