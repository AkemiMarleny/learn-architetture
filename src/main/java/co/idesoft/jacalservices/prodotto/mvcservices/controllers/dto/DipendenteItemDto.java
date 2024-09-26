package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Dipendente;

public record DipendenteItemDto(
        Long dipendenteId,
        String nome,
        String descrizione) {

    public static DipendenteItemDto fromEntity(Dipendente dipendente) {
        return new DipendenteItemDto(
                dipendente.getDipendenteId(),
                dipendente.getNome(),
                dipendente.getDescrizione());
    }

}
