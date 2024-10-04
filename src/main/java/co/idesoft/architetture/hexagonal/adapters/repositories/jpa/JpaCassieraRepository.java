package co.idesoft.architetture.hexagonal.adapters.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cassiera;

public interface JpaCassieraRepository extends JpaRepository<Cassiera, Long> {

    Long countByChecksum(String checksum);

}
