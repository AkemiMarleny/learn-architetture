package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.hexagonal.domain.valuables.CreareCliente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import jakarta.validation.Valid;

public interface CreareClienteUseCase {
    Long creareCliente(@Valid CreareCliente creareCliente) throws ConflictException;
}
