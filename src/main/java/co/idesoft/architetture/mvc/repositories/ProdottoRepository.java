package co.idesoft.architetture.mvc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvc.entities.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    Page<Prodotto> findByNomeContaining(Pageable pageable, String query);

    Long countByChecksum(String checksum);

    Long countByChecksumAndProdottoIdNotIn(String checksum, List<Long> ids);
}
