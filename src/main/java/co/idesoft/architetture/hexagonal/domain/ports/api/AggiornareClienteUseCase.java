package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.hexagonal.domain.valuables.AggiornareCliente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;

public interface AggiornareClienteUseCase {
    void aggiornaCliente(@Valid AggiornareCliente payload) throws ConflictException, RecordNotFoundException;
}
