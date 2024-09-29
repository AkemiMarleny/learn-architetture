package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Dipendente;

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
