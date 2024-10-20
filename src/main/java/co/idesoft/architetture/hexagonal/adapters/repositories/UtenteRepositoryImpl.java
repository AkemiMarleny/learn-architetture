package co.idesoft.architetture.hexagonal.adapters.repositories;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Utente;
import co.idesoft.architetture.hexagonal.adapters.repositories.jpa.JpaUtenteRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.UtenteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvaNuovoUsernameUtente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareUtente;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UtenteRepositoryImpl implements UtenteRepository {

    private final JpaUtenteRepository jpaUtenteRepository;

    @Override
    public Long save(SalvareUtente payload) {
        return jpaUtenteRepository.save(Utente.from(payload)).getId();
    }

    @Override
    public void update(SalvaNuovoUsernameUtente payload) throws RecordNotFoundException {
        Utente utente = jpaUtenteRepository.findById(payload.utenteId()).orElseThrow(RecordNotFoundException::new);

        utente.aggiornaCon(payload);

        jpaUtenteRepository.save(utente);
    }

    @Override
    public Long countByUsername(String username) {
        return jpaUtenteRepository.countByUsername(username);
    }

    @Override
    public Long countByUsernameAndIdNotIn(String username, List<Long> utenteIds) {
        return jpaUtenteRepository.countByUsernameAndIdNotIn(username, utenteIds);
    }
}
