package co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.api;

import java.util.Optional;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.ClienteDettaglio;

public interface FindClienteByIdUseCase {
    Optional<ClienteDettaglio> findClienteByid(Long clienteId);
}
