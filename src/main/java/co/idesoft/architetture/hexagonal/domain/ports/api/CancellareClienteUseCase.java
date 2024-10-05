package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;

public interface CancellareClienteUseCase {
    void cancellaCliente(Long clienteId) throws RecordNotFoundException;
}
