package co.idesoft.architetture.hexagonal.domain.ports.spi;

import java.util.Optional;

import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;

public interface ClienteRepository {
    Long save(SalvareCliente salvareCliente);
    Optional<ClienteDettaglio> findById(Long clienteId);
}
