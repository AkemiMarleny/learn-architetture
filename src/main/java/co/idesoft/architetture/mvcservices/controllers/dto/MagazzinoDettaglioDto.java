package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Magazzino;

public record MagazzinoDettaglioDto(
        Long magazzinoId,
        String nome,
        String descrizione) {

    public static MagazzinoDettaglioDto fromEntity(Magazzino magazzino) {
        return new MagazzinoDettaglioDto(
                magazzino.getMagazzinoId(),
                magazzino.getNome(),
                magazzino.getDescrizione());
    }
}
