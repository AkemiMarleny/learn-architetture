package co.idesoft.architetture.hexagonal.adapters.http.dto;

import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;

public record ClienteDettaglioDto(
                Long id,
                String nome,
                String cognomePaterno,
                String cognomeMaterno,
                String descrizione) {

        public static ClienteDettaglioDto from(ClienteDettaglio clienteDettaglio) {
                return new ClienteDettaglioDto(
                                clienteDettaglio.id(),
                                clienteDettaglio.nome(),
                                clienteDettaglio.cognomePaterno(),
                                clienteDettaglio.cognomeMaterno(),
                                clienteDettaglio.descrizione());
        }

}
