package co.idesoft.architetture.hexagonal.domain.ports.spi;

import java.util.List;
import java.util.Optional;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.services.SalvaAggiornamentoCassiera;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;

public interface CassieraRepository {

    Long countByChecksum(String checksum);

    Long save(SalvareCassiera salvareCassiera);

    Optional<CassieraDettaglio> findById(Long cassieraId);

    Page<CassieraItem> findByNomeContaining(Pagination pagination, String query);

    Long countByChecksumAndIdNotIn(String checksum, List<Long> ids);

    void update(SalvaAggiornamentoCassiera payloadAggiornamentoCassiera) throws RecordNotFoundException;

    void deleteById(Long cassieraId);
}
