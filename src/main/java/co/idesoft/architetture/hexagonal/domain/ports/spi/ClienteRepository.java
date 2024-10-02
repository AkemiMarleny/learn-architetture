package co.idesoft.architetture.hexagonal.domain.ports.spi;

import java.util.Optional;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteItem;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;

public interface ClienteRepository {

    Long countByChecksum(String checksum);
    Long save(SalvareCliente salvareCliente);
    Optional<ClienteDettaglio> findById(Long clienteId);
    Page<ClienteItem> findAll(Pagination pagination);

}
