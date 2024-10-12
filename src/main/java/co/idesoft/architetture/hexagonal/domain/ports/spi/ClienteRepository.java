package co.idesoft.architetture.hexagonal.domain.ports.spi;

import java.util.List;
import java.util.Optional;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteItem;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvaAggiornamentoCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;

public interface ClienteRepository {

    Long countByChecksum(String checksum);

    Long save(SalvareCliente salvareCliente);

    Optional<ClienteDettaglio> findById(Long clienteId);

    Page<ClienteItem> findAll(Pagination pagination, String query);

    void update(SalvaAggiornamentoCliente payload) throws RecordNotFoundException;

    Long countByChecksumAndIdNotIn(String checksum, List<Long> ids);

    void deleteById(Long clienteId) throws RecordNotFoundException;

}
