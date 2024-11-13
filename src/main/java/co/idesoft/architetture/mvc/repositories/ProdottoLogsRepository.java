package co.idesoft.architetture.mvc.repositories;

import co.idesoft.architetture.mvc.entities.ProdottoLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoLogsRepository extends JpaRepository<ProdottoLogs, Long> {
}
