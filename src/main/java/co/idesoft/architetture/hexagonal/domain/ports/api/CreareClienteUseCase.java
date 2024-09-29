package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.hexagonal.domain.valuables.CreareCliente;
import jakarta.validation.Valid;

public interface CreareClienteUseCase {
    Long creareCliente(@Valid CreareCliente creareCliente);
}
