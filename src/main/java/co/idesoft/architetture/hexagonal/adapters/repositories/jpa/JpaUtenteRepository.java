package co.idesoft.architetture.hexagonal.adapters.repositories.jpa;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUtenteRepository extends JpaRepository<Utente, Long> {

    Long countByUsername(String username);

    Long countByUsernameAndIdNotIn(String username, List<Long> ids);
}
