package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Supermercato;

public record SupermercatoItemDto(
        Long supermercatoId,
        String nome,
        String descrizione) {

    public static SupermercatoItemDto fromEntity(Supermercato supermercato) {
        return new SupermercatoItemDto(
                supermercato.getSupermercatoId(),
                supermercato.getNome(),
                supermercato.getDescrizione());
    }

}
