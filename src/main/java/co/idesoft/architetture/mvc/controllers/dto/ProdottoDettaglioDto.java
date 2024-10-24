package co.idesoft.architetture.mvc.controllers.dto;

import co.idesoft.architetture.mvc.entities.Prodotto;

public record ProdottoDettaglioDto(
        Long prodottoId,
        String nome,
        Long unitaMisuraId,
        String unitaMisuraNome,
        String descrizione) {

    public static ProdottoDettaglioDto fromEntity(Prodotto prodotto) {
        return new ProdottoDettaglioDto(
                prodotto.getProdottoId(),
                prodotto.getNome(),
                prodotto.getUnitaMisuraId(),
                prodotto.getUnitaMisura().getNome(),
                prodotto.getDescrizione());
    }

}
