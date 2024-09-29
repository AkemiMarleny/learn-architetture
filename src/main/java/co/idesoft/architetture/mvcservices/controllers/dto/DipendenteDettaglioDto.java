package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Dipendente;

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
