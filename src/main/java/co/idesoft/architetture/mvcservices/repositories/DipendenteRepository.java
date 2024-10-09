package co.idesoft.architetture.mvcservices.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Dipendente;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    Long countByChecksum(String checksum);

    Page<Dipendente> findByNomeContaining(Pageable pageable, String query);

    Long countByChecksumAndDipendenteIdNotIn(String checksum, List<Long> ids);

}
