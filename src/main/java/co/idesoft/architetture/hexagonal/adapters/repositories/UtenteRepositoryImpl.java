package co.idesoft.architetture.hexagonal.adapters.repositories;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Utente;
import co.idesoft.architetture.hexagonal.adapters.repositories.jpa.JpaUtenteRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.UtenteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UtenteRepositoryImpl implements UtenteRepository {

    private final JpaUtenteRepository jpaUtenteRepository;

    @Override
    public Long save(SalvareUtente payload) {
        return jpaUtenteRepository.save(Utente.from(payload)).getId();
    }
}
