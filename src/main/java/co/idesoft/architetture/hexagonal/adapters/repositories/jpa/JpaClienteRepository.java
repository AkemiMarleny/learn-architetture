package co.idesoft.architetture.hexagonal.adapters.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cliente;

public interface JpaClienteRepository extends JpaRepository<Cliente, Long> {

    Long countByChecksum(String checksum);

    Long countByChecksumAndIdNotIn(String checksum, List<Long> ids);
}
