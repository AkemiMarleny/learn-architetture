package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Dipendente;

public record DipendenteDettaglioDto(
        Long dipendenteId,
        String nome,
        String descrizione) {

    public static DipendenteDettaglioDto fromEntity(Dipendente dipendente) {
        return new DipendenteDettaglioDto(
                dipendente.getDipendenteId(),
                dipendente.getNome(),
                dipendente.getDescrizione());
    }

}
