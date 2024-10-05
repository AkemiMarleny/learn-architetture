package co.idesoft.architetture.hexagonal.adapters.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cliente;

public interface JpaClienteRepository extends JpaRepository<Cliente, Long> {

    Long countByChecksum(String checksum);

    Long countByChecksumAndIdNotIn(String checksum, List<Long> ids);

    Optional<Cliente> findByIdAndDataEliminazioneIsNull(Long id);

    Page<Cliente> findByDataEliminazioneIsNull(Pageable pageable);
}
