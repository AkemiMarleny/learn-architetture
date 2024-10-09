package co.idesoft.architetture.mvcservices.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Magazzino;

public interface MagazzinoRepository extends JpaRepository<Magazzino, Long> {

    Long countByChecksum(String checksum);

    Page<Magazzino> findByNomeContaining(Pageable pageable, String query);

    Long countByChecksumAndMagazzinoIdNotIn(String checksum, List<Long> ids);
}
