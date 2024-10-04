package co.idesoft.architetture.hexagonal.domain.ports.spi;

import java.util.Optional;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;

public interface CassieraRepository {

    Long countByChecksum(String checksum);

    Long save(SalvareCassiera salvareCassiera);

    Optional<CassieraDettaglio> findById(Long cassieraId);

    Page<CassieraItem> findAll(Pagination pagination);
}
