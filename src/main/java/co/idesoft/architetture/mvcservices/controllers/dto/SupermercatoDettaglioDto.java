package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Supermercato;

public record SupermercatoDettaglioDto(
        Long supermercatoId,
        String nome,
        String descrizione) {

    public static SupermercatoDettaglioDto fromEntity(Supermercato supermercato) {
        return new SupermercatoDettaglioDto(
                supermercato.getSupermercatoId(),
                supermercato.getNome(),
                supermercato.getDescrizione());
    }

}
