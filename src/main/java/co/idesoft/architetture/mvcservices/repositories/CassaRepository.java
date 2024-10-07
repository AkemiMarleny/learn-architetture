package co.idesoft.architetture.mvcservices.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Cassa;

public interface CassaRepository extends JpaRepository<Cassa, Long> {

    Long countByChecksum(String checksum);

    Page<Cassa> findByNomeContaining(Pageable pageable, String query);

}
