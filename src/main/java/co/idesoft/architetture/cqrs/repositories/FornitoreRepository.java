package co.idesoft.architetture.cqrs.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.cqrs.entities.Fornitore;

public interface FornitoreRepository extends JpaRepository<Fornitore, Long> {

    Long countByChecksum(String checksum);

    Page<Fornitore> findByNomeContaining(Pageable pageable, String query);

    Long countByChecksumAndIdNotIn(String checksum, List<Long> ids);
}
