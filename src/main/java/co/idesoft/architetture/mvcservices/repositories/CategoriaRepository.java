package co.idesoft.architetture.mvcservices.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Long countByChecksum(String checksum);

    Page<Categoria> findByNomeContaining(Pageable pageable, String query);

    Long countByChecksumAndCategoriaIdNotIn(String checksum, List<Long> ids);
}