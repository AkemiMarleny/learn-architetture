package co.idesoft.architetture.hexagonal.domain.ports.spi;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;

public interface CassieraRepository {

    Long countByChecksum(String checksum);

    Long save(SalvareCassiera salvareCassiera);
}
