package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Cassa;

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
