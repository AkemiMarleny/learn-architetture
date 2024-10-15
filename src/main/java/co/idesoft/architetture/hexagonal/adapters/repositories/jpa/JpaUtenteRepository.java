package co.idesoft.architetture.hexagonal.adapters.repositories.jpa;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUtenteRepository extends JpaRepository<Utente, Long> {

    
}
