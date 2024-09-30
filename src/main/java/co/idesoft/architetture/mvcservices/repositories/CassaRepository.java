package co.idesoft.architetture.mvcservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Cassa;

public interface CassaRepository extends JpaRepository<Cassa, Long> {

    Long countByChecksum(String checksum);

}
