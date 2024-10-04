package co.idesoft.architetture.hexagonal.domain.ports.spi;

import java.util.Optional;

import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;

public interface CassieraRepository {

    Long countByChecksum(String checksum);

    Long save(SalvareCassiera salvareCassiera);

    Optional<CassieraDettaglio> findById(Long cassieraId);
}
