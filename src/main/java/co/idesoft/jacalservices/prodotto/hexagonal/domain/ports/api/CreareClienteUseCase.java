package co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.api;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.CreareCliente;
import jakarta.validation.Valid;

public interface CreareClienteUseCase {
    Long creareCliente(@Valid CreareCliente creareCliente);
}
