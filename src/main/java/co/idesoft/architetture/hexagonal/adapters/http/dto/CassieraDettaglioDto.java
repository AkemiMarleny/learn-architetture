package co.idesoft.architetture.hexagonal.adapters.http.dto;

import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;

public record CassieraDettaglioDto(
        Long id,
        String nome,
        String descrizione) {

    public static CassieraDettaglioDto from(CassieraDettaglio cassieraDettaglio) {
        return new CassieraDettaglioDto(
                cassieraDettaglio.id(),
                cassieraDettaglio.nome(),
                cassieraDettaglio.descrizione());
    }

}
