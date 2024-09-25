package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Cassa;

public record CassaDettaglioDto(
        Long cassaId,
        String nome,
        String descrizione) {

    public static CassaDettaglioDto fromEntity(Cassa cassa) {
        return new CassaDettaglioDto(
                cassa.getCassaId(),
                cassa.getNome(),
                cassa.getDescrizione());
    }
}
