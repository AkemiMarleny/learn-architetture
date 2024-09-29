package co.idesoft.jacalservices.prodotto.hexagonal.adapters.http.dto;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.ClienteDettaglio;

public record ClienteDettaglioDto(
        Long id,
        String nome,
        String descrizione) {

        public static ClienteDettaglioDto from(ClienteDettaglio clienteDettaglio) {
                return new ClienteDettaglioDto(clienteDettaglio.id(), clienteDettaglio.nome(), clienteDettaglio.descrizione());
        }

}
