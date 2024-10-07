package co.idesoft.architetture.hexagonal.adapters.repositories.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cassiera;

public interface JpaCassieraRepository extends JpaRepository<Cassiera, Long> {

    Long countByChecksum(String checksum);

    Long countByChecksumAndIdNotIn(String checksum, List<Long> ids);

    Page<Cassiera> findByNomeContaining(Pageable pageable, String query);

}
