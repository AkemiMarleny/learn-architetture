package co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.spi;

import java.util.Optional;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.SalvareCliente;

public interface ClienteRepository {
    Long save(SalvareCliente salvareCliente);
    Optional<ClienteDettaglio> findById(Long clienteId);
}
