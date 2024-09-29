package co.idesoft.architetture.hexagonal.domain.ports.api;

import java.util.Optional;

import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;

public interface FindClienteByIdUseCase {
    Optional<ClienteDettaglio> findClienteByid(Long clienteId);
}
