package co.idesoft.architetture.hexagonal.adapters.http.dto;

import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;

public record CassieraItemDto(
                Long id,
                String nome,
                String descrizione) {

        public static CassieraItemDto from(CassieraItem cassieraItem) {
                return new CassieraItemDto(
                                cassieraItem.id(),
                                cassieraItem.nome(),
                                cassieraItem.descrizione());
        }

}
